package com.sc200.service;

import com.sc200.domain.Files;

import java.io.File;
import java.io.IOException;

public interface FileService {


    public String parseFile(Files files) throws IOException;

    public boolean createDirectories(String path);

    public boolean createFile(Files files) throws IOException;

    public String[] getPaths();

    public String[] getContents();

    public void setPathsAndContent(File dir);

}
