import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    currentlyLoggedIn;
    loggedInUser: Subscription;
    userName;

    constructor(private loginService: LoginService) { }

    ngOnInit() {
        this.loggedInUser = this.loginService.$userData.subscribe(user => this.userName = `#${user.firstName}`);
        this.userName = this.loggedInUser;
        // this.currentlyLoggedIn = this.loginService.$userData.subscribe(user =>this.currentlyLoggedIn = `#${user.currentlyLoggedIn}`);
        console.log(this.currentlyLoggedIn);
    }
}
