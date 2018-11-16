package com.example.UserProfile.kafka.consumer;


import com.example.UserProfile.domain.Challenge;
import com.example.UserProfile.domain.UserId;
import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileNotFoundException;
import com.example.UserProfile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

@Autowired
   public UserProfileRepository userProfileRepository;


    @KafkaListener(topics = "test5", groupId = "listenfromself", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(@Payload UserProfile user) {
        System.out.println("Consumed JSON Message: " + user);
        System.out.println("filtered data is "+user.getFirstName());
    }
    @KafkaListener(topics = "test-challenge", groupId = "listenchallenge", containerFactory = "challengeKafkaListenerFactory")
    public void consumeJsonFromChallengeService(@Payload Challenge challenge) {

        System.out.println("Consumed JSON Message of challenge: " + challenge);
       System.out.println("filtered data is "+challenge.getChallengeName());
  }
  @KafkaListener(topics = "userProfile", groupId = "listenregistration", containerFactory = "registrationKafkaListenerFactory")
    public void consumeJsonfromRegService(@Payload UserProfile userProfile) {

        System.out.println("Consumed JSON Message of UserProfile from RegService: " + userProfile);
        System.out.println("filtered data is "+userProfile.getFirstName());
      userProfileRepository.save(userProfile);


    }


}