package com.stackroute.ChallangeCreator.service;

import com.stackroute.ChallangeCreator.domain.Challange;
import com.stackroute.ChallangeCreator.exceptions.ChallangeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ChallangeService {

    public Challange addChallange(Challange challange);
    public Optional<Challange> getChallangeById(int id) throws ChallangeNotFoundException;
    public List<Challange> getChallangeByTitle(String title) throws ChallangeNotFoundException;
    public List<Challange> getChallangeByRating(double lowerBound,double upperBound) throws ChallangeNotFoundException;
    public Challange updateChallange(int id,Challange challange) throws ChallangeNotFoundException;
    public List<Challange> getAllChallanges();
    public List<Challange> deleteChallange(int id) throws ChallangeNotFoundException;
}
