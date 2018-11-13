package com.sc200.service;

import com.sc200.domain.File;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
public class CompileServiceImpl implements CompileService {


    FileParserService fileParserService;

    public CompileServiceImpl(FileParserService fileParserService){this.fileParserService = fileParserService;}


    @Override
    public ArrayList<String> runFile(File file) throws IOException {

        ArrayList<String> lines = new ArrayList<String>();
        int i=0;

        try{
            System.out.println("start excution......");
            String relativePath =  this.fileParserService.findRelativePath(file);
            String fileName =  this.fileParserService.findFileName(file);
            System.out.println(relativePath);
            System.out.println(fileName);
            String[] command = {"/bin/bash", "/home/anshul/SC200/Compilation/src/main/resources/shellScript.sh", "/home/anshul/SC200/Fileparser/" + relativePath , fileName};
//            System.out.println(command);
            ProcessBuilder p = new ProcessBuilder(command);
            Process p2 = p.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {

                System.out.println(line);
                lines.add(line);
                i++;
                
            }
            System.out.println("ending excution......");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lines;
    }
}
