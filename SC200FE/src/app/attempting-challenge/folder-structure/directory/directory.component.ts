import { HttpClient } from '@angular/common/http';
import { Component, Input, Output, EventEmitter, Injectable } from '@angular/core';
import { FileElement } from './model/file-element';
import { MatMenuTrigger } from '@angular/material/menu';
import { MatDialog } from '@angular/material/dialog';
import { NewFolderDialogComponent } from './modals/new-folder-dialog/new-folder-dialog.component';
import { RenameDialogComponent } from './modals/rename-dialog/rename-dialog.component';
import { NewFileDialogComponent } from './modals/new-file-dialog/new-file-dialog.component';
import { Router } from '@angular/router';
import { FileService } from './file.service';
import {SelectionModel} from '@angular/cdk/collections';
import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlattener, MatTreeFlatDataSource} from '@angular/material/tree';
import {of as ofObservable, Observable, BehaviorSubject} from 'rxjs';



export class TodoItemNode {
  children: TodoItemNode[];
  item: string;
}

export class TodoItemFlatNode {
  item: string;
  level: number;
  expandable: boolean;
}


export class ChecklistDatabase {
  dataChange: BehaviorSubject<TodoItemNode[]> = new BehaviorSubject<TodoItemNode[]>([]);
  //public url = "http://localhost:8080/file/structure";
  public TREE: string;
  

  get data(): TodoItemNode[] { return this.dataChange.value; }

  constructor(private http: HttpClient, private fileService: FileService) {
    // this.initialize();
  }
initialize() {
  // Build the tree nodes from Json object. The result is a list of `TodoItemNode` with nested
  //     file node as children.
    
        this.TREE = this.fileService.getFileStructure().toString();
        console.log(this.TREE);
        this.TREE = this.TREE.replace(/'/g,'\"');
        console.log(this.TREE);
        this.TREE = JSON.parse(this.TREE);
        //this.TREE = {'root':{'test':{'ArrayDifference.js' : null, 'ArrayDifference2.js' : null}}, 'test':{'test2':{'ArrayDifference.js' : null}}};
      
        const values = this.buildFileTree(this.TREE, 0);
        // Notify the change.
        this.dataChange.next(values);
       // pipe(map(response => response.toString()))
  
  }

/**
 * Build the file structure tree. The `value` is the Json object, or a sub-tree of a Json object.
 * The return value is the list of `TodoItemNode`.
 */
buildFileTree(value: any, level: number) {
  const data: any[] = [];
  for (const k in value) {
    const v = value[k];
    const node = new TodoItemNode();
    node.item = `${k}`;
    if (v === null || v === undefined) {
      // no action
    } else if (typeof v === 'object') {
      node.children = this.buildFileTree(v, level + 1);
    } else {
      node.item = v;
    }
    data.push(node);
  }
  return data;
}

/** Add an item to to-do list */
insertItem(parent: TodoItemNode, name: string) {
  const child = <TodoItemNode>{item: name};
  if (parent.children) {
    parent.children.push(child);
    this.dataChange.next(this.data);
  }
}

updateItem(node: TodoItemNode, name: string) {
  node.item = name;
  this.dataChange.next(this.data);
}





@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.css']
})
export class DirectoryComponent {

  constructor(public dialog: MatDialog, private router: Router,private fileService:FileService ) { 
    // , private cd : ChangeDetectorRef
    // this.cd.detectChanges();
  }

  @Input() fileElements: FileElement[];
  @Input() canNavigateUp: string;
  @Input() path: string;

  @Output() folderAdded = new EventEmitter<{ name: string }>();
  @Output() elementRemoved = new EventEmitter<FileElement>();
  @Output() elementRenamed = new EventEmitter<FileElement>();
  @Output() elementMoved = new EventEmitter<{ element: FileElement; moveTo: FileElement }>();
  @Output() navigatedDown = new EventEmitter<FileElement>();
  @Output() navigatedUp = new EventEmitter();
  @Output() fileAdded = new EventEmitter<{ name: string }>();

  deleteElement(element: FileElement) {
    this.elementRemoved.emit(element);
  }


  navigate(element: FileElement) {
    // console.log("again clicked ");

    if (element.isFolder) {
      this.navigatedDown.emit(element);
    }
    else {
      // console.log("routes changes ");
      // send the file content to the editor or in simple what you have to do 
      // is send the file name from directory component to the editor component
       
      var names = element.name.split(".");
      this.router.navigate(["attempt"+"/" + names[1] + "/" + names[0]]);

    }

  }

  navigateUp() {
    this.router.navigate(["/attempt"]);
    this.navigatedUp.emit();
  }

  moveElement(element: FileElement, moveTo: FileElement) {
    this.elementMoved.emit({ element: element, moveTo: moveTo });
  }

  // openNewFolderDialog() {
  //   let dialogRef = this.dialog.open(NewFolderDialogComponent);
  //   dialogRef.afterClosed().subscribe(res => {
  //     console.log(res);
  //     if (res) {
  //       this.folderAdded.emit({ name: res });
  //     }
  //   });
  // }
  // openNewFileDialog() {
  //   let dialogRef1 = this.dialog.open(NewFileDialogComponent);
  //   dialogRef1.afterClosed().subscribe(res => {
  //     // console.log(res);
  //     if (res) {
  //       this.fileAdded.emit({ name: res });
  //       // console.log("files added");
  //     }
  //   });
  // }



  // openRenameDialog(element: FileElement) {
  //   let dialogRef = this.dialog.open(RenameDialogComponent);
  //   dialogRef.afterClosed().subscribe(res => {
  //     if (res) {
  //       element.name = res;
  //       this.elementRenamed.emit(element);
  //     }
  //   });
  // }

  openMenu(event: MouseEvent, viewChild: MatMenuTrigger) {
    event.preventDefault();
    viewChild.openMenu();
  }

}
