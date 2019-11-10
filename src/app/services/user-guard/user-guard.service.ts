import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router, ActivatedRouteSnapshot, RouterStateSnapshot, Route } from '@angular/router';

import { ToastService } from 'ng-uikit-pro-standard';

import { LoginService } from 'src/app/services/login/login.service';

@Injectable({
    providedIn: 'root'
})
export class UserGuardService implements CanActivate, CanLoad {

    constructor(
        private loginService: LoginService,
        private _router: Router,
        private toastrService: ToastService
    ) { }

    canActivate(_next: ActivatedRouteSnapshot, _state: RouterStateSnapshot): boolean {
        return this.checkLoggedIn(_state.url);
    }

    canLoad(_route: Route): boolean {
        return this.checkLoggedIn(_route.path);
    }

    showError() {
        const options = { opacity: 1, progressBar: true, timeOut: 3000, closeButton: true, toastClass: 'pink', enableHtml: true, messageClass: 'lead', titleClass: 'h6 mb-0' };
        this.toastrService.error('You aren\'t logged in! &#129320;', 'Hey!', options);
    }

    checkLoggedIn(url: string): boolean {
        if (this.loginService.currentlyLoggedIn) {
            return true;
        }
        this.showError();
        this._router.navigate(['/']);
        return false;
    }
}
