# Spring Boot + AWS SQS FIFO Quickstart

This application demonstrates how to quickly setup and configure a AWS SQS FIFO queue. It is based on a fictional marketplace which has a set number of items available to order. As requests are added to the sqs queue, the service processes the requests and decrements the total items left for purchases.

## Environment variables

The following environment variables can/should be set to run the application

`SIXTHPOINT_SQSURL = #YOUR_SQS_URL`

`CLOUD_AWS_STACK_CREDENTIALS_ACCESS_KEY = #ENTER_YOUR_KEY_HERE`

`CLOUD_AWS_STACK_CREDENTIALS_SECRET_KEY = #ENTER_YOUR_KEY_HERE`

`CLOUD_AWS_STACK_REGION_STATIC = #YOUR_REGION`