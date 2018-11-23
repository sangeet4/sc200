import { QuestDetail } from './quest-detail';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  public baseUrl = 'http://35.154.116.88:8180/challengeAPI/v1';

  constructor(private http: HttpClient) { }

  createChallenge(quest: QuestDetail): Observable<any> {
    return this.http.post(this.baseUrl, quest);
  }

  getChallengeById(id: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + id);
  }

  updateChallenge(id: string, quest: QuestDetail): Observable<any> {
    return this.http.put(this.baseUrl + '/' + id, quest);
  }

}
