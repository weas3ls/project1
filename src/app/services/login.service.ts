import { Injectable, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Subject, Observable } from 'rxjs';
import { User } from '../components/models/user';
import { DomElementSchemaRegistry } from '@angular/compiler';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    currentlyLoggedIn = false;
    loggedInUser: User;

    constructor(private router: Router, private httpClient: HttpClient) { }

    async login(credentials: { email: string, password: string }) {

        const url = 'http://localhost:11000/project1/login';
        const user = await this.httpClient.post(url, credentials).toPromise();

        if (user) {
            console.log(user['firstName']);
            this.loggedInUser = {
                id: user['id'],
                firstName: user['firstName'],
                email: user['email'],
                currentlyLoggedIn: true
            }
            return this.loggedInUser;
        } else {
            return null;
        }
    }
}