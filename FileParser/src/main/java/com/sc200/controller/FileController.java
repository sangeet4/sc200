package com.sc200.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc200.domain.File;
import com.sc200.service.FileService;
import org.json.JSONException;
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

    @GetMapping(value = "/structure")
    public String getDirectoryStrucuture() throws JSONException {
       ObjectMapper mapperObj = new ObjectMapper();
        try {
            String jsonStr = mapperObj.writeValueAsString(fileService.createTree());
            jsonStr = jsonStr.replaceAll("'","\"");
            jsonStr = jsonStr.replaceFirst("\"","'");
            jsonStr = fileService.replaceLast(jsonStr,"\"","'");
            return jsonStr;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }

    }
}