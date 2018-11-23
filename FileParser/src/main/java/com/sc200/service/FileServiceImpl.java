package com.sc200.service;

import com.sun.tools.javac.util.List;
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

        if (createDirectories(directory) && createFile(files)) {
            return "Successfully Created";
        } else {

            recursiveDelete(new File(directory1));
            if (createDirectories(directory) && createFile(files)) {
                return "Successfully Created";
            } else {
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

//    public String createTree() {
//        String[] strings = {"a/b/d/c/HelloWorld.java" , "c/HelloWorld.java" , "a/d/e/HelloWorld.java" };
//
//        Directory directory = new Directory( "root" , new ArrayList<>());
//
//        Directory parent = directory;
//        Directory oldParent = directory;
//        ArrayList<Directory> allCurrent = parent.value;
//        //        Directory current = allCurrent.get(0);
//        Directory toBeAdded;
//        String parentString = new String();
//        String directoryString = new String();
//        String[] subStrings1 = strings[0].split("/");
//        toBeAdded = PathToTree(subStrings1);
//        directory.value.add(toBeAdded);
////        System.out.println(directory.toString());
//        for(int j = 1; j<strings.length;j++)
//        {
//
//            parent = directory;
//            String[] subStrings = strings[j].split("/");
////            System.out.println(strings[j]);
//            toBeAdded = PathToTree(subStrings);
//            boolean a = true;
//            while(a){
////                System.out.println("11");
//                for(int k=0;k<allCurrent.size();k++)
//                {
////                    System.out.println(k);
////                    System.out.println("first"+allCurrent.get(k).key+"in end");
////                    System.out.println("second"+toBeAdded.key+"in end");
//                    if(allCurrent.get(k).key.equals(toBeAdded.key))
//                    {
////                        System.out.println("hello");
//                        oldParent = parent;
//                        parent = allCurrent.get(k);
//                        allCurrent = parent.value;
////                        System.out.println(parent.key);
////                        System.out.println(toBeAdded.toString() + "ssjn");
//                        toBeAdded = toBeAdded.value.get(0);
////                        System.out.println(toBeAdded.toString());
//                        //   directory.value.add(parent);
//                        break;
//                    }
//                    else
//                    {
//
////                        System.out.println("third:"+toBeAdded.toString() + "in else");
//                        parent.value.add(toBeAdded);
////                        System.out.println("Directory inside loop : " + directory.value.get(1));
////                        System.out.println("Parent inside loop : " + parent.value.get(1));
//                        a=false;
////                        System.out.println(directory.toString() + "1111");
//
////                        System.out.println(parent.toString() );
//
//                        break;
//
//                    }
//
//                }
////                System.out.println("loop ended");
//
////                System.out.println(directory.toString() + "3");
////                if(parent == directory)
////                {
////                    parent.value.add(toBeAdded);
////                }
//            }
//            parentString = parent.toString();
//            directoryString = directory.toString();
////            System.out.println("Parent : " + parent.toString());
////            System.out.println("Directory : " + directory.toString());
////            directory.value.add(parent);
////            System.out.println("");
//            directoryString.replace(oldParent.toString() , parentString);
//
//        }
//
//
////        Gson gson = new Gson();
////        String json = gson.toJson(directory);
////        System.out.println("ended");
//        //
//
//
////        System.out.println(directoryString );
////        Gson gson = new Gson();
////        String json = gson.toJson(directory);
////        System.out.println(json);
//
//        directoryString = "{'" + directoryString;
//        directoryString = directoryString.replaceAll("\\[", "':{'");
//        directoryString = directoryString.replaceAll("]" , "'}'");
//        directoryString = directoryString.replaceAll(" :" , "' : '");
//        directoryString = directoryString.replaceAll("''" , "");
//        directoryString = directoryString.replaceAll(", " , ", '");
//        directoryString = directoryString.replaceAll("}}'" , "}}");
//        directoryString = directoryString.replaceAll("' null'" , "null");
//        directoryString = directoryString.substring(0,directoryString.length()-2);
//
//        int leftbraces = 0;
//        int rightbraces = 0;
//
//        for(int l=0;l<directoryString.length();l++)
//        {
//            if(directoryString.charAt(l)=='{')
//            {
//                leftbraces++;
//            }
//            else if(directoryString.charAt(l)=='}')
//            {
//                rightbraces++;
//            }
//        }
//
//        for(int l=0;l<leftbraces-rightbraces;l++){
//            directoryString = directoryString + "}";
//        }
//
//        System.out.println(directoryString);
//
//
//
//        return directoryString;
//    }
//

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

//        for(int i=0;i<path.length;i++){
//        System.out.println(path[i]);}




        for(int i=0; i<listFile.length; i++) {

            if(path[i] !="null") {

                this.paths.add(path[i]);

            }
        }






        for(int i=0; i<listFile.length; i++) {

            if(content[i]!=null){
            this.contents.add(content[i]);}
        }

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



//    public Directory PathToTree(String[] subString) {
//
//
//        this.i = subString.length;
//        Directory prevNode = new Directory(subString[subString.length-1] , null);
//
//        for (int i = subString.length - 2; i >= 0; i--)
//        {
////            System.out.println(subString[i]);
//            Directory node = new Directory(subString[i], new ArrayList<>());
//            node.value.add(prevNode);
//            prevNode = node;
//        }
//
//        return prevNode;
//    }



//    public static class Directory {
//
//        String key;
//        ArrayList<Directory> value;
//
//        public Directory(String key , ArrayList<Directory> value)
//        {
//            this.key = key;
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            if(this.value==null)
//            {
//                return this.key + " : "  + this.value;
//            }
//            String a = "";
//            a = a + this.key + this.value.toString() ;
//            return a;
//        }
//
//    }
//
//    public  String replaceLast(String string, String toReplace, String replacement) {
//        int pos = string.lastIndexOf(toReplace);
//        if (pos > -1) {
//            return string.substring(0, pos)
//                    + replacement
//                    + string.substring(pos + toReplace.length(), string.length());
//        } else {
//            return string;
//        }
//    }

    @Override
    public String[] getTemplate(String path) {

        System.out.println(path);
//        File folder = new File(path);
//        File[] listOfFiles = folder.listFiles();
//        System.out.println(listOfFiles.length);
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }

        System.out.println("hello1");

        File directory = new File("/home/anshul/SC200/sc200/FileParser/" + path);

        //get all the files from a directory

        File[] fList = directory.listFiles();
        System.out.println(fList.length);

        for (File file : fList){

            System.out.println(file.getName());

        }

        System.out.println("hello2");
        return null;
    }

}

