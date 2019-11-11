import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AllRequestsComponent } from './components/all-requests/all-requests.component';
import { RequestDetailComponent } from './components/request-detail/request-detail.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NewRequestComponent } from './components/new-request/new-request.component';
import { UserGuardService } from './services/user-guard/user-guard.service';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [{
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
}, {
    path: 'login',
    component: LoginComponent
}, {
    path: 'new-request',
    // canActivate: [UserGuardService],
    component: NewRequestComponent
}, {
    path: 'profile',
    // canActivate: [UserGuardService],
    component: ProfileComponent
}, {
    path: 'request/:id',
    // canActivate: [UserGuardService],
    component: RequestDetailComponent
}, {
    path: 'all-requests',
    // canActivate: [UserGuardService],
    component: AllRequestsComponent
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
