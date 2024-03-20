import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ContactFormDataModel} from "../models/contactFormData.model";
import {ContactListItemModel} from "../models/contactListItem.model";
const BASE_URL:string ="http://localhost:8080/api/contacts"
@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http:HttpClient) { }
  createNewContact(data:ContactFormDataModel){
    return this.http.post<number>(BASE_URL,data)
  }

  deleteContactInfosById(id:number){
    return this.http.delete(BASE_URL+"/"+id)
  }
  getAllContactsByUser(){
    return this.http.get<Array<ContactListItemModel>>(BASE_URL)
  }
  deletePhoneNumberByContactAndId(contactId:number,phoneNumberId:number){
    return this.http.delete(BASE_URL+"/"+contactId+"/phoneNumber/"+phoneNumberId)
  }
  addPhoneNumberByContactId(id:number,phoneNumber:string){
    return this.http.post(BASE_URL+"/"+id+"/phoneNumber",phoneNumber)
  }

  getPhoneNumbersByContact(contactId: number) {
    return this.http.get<ContactListItemModel>(BASE_URL+"/"+contactId)
  }

  updateContactById(contactId: number, data: ContactFormDataModel) {
    return this.http.put(BASE_URL+"/"+contactId,data)
  }
}
