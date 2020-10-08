package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
@Controller
@ComponentScan
public class DemoApplicationCallbackSincrono implements CommandLineRunner {


    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;


    private static final Logger log = LoggerFactory.getLogger(DemoApplicationCallbackSincrono.class);

    @KafkaListener(topics = "test", groupId = "group-Test-spring")
    public void listenTo(String message) {
        log.info("message receive {}", message);

    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationCallbackSincrono.class, args);

    }


    //Method  callback async , intend to know what happened before.
    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("test","1").get();

    }


}
