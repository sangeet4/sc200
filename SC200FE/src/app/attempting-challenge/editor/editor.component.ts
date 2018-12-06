import { environment } from 'src/environments/environment';
import { FileService } from "./../folder-structure/directory/file.service";
import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { MonacoFile } from "ngx-monaco";
import { FilesService } from "../files.service";
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

@Component({
  selector: "app-editor",
  templateUrl: "./editor.component.html",
  styleUrls: ["./editor.component.css"]
})
export class EditorComponent implements OnInit, OnChanges {
  @Input() fileName: string;
  @Input() userName: string;
  @Input() challengeId: string;
  count: number = 0;
  content = "hi";
  httpResponse;
  stompClient = null;
  sessionId: String;
  socketUrl = environment.apiUrl+"compile/compile";

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
  ) {
    this.initializeWebSocketConnection();
  }

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
    const ws = new SockJS(this.socketUrl,{
      transport:["wss","https"]
    });
    this.stompClient = Stomp.over(ws);
    const that = this;
    //connect to service using stompclinet
    this.stompClient.connect(
      {},
      function(frame) {
        console.log("Socket Connection Established");
        that.sessionId = /\/([^\/]+)\/websocket/.exec(ws._transport.url)[1];
        that.stompClient.subscribe("/results/" + that.sessionId, message => {
          if (message.body) {
            that.httpResponse = JSON.parse(message.body).body;
            console.log(that.httpResponse);
          }
        });
        that.stompClient.subscribe(
          "/chat/errors/" + that.sessionId,
          message => {
            if (message.body) {
              console.log(message.body);
            }
          }
        );
      }
    );
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
    this.filesService.setContent(this.file.uri, this.file.content);
    this.filesService.SaveFile(this.userName, this.challengeId).subscribe();
  }
  public compileCode() {
    //console.log(this.file.uri , this.file.content);
    // var a =this.file.uri;
    // var b = this.file.content;
    // var file : any[];
    this.saveCode();
    this.stompClient.send(
      "/app/send/message/" + this.sessionId,
      {},
      this.userName + "/" + this.challengeId
    );
    // this.filesService.RunFile(this.userName, this.challengeId).subscribe(data => {
    //   this.httpResponse = data;
    //   console.log(this.httpResponse);
    //   console.log(data);
    // });
  }
  public showResults() {
    console.log(this.httpResponse);
  }
}
