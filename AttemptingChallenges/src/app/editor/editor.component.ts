import { Component, OnInit } from '@angular/core';
import { MonacoFile } from 'ngx-monaco';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  title = 'app';
  options = {
    theme: 'vs-dark',
    language: 'java'
  };
  file: MonacoFile = {
		uri: 'index.java',
		language: 'java',
		content: `//Welcome`
	};

	onFileChange(file: MonacoFile) {
    // Handle file change
    console.log(file.content);
	}

}