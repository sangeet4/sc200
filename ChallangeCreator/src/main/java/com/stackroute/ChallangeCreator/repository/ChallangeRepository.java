package com.stackroute.ChallangeCreator.repository;

import com.stackroute.ChallangeCreator.domain.Challange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallangeRepository extends MongoRepository<Challange,Integer> {
    @Query("{ 'challangeTitle' : ?0 }")
    public List<Challange> getChallangeByTitle(String title);

    @Query("{ 'rating' : { $gt: ?0, $lt: ?1 } }")
    public List<Challange> getChallangeByRating(double ratingGT,double ratingLT);
}
