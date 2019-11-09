import { Component, OnInit, ViewChild, HostListener, ChangeDetectorRef } from '@angular/core';
import { MdbTableDirective, MdbTablePaginationComponent } from 'ng-uikit-pro-standard';

@Component({
    selector: 'app-requests',
    templateUrl: './requests.component.html',
    styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {
    @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
    @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective
    elements: any = [];
    previous: any = [];

    headElements = ['Reimbursement ID', 'Type', 'Status', 'Amount', 'Date Submitted', 'Date Resolved'];

    searchText: string = '';

    constructor(private cdRef: ChangeDetectorRef) { }

    @HostListener('input') oninput() {
        this.searchItems();
    }

    ngOnInit() {
        for (let i = 1; i <= 23; i++) {
            if (i % 2) {
                status = 'Open';
            } else {
                status = 'Closed';
            }
            if (i % 4 == 1) {
                var type = 'Food';
            } else if (i % 4 == 2) {
                var type = 'Travel';
            } else if (i % 4 == 3) {
                var type = 'Lodging';
            } else {
                var type = 'Other';
            }
            this.elements.push({
                reimb_id: i,
                reimb_amount: i * 37,
                reimb_submitted: '2019-02-' + i,
                reimb_resolved: '2019-05-' + i,
                reimb_status_id: status,
                reimb_type_id: type
            });
        }

        this.mdbTable.setDataSource(this.elements);
        this.elements = this.mdbTable.getDataSource();
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
            this.elements = this.mdbTable.getDataSource();
        }

        if (this.searchText) {
            this.elements = this.mdbTable.searchLocalDataBy(this.searchText);
            this.mdbTable.setDataSource(prev);
        }
    }
}
