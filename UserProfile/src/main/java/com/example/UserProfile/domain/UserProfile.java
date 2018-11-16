package com.example.UserProfile.domain;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.UserProfile.domain.Challenge;

import java.util.Arrays;

@Document(collection="userProfile1")

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder

public class UserProfile {








    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String dateOfBirth;
    private String contactNumber;
    private int score;
    private int ranking;
    private String preferredLang;
    private Challenge challengeAttempted[];
    private Challenge challengeCreated[];
    private Challenge challengeUpvoted[];
    private Challenge challengeDownvoted[];



}
