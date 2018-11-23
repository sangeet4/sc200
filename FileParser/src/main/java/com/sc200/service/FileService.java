package com.sc200.service;

import com.sc200.domain.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface FileService {

    public String parseFile(Files file) throws IOException;

    public boolean createDirectories(String path);

    public boolean createFile(Files file) throws IOException;


    public ArrayList<String> getPaths();

    public ArrayList<String> getContents();

    public void setPathsAndContent(File dir);



<<<<<<< HEAD
=======
    public  String replaceLast(String string, String toReplace, String replacement);

    public String[] getTemplate(String path);
>>>>>>> b735ce70bf6a299feb46fcedea123055527c44c8
}
