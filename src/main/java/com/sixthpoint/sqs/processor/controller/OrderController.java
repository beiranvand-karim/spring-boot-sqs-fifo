package com.sixthpoint.sqs.processor.controller;

import com.sixthpoint.sqs.processor.service.OrderProcessingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderProcessingService orderCreationService;

    public OrderController(OrderProcessingService orderCreationService) {
        this.orderCreationService = orderCreationService;
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public void createOrder(@RequestParam int itemNumber){
        orderCreationService.createOrder(itemNumber);
    }
}