package com.example.mservicekafka.kafka;

import com.example.mservicekafka.domain.Message;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface GreetingsGateway {

    @Gateway(requestChannel = Topics.DEMO)
    void directGreet(Message msg);
}
