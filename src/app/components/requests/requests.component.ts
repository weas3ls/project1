import { ProfileComponent } from './../profile/profile.component';
import { Component, OnInit, ViewChild, HostListener, ChangeDetectorRef } from '@angular/core';
import { MdbTableDirective, MdbTablePaginationComponent } from 'ng-uikit-pro-standard';
import { RequestDetailService } from 'src/app/services/request-detail/request-detail.service';

@Component({
    selector: 'app-requests',
    templateUrl: './requests.component.html',
    styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {

    @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
    @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective
    reimbursements: any = [];
    previous: any = [];
    reimb_type: string;

    searchText: string = '';

    constructor(
        private cdRef: ChangeDetectorRef,
        private requestDetailService: RequestDetailService,
        private profileComponent: ProfileComponent
    ) { }

    @HostListener('input') oninput() {
        this.searchItems();
    }

    ngOnInit() {
        this.profileComponent.userReimbursements.forEach(reimbursement => {
            switch (reimbursement.status_id) {
                case 1:
                    status = 'Pending'
                    break;
                case 2:
                    status = 'Approved'
                    break;
                case 3:
                    status = 'Denied'
                    break;
                default:
                    break;
            }

            switch (reimbursement.type_id) {
                case 1:
                    this.reimb_type = 'Lodging'
                    break;
                case 2:
                    this.reimb_type = 'Travel'
                    break;
                case 3:
                    this.reimb_type = 'Food'
                    break;
                case 4:
                    this.reimb_type = 'Other'
                    break;
                default:
                    break;
            }

            this.reimbursements.push({
                reimb_id: reimbursement.id,
                reimb_amount: reimbursement.amount,
                reimb_submitted: reimbursement.date_submitted,
                reimb_resolved: reimbursement.date_resolved,
                reimb_status_id: status,
                reimb_type_id: this.reimb_type
            });
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

    getReimbursementDetails(reimbId: number) {
        this.requestDetailService.getReimbursement(reimbId);
    }
}
