import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }
  findByText(searchText: string){
    console.log(searchText+"http");
     return this.http.get<any>(`https://35.154.116.88:8080/challenge/challengeAPI/v1/suggestion/`+searchText)
      
  
  }
  findAll(){
    return this.http.get<any>(`https://35.154.116.88:8080/challenge/challengeAPI/v1/suggestion/basic`)
  }
}
