import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';
import { User } from '../_models';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

  

    register(user: User) {
        return this.http.post(`https://35.154.116.88:8080/profile/api/v1/user/add`, user);
    }


}