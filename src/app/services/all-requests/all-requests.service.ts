import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from 'src/app/components/models/User';
import { Reimbursement } from 'src/app/components/models/Reimbursement';

import { LoginService } from '../login/login.service';

@Injectable({
    providedIn: 'root'
})
export class AllRequestsService {
    currentlyLoggedIn: boolean;
    loggedInUser: User;
    reimbursements: Array<Reimbursement>;

    constructor(
        private httpClient: HttpClient,
        private loginService: LoginService
    ) { }

    async getReimbursements() {
        this.loggedInUser = this.loginService.loggedInUser;
        const url = 'http://localhost:11000/project1/manager';
        const data = await this.httpClient.get(url).toPromise();
        if (data) {
            this.reimbursements = JSON.parse(JSON.stringify(data));
        }
        return this.reimbursements;
    }
}
