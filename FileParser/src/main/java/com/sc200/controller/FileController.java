package com.sc200.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc200.domain.File;
import com.sc200.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/create")
    public String createDirectoryLayer(@RequestBody @Valid File file) throws IOException {

        return fileService.parseFile(file);

    }

  
}
