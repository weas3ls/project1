import { Injectable, ViewChild, ElementRef } from '@angular/core';
import { CanActivate, CanLoad, Router, ActivatedRouteSnapshot, RouterStateSnapshot, Route } from '@angular/router';

import { ToastService } from 'ng-uikit-pro-standard';

import { LoginService } from 'src/app/services/login/login.service';

@Injectable({
    providedIn: 'root'
})
export class UserGuardService implements CanActivate, CanLoad {

    @ViewChild('alert', { static: true }) alert: ElementRef;

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
        const options = { opacity: 1 };
        this.toastrService.error('You aren\'t logged in!', 'Error!', options);
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
