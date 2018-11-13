package com.sc200.controller;


import com.sc200.domain.Directory;
import com.sc200.domain.Files;
import com.sc200.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
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
    public ResponseEntity<?> createDirectoryLayer(@RequestBody @Valid Files files) throws IOException {

        ResponseEntity responseEntity;

        try{
            String create = fileService.parseFile(files);
            responseEntity = new ResponseEntity<String>(create , HttpStatus.OK);
        }
        catch (Exception e){

            String create = fileService.parseFile(files);
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<?> getDirectoryStructure(@RequestBody @Valid String folderName) throws  IOException{

        ResponseEntity responseEntity;

        try{

            fileService.setPathsAndContent(new File(folderName));
            Directory directory = new Directory(fileService.getPaths(),fileService.getContents());
            responseEntity = new ResponseEntity<Directory>(directory , HttpStatus.OK);
        }
        catch (Exception e){

            fileService.setPathsAndContent(new File(folderName));
            Directory directory = new Directory(fileService.getPaths(),fileService.getContents());
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

        return  responseEntity;
    }
}
