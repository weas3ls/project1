import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

    currentlyLoggedIn: boolean;
    loggedInUser: User;
    userFirstName: string;

    constructor(private loginService: LoginService) { }

    async ngOnInit() {
        this.loggedInUser = await this.loginService.loggedInUser;
        if (this.loggedInUser) {
            this.loggedInUser = this.loginService.loggedInUser;
            this.userFirstName = this.loggedInUser.firstName;
            this.currentlyLoggedIn = this.loggedInUser.currentlyLoggedIn;
        }
    }

}
