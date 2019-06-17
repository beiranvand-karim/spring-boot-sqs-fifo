package com.sixthpoint.sqs.processor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sixthpoint")
public class ConfigProperties {

    private ObjectMapper objectMapper = new ObjectMapper();

    private String sqsURL = "https://sqs.us-east-1.amazonaws.com/588513328091/PendingOrders.fifo";

}
