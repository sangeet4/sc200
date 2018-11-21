package com.example.UserProfile.kafka.consumer;


import com.example.UserProfile.domain.Challenge;
import com.example.UserProfile.domain.UserId;
import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileNotFoundException;
import com.example.UserProfile.repository.UserProfileRepository;
import com.example.UserProfile.service.UserProfileService;
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

@Autowired
public UserProfileService userProfileService;


    @KafkaListener(topics = "test5", groupId = "group_id6", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(@Payload UserProfile user) {
        System.out.println("Consumed JSON Message: " + user);
        System.out.println("filtered data is "+user.getFirstName());
    }

    //listen to create challenge topic and update the database.

    @KafkaListener(topics = "test-challenge", groupId = "group_id7", containerFactory = "challengeKafkaListenerFactory")
    public void consumeJsonFromChallengeService(@Payload Challenge challenge) {

        System.out.println("Consumed JSON Message of challenge: " + challenge);
       System.out.println("filtered data is "+challenge.getChallengeId());

       try {

           UserProfile userProfile = userProfileService.searchUserProfileById(challenge.getUserId());
           userProfileService.updateCreateChallengeToProfileById(challenge.getUserId(),challenge);

       }
       catch (UserProfileNotFoundException ex){
           ex.printStackTrace();
       }
  }
  @KafkaListener(topics = "userProfile", groupId = "group_id8", containerFactory = "registrationKafkaListenerFactory")
    public void consumeJsonfromRegService(@Payload UserProfile userProfile) {

        System.out.println("Consumed JSON Message of UserProfile from RegService: " + userProfile);
      userProfileRepository.save(userProfile);


    }

    //need to add kafka listener for scoring service

    @KafkaListener(topics = "scoringTopic",groupId = "group_id9",containerFactory ="scoringKafkaListenerFactory")
    public void consumeJsonFromScoringService(@Payload Challenge challenge) {

        System.out.println("Consumed JSON Message of challenge: " + challenge);
        System.out.println("filtered data is "+challenge.getChallengeId());

        try{
            UserProfile userProfile = userProfileService.searchUserProfileById(challenge.getUserId());
            userProfileService.updateAttemptChallengeToProfileById(challenge.getUserId(),challenge);
        }
        catch (UserProfileNotFoundException ex){
            ex.printStackTrace();
        }


    }

    // add the kafka topic for both upvoted and downvoted challenges

    @KafkaListener(topics="votingTopic",groupId = "group_id10",containerFactory = "votingKafkaListenerFactory")
    public void consumeJsonFromVotingService(@Payload Challenge challenge){
        try{
            UserProfile userProfile = userProfileService.searchUserProfileById(challenge.getUserId());
            userProfileService.updateUpvoteChallengeToProfileById(challenge.getUserId(),challenge);
        }
        catch (UserProfileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @KafkaListener(topics="votingTopicUpvote",groupId = "group_id11",containerFactory = "votingKafkaListenerFactory")
    public void consumeJsonFromDownvoteVotingService(@Payload Challenge challenge){
        try{
            UserProfile userProfile = userProfileService.searchUserProfileById(challenge.getUserId());
            userProfileService.updateDownvoteChallengeToProfileById(challenge.getUserId(),challenge);
        }
        catch (UserProfileNotFoundException ex){
            ex.printStackTrace();
        }
    }

}