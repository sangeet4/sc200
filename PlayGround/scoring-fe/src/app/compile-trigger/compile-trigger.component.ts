import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompilationService } from '../compilation.service';

@Component({
  selector: 'app-compile-trigger',
  templateUrl: './compile-trigger.component.html',
  styleUrls: ['./compile-trigger.component.css']
})
export class CompileTriggerComponent implements OnInit {

  model: any = {};
  flag = 0;
  test = [0, 1, 0, 1, 1, 0, 1, 0];
  receivedData;
  myField = '';
  constructor(/*private compilationService: CompilationService*/) { }

  ngOnInit() {
    this.model.solved = 1;
  }

  onSubmit() {
    console.log('SUCCESS!! :-)\n\n' + JSON.stringify(this.model));
    this.flag = 1;
    // this.compilationService.receiveHtml('hgjhj')
    //   .subscribe(data => {
    //     this.receivedData = data;
    //     console.log(data);
    //     this.render();
    //   });
    // while (this.receivedData == null) {}
  }

  // render() {
  //   let i = 1;
  //   while (this.receivedData[i] !== '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">') {i++;}
  //   while (i < this.receivedData.length) {
  //     this.myField += this.receivedData[i];
  //     i++;
  //   }
  //   console.log(this.myField);
  // }

}
