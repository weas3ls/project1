import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { Reimbursement } from './../../components/models/Reimbursement';
import { User } from 'src/app/components/models/User';

@Injectable({
    providedIn: 'root'
})
export class RequestDetailService {

    reimbursement: Reimbursement;
    currentlyLoggedIn: boolean;
    loggedInUser: User;

    constructor(
        private httpClient: HttpClient,
        private router: Router,
    ) { }

    async getReimbursement(reimbId: number) {
        const url = `http://localhost:11000/project1/request/${reimbId}`;

        const data = await this.httpClient.get(url).toPromise();
        if (data) {
            this.reimbursement = {
                id: data['id'],
                amount: data['amount'],
                description: data['description'],
                requestee_id: data['requestee_id'],
                resolver_id: data['resolver_id'],
                status: data['status'],
                type: data['type'],
                date_submitted: data['date_submitted'],
                date_resolved: data['date_resolved']
            }
            return this.reimbursement;
        }
    }

    async approveRequest(reimb_id: number) {
        const send_data = {
            id: reimb_id,
            status: 2,
            resolver_id: 24
        }
        const url = 'http://localhost:11000/project1/resolve';
        const response = await this.httpClient.post(url, send_data).toPromise();
    }

    async denyRequest(reimb_id: number) {
        const send_data = {
            id: reimb_id,
            status: 3,
            resolver_id: 24
        }
        const url = 'http://localhost:11000/project1/resolve';
        const response = await this.httpClient.post(url, send_data).toPromise();
    }
}
