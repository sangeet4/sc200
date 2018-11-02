import { Component, OnInit, Input, Output } from '@angular/core';
import { MonacoFile } from 'ngx-monaco';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  @Input() files: File[];
  @Input() input;

  private fileName = 'index.js';
  private content = '//Welcome454645';

  constructor(private activatedroute: ActivatedRoute) { }

  ngOnInit() {

    console.log(this.input);
    // this.activatedroute.params.subscribe(params => this.file.content = params['name']);
    // // console.log(name);
    // // this.file.content = name;
    // console.log(this.file.content);
  }

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

  onClick() {
    window.open(this.file.uri);
  }

}

