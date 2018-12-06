package com.stackroute.challengecreator.repository;

import com.stackroute.challengecreator.domain.Challenge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ChallengeRepositoryTest {

    @Autowired
    private ChallengeRepository challengeRepository;
    private Challenge challenge;
    private Challenge challenge1;
    private Challenge challenge2;


    @Before
    public void setUp() throws Exception {

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

        challenge1 = new Challenge();
        challenge1.setChallengeId("2");
        challenge1.setLevel(2);
        challenge1.setMaxRuntime(8);
        challenge1.setMaxScore(100);
        challenge1.setInputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge1.setConstraints("1 <= T <= 20 1 <= T <= 200 1 <= T <= 2000 1 <= T <= 299990 ");
        challenge1.setChallengeTitle("MovieCruer1");
        challenge1.setBoilerPlateUrl("string");
        challenge1.setChallengeStamp("Your task is to find the city number in which Dory will find Nemo.");
        challenge1.setChallengeStatement("First, she gets the mark from a city, then uses it to mark the next city she visits, gets next mark from next city ");
        challenge1.setOutputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge1.setUserId("b@c.com");
        challenge1.setProgrammingLang("java");
        challenge1.setTopic("Array");
        challenge1.setSolutionUrl("string");
        challenge1.setDownvotes(0);
        challenge1.setUpvotes(0);
        challenge1.setRating(0);

        challenge2 = new Challenge();
        challenge2.setChallengeId("3");
        challenge2.setLevel(1);
        challenge2.setMaxRuntime(8);
        challenge2.setMaxScore(100);
        challenge2.setInputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge2.setConstraints("1 <= T <= 20 1 <= T <= 200 1 <= T <= 2000 1 <= T <= 299990 ");
        challenge2.setChallengeTitle("MovieCruer2");
        challenge2.setBoilerPlateUrl("string");
        challenge2.setChallengeStamp("Your task is to find the city number in which Dory will find Nemo.");
        challenge2.setChallengeStatement("First, she gets the mark from a city, then uses it to mark the next city she visits, gets next mark from next city ");
        challenge2.setOutputFormat("First input line contains number of test cases T Your task is to find the city number in which Dory will find Nemo.Your task is to find the city number in which Dory will find Nemo.");
        challenge2.setUserId("c@d.com");
        challenge2.setProgrammingLang("java");
        challenge2.setTopic("Map");
        challenge2.setSolutionUrl("string");
        challenge2.setDownvotes(0);
        challenge2.setUpvotes(0);
        challenge2.setRating(0);

    }

    @Test
    public void testSaveChallenge(){
        challengeRepository.save(challenge);
        Challenge fetchChallenges = challengeRepository.findById(challenge.getChallengeId()).get();
        Assert.assertEquals("1",fetchChallenges.getChallengeId());
        challengeRepository.deleteAll();
    }


    @Test
    public void testGetallChallenges(){
        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        List<Challenge> challengesList = challengeRepository.findAll();
        Assert.assertEquals("b@c.com",challengesList.get(0).getUserId());
        challengeRepository.deleteAll();

    }

    @Test
    public void testUpdateChallengesbyId(){

        challengeRepository.save(challenge);
        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        Challenge challengeEdited = challengeRepository.findById("1").get();
        challengeEdited.setUserId("new@b.com");
        challengeRepository.save(challengeEdited);
        List<Challenge> challengesList = challengeRepository.findAll();
        Assert.assertEquals("new@b.com",challengesList.get(0).getUserId());
        challengeRepository.deleteAll();
    }

    @Test
    public void testDeleteChallenges(){

        challengeRepository.save(challenge);
        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        challengeRepository.deleteById("2");
        List<Challenge> challengeList = challengeRepository.findAll();
        Assert.assertEquals("3",challengeList.get(1).getChallengeId());
        challengeRepository.deleteAll();
    }
}