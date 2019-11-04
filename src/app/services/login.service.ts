import { Injectable, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Subject, Observable } from 'rxjs';
import { User } from '../components/models/user';
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

    login(credentials: { email: string, password: string }) {
        const valid = this.validCredentials.some(obj => {
            return obj.email === credentials.email &&
                obj.password === credentials.password;
        });

        if (valid) {
            this.currentlyLoggedIn = true;
            this.router.navigateByUrl('/profile');
            let user: User = {
                id: 0,
                name: 'Abby',
                email: credentials.email,
                currentlyLoggedIn: true
            }
            return user;
            // this.httpClient.get(url).subscribe((user: User) => this.loggedInUser.next(user));
        } else {
            return null;
        }
    }

    loginHttp(credentials: { email: string, password: string }) {
        const loginCredentials = {
            username: credentials.email,
            password: credentials.password
        };
        const url = 'http://localhost:8080/second-webapp/session';
        this.httpClient.post(url, loginCredentials)
            .subscribe((data) => {
                console.log(data);
            });
    }
}