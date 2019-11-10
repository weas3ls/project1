import { Reimbursement } from './../../components/models/Reimbursement';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/components/models/User';

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    currentlyLoggedIn: boolean;
    loggedInUser: User;
    userReimbursements: Reimbursement[];

    constructor(private httpClient: HttpClient) { }

    async getUserReimbursements() {
        const url = `http://localhost:11000/project1/profile/${this.loggedInUser.id}`;

        const userReimbursementsData = await this.httpClient.get(url).toPromise();
        this.userReimbursements = JSON.parse(JSON.stringify(userReimbursementsData));
        console.log(userReimbursementsData);
        if (userReimbursementsData) {
            this.userReimbursements.forEach(reimbursement => {
                const userReimbursement = {
                    id: reimbursement['id'],
                    amount: reimbursement['amount'],
                    description: reimbursement['description'],
                    requestee_id: reimbursement['requestee_id'],
                    resolver_id: reimbursement['resolver_id'],
                    status_id: reimbursement['status_id'],
                    type_id: reimbursement['type_id'],
                    date_submitted: reimbursement['date_submitted'],
                    date_resolved: reimbursement['date_resolved']
                }
                this.userReimbursements.push(userReimbursement);
            });
        }
        console.log(this.userReimbursements);
    }
}
