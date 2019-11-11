import { Injectable, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Subject, Observable } from 'rxjs';
import { User } from '../../components/models/user';
import { DomElementSchemaRegistry } from '@angular/compiler';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    currentlyLoggedIn = false;
    loggedInUser: User = null;

    constructor(private httpClient: HttpClient) { }

    async login(credentials: { email: string, password: string }) {

        const url = 'http://localhost:11000/project1/login';
        const user = await this.httpClient.post(url, credentials).toPromise();
        if (user) {
            this.loggedInUser = {
                id: user['id'],
                firstName: user['firstName'],
                email: user['email'],
                accountType: user['roleId'],
                currentlyLoggedIn: true
            }
        }
        return this.loggedInUser;
    }

    getUser() {
        return this.loggedInUser;
    }
}