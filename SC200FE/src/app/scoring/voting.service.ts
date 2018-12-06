import { Voting } from './voting';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VotingService {

  public baseUrl = environment.apiUrl + 'vote/';
  constructor(private http: HttpClient) { }

  upVote(voting: Voting): Observable<any> {
    return this.http.put(this.baseUrl + 'upvoting', voting);
  }

  downVote(voting: Voting): Observable<any> {
    return this.http.put(this.baseUrl + 'downvoting', voting);
  }

}
