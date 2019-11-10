import { User } from './../models/User';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { LoginService } from 'src/app/services/login/login.service';

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
    loggedInUser: User;
    userFirstName;
    currentlyLoggedIn = false;
    user: User;

    constructor(private loginService: LoginService) { }

    ngOnInit() {
        this.validatingForm = new FormGroup({
            email: new FormControl('', Validators.email),
            password: new FormControl('', Validators.required)
        });
    }

    get email() {
        return this.validatingForm.get('email');
    }

    get password() {
        return this.validatingForm.get('password');
    }

    async submit() {
        const credentials = {
            email: this.email.value,
            password: this.password.value
        };
        this.user = await this.loginService.login(credentials);
        if (this.user) {
            this.currentlyLoggedIn = this.user.currentlyLoggedIn;
            this.userFirstName = this.user.firstName;
        } else {
            console.log('login failed');
        }
    }
}