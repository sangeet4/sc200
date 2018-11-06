import { Component, OnInit, Input} from '@angular/core';
import { MonacoFile } from 'ngx-monaco';
import { ActivatedRoute } from '@angular/router';
import { FilesService } from '../files.service'

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  @Input() displayFile : string;


  private fileName = 'index.js';
  private content = '//Welcome';

  constructor(private activatedroute: ActivatedRoute , private filesService : FilesService) { }

  
  ngOnInit(): void {
    this.activatedroute.params.subscribe((params) => {
      this.content="hello";
      console.log("dfhfvdhdfbfv");
});}

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
    console.log(this.content);
 //   console.log(file.content);
  }

}

