import { Component, OnInit, Input } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-recommedation-card',
  templateUrl: './recommedation-card.component.html',
  styleUrls: ['./recommedation-card.component.css']
})
export class RecommedationCardComponent implements OnInit {
  title = 'RecommendationSystem';
  name:string= '' ;
  challenges=[];
  isClicked:boolean=false;

  @Input()
  inName: string;

  constructor(private http:HttpClient) {
   }
   ngOnInit() {
     this.name = this.inName;
    this.http.get('http://172.23.239.218:8081/api/v1/recommendation/'+this.name).subscribe((res:any)=> {
      this.challenges=res;
      // console.log(this.challenges);
  })
  }
   search(){
    // var http:HttpClient;
    

  }
  attempt(){

  }

}
