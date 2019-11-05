import { LoginService } from './services/login.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RequestsComponent } from './components/requests/requests.component';
import { NewRequestComponent } from './components/new-request/new-request.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        LoginComponent,
        ProfileComponent,
        RequestsComponent,
        NewRequestComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MDBBootstrapModule.forRoot(),
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
    ],
    providers: [LoginService],
    bootstrap: [AppComponent, HeaderComponent, LoginComponent, ProfileComponent, RequestsComponent, NewRequestComponent]
})
export class AppModule { }
