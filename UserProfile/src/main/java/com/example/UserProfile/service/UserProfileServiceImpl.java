package com.example.UserProfile.service;

import com.example.UserProfile.domain.Challenge;
import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileAlreadyExitsException;
import com.example.UserProfile.exception.UserProfileNotFoundException;
import com.example.UserProfile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class UserProfileServiceImpl implements UserProfileService {




    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }


    @Override
    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> topFive = new ArrayList<UserProfile>();
        List<UserProfile> allElement=new ArrayList<UserProfile>();
       allElement=userProfileRepository.findAll();

        return allElement;
    }
    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) throws UserProfileAlreadyExitsException {
        if(userProfileRepository.existsById(userProfile.getEmail())){
           // logger.info("exception ocuured");
            throw new UserProfileAlreadyExitsException("userProfile-service.userExist");
        }


        UserProfile saveuserProfile= userProfileRepository.save(userProfile);

        return saveuserProfile;
    }
    @Override
    public UserProfile searchUserProfileById(String id) throws UserProfileNotFoundException {

        if(userProfileRepository.existsById(id)) {
            UserProfile userProfile = userProfileRepository.findById(id).get();
            return userProfile;
        }
        else{
            throw new UserProfileNotFoundException("userProfile-controller.noUser");
        }
    }
    @Override
      public boolean deleteUserProfilebyId(String id) {

       if (userProfileRepository.existsById(id)) {

           userProfileRepository.deleteById(id);
           return true;
       }
       else{
           return false;
       }
    }

    @Override
    public UserProfile  updateuserProfileById(String id,UserProfile userProfile1) throws UserProfileNotFoundException{

        if(userProfileRepository.existsById(id)) {
            UserProfile userProfile = userProfileRepository.findById(id).get();





            userProfile.setFirstName(userProfile1.getFirstName());
            userProfile.setLastName(userProfile1.getLastName());
            userProfile.setPhone(userProfile1.getPhone());
            userProfile.setUsername(userProfile1.getUsername());
            userProfile.setRanking(userProfile1.getRanking());
            userProfile.setScore(userProfile1.getScore());
            userProfile.setDateOfBirth(userProfile1.getDateOfBirth());
            userProfile.setPreferredLang(userProfile1.getPreferredLang());

            //need to get the list already present and then add the new one

            List<Challenge> attemptedChallenge = userProfile.getChallengeAttempted();
            attemptedChallenge.add(userProfile1.getChallengeAttempted().get(0));
            userProfile.setChallengeAttempted(attemptedChallenge);

            List<Challenge> createdChallenge = userProfile.getChallengeCreated();
            createdChallenge.add(userProfile1.getChallengeCreated().get(0));
            userProfile.setChallengeCreated(createdChallenge);



            List<Challenge> upvotedChallenge = userProfile.getChallengeUpvoted();
            upvotedChallenge.add(userProfile1.getChallengeUpvoted().get(0));
            userProfile.setChallengeUpvoted(upvotedChallenge);


            List<Challenge> downvotedChallenge = userProfile.getChallengeDownvoted();
            upvotedChallenge.add(userProfile1.getChallengeDownvoted().get(0));
            userProfile.setChallengeUpvoted(downvotedChallenge);





            UserProfile userProfile2 = userProfileRepository.save(userProfile);
            return userProfile2;
        }
        else{
            throw new UserProfileNotFoundException("userProfile-controller.noUser");
        }
    }



}
