package com.sixthpoint.sqs.processor.model;

import lombok.Data;

@Data
public class PendingOrder {

    private int itemCount;
    private long requestTime;

}
