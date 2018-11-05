package com.sc200.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    public String parseFile(com.sc200.domain.File file) throws IOException {
        int lastIndex = file.getPath().lastIndexOf('/');
        String directory = file.getPath().substring(0,lastIndex);

        if(createDirectories(directory) && createFile(file)) {
            return "Successfully Created";
        }
        else {
            return "Directory already exists";
        }


    }

    public boolean createDirectories(String path) {

        //create layers of directories

        File files = new File("Directory/Sub2/Sub-Sub2/");
        if(!files.exists()) {
            if(files.mkdirs()) {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean createFile(com.sc200.domain.File file) throws IOException {

        //Create the file
        File newFile = new File(file.getPath());
        if (newFile.createNewFile())
        {
            FileWriter writer = new FileWriter(newFile);
            writer.write(file.getInput());
            writer.close();
            return true;
        } else {
            return false;
        }

    }
}
