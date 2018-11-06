package com.stackroute.ChallangeCreator.service;

import com.stackroute.ChallangeCreator.domain.Challange;
import com.stackroute.ChallangeCreator.exceptions.ChallangeNotFoundException;
import com.stackroute.ChallangeCreator.repository.ChallangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallangeServiceImpl implements ChallangeService {

    ChallangeRepository challangeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallangeServiceImpl.class);

    public ChallangeServiceImpl(ChallangeRepository challangeRepository) {
        this.challangeRepository = challangeRepository;
    }

    @Override
    public Challange addChallange(Challange challange) {
        Challange savedChallange = challangeRepository.save(challange);

        if(savedChallange == null){
            LOGGER.warn("Challange already exists exception ");
        }

        return savedChallange;
    }

    @Override
    public Optional<Challange> getChallangeById(int id) throws ChallangeNotFoundException {
        Optional<Challange> challange;
        challange=challangeRepository.findById(id);
        return challange;
    }

    @Override
    public List<Challange> getChallangeByTitle(String title) throws ChallangeNotFoundException {

        List<Challange> savedChallange = challangeRepository.getChallangeByTitle(title);
        if(savedChallange == null){
            LOGGER.warn("Challange not found exception - {}", title);
            throw new ChallangeNotFoundException(title);
        }
        return savedChallange;
    }

    @Override
    public List<Challange> getChallangeByRating(double lowerBound, double upperBound) throws ChallangeNotFoundException {

        List<Challange> challangesList = challangeRepository.getChallangeByRating(lowerBound,upperBound);
        if(challangesList == null){
            throw new ChallangeNotFoundException();
        }
        return challangesList;
    }

    @Override
    public Challange updateChallange(int id, Challange challange) throws ChallangeNotFoundException {

        if(!challangeRepository.existsById(id))
            throw new ChallangeNotFoundException(id);
        Optional<Challange> challange1= getChallangeById(id);
        if(challange1.isPresent())
        {
            Challange savedChallange = challangeRepository.save(challange1.get());
            return savedChallange;
        }

        return null;
    }

    @Override
    public List<Challange> getAllChallanges() {
        return (List<Challange>) challangeRepository.findAll();
    }

    @Override
    public List<Challange> deleteChallange(int id) throws ChallangeNotFoundException {
        if(!challangeRepository.existsById(id))
            throw new ChallangeNotFoundException(id);
        Optional<Challange> challange1=getChallangeById(id);
        if(challange1.isPresent()) {
            challangeRepository.delete(challange1.get());
        }
        return getAllChallanges();
    }
}
