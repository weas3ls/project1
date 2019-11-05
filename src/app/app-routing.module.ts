import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewRequestComponent } from './components/new-request/new-request.component';

const routes: Routes = [{
    path: 'new-request',
    component: NewRequestComponent
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
