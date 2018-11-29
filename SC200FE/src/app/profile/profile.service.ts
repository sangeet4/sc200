import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from './profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  public baseUrl = 'https://35.154.116.88:8080/profile/sc200/userProfile/';

  constructor(private http: HttpClient) { }

  getProfileById(id: string): Observable<any> {
    return this.http.get(this.baseUrl  + id);
  }

  updateProfile(id: string, prof: Profile): Observable<any> {
    return this.http.put(this.baseUrl  + id, prof);
  }
}
