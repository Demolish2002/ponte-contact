import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {RegisterFormDataModel} from "../models/registerFormData.model";
const BASE_URL:string ="http://localhost:8080/api/users"



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
    authenticate(credentials: { username: string, password: string }): Observable<any> {
        const headers = new HttpHeaders(credentials
            ? {authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)}
            : {});
        return this.http.get(BASE_URL + '/me', {headers: headers});
    }
    saveNewUser(data: RegisterFormDataModel) {
        return this.http.post(BASE_URL,data)
    }
}
