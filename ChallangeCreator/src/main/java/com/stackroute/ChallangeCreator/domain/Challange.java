package com.stackroute.ChallangeCreator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challange {

    @Id
    private int id ;

    @NotNull
    private String challengeTitle,challengeDesc, problemStat, progLang, solutionUrl;

    private String inputFormat, constraints, outputFormat;

    @NotNull
    private double maxScore, maxRuntime, level, rating;
}
