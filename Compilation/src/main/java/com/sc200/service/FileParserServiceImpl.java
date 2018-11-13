package com.sc200.service;

import com.sc200.domain.File;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileParserServiceImpl implements FileParserService {


    @Override
    public String findRelativePath(File  file) throws IOException{

        String[] subStrings = file.path.split("/");
        String relativePath = "";
        for(int i=0 ; i<subStrings.length-2; i++)
        {
//            System.out.println(subStrings[i]);
            relativePath = relativePath  + subStrings[i] + "/";
        }

        relativePath = relativePath + subStrings[subStrings.length-2];

//        System.out.println(relativePath);
        return relativePath;

    }

    @Override
    public String findFileName(File  file) throws IOException{

        String[] subStrings = file.path.split("/");

        String fileNameWithExt = "";
        fileNameWithExt = subStrings[subStrings.length-1];
//        System.out.println(fileNameWithExt);
        String[] fileNameSubString = fileNameWithExt.split("\\." );
        String fileName = fileNameSubString[0];
//        System.out.println(fileName + " dbbjfhd");
        return fileName;

    }



}
