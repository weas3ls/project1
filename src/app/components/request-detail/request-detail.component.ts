import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-request-detail',
    templateUrl: './request-detail.component.html',
    styleUrls: ['./request-detail.component.scss']
})
export class RequestDetailComponent implements OnInit {
    @Input() request: Request;

    constructor() { }

    ngOnInit() {
    }

}
