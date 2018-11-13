package com.sc200.controller;


import com.sc200.domain.File;
import com.sc200.service.CompileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/compile")
public class CompilerController {

    private CompileService compileService;

    @Autowired
    public CompilerController(CompileService compileService) {
        this.compileService = compileService;
    }

    @PostMapping()
    public ArrayList<String> createDirectoryLayer(@RequestBody @Valid File file) throws IOException {

        ArrayList<String> output = compileService.runFile(file);
        return output;
    }
}
