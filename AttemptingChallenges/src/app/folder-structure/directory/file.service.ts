import { Injectable } from '@angular/core';
import { FileElement } from '../directory/model/file-element';
import { Observable } from 'rxjs';
import { v4 } from 'uuid';
import { BehaviorSubject } from 'rxjs';
import { FilesService } from 'src/app/files.service';



export interface IFileService {
  add(fileElement: FileElement);
  delete(id: string);
  update(id: string, update: Partial<FileElement>);
  queryInFolder(folderId: string): Observable<FileElement[]>;
  get(id: string): FileElement;
}
@Injectable()
export class FileService implements IFileService {

  private map = new Map<string, FileElement>();

  constructor(private filesService:FilesService) { }

  add(fileElement: FileElement) {
    fileElement.id = v4();
    this.map.set(fileElement.id, this.clone(fileElement));
    return fileElement;
    console.log(fileElement);
  }
  addFiles(fileElem: FileElement) {
    fileElem.id = v4();
    this.map.set(fileElem.id, this.clone(fileElem));
    return fileElem;
  }
  //take input in uploaded files
  addUploadedFiles(){
    var fileelem:FileElement;
    var fileElements_array:FileElement[]=[];
    var temp=["Pictures/Screenshot from 2018-10-05 14-13-32.png", "Pictures/Screenshot from 2018-10-05 20-31-48.png", "Pictures/Screenshot from 2018-10-31 11-00-57.png", "Pictures/Screenshot from 2018-10-15 12-24-40.png", "Pictures/Screenshot from 2018-09-27 09-36-05.png", "Pictures/Screenshot from 2018-09-08 18-10-45.png", "Pictures/Screenshot from 2018-10-15 18-12-37.png", "Pictures/Screenshot from 2018-09-14 14-49-36.png", "Pictures/Screenshot from 2018-10-31 18-40-44.png", "Pictures/Screenshot from 2018-10-17 12-32-50.png", "Pictures/Screenshot from 2018-11-02 17-32-46.png", "Pictures/Screenshot from 2018-09-27 12-59-26.png", "Pictures/Screenshot from 2018-09-08 18-10-53.png", "Pictures/Screenshot from 2018-10-16 19-32-32.png", "Pictures/Screenshot from 2018-10-14 00-43-14.png", "Pictures/Screenshot from 2018-09-27 09-27-13.png", "Pictures/Screenshot from 2018-10-14 00-42-42.png", "Pictures/Screenshot from 2018-10-18 01-45-38.png", "Pictures/Screenshot from 2018-09-08 18-10-25.png", "Pictures/Screenshot from 2018-10-18 01-46-05.png"];
    var len = temp.length;
    for(let i = 0 ; i<len;i++){
      var splitted=temp[i].split('/');

      var len_temp = splitted.length;
      var split_string:Array<string>=[];

      for(let j=0;j<len_temp;j++){
        splitted[j]=splitted[j].toString()
      }
      
//storing the splitted strings into an array and checkign whether it is a file or folder
      for(let j=0;j<len_temp;j++){
         split_string.push(splitted[j]);
      }

      for(let j=0;j<len_temp;j++){
        if(split_string[j].indexOf('.')>0){

        var fileelem:FileElement=
          {name:split_string[j],
          id:v4(),
          content:null,
          isFolder:false,
          url:temp[i],
          parent:split_string[j-1]
        }
          fileElements_array.push(fileelem);
        }
        else{
          //if it is first file then its parent is root else the previous folder
          if(j==0){
            var fileelem:FileElement =
            {name:split_string[j],
            id:v4(),
            content:null,
            parent:'root',
            isFolder:true,
            url:null
            
          }
          
          }
          else{
          var fileelem:FileElement =
            {name:split_string[j],
            id:v4(),
            content:null,
            parent:split_string[j-1],
            isFolder:true,
            url:null
          }
        }
          fileElements_array.push(fileelem);
        }
      }
    }
    console.log(fileElements_array);
    
  }

  delete(id: string) {
    this.map.delete(id);
  }

  update(id: string, update: Partial<FileElement>) {
    let element = this.map.get(id);
    element = Object.assign(element, update);
    this.map.set(element.id, element);
  }

  private querySubject: BehaviorSubject<FileElement[]>;
  queryInFolder(folderId: string) {
    const result: FileElement[] = [];
    this.map.forEach(element => {
      if (element.parent === folderId) {
        result.push(this.clone(element));
      }
    });
    if (!this.querySubject) {
      this.querySubject = new BehaviorSubject(result);
    } else {
      this.querySubject.next(result);
    }
    return this.querySubject.asObservable();
  }

  get(id: string) {
    return this.map.get(id);
  }

  clone(element: FileElement) {
    return JSON.parse(JSON.stringify(element));
  }
}