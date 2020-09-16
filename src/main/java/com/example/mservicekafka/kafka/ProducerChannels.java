package com.example.mservicekafka.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import static com.example.mservicekafka.kafka.Topics.DEMO;

public interface ProducerChannels {

    @Output(DEMO)
    MessageChannel test();
}
