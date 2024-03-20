import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpRequestInterceptor} from "./utility/httpRequestInterceptor";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { ContactFormComponent } from './components/contact-form/contact-form.component';
import { AddressFormComponent } from './components/address-form/address-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { PhonenumbersComponent } from './components/phonenumbers/phonenumbers.component';
import {ReactiveFormsModule} from "@angular/forms";
import { AddressesComponent } from './components/addresses/addresses.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ContactsComponent,
    ContactFormComponent,
    AddressFormComponent,
    LoginFormComponent,
    RegisterFormComponent,
    PhonenumbersComponent,
    AddressesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true},],
  bootstrap: [AppComponent]
})
export class AppModule { }
