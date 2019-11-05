import { User } from './../models/User';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { LoginService } from 'src/app/services/login.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    // Input values
    inputEmail = '';
    inputPassword = '';
    validatingForm: FormGroup;
    loggedInUser: Subscription;
    userName;
    currentlyLoggedIn = false;
    user: User;

    // Flag set to true when user supplies invalid input
    invalidInput = false;

    constructor(private loginService: LoginService) { }

    ngOnInit() {
        this.validatingForm = new FormGroup({
            email: new FormControl('', Validators.email),
            password: new FormControl('', Validators.required)
        });
        this.loggedInUser = this.loginService.$userData.subscribe(user => this.userName = `#${user.name}`);
        // this.currentlyLoggedIn = this.loginService.$userData.subscribe(user => this.currentlyLoggedIn = `#${user.currentlyLoggedIn}`);
        console.log(this.userName);
    }

    get email() {
        return this.validatingForm.get('email');
    }

    get password() {
        return this.validatingForm.get('password');
    }

    submit() {
        const credentials = {
            email: this.email.value,
            password: this.password.value
        };
        this.user = this.loginService.login(credentials);
        if (this.user) {
            this.currentlyLoggedIn = this.user.currentlyLoggedIn;
            this.userName = this.user.name;

        } else {
            console.log('login failed');
        }
    }
}