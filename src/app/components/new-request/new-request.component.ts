import { Component, OnInit, EventEmitter } from '@angular/core';
import { UploadFile, UploadInput, UploadOutput, humanizeBytes } from 'ng-uikit-pro-standard';
import { User } from '../models/User';
import { NewRequestService } from 'src/app/services/new-request/new-request.service';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-new-request',
    templateUrl: './new-request.component.html',
    styleUrls: ['./new-request.component.scss']
})
export class NewRequestComponent implements OnInit {
    optionsSelect: Array<any>;
    formData: FormData;
    files: UploadFile[];
    uploadInput: EventEmitter<UploadInput>;
    humanizeBytes: Function;
    dragOver: boolean;

    reimb_type;
    reimb_amount;
    reimb_description;
    reimb_receipt;

    currentlyLoggedIn: boolean = true;
    loggedInUser: User;

    constructor(
        private newRequestService: NewRequestService,
        private loginService: LoginService,
        private router: Router,
    ) {
        this.files = [];
        this.uploadInput = new EventEmitter<UploadInput>();
        this.humanizeBytes = humanizeBytes;
    }

    ngOnInit() {
        this.loggedInUser = this.loginService.loggedInUser;
        console.log(this.loggedInUser.id);
        this.optionsSelect = [
            { value: '1', label: 'Lodging' },
            { value: '2', label: 'Travel' },
            { value: '3', label: 'Food' },
            { value: '4', label: 'Other' }
        ];
    }

    showFiles() {
        let files = '';
        for (let i = 0; i < this.files.length; i++) {
            files += this.files[i].name;
            if (!(this.files.length - 1 === i)) {
                files += ',';
            }
        }
        return files;
    }

    startUpload(): void {
        const event: UploadInput = {
            type: 'uploadAll',
            url: 'your-path-to-backend-endpoint',
            method: 'POST',
            data: { foo: 'bar' },
        };
        this.files = [];
        this.uploadInput.emit(event);
    }

    cancelUpload(id: string): void {
        this.uploadInput.emit({ type: 'cancel', id: id });
    }

    onUploadOutput(output: UploadOutput | any): void {

        if (output.type === 'allAddedToQueue') {
        } else if (output.type === 'addedToQueue') {
            this.files.push(output.file); // add file to array when added
        } else if (output.type === 'uploading') {
            // update current data in files array for uploading file
            const index = this.files.findIndex(file => file.id === output.file.id);
            this.files[index] = output.file;
        } else if (output.type === 'removed') {
            // remove file from array when removed
            this.files = this.files.filter((file: UploadFile) => file !== output.file);
        } else if (output.type === 'dragOver') {
            this.dragOver = true;
        } else if (output.type === 'dragOut') {
        } else if (output.type === 'drop') {
            this.dragOver = false;
        }
        this.showFiles();
    }

    submit() {
        const requestData = {
            requestee_id: this.loggedInUser.id,
            type: this.reimb_type,
            amount: this.reimb_amount,
            description: this.reimb_description,
            status: 1,
            reimb_receipt: `E:\\Revature\\Training\\Projects\\Project 01\\media\\${this.files[0].name}`
        }
        this.newRequestService.uploadTicket(requestData);
        this.router.navigateByUrl('/profile');
        location.reload();
    }
}
