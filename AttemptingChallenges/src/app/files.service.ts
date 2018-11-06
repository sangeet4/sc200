import { Injectable } from '@angular/core';
import { FileService } from './folder-structure/directory/file.service';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor() { }
 
  allFiles;
  textFiles;
  fileContent;

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


  GetAllFiles(){
    return this.allFiles;
  }

}
