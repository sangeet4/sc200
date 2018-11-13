package com.sc200.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
public class Directory {

    private String[] paths;

    private String[] contents;

    public Directory(String[] paths, String[] contents) {
        this.paths = paths;
        this.contents = contents;
    }

    public String[] getContents() {
        return contents;
    }

    public String[] getPaths() {
        return paths;
    }
}
