package com.sc200.controller;


import com.sc200.domain.File;
import com.sc200.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createDirectoryLayer(@RequestBody @Valid File file) throws IOException {

        ResponseEntity responseEntity;

        try{
            String create = fileService.parseFile(file);
            responseEntity = new ResponseEntity<String>(create , HttpStatus.OK);
        }
        catch (Exception e){

            String create = fileService.parseFile(file);
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.OK);
        }
        return responseEntity;
    }
}
