package com.sc200.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    public String parseFile(com.sc200.domain.File file) throws IOException {
        int lastIndex = file.getUri().lastIndexOf("/");
        String directory = file.getUri().substring(0,lastIndex);
        int firstIndex = file.getUri().indexOf("/");
        String directory1 = file.getUri().substring(0,firstIndex);

        if(createDirectories(directory) && createFile(file)) {
            return "Successfully Created";
        }
        else {

            recursiveDelete(new File(directory1));
            if(createDirectories(directory) && createFile(file)) {
                return "Successfully Created";
            }
            else {
                return "Directory already exists";
            }
        }


    }


    public static void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists())
            return;

        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }


    public boolean createDirectories(String path) {

        //create layers of directories

        File files = new File(path);
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
        File newFile = new File(file.getUri());
        if (newFile.createNewFile())
        {
            FileWriter writer = new FileWriter(newFile);
            writer.write(file.getContent());
            writer.close();
            return true;
        } else {
            return false;
        }

    }
}
