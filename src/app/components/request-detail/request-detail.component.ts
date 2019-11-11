import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { User } from '../models/User';
import { LoginService } from 'src/app/services/login/login.service';
import { RequestDetailService } from 'src/app/services/request-detail/request-detail.service';

@Component({
    selector: 'app-request-detail',
    templateUrl: './request-detail.component.html',
    styleUrls: ['./request-detail.component.scss']
})
export class RequestDetailComponent implements OnInit {

    request;
    requestId = Number (this.activatedRoute.snapshot.paramMap.get('id'));
    currentlyLoggedIn: boolean;
    loggedInUser: User;
    userFirstName: string;
    isManager: boolean = false;

    constructor(
        private loginService: LoginService,
        private requestDetailService: RequestDetailService,
        private activatedRoute: ActivatedRoute
    ) { }

    async ngOnInit() {
        this.loggedInUser = await this.loginService.getUser();
        if (this.loginService.loggedInUser) {
            this.currentlyLoggedIn = this.loggedInUser.currentlyLoggedIn;
            this.userFirstName = this.loggedInUser.firstName;
            if (this.loggedInUser.accountType == '1') {
                this.isManager = true;
            }
        }
        this.request = await this.requestDetailService.getReimbursement(this.requestId);
        switch ('' + this.request.status) {
            case '1':
                this.request.status = 'Pending'
                break;
            case '2':
                this.request.status = 'Approved'
                break;
            case '3':
                this.request.status = 'Denied'
                break;
            default:
                break;
        }
        
        switch ('' + this.request.type) {
            case '1':
                this.request.type = 'Lodging'
                break;
            case '2':
                this.request.type = 'Travel'
                break;
            case '3':
                this.request.type = 'Food'
                break;
            case '4':
                this.request.type = 'Other'
                break;
            default:
                break;
        }
    }

    approveRequest(reimb_id: number) {
        this.requestDetailService.approveRequest(reimb_id);
        location.reload();
    }

    denyRequest(reimb_id: number) {
        this.requestDetailService.denyRequest(reimb_id);
        location.reload();
    }
}
