import { ScoringService } from './../scoring.service';
import { Component, OnInit, Input } from '@angular/core';
import { Profile } from '../profile';

@Component({
  selector: 'app-scoring',
  templateUrl: './scoring.component.html',
  styleUrls: ['./scoring.component.css']
})
export class ScoringComponent implements OnInit {

  @Input()
  detail: any;
  score: number;
  value: number;
  profile: Profile;
  constructor(private scoringService: ScoringService) { }

  ngOnInit() {
    console.log(this.detail);
    if (this.detail.solved === 0) {
      this.score = 0;
    } else {
      this.score = 100;
    }
    this.value = this.score * this.detail.maxScore / 100;
    this.profile = new Profile(this.detail.challengeId, this.detail.challengeTitle, this.detail.userId, this.detail.value);
    console.log(this.value);
    if (this.value === this.detail.maxScore) { // till test cases are not implemented
      this.scoringService.sendToProfile(this.profile)
        .subscribe(data => {
          console.log(data);
        });
    }
  }

  retry() {

  }

  home() {

  }

}
