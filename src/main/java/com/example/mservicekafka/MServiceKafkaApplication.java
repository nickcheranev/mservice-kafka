package com.example.mservicekafka;

import com.example.mservicekafka.kafka.ConsumerChannels;
import com.example.mservicekafka.kafka.ProducerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ConsumerChannels.class, ProducerChannels.class})
public class MServiceKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MServiceKafkaApplication.class, args);
    }
}
