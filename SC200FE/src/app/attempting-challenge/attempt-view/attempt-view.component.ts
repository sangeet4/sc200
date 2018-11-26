import { Component, OnInit } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Routes } from '@angular/router';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-attempt-view',
  templateUrl: './attempt-view.component.html',
  styleUrls: ['./attempt-view.component.css']
})
export class AttemptViewComponent implements OnInit {
  
  completechallenge1=[];
  private id:string="";
  constructor(private http:HttpClient,private route:ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    //console.log(this.id)
    var http:HttpClient;
    //console.log(`hey ${id}`);
    this.http.get('http://172.23.239.81:8180/challengeAPI/v1/'+this.id).subscribe((res:any)=> {
      console.log("inside the sub");
        this.completechallenge1=res;
        
        
  
    })
  }




 

  Attempt(){
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
    //var http:HttpClient;
    //console.log(`hey ${id}`);
    this.http.get('http://172.23.239.81:8180/challengeAPI/v1'+this.id).subscribe((res:any)=> {
        this.completechallenge1=res
        console.log("gggggg ", res);
        
  
    })
  }
  solve(){

  }

}
