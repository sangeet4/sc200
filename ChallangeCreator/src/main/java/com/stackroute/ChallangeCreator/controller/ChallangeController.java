package com.stackroute.ChallangeCreator.controller;

import com.stackroute.ChallangeCreator.domain.Challange;
import com.stackroute.ChallangeCreator.exceptions.ChallangeNotFoundException;
import com.stackroute.ChallangeCreator.service.ChallangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/challangeAPI/v1")
@CrossOrigin
public class ChallangeController {

    private ChallangeService challangeService;
    private String exceptionMessage="";

    @Autowired
    public ChallangeController(ChallangeService challangeService) {
        this.challangeService = challangeService;
    }


    @PostMapping(value = "/challange")
    public ResponseEntity<?> saveChallange(@RequestBody @Valid Challange challange){

        Challange savedChallange = challangeService.addChallange(challange);
        ResponseEntity responseEntity = new ResponseEntity(savedChallange, HttpStatus.OK);

        return responseEntity;

    }

    @PutMapping(value = "/challange/{id}")
    public ResponseEntity<?> updateChallange(@PathVariable(value = "id") int challangeId ,@Valid @RequestBody Challange challange ){

        ResponseEntity responseEntity;
        try {
            Challange savedChallange = challangeService.updateChallange(challangeId,challange);
            responseEntity = new ResponseEntity<Challange>(savedChallange, HttpStatus.OK);
        }
        catch (ChallangeNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping(value = "/challange/{id}")
    public ResponseEntity<?> getChallangeById(@PathVariable(value = "id") int challangeId){

        ResponseEntity responseEntity;
        try {
            Challange savedChallange = challangeService.getChallangeById(challangeId).get();
            responseEntity = new ResponseEntity<Challange>(savedChallange,HttpStatus.OK);
        }
        catch (ChallangeNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping(value = "/challange/title/{title}")
    public ResponseEntity<?> getChallangeByTitle(@PathVariable(value = "title") String challangeTitle){

        ResponseEntity responseEntity;
        try {
            List<Challange> savedChallange = challangeService.getChallangeByTitle(challangeTitle);
            responseEntity = new ResponseEntity(savedChallange,HttpStatus.OK);
        }
        catch (ChallangeNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping(value = "/challange")
    public ResponseEntity<?> getAllChallanges(){

        ResponseEntity responseEntity;
        List<Challange> challangesList = challangeService.getAllChallanges();
        responseEntity = new ResponseEntity<List<Challange>>(challangesList,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping(value = "/challange/range")
    public ResponseEntity<?> getChallangesByRating(@RequestParam(value = "LB",required = true) double lowerBound, @RequestParam(value = "UB", required = false) double upperBound){
        ResponseEntity responseEntity;
        try {
            List<Challange> challangeList = challangeService.getChallangeByRating(lowerBound,upperBound);
            responseEntity = new ResponseEntity(challangeList,HttpStatus.OK);
        }
        catch (ChallangeNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @DeleteMapping(value = "/challange/{id}")
    public ResponseEntity<?> deleteChallange(@PathVariable(value = "id") int challangeId){

        ResponseEntity responseEntity;

        try {
            List<Challange> challangeList = challangeService.deleteChallange(challangeId);
            responseEntity = new ResponseEntity(challangeList,HttpStatus.OK);
        }
        catch (ChallangeNotFoundException ex){
            responseEntity = new ResponseEntity(exceptionMessage,HttpStatus.CONFLICT);
        }

        return responseEntity;
    }
}