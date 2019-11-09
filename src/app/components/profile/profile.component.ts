import { Component, OnInit } from '@angular/core';

import { User } from './../models/User';
import { LoginService } from './../../services/login.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    currentlyLoggedIn = true;
    loggedInUser: User;
    userFirstName;
    selectedRequest: Request;

    constructor(private loginService: LoginService) { }

    async ngOnInit() {
        this.loggedInUser = await this.loginService.loggedInUser;
        if (this.loggedInUser) {
            this.loggedInUser = this.loginService.loggedInUser;
            this.userFirstName = this.loggedInUser.firstName;
            this.currentlyLoggedIn = this.loggedInUser.currentlyLoggedIn;
        } else {
            // this.currentlyLoggedIn = false;
        }
    }

    onSelect(request: Request): void {
        this.selectedRequest = request;
    }
}
