import { Injectable } from '@angular/core';
//import { FileService } from './folder-structure/directory/file.service';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { File } from './folder-structure/directory/model/file'
import { containsElement } from '@angular/animations/browser/src/render/shared';
import { MonacoFile } from 'ngx-monaco';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};
@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor(private http: HttpClient,) { }
 
  allFiles;
  textFiles;
  fileContent;
  files:[string];
  url = "http://172.23.239.117:8020/file/create";
  url1 = "http://172.23.239.117:8021/compile";
  newurl:string = "";
  httpresponse;


  StoreFiles(allFiles , textFiles , fileContent){
    this.allFiles = allFiles;
    this.textFiles = textFiles;
    this.fileContent = fileContent;
  }

  DisplayFiles(){
    console.log(this.textFiles);
    console.log(this.fileContent);
    console.log(this.allFiles);

  
      
  }

  SaveFile(file:MonacoFile){
    
    // var file : File;
    // file.url = url;
    // file.content = content;
    
    //console.log(file.content);
   // console.log(file);
   this.newurl= this.GetFilePath(file.uri);
   file.uri=this.newurl;
    //file.content=file.content.replace(/"/g, " \\\"");
    console.log(file);
     return this.http.post(this.url, file, httpOptions);

  }

  RunFile(file){
    
    // var file : File;
    // file.url = url;
    // file.content = content;
    
    //console.log(file.content);
   // console.log(file.uri);
      this.newurl= this.GetFilePath(file.uri);
    console.log(this.newurl);
     return this.http.post(this.url1,this.newurl, httpOptions);

  }
  showResponse(){
    return this.httpresponse;
  }

  GetAllFiles(){
    console.log(this.allFiles);
    return this.allFiles;
  }
 

  GetContent(fileName){

    console.log(fileName);
    var index;
    for(var i=0; i < this.textFiles.length; i++ )
    {
      var temp = this.textFiles[i];
      if(fileName == this.textFiles[i]){
          index = i;
    }

    return this.fileContent[index];

  }
}
GetFilePath(fileName){
   this.files = this.GetAllFiles();
  for(let i=0;i<this.files.length;i++){
    if(this.files[i].includes(fileName)){
      console.log(this.files[i]);
      return this.files[i];
    }
  }
}
}
