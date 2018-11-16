package com.sc200.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
public class File {

    private String path;

    private String input;

  
}