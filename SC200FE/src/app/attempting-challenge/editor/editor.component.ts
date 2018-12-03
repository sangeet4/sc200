import { FileService } from "./../folder-structure/directory/file.service";
import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { MonacoFile } from "ngx-monaco";
import { ActivatedRoute } from "@angular/router";
import { FilesService } from "../files.service";
import { File } from "../folder-structure/directory/model/file";
import { Browser } from "protractor";

@Component({
  selector: "app-editor",
  templateUrl: "./editor.component.html",
  styleUrls: ["./editor.component.css"]
})
export class EditorComponent implements OnInit, OnChanges {
  @Input() fileName: string;
  count: number = 0;
  content = "hi";
  httpResponse;

  title = "app";
  options = {
    theme: "vs-dark",
    language: "java"
  };

  file: MonacoFile = {
    uri: "",
    language: "java",
    content: ""
  };

  constructor(
    private filesService: FilesService,
    private fileService: FileService
  ) {}

  ngOnInit() {
    console.log("ngOninit evoked --------------->");
    //this.file.content = "public class Hello";
    // this.changeContent();
    //this.file.content = this.content;
  }
  ngOnChanges() {
    console.log("ngOnChanges evoked---------------> ");
    this.changeContentOfEditor();
  }

  changeContentOfEditor() {
    this.filesService.GetContent(this.fileName).subscribe(data => {
      
      console.log("file uri is ", this.file.uri);
      this.file = {
        ...this.file,
        content: data["content"],
        uri: this.fileName
      }
      // this.file.content = data["content"];
      // this.file.uri = this.fileName;
    });
  }
  onFileChange(file: MonacoFile) {
    this.content = this.file.content;
    console.log(this.content);
    console.log(file.content);
  }

  public saveCode() {
    console.log(this.content);
    this.file.content = this.content;
    console.log(this.file.uri, this.file.content);
    // var a =this.file.uri;
    // var b = this.file.content;
    // var file : any[];
    this.filesService.SaveFile(this.file).subscribe();
  }
  public compileCode() {
    //console.log(this.file.uri , this.file.content);
    // var a =this.file.uri;
    // var b = this.file.content;
    // var file : any[];

    this.filesService.RunFile(this.file).subscribe(data => {
      this.httpResponse = data;
      console.log(this.httpResponse);
      console.log(data);
    });
  }
  public showResults() {
    console.log(this.httpResponse);
  }
}

// changeContent() {
//   if (this.count < 2) {
//     this.count++;
//     // this.file.content = "hidgfss";
//   } else {
//     this.count++;
//     this.file.content = this.content;
//     console.log(
//       "HI changeContent is being called ->>>>>>>>>>>>>>>>>>>>>>>>>>.",
//       this.file.content
//     );
//     this.filesService.getContentfromUrl(this.fileName).subscribe(data => {
//       this.file.uri = this.fileName;
//       console.log(this.file.uri);
//       // this.file.content="";
//       // this.content="";
//       // this.content = data['content'];
//       this.file.content = data["content"];
//       console.log(this.file.content);
//       this.content = this.file.content;
//     });
//   }
// }
