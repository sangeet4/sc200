package com.stackroute.ChallangeCreator.exceptions;

public class ChallangeNotFoundException extends Exception {

    public ChallangeNotFoundException(String title) {
        super(title+" Challange not found");
    }
    public ChallangeNotFoundException(int id) {
        super(id+" Challange not found");
    }
    public ChallangeNotFoundException() {
        super(" Challanges not found in between");
    }
}
