import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

import { LoginService } from 'src/app/services/login/login.service';
import { ToastService } from 'ng-uikit-pro-standard';

import { User } from './../models/User';

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

    private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    constructor(
        private loginService: LoginService,
        private router: Router,
        private toastrService: ToastService
    ) { }

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
            const options = { opacity: 1, progressBar: true, timeOut: 3000, closeButton: true, toastClass: 'pink' };
            this.toastrService.error('Login Failed!', 'Sorry!', options);
        }
    }

    logout() {
        // this.loggedIn.next(false);
        this.user = null;
        this.router.navigate(['/']);
    }
}