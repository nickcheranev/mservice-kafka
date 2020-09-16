package com.example.mservicekafka.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import static com.example.mservicekafka.kafka.Topics.DEMO;

public interface ConsumerChannels {

    @Input(DEMO)
    SubscribableChannel demo();
}