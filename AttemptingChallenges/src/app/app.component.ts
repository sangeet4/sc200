import { Component } from '@angular/core';
import { FileElement } from './folder-structure/directory/model/file-element';
import { FileService } from './folder-structure/directory/file.service';
import { Observable } from 'rxjs';
import { FilesService } from './files.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AttemptingChallenges';

  public fileElements: Observable<FileElement[]>;

  files: File[];
  
  currentRoot: FileElement;
  currentPath: string;
  canNavigateUp = false;

  constructor(public fileService: FileService, public filesService: FilesService) { }
  
 
  ngOnInit() {
    this.fileElements = this.filesService.allFiles;
    console.log(this.fileElements);
    
    // const folderA = this.fileService.add({ name: 'Folder A', isFolder: true, parent: 'root' ,url:null,content:null});
    // this.updateFileElementQuery();
    }
    private fileElement_array : FileElement[];
    

  addFile(file: { name: string }) {
    this.fileService.addFiles({ isFolder: false, name: file.name, parent: this.currentRoot ? this.currentRoot.id : 'root',url:null,content:null });
    this.updateFileElementQuery();
  }



  addFolder(folder: { name: string }) {
    this.fileService.add({ isFolder: true, name: folder.name, parent: this.currentRoot ? this.currentRoot.id : 'root',url:null,content:null });
    this.updateFileElementQuery();
  }

  // addFileElement(fileElement:{name:string,isFolder:boolean,parent:string,url:string,content:null,id:string}){
  //   this.fileService.add(fileElement);
  //   this.updateFileElementQuery();
  // }

  addFileElementArray(){
    this.fileElement_array = this.fileService.addUploadedFiles()
    var len = this.fileElement_array.length;
    for(let i=0;i<len;i++){
      this.fileService.add(this.fileElement_array[i]);
    }
    this.updateFileElementQuery();
  }


  removeElement(element: FileElement) {
    this.fileService.delete(element.id);
    this.updateFileElementQuery();
  }

  navigateToFolder(element: FileElement) {
    this.currentRoot = element;
    this.updateFileElementQuery();
    this.currentPath = this.pushToPath(this.currentPath, element.name);
    this.canNavigateUp = true;
  }

  navigateUp() {
    if (this.currentRoot && this.currentRoot.parent === 'root') {
      this.currentRoot = null;
      this.canNavigateUp = false;
      this.updateFileElementQuery();
    } else {
      this.currentRoot = this.fileService.get(this.currentRoot.parent);
      this.updateFileElementQuery();
    }
    this.currentPath = this.popFromPath(this.currentPath);
  }

  moveElement(event: { element: FileElement; moveTo: FileElement }) {
    this.fileService.update(event.element.id, { parent: event.moveTo.id });
    this.updateFileElementQuery();
  }

  renameElement(element: FileElement) {
    this.fileService.update(element.id, { name: element.name });
    this.updateFileElementQuery();
  }

  updateFileElementQuery() {
    this.fileElements = this.fileService.queryInFolder(this.currentRoot ? this.currentRoot.id : 'root');
  }

  pushToPath(path: string, folderName: string) {
    let p = path ? path : '';
    p += `${folderName}/`;
    return p;
  }

  popFromPath(path: string) {
    let p = path ? path : '';
    let split = p.split('/');
    split.splice(split.length - 2, 1);
    p = split.join('/');
    return p;
  }
}
