package com.sc200.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@Builder
public class Directory {

    private ArrayList<String> paths;

    private ArrayList<String> contents;

    public Directory(ArrayList<String> paths, ArrayList<String> contents) {
        this.paths = paths;
        this.contents = contents;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

}
