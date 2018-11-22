package com.stackroute.kafka.producer;

import com.stackroute.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    @Autowired
    private KafkaTemplate<String, Profile> kafkaTemplate;
    private static final String TOPIC="scoreTOPIC";

    public void putIntoTopic(Profile profile)
    {
        System.out.println("inside topic: "+TOPIC);
        kafkaTemplate.send(TOPIC,profile);
        System.out.println("Sent!");
    }

}
