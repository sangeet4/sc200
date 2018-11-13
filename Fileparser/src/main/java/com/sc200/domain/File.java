package com.sc200.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
public class File {

    private String uri;

    private String content;

    private String language;

    public File(String path, String language, String input) {
        this.uri = path;
        this.content = input;
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public String getLanguage() {
        return language;
    }

    public String getUri() {
        return uri;
    }
}
