import { Reimbursement } from './../../components/models/Reimbursement';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/components/models/User';
import { LoginService } from '../login/login.service';

@Injectable({
    providedIn: 'root'
})
export class RequestsService {

    currentlyLoggedIn: boolean;
    loggedInUser: User;
    userReimbursements: Array<Reimbursement>;

    constructor(
        private httpClient: HttpClient,
        private loginService: LoginService
    ) { }

    async getUserReimbursements(userId: number) {
        this.loggedInUser = this.loginService.loggedInUser;
        const url = `http://localhost:11000/project1/profile/${userId}`;
        const data = await this.httpClient.get(url).toPromise();
        if (data) {
            this.userReimbursements = JSON.parse(JSON.stringify(data));
        }
        return this.userReimbursements;
    }
}
