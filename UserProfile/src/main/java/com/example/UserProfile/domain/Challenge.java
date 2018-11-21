package com.example.UserProfile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder

public class Challenge {
   public String createrId;
   public String challengeId;
   public  String challengeTitle;
   public double challengeScore;


}
