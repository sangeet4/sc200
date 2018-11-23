import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-compile-trigger',
  templateUrl: './compile-trigger.component.html',
  styleUrls: ['./compile-trigger.component.css']
})
export class CompileTriggerComponent implements OnInit {

  model: any = {};
  flag = 0;
  test = [0, 1, 0, 1, 1, 0, 1, 0];
  constructor() { }

  ngOnInit() {
    this.model.solved = 1;
  }

  onSubmit() {
    console.log('SUCCESS!! :-)\n\n' + JSON.stringify(this.model));
    this.flag = 1;
  }

}
