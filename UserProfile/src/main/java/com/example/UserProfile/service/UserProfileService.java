package com.example.UserProfile.service;

import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileAlreadyExitsException;
import com.example.UserProfile.exception.UserProfileNotFoundException;

import java.util.List;

public interface UserProfileService {


   // UserProfile updateUserProfileById(int id, UserProfile userprofile);

    List<UserProfile> getAllUserProfiles();
    UserProfile saveUserProfile(UserProfile userProfile) throws UserProfileAlreadyExitsException;
    UserProfile searchUserProfileById(String id) throws UserProfileNotFoundException;
    UserProfile updateuserProfileById(String id,UserProfile userProfile) throws UserProfileNotFoundException;

    boolean deleteUserProfilebyId(String id);
}
