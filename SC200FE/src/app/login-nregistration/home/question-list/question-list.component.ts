import { Component, OnInit } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.css']
})
export class QuestionListComponent implements OnInit {

  quesList=[];
  id:string="";

  constructor(private http:HttpClient, private router:Router) { }

  ngOnInit() {
   
      
       var http:HttpClient;
     
      //console.log(`hey ${id}`);
      this.http.get('https://35.154.116.88:8080/challenge/challengeAPI/v1/basic').subscribe((res:any)=> {
         
        this.quesList=res;
       
          
    
      })
  }

  View(id:string){
    this.router.navigate(['attempt',id]);
  }

}



