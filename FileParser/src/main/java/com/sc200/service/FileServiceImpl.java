package com.sc200.service;

import com.sc200.domain.Files;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.io.FileInputStream;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {

    private ArrayList<String> paths = new ArrayList<String>();

    private ArrayList<String> contents = new ArrayList<String>();


    public String parseFile(Files files) throws IOException {
        int lastIndex = files.getUri().lastIndexOf("/");
        String directory = files.getUri().substring(0,lastIndex);
        int firstIndex = files.getUri().indexOf("/");
        String directory1 = files.getUri().substring(0,firstIndex);

        if(createDirectories(directory) && createFile(files)) {
            return "Successfully Created";
        }
        else {

            recursiveDelete(new File(directory1));
            if(createDirectories(directory) && createFile(files)) {
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

    public boolean createFile(Files files) throws IOException {

        //Create the files
        File newFile = new File(files.getUri());
        if (newFile.createNewFile())
        {
            FileWriter writer = new FileWriter(newFile);
            writer.write(files.getContent());
            writer.close();
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<String> getPaths() {
        return this.paths;
    }

    public ArrayList<String> getContents() {
        return this.contents;
    }

    @Override
    public void setPathsAndContent(File dir) {

        this.contents = new ArrayList<String>();
        this.paths = new ArrayList<String>();
        String pattern = ".java"; //for example ".java"
        String pattern1 = ".yml";
        String pattern2 = ".properties";
        String pattern3 = ".css";
        String pattern4 = ".html";
        String pattern5 = ".js";
        String pattern6 = ".ts";
        String pattern7 = ".json";

        File listFile[] = dir.listFiles();
        String path[] = new String[listFile.length];
        String content[] = new String[listFile.length];
        if(listFile != null) {
            for(int i=0; i<listFile.length; i++) {
                if(listFile[i].isDirectory()) {
                    setPathsAndContent(listFile[i]);
                } else {
                    if(listFile[i].getName().endsWith(pattern) || listFile[i].getName().endsWith(pattern1) || listFile[i].getName().endsWith(pattern2) || listFile[i].getName().endsWith(pattern3) || listFile[i].getName().endsWith(pattern4) || listFile[i].getName().endsWith(pattern5) || listFile[i].getName().endsWith(pattern6) || listFile[i].getName().endsWith(pattern7) )
                    {
                        try{
                            content[i] = readFile(listFile[i].getPath());
                        }
                        catch (Exception e)
                        {
                            System.out.println("bshfjbdj");
                        }
                        path[i] = listFile[i].getPath();
                    }
                }
            }

        }

        for(int i=0; i<listFile.length; i++) {
            System.out.println(path[i]);
            this.paths.add(path[i]);
        }

        for(int i=0; i<listFile.length; i++) {
            this.contents.add(content[i]);
        }
    }


    public static String readFile(String Path) {
        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(Path)));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            if(stringBuilder.length()>0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            reader.close();
            String content = stringBuilder.toString();
            return content;

        } catch (Exception e) {
            System.out.println("\n\n\n\n" + e.getMessage() +  "\n\n\n\n" );
            return "Error";
        }
    }

}









