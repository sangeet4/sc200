import { Injectable } from '@angular/core';
//import { FileService } from './folder-structure/directory/file.service';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { File } from './folder-structure/directory/model/file'

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
  url = "localhost:8020/file/create";


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

  SaveFile(file){
    
    // var file : File;
    // file.url = url;
    // file.content = content;
    
    //console.log(file.content);
    return this.http.post(this.url, file, httpOptions);

  }

  GetAllFiles(){
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
}
