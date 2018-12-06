package com.stackroute.challengecreator.service;

import com.stackroute.challengecreator.domain.*;
import com.stackroute.challengecreator.exceptions.ChallengeAlreadyExistsException;
import com.stackroute.challengecreator.repository.ChallengeRepository;
import com.stackroute.challengecreator.repository.ChallengeRepositoryL1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChallengeServiceImplTest {

    Challenge challenge;
    ChallengeObjL1 challengeObjL1;

    @Mock
    ChallengeRepository challengeRepository;

    @Mock
    ChallengeRepositoryL1 challengeRepositoryL1;

    @InjectMocks
    ChallengeServiceImpl challengeService;
    List<Challenge> list = null;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        challenge = new Challenge();
        challenge.setChallengeId("1");
        challenge.setLevel(1);
        challenge.setMaxRuntime(8);
        challenge.setMaxScore(100);
        challenge.setInputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge.setConstraints("1 <= T <= 20 1 <= T <= 200 1 <= T <= 2000 1 <= T <= 299990 ");
        challenge.setChallengeTitle("MovieCruer");
        challenge.setBoilerPlateUrl("string");
        challenge.setChallengeStamp("Your task is to find the city number in which Dory will find Nemo.");
        challenge.setChallengeStatement("First, she gets the mark from a city, then uses it to mark the next city she visits, gets next mark from next city ");
        challenge.setOutputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge.setUserId("a@b.com");
        challenge.setProgrammingLang("java");
        challenge.setTopic("Array");
        challenge.setSolutionUrl("string");
        challenge.setDownvotes(0);
        challenge.setUpvotes(0);
        challenge.setRating(0);

        challengeObjL1 = new ChallengeObjL1();
        ChallengeObjL2 challengeObjL2 = new ChallengeObjL2();
        ChallengeObjL3 challengeObjL3 = new ChallengeObjL3();
        ChallengeObjL4 challengeObjL4 = new ChallengeObjL4();

        challengeObjL4.setChallengeId(challenge.getChallengeId());
        challengeObjL4.setUserId(challenge.getUserId());
        challengeObjL4.setChallengeTitle(challenge.getChallengeTitle());
        challengeObjL4.setChallengeStamp(challenge.getChallengeStamp());
        challengeObjL4.setChallengeStatement(challenge.getChallengeStatement());
        challengeObjL4.setInputFormat(challenge.getInputFormat());
        challengeObjL4.setOutputFormat(challenge.getOutputFormat());
        challengeObjL4.setBoilerPlateUrl(challenge.getBoilerPlateUrl());
        challengeObjL4.setSolutionUrl(challenge.getSolutionUrl());
        challengeObjL4.setMaxRuntime(challenge.getMaxRuntime());
        challengeObjL4.setMaxScore(challenge.getMaxScore());
        challengeObjL4.setRating(challenge.getRating());
        challengeObjL4.setProgrammingLang(challenge.getProgrammingLang());
        challengeObjL4.setTopic(challenge.getTopic());
        challengeObjL4.setLevel(challenge.getLevel());
        challengeObjL4.setConstraints(challenge.getConstraints());
        challengeObjL4.setUpvotes(challenge.getUpvotes());
        challengeObjL4.setDownvotes(challenge.getDownvotes());

        Map<String,ChallengeObjL4> tempL4 = new HashMap<>();
        tempL4.put(challenge.getChallengeId(),challengeObjL4);
        ArrayList<String> tempChallengeTitleList = new ArrayList<>();
        tempChallengeTitleList.add(challenge.getChallengeTitle());

        challengeObjL3.setLevel(challenge.getLevel());
        challengeObjL3.setChallengeTitleList(tempChallengeTitleList);
        challengeObjL3.setChallengesList(tempL4);


        Map<Double,ChallengeObjL3> tempL3 = new HashMap<>() ;
        tempL3.put(challenge.getLevel(),challengeObjL3);
        ArrayList<Double> tempLevelsList = new ArrayList<>();
        tempLevelsList.add(challenge.getLevel());

        challengeObjL2.setTopic(challenge.getTopic());
        challengeObjL2.setLevels(tempL3);
        challengeObjL2.setLevelsList(tempLevelsList);


        Map<String,ChallengeObjL2> tempL2 = new HashMap<>();
        tempL2.put(challenge.getTopic(),challengeObjL2);
        ArrayList<String> tempTopicsList = new ArrayList<>();
        tempTopicsList.add(challenge.getTopic());

        challengeObjL1.setProgrammingLang(challenge.getProgrammingLang());
        challengeObjL1.setTopics(tempL2);
        challengeObjL1.setTopicsList(tempTopicsList);
    }

    @Test
    public void addChallengeTest() throws ChallengeAlreadyExistsException {

        when(challengeRepositoryL1.save((ChallengeObjL1) any())).thenReturn(challengeObjL1);
        ChallengeObjL1 savedChallenge = challengeService.addChallengeObjL1(challenge);
        Assert.assertEquals(challengeObjL1, savedChallenge);
        verify(challengeRepositoryL1, times(1)).save(challengeObjL1);

    }


    @Test
    public void getAllChallengesBasicTest() {

        challengeRepository.save(challenge);
        when(challengeRepository.findAll()).thenReturn(list);
        List<Challenge> userList = challengeService.getAllChallengesBasic();
        Assert.assertEquals(list, userList);
        verify(challengeRepository, times(1)).findAll();

    }

}