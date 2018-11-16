package com.sc200.service;

import com.sc200.domain.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface FileService {


    public String parseFile(Files files) throws IOException;

    public boolean createDirectories(String path);

    public boolean createFile(Files files) throws IOException;

    public ArrayList<String> getPaths();

    public ArrayList<String> getContents();

    public void setPathsAndContent(File dir);

}
