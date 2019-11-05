import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';

@Component({
    selector: 'app-requests',
    templateUrl: './requests.component.html',
    styleUrls: ['./requests.component.scss']
})
export class RequestsComponent implements OnInit {
    @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective;

    elements: any = [];

    headElements = ['Request ID', 'Amount', 'Date Submitted', 'Date Resolved', 'Status', 'Type'];

    searchText: string = '';
    previous: string;

    constructor() { }

    @HostListener('input') oninput() {
        this.searchItems();
    }

    ngOnInit() {
        for (let i = 1; i <= 11; i++) {
            if (i % 2) {
                status = 'Open';
            } else {
                status = 'Closed';
            }
            if (i % 3 == 1) {
                var type = 'Food';
            } else if (i % 3 == 2) {
                var type = 'Travel';
            } else {
                var type = 'Lodging';
            }
            this.elements.push({
                request_id: i,
                amount: i * 37,
                date_submitted: '2019-02-'+i,
                date_resolved: '2019-05-' + i,
                status: status,
                type: type
            });
        }

        this.mdbTable.setDataSource(this.elements);
        this.previous = this.mdbTable.getDataSource();
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
