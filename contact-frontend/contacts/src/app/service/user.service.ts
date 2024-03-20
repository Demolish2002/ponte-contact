import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {RegisterFormDataModel} from "../models/registerFormData.model";
import {UserDetailsModel} from "../models/userDetails.model";
import {LoginFormModel} from "../models/loginForm.model";
const BASE_URL:string ="http://localhost:8080/api/users"



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
    login(credentials: LoginFormModel): Observable<UserDetailsModel> {
        const headers = new HttpHeaders(credentials
            ? {authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)}
            : {});
        return this.http.get<UserDetailsModel>(BASE_URL+"/me", {headers: headers});
    }
    saveNewUser(data: RegisterFormDataModel) {
        return this.http.post(BASE_URL,data)
    }
}
