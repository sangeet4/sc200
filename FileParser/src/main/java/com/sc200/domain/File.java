package com.sc200.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
public class File {

    private String path;

    private String input;

    public File(String path, String input) {
        this.path = path;
        this.input = input;
    }
}
