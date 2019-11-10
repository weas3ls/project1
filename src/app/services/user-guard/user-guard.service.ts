import { LoginService } from 'src/app/services/login/login.service';
import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router, ActivatedRouteSnapshot, RouterStateSnapshot, Route } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class UserGuardService implements CanActivate, CanLoad {

    constructor(
        private loginService: LoginService,
        private _router: Router
    ) { }

    canActivate(_next: ActivatedRouteSnapshot, _state: RouterStateSnapshot): boolean {
        return this.checkLoggedIn(_state.url);
    }

    canLoad(_route: Route): boolean {
        return this.checkLoggedIn(_route.path);
    }
    checkLoggedIn(url: string): boolean {
        if (this.loginService.currentlyLoggedIn) {
            return true;
        }
        this._router.navigate(['/']);
        return false;
    }
}
