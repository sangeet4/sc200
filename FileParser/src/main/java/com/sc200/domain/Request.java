package com.sc200.domain;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder

public class Request {

    private String userId;

    private String challengeId;

    private ArrayList<String> textFile;

    private ArrayList<String> fileContent;

    public ArrayList<String> getFileContents() {
        return fileContents;
    }

    public ArrayList<String> getTextFile() {
        return textFile;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getUserId() {
        return userId;
    }
}
