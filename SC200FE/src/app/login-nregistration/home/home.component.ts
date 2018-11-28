import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { UserService } from '../_services';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    currentUser: User;
    users: User[] = [];
    searchText: string;
    result;

    constructor(private userService: UserService,private searchService: SearchService,private router: Router) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
    setSearchText(par: string){
        this.searchText = par;
        console.log(this.searchText);
        this.searchService.findByText(this.searchText)
        .subscribe(
        results =>{
          this.result=results;
        }
 
      );
      this.searchText="";
      this.result = '';
    }
    attempt(id: string) {
        this.router.navigate(['attempt', id]);
      }
    ngOnInit() {
        // this.loadAllUsers();
    }

   
}