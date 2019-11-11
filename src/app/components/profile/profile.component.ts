import { Component, OnInit } from '@angular/core';

import { User } from './../models/User';
import { Reimbursement } from '../models/Reimbursement';

import { ProfileService } from './../../services/profile/profile.service';
import { LoginService } from '../../services/login/login.service';

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
    userReimbursements;

    constructor(
        private loginService: LoginService,
        private profileService: ProfileService
    ) { }

    async ngOnInit() {
        this.loggedInUser = this.loginService.loggedInUser;
        console.log(this.loggedInUser);
        if (this.loggedInUser) {
            this.loggedInUser = this.loginService.loggedInUser;
            this.userFirstName = this.loggedInUser.firstName;
            this.currentlyLoggedIn = this.loggedInUser.currentlyLoggedIn;
            this.userReimbursements = this.profileService.getUserReimbursements;
        }
    }
}
