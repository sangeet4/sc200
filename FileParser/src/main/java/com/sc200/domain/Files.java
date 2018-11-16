package com.sc200.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
public class Files {

    private String uri;

    private String content;

    private String language;

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
