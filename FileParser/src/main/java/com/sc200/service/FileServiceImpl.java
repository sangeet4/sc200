package com.sc200.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

import com.sc200.domain.Files;

@Service
public class FileServiceImpl implements FileService {

    private ArrayList<String> paths = new ArrayList<String>();
    private ArrayList<String> contents = new ArrayList<String>();
    private int i;

    public String parseFile(Files files) throws IOException {
        int lastIndex = files.getUri().lastIndexOf("/");
        String directory = files.getUri().substring(0, lastIndex);
        int firstIndex = files.getUri().indexOf("/");
        String directory1 = files.getUri().substring(0, firstIndex);

        if (createFile(files)) {
            return "Successfully Created";
        } else {
            recursiveDelete(new File(files.getUri()));
            if (createFile(files)) {
                return "Successfully Created";
            } else {
                return "Some Error";
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

    public boolean createFile(Files file) throws IOException {

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

//

    public ArrayList<String> getPaths() {
        return this.paths;
    }

    public ArrayList<String> getContents() {
        return this.contents;
    }

    @Override
    public void setPathsAndContent(File dir) {


        String pattern = ".java"; //for example ".java"
        String pattern1 = ".yml";
        String pattern2 = ".properties";
        String pattern3 = ".css";
        String pattern4 = ".html";
        String pattern5 = ".js";
        String pattern6 = ".ts";
        String pattern7 = ".json";
        String pattern8 = ".md";
        String pattern9 = ".txt";

        System.out.println(dir);


        File listFile[] = dir.listFiles();
        System.out.println(listFile[0]);
        System.out.println("check");


        String path[] = new String[listFile.length];
        String content[] = new String[listFile.length];
        if(listFile != null) {
            for(int i=0; i<listFile.length; i++) {
                System.out.println(listFile[i]);
                if (listFile[i] != null) {
                    if (listFile[i].isDirectory()) {
                       // path[i]=listFile[i].getPath();
                        setPathsAndContent(listFile[i]);
                    } else {
                        if (listFile[i].getName().endsWith(pattern) || listFile[i].getName().endsWith(pattern1) || listFile[i].getName().endsWith(pattern2) || listFile[i].getName().endsWith(pattern3) || listFile[i].getName().endsWith(pattern4) || listFile[i].getName().endsWith(pattern5) || listFile[i].getName().endsWith(pattern6) || listFile[i].getName().endsWith(pattern7) || listFile[i].getName().endsWith(pattern8) || listFile[i].getName().endsWith(pattern9)) {
                            try {
                                content[i] = readFile(listFile[i].getPath());
                            } catch (Exception e) {
                                System.out.println("bshfjbdj");
                            }
                            path[i] = listFile[i].getPath();
                        }
                    }
                }
            }

        }
        for(int i=0; i<listFile.length; i++) {

            if(path[i] !="null") {

                paths.add(path[i]);

            }
        }
        for(int i=0; i<listFile.length; i++) {

            if(content[i]!=null){
            contents.add(content[i]);}
        }


    }
    @Override
    public  String customFileReader(String path) throws IOException {
        File f = new File(path);
        String everything = "";
        System.out.println(f + " hello ");
        System.out.println(f.isDirectory());
        BufferedReader br = null;
        if (f.isFile()) {
            try {
                br = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                everything = sb.toString();
                System.out.println(everything);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                br.close();
            }
        }
        return everything;
    }



    public static String readFile(String Path) {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Path)));
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

