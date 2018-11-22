package com.stackroute.controller;

import com.stackroute.domain.Profile;
import com.stackroute.kafka.producer.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/results")
@CrossOrigin
public class ScoringController {

    @Autowired
    private UserResource userResource;

    @PostMapping("")
    public ResponseEntity<?> sendProfile(@RequestBody Profile profile){
        System.out.println("controller starts");
        userResource.putIntoTopic(profile);
        System.out.println("inside controller");
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }
}
