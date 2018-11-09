import { Component, OnInit, Input} from '@angular/core';
import { MonacoFile } from 'ngx-monaco';
import { ActivatedRoute } from '@angular/router';
import { FilesService } from '../files.service'
import { File } from '../folder-structure/directory/model/file';


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  @Input() displayFile : string;


  private fileName = 'index.js';
  private content ;

  constructor(private activatedroute: ActivatedRoute , private filesService : FilesService) { }

  
  ngOnInit(): void {
    this.activatedroute.params.subscribe((params) => {
      this.file.content="hello " + params.name;
      // console.log("hello " + params.name );
      // document.getElementById("editor").load();
});
}

  //   ngOnInit(){
  //     this.activatedroute.params.subscribe((params)=>this.content = params.toString())
        
  //     console.log("{sfhjdgfdbjhdgbjvv");
  // }

  // ngOnInit(): void {

  // // this.content = this.displayFile;
  //  console.log("fdbhjdbhjfbejhfb");
   
  //   console.log(this.displayFile);
  //   // this.activatedroute.params.subscribe(params => this.file.content = params['name']);
  //   // // console.log(name);
  //   // // this.file.content = name;
  //   // console.log(this.file.content);
  // }

  title = 'app';
  options = {
    theme: 'vs-dark',
    language: 'javascript',

  };
  file: MonacoFile = {
    uri: this.fileName,
    language: 'javascript',
    content: this.content
  };

  onFileChange(file: MonacoFile) {
    this.content = file.content;
    // console.log(this.content);
 //   console.log(file.content);
  }

   public onClick(){

    console.log(this.file.uri , this.file.content);
    // var a =this.file.uri; 
    // var b = this.file.content;
    // var file : any[];

    this.filesService.SaveFile(this.file)
          .subscribe();
  }


}

