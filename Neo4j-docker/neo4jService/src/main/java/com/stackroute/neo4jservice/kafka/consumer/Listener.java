package com.stackroute.neo4jservice.kafka.consumer;

import com.stackroute.neo4jservice.domain.nodes.Challenge;
import com.stackroute.neo4jservice.domain.nodes.User;
import com.stackroute.neo4jservice.repository.noderepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @Autowired
    UserRepository userRepository;

    @KafkaListener(topics = "test5", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(@Payload User user) {

        user.setName(user.getFirstName()+user.getLastName());
        System.out.println("Consumed JSON Message: " + user);


        System.out.println("filtered data is "+user.getFirstName());
        User user1=new User();
        user1.setName(user.getFirstName()+user.getLastName());
        user1.setPreferredLang(user.getPreferredLang());
        user1.setId(user.getId());
        System.out.println(user1);
        User savedUser=userRepository.save(user1);
    }


        @KafkaListener(topics = "test5", containerFactory = "userKafkaListenerFactory")
        public void consumeJsonfromCreateChallenge(@Payload Challenge challenge) {

            System.out.println("create challenge json success");


    }
}
