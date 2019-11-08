import { Injectable, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Subject, Observable } from 'rxjs';
import { User } from '../components/models/user';
import { DomElementSchemaRegistry } from '@angular/compiler';
// import { url } from 'inspector';

/* Note that services will generally have this decorator */
@Injectable({
    providedIn: 'root'
})
export class LoginService {

    currentlyLoggedIn = false;

    private loggedInUser: Subject<User> = new Subject();
    public $userData: Observable<User> = this.loggedInUser.asObservable();

    private validCredentials = [{
        email: 'abby@aol.com',
        password: 'abs'
    }, {
        email: 'billy@biceps.com',
        password: 'biceps'
    }, {
        email: 'cindy@cinderblock.com',
        password: 'cider'
    }];

    // Assuming there is a service called 'Router' how
    // would it be injected?
    constructor(private router: Router, private httpClient: HttpClient) { }

    async login(credentials: { email: string, password: string }) {

        const url = 'http://localhost:11000/project1/login';
        const user = await this.httpClient.post(url, credentials).toPromise();
        
        if (user) {
            console.log(user['firstName']);
            let loggedInUser:User = {
                id: user['id'],
                firstName: user['firstName'],
                email: user['email'],
                currentlyLoggedIn: true
            }
            return loggedInUser;
            // this.currentlyLoggedIn = true;
            // this.router.navigateByUrl('/profile');
            // let user: User = {
            //     id: 0,
            //     name: 'Abby',
            //     email: credentials.email,
            //     currentlyLoggedIn: true
            //console.log(user);

            //}
            // return user;
            // this.httpClient.get(url).subscribe((user: User) => this.loggedInUser.next(user));
        } else {
            return null;
        }
    }
}