import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class NewRequestService {

    constructor(private httpClient: HttpClient) { }

    async uploadTicket(requestData) {
        const url = 'http://localhost:11000/project1/new-request';
        const response = await this.httpClient.post(url, requestData).toPromise();
    }
}
