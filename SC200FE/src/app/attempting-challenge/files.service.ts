import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { File } from "./folder-structure/directory/model/file";
import { containsElement } from "@angular/animations/browser/src/render/shared";
import { MonacoFile } from "ngx-monaco";
import { FileElement } from "./folder-structure/directory/model/file-element";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    Authorization: "my-auth-token"
  })
};
@Injectable({
  providedIn: "root"
})
export class FilesService {
  constructor(private http: HttpClient) {}

  allFiles;
  textFiles: String[] = [];
  fileContent;
  testData = "";
  sendContent = "";
  files: [string];
  // url = "http://35.154.116.88:8182/";
  url = environment.apiUrl;
  url1 = environment.apiUrl + "compile";
  newurl: string = "";
  httpresponse;

  StoreFiles(allFiles, textFiles, fileContent) {
    this.allFiles = allFiles;
    this.textFiles = textFiles;
    this.fileContent = fileContent;
  }

  // DisplayFiles(){
  //   console.log(this.textFiles);
  //   console.log(this.fileContent);
  //   console.log(this.allFiles);

  // }

  SaveFile(file: MonacoFile) {
    this.newurl = this.GetFilePath(file.uri);
    file.uri = this.newurl;

    console.log(file);
    return this.http.post(this.url + "file/create", file, httpOptions);
  }

  RunFile(file) {
    this.newurl = this.GetFilePath(file.uri);
    console.log(this.newurl);
    return this.http.post(this.url1, "a/challengecreator", httpOptions);

    // return this.http.post(this.url1, this.newurl, httpOptions);
  }
  getTemplate() {
    console.log("into the get template func");
    return this.http.post(this.url + "file/struct", "challenges/", httpOptions);
  }
  getRepsoitory(url: string) {
    return this.http.post(this.url1 + "/clone", url);
  }

  setPaths(data) {
    this.allFiles = data;
    this.setTextFiles();
  }

  setTextFiles() {
    for (var i = 0; i < this.allFiles.length; i++) {
      if (this.allFiles[i] != null) {
        var index = this.allFiles[i].lastIndexOf("/");
        this.textFiles[i] = this.allFiles[i].substring(
          index + 1,
          this.allFiles[i].length
        );
      }
    }
  }

  getContentfromUrl(filename: string): Observable<any> {
    let path = this.GetFilePath(filename);
    console.log(path);
    var content: string[];
    return this.http.post(this.url + "file/content", path);
  }

  setContent(data) {
    this.fileContent = data;
    console.log("from set content func");
    console.log(this.fileContent);
  }

  showResponse() {
    return this.httpresponse;
  }

  GetAllFiles() {
    return this.allFiles;
  }

  GetContent(fileName: string) {
    let index;
    for (let i = 0; i < this.textFiles.length; i++) {
      //console.log(this.textFiles[i]);
      if (fileName == this.textFiles[i]) {
        index = i;
      }
    }
    return this.fileContent[index];
  }
  GetFilePath(fileName) {
    this.files = this.GetAllFiles();
    for (let i = 0; i < this.files.length; i++) {
      if (this.files[i].includes(fileName)) {
        //console.log(this.files[i]);
        return this.files[i];
      }
    }
  }
}
