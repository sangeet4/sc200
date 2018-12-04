import { FileService } from "./../folder-structure/directory/file.service";
import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { MonacoFile } from "ngx-monaco";
import { ActivatedRoute } from "@angular/router";
import { FilesService } from "../files.service";
import { File } from "../folder-structure/directory/model/file";
import { Browser } from "protractor";
import * as Stomp from "stompjs";
import * as SockJS from "sockjs-client";
import { environment } from "./../../../environments/environment";


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
  stompClient  = null;
  sessionId: String;
  //socketUrl = environment.apiUrl + "compile";
  socketUrl = "http://localhost:8183/socket"

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

  initializeWebSocketConnection() {
    const ws = new SockJS(this.socketUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    //connect to service using stompclinet
    this.stompClient.connect({}, function(frame) {
      that.sessionId = /\/([^\/]+)\/websocket/.exec(ws._transport.url)[1];
      that.stompClient.subscribe("/results/" + that.sessionId, (message) => {
        if ( message.body ) {
          console.log(message.body);
          that.httpResponse = message.body;
        }
      });
      that.stompClient.subscribe("/chat/errors/" + that.sessionId,(message) => {
        if (message.body) {
          console.log(message.body);
        }
      });
    });
  }

  changeContentOfEditor() {
    console.log("file uri is ", this.file.uri);
    this.file = {
      ...this.file,
      content: this.filesService.GetContent(this.fileName),
      uri: this.fileName
    };
    // this.file.content = data["content"];
    // this.file.uri = this.fileName;
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
    //send the data to the compilation service using sockets
    // that.stompClient.send("/app/send/message"+that.sessionId,{},that.filesService.GetFilePath(this.file.uri));
    this.stompClient.send("/app/send/message"+this.sessionId,{},"a/challengecreator");

    
    // this.filesService.RunFile(this.file).subscribe(data => {
    //   this.httpResponse = data;
    //   console.log(this.httpResponse);
    //   console.log(data);
    // });
  }
  public showResults() {
    console.log(this.httpResponse);
  }
}


