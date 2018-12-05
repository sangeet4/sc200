package com.sc200.controller;


import com.sc200.domain.File;
import com.sc200.service.CompileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/compile")
public class CompilerController {

    private CompileService compileService;

    @Autowired
    public CompilerController(CompileService compileService) {
        this.compileService = compileService;
    }

    @PostMapping()
    public ResponseEntity<?> createDirectoryLayer(@RequestBody @Valid String path) throws IOException {
        ResponseEntity responseEntity;
        path = path.replaceAll("\"" , "");
        File file = new File(path);
        try{
            ArrayList<String> output = compileService.runFile(file);
            responseEntity = new ResponseEntity<ArrayList<String>>(output , HttpStatus.OK);
        }
        catch (Exception e){
            ArrayList<String> output = compileService.runFile(file);
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    //adding the git cloning

    @PostMapping("/clone")
    public ResponseEntity<?> createClonedDirectory(@RequestBody @Valid String url) throws IOException{
        ResponseEntity responseEntity;
        try{
            compileService.clone(url);
            responseEntity = new ResponseEntity<String>("created",HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
