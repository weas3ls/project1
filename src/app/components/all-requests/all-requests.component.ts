import { Component, OnInit, ViewChild, ChangeDetectorRef, HostListener } from '@angular/core';
import { MdbTablePaginationComponent, MdbTableDirective } from 'ng-uikit-pro-standard';

import { Reimbursement } from '../models/Reimbursement';
import { User } from '../models/User';

import { AllRequestsService } from 'src/app/services/all-requests/all-requests.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
    selector: 'app-all-requests',
    templateUrl: './all-requests.component.html',
    styleUrls: ['./all-requests.component.scss']
})
export class AllRequestsComponent implements OnInit {
    @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
    @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective
    
    reimbursements: Array<Reimbursement>;
    previous: any = [];
    loggedInUser: User;
    searchText: string = '';

    constructor(
        private cdRef: ChangeDetectorRef,
        private loginService: LoginService,
        private allRequestsService: AllRequestsService
    ) { }

    @HostListener('input') oninput() {
        this.searchItems();
    }

    async ngOnInit() {
        this.loggedInUser = await this.loginService.getUser();
        this.reimbursements = await this.allRequestsService.getReimbursements();
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
