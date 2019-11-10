import { Reimbursement } from './../../components/models/Reimbursement';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class RequestDetailService {

    reimbursement: Reimbursement;
    currentlyLoggedIn: boolean;

    constructor(private httpClient: HttpClient) { }

    async getReimbursement(reimbId: number) {
        const url = `http://localhost:11000/project1/profile/${reimbId}`;

        const reimbursementData = await this.httpClient.get(url).toPromise();

        if (reimbursementData) {
            this.reimbursement = {
                id: reimbursementData['id'],
                amount: reimbursementData['amount'],
                description: reimbursementData['description'],
                requestee_id: reimbursementData['requestee_id'],
                resolver_id: reimbursementData['resolver_id'],
                status_id: reimbursementData['status_id'],
                type_id: reimbursementData['type_id'],
                date_submitted: reimbursementData['date_submitted'],
                date_resolved: reimbursementData['date_resolved']
            }
            return this.reimbursement;
        }
    }
}
