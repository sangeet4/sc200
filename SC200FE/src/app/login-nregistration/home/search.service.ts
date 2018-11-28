import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }
  findByText(searchText: string){
    console.log(searchText+"http");
     return this.http.get<any>(`http://172.23.239.81:8180/challengeAPI/v1/suggestion/`+searchText)
      
  
  }
  findAll(){
    return this.http.get<any>(`http://172.23.239.81:8180/challengeAPI/v1/suggestion/basic`)
  }
}
