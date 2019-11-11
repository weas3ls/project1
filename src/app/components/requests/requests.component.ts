import { Component, OnInit, ViewChild, HostListener, ChangeDetectorRef } from '@angular/core';

import { MdbTableDirective, MdbTablePaginationComponent } from 'ng-uikit-pro-standard';

import { RequestDetailService } from 'src/app/services/request-detail/request-detail.service';
import { RequestsService } from 'src/app/services/requests/requests.service';

import { Reimbursement } from '../models/Reimbursement';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
    selector: 'app-requests',
    templateUrl: './requests.component.html',
    styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {

    @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
    @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective

    reimbursements: Array<Reimbursement>;
    previous: any = [];
    type: string;

    searchText: string = '';

    constructor(
        private cdRef: ChangeDetectorRef,
        private loginService: LoginService,
        private requestDetailService: RequestDetailService,
        private requestsService: RequestsService
    ) { }

    @HostListener('input') oninput() {
        this.searchItems();
    }

    async ngOnInit() {
        this.reimbursements = await this.requestsService.getUserReimbursements(this.loginService.loggedInUser.id);
        this.reimbursements.forEach(reimbursement => {
            switch ('' + reimbursement.status) {
                case '1':
                    reimbursement.status = 'Pending'
                    break;
                case '2':
                    reimbursement.status = 'Approved'
                    break;
                case '3':
                    reimbursement.status = 'Denied'
                    break;
                default:
                    break;
            }
            
            switch ('' + reimbursement.type) {
                case '1':
                    reimbursement.type = 'Lodging'
                    break;
                case '2':
                    reimbursement.type = 'Travel'
                    break;
                case '3':
                    reimbursement.type = 'Food'
                    break;
                case '4':
                    reimbursement.type = 'Other'
                    break;
                default:
                    break;
            }
        });

        this.mdbTable.setDataSource(this.reimbursements);
        this.reimbursements = this.mdbTable.getDataSource();
        this.previous = this.mdbTable.getDataSource();
    }

    ngAfterViewInit() {
        this.mdbTablePagination.setMaxVisibleItemsNumberTo(7);

        this.mdbTablePagination.calculateFirstItemIndex();
        this.mdbTablePagination.calculateLastItemIndex();
        this.cdRef.detectChanges();
    }

    searchItems() {
        const prev = this.mdbTable.getDataSource();

        if (!this.searchText) {
            this.mdbTable.setDataSource(this.previous);
            this.reimbursements = this.mdbTable.getDataSource();
        }

        if (this.searchText) {
            this.reimbursements = this.mdbTable.searchLocalDataBy(this.searchText);
            this.mdbTable.setDataSource(prev);
        }
    }
}
