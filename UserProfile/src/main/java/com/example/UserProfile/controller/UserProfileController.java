package com.example.UserProfile.controller;

import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileAlreadyExitsException;
import com.example.UserProfile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping(value = "/sc200/userProfile")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;

    }

    @PostMapping("posting")
    public ResponseEntity<?> saveUserProfile(@Valid @RequestBody UserProfile userProfile){
        ResponseEntity responseEntity;
              try {
                  userProfileService.saveUserProfile(userProfile);
                  responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
              }
              catch(UserProfileAlreadyExitsException e){
                  responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
              }

        return responseEntity;
    }

    @GetMapping(value = "gettingall")
    public ResponseEntity<?> getAllUserProfiles() {
        return new ResponseEntity<List<UserProfile>>(userProfileService.getAllUserProfiles(), HttpStatus.OK);
    }

    @GetMapping(path = "getting/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") String id) {
       ResponseEntity responseEntity;
      try{
          // Movie movie= movieService.searchMovieById(id);
           UserProfile userProfile=userProfileService.searchUserProfileById(id);
          responseEntity= new ResponseEntity<String>(userProfile.toString(),HttpStatus.FOUND);
        }catch (Exception e){
           responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
      }
       return responseEntity;
        //return new ResponseEntity<UserProfile>(userProfileService.searchUserProfileById(id), HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        ResponseEntity responseEntity;

           if (userProfileService.deleteUserProfilebyId(id)==true) {
               responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);

           }
           else{
               responseEntity= new ResponseEntity<String>("UserProfile does not exist",HttpStatus.NOT_FOUND);
           }
        return responseEntity;
    }

    @PutMapping("updating/{id}")
    public ResponseEntity<?> updateuserProfileById(@Valid@RequestBody UserProfile userProfile ,@PathVariable("id") String id){
        ResponseEntity responseEntity;
       try {
            userProfileService.updateuserProfileById(id,userProfile);
            responseEntity = new ResponseEntity<String>("Successfully Updated",HttpStatus.CREATED);
       }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
       }
        return responseEntity;
    }
}