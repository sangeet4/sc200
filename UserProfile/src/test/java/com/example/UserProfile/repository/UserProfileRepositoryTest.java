package com.example.UserProfile.repository;

import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileAlreadyExitsException;
import com.example.UserProfile.exception.UserProfileNotFoundException;

import com.example.UserProfile.repository.*;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
//we will use db server
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserProfileRepositoryTest {





    @Autowired
    UserProfileRepository userProfileRepository;
    UserProfile user;

    @Before
    public void setUp(){
        user = new UserProfile();
        user.setChallengeCreated(null);
        user.setEmail("1@gmail.com");
        user.setChallengeUpvoted(null);
        user.setChallengeAttempted(null);
        user.setChallengeDownvoted(null);
        user.setDateOfBirth("20-07-1996");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPhone( 9087654321L );
        user.setPreferredLang("java");
        user.setRanking(7);
        user.setUsername("user1");
        user.setScore(9.53);
    }

    @Test
    public void testSaveUser(){
        userProfileRepository.save(user);
        UserProfile fetchUserProfile = userProfileRepository.findById(user.getEmail()).get();
        Assert.assertEquals("user1",fetchUserProfile.getUsername());
    }

    @Test
    public void testSaveUserFailure(){
        userProfileRepository.save(user);
        UserProfile fetchUserProfile = userProfileRepository.findById(user.getEmail()).get();
        Assert.assertEquals(user,fetchUserProfile);
    }

    @Test
    public void testGetallUserProfile(){
        UserProfile user1 = new UserProfile("1@gmail.com","first","last","user1","20-07-1996",9087654321L,12.3,7,"java",null,null,null,null);
        UserProfile user2 = new UserProfile("2@gmail.com","second","last","user2","20-07-1997",9087654301L,12.9,4,"java",null,null,null,null);
        userProfileRepository.save(user1);
        userProfileRepository.save(user2);
        List<UserProfile> fetchUserProfileList = userProfileRepository.findAll();
        Assert.assertEquals("user2",fetchUserProfileList.get(0).getUsername());
    }

    @Test
    public void testUpdateUserProfilebyId(){
        UserProfile user1 = new UserProfile("1@gmail.com","first","last","user1","20-07-1996",9087654321L,12.3,7,"java",null,null,null,null);
        UserProfile user2 = new UserProfile("2@gmail.com","second","last","user2","20-07-1997",9087654301L,12.9,4,"java",null,null,null,null);

        userProfileRepository.save(user1);
        userProfileRepository.save(user2);
        UserProfile temp = userProfileRepository.findById("1@gmail.com").get();
       // System.out.println(temp);
        temp.setFirstName("newFirst");
        //System.out.println(temp);
        userProfileRepository.save(temp);
        List<UserProfile> fetchUserProfileList = userProfileRepository.findAll();
        for(int i=0;i<fetchUserProfileList.size();i++){
            System.out.println(fetchUserProfileList.get(i));
        }


        Assert.assertEquals("newFirst",fetchUserProfileList.get(1).getFirstName());
    }

    @Test
    public void testDeleteUserProfile(){
        UserProfile user1 = new UserProfile("1@gmail.com","first","last","user1","20-07-1996",9087654321L,12.3,7,"java",null,null,null,null);
        UserProfile user2 = new UserProfile("2@gmail.com","second","last","user2","20-07-1997",9087654301L,12.9,4,"java",null,null,null,null);
        userProfileRepository.save(user1);
        userProfileRepository.save(user2);
        userProfileRepository.deleteById("1@gmail.com");
        List<UserProfile> fetchUserProfileList = userProfileRepository.findAll();
        Assert.assertEquals("user2",fetchUserProfileList.get(0).getUsername());

    }
}
