import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { User } from './../models/User';
import { LoginService } from './../../services/login.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    currentlyLoggedIn;
    loggedInUser: User;
    userFirstName;
    selectedRequest: Request;

    constructor(private loginService: LoginService) { }

    ngOnInit() {
        this.loggedInUser = this.loginService.loggedInUser;
        this.userFirstName = this.loggedInUser.firstName;
        this.currentlyLoggedIn = this.loginService.currentlyLoggedIn;
    }

    onSelect(request: Request): void {
        this.selectedRequest = request;
    }
}
