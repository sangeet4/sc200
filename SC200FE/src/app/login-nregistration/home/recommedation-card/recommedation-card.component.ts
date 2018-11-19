import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-recommedation-card',
  templateUrl: './recommedation-card.component.html',
  styleUrls: ['./recommedation-card.component.css']
})
export class RecommedationCardComponent implements OnInit {
  title = 'RecommendationSystem';
  challenges = [];
  isClicked: boolean = false;

  @Input()
  name: string;

  constructor(private http: HttpClient) {
  }
  ngOnInit() {
    this.http.get('http://localhost:8186/api/v1/challenge/recommendation/' + this.name).subscribe((res: any) => {
      this.challenges = res;
      // console.log(this.challenges);
    })
  }
  search() {
    // var http:HttpClient;


  }
  attempt() {

  }

}
