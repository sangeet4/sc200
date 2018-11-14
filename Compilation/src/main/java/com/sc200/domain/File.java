package com.sc200.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
public class File {

    public String path;

//    private String input;
//
    public File(String path) {
        this.path = path;
//        this.input = input;
    }

}
