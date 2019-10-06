package com.sixthpoint.sqs.processor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sixthpoint")
public class ConfigProperties {

    private ObjectMapper objectMapper = new ObjectMapper();

    private String sqsURL = "https://sqs.eu-west-1.amazonaws.com/145549860468/PendingOrders.fifo";

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getSqsURL() {
        return sqsURL;
    }

    public void setSqsURL(String sqsURL) {
        this.sqsURL = sqsURL;
    }
}
