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

  
}
