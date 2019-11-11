import { Component, OnInit } from '@angular/core';

import { User } from './../models/User';
import { LoginService } from '../../services/login/login.service';
import { Reimbursement } from '../models/Reimbursement';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    currentlyLoggedIn: boolean;
    loggedInUser: User;
    userFirstName: string;

    constructor(private loginService: LoginService) { }

    async ngOnInit() {
        this.loggedInUser = this.loginService.loggedInUser;
        if (this.loggedInUser) {
            this.userFirstName = this.loggedInUser.firstName;
            this.currentlyLoggedIn = this.loggedInUser.currentlyLoggedIn;
        }
    }
}
