package com.sc200.service;

import com.sc200.domain.Files;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.io.FileInputStream;

@Service
public class FileServiceImpl implements FileService {

    private String[] paths;

    private String[] contents;


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

    public String[] getPaths() {
        return paths;
    }

    public String[] getContents() {
        return contents;
    }

    @Override
    public void setPathsAndContent(File dir) {

        //System.out.println(dir);
        File listFile[] = dir.listFiles();
        String path[] = new String[listFile.length];
        String content[] = new String[listFile.length];
        if(listFile != null) {
            for(int i=0; i<listFile.length; i++) {
                if(listFile[i].isDirectory()) {
                    setPathsAndContent(listFile[i]);
                } else {
                       try{
                            content[i] = readFile(listFile[i].getPath());
//                            System.out.println(listFile[i].getEncoding());
                        }
                        catch (Exception e)
                        {
                            System.out.println("bshfjbdj");
                        }
                        path[i] = listFile[i].getPath();

                }
            }
        }

        for(int i=0; i<listFile.length; i++) {
            System.out.println(path[i]);
//            this.paths[i] = path[i];
        }
        this.paths =path;

        for(int i=0; i<listFile.length; i++) {
            System.out.println(content[i]);
//            this.contents[i] = content[i];
        }

        this.contents = content;
    }


    public static String readFile(String Path)
    {
        if(Path.contains(".java") || Path.contains(".ts") || Path.contains(".html") || Path.contains(".css") || Path.contains(".json"))
        {
            try {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(Path), "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                String ls = System.getProperty("line.separator");
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                reader.close();
                String content = stringBuilder.toString();
                return content;

            }
            catch (Exception e)
            {
                System.out.println("fbhd");
                return "fcbdj";
            }
        }
        else
        {
            return "";
        }
    }

}









