import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UrlSerializer } from '@angular/router';

import { MDBBootstrapModulesPro, MDBSpinningPreloader, ToastModule } from 'ng-uikit-pro-standard';

import { AppRoutingModule } from './app-routing.module';
import { LowerCaseUrlSerializer } from './lowerCaseUrlSerializer';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RequestsComponent } from './components/requests/requests.component';
import { NewRequestComponent } from './components/new-request/new-request.component';
import { RequestDetailComponent } from './components/request-detail/request-detail.component';
import { AllRequestsComponent } from './components/all-requests/all-requests.component';

import { LoginService } from './services/login/login.service';
import { UserGuardService } from './services/user-guard/user-guard.service';
import { RequestDetailService } from './services/request-detail/request-detail.service';
import { RequestsService } from './services/requests/requests.service';

import { ErsIdPipe } from './pipes/ers-id.pipe';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        LoginComponent,
        ProfileComponent,
        RequestsComponent,
        NewRequestComponent,
        RequestDetailComponent,
        ErsIdPipe,
        AllRequestsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MDBBootstrapModulesPro.forRoot(),
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        ToastModule.forRoot()
    ],
    providers: [
        LoginService,
        UserGuardService,
        RequestsService,
        RequestDetailService,
        MDBSpinningPreloader,
        {
            provide: UrlSerializer,
            useClass: LowerCaseUrlSerializer
        }
    ],
    bootstrap: [AppComponent, HeaderComponent, LoginComponent, ProfileComponent, RequestsComponent, NewRequestComponent]
})
export class AppModule { }
