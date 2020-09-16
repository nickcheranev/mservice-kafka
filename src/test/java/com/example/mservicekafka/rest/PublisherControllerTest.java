package com.example.mservicekafka.rest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.example.mservicekafka.util.MemoryAppender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Cheranev N.
 * created on 16.09.2020.
 */
@AutoConfigureMockMvc
@SpringBootTest
class PublisherControllerTest {

    private static final String LOGGER_NAME = "ROOT";
    private MemoryAppender memoryAppender;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @Test
    public void sendMessageToKafkaTest() throws Exception {
        mvc.perform(get("/hello?name=World"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));

        Assertions.assertTrue(memoryAppender.containsStartAndEndWith(
                "Received message: Message(id=",
                "message=Hello, World!)", Level.INFO));

    }
}