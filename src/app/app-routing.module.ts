import { RequestDetailComponent } from './components/request-detail/request-detail.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewRequestComponent } from './components/new-request/new-request.component';

const routes: Routes = [{
    path: '',
    redirectTo: '/profile',
    pathMatch: 'full'
}, {
    path: 'new-request',
    component: NewRequestComponent
}, {
    path: 'profile',
    component: ProfileComponent
}, {
    path: 'request/:id',
    component: RequestDetailComponent
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
