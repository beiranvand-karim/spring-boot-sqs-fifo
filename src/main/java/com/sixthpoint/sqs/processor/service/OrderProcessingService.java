package com.sixthpoint.sqs.processor.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixthpoint.sqs.processor.config.ConfigProperties;
import com.sixthpoint.sqs.processor.model.PendingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class OrderProcessingService {

    private static final Logger log = LoggerFactory.getLogger(OrderProcessingService.class);

    private int availableItems = 5;
    private final ConfigProperties configProperties;

    public OrderProcessingService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public void createOrder(int itemCount) {
        try {
            PendingOrder pendingOrder = new PendingOrder();
            pendingOrder.setItemCount(itemCount);
            pendingOrder.setRequestTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            String pendingOrderJson = configProperties.getObjectMapper().writeValueAsString(pendingOrder);

            final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            sqs.sendMessage(new SendMessageRequest(configProperties.getSqsURL(), pendingOrderJson).withMessageGroupId("exampleGroupId1"));

        } catch (final AmazonClientException | JsonProcessingException ase) {
            log.error("Error Message: " + ase.getMessage());
        }
    }

    @SqsListener("PendingOrders.fifo")
    public void listen(String json) throws IOException {
        PendingOrder pendingOrder = configProperties.getObjectMapper().readValue(json, PendingOrder.class);
        if(availableItems > 0 && availableItems >= pendingOrder.getItemCount()) {
            availableItems = availableItems - pendingOrder.getItemCount();
            log.info("Items purchased, now have {} items remaining", availableItems);
        } else {
            log.error("No more items are available");
        }
    }
}
