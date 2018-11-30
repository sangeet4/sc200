import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recommedation-card',
  templateUrl: './recommedation-card.component.html',
  styleUrls: ['./recommedation-card.component.css']
})
export class RecommedationCardComponent implements OnInit {
  title = 'the World of Programming';
  challenges = [];
  isClicked: boolean = false;

  @Input()
  name: string;

  constructor(private http: HttpClient, private router: Router) {
  }
  ngOnInit() {
    this.http.get('https://35.154.116.88:8080/secure/api/v1/challenge/recommendation/' + this.name).subscribe((res: any) => {
      this.challenges = res;
    })
  }
  
  attempt(id: string) {
    this.router.navigate(['attempt', id]);
  }

}
