import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {RegisterFormComponent} from "./components/register-form/register-form.component";
import {ContactsComponent} from "./components/contacts/contacts.component";
import {AddressFormComponent} from "./components/address-form/address-form.component";
import {ContactFormComponent} from "./components/contact-form/contact-form.component";
import {AddressesComponent} from "./components/addresses/addresses.component";
import {PhonenumbersComponent} from "./components/phonenumbers/phonenumbers.component";

const routes: Routes = [
  {path:"" ,component:LoginFormComponent},
  {path:"login", component:LoginFormComponent} ,
  {path:"register", component:RegisterFormComponent} ,
  {path:"contacts", component:ContactsComponent} ,
  {path:"address-form/:id", component:AddressFormComponent} ,
  {path:"address-form/:id/:addressId", component:AddressFormComponent} ,
  {path:"contact-form", component:ContactFormComponent} ,
  {path:"contact-form/:id", component:ContactFormComponent} ,
  {path:"address-list/:id", component:AddressesComponent} ,
  {path:"phoneNumber-list/:id", component:PhonenumbersComponent} ,

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
