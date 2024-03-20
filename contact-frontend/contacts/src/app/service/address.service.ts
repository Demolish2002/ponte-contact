import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddressFormDataModel} from "../models/addressFormData.model";
import {Observable} from "rxjs";
import {AddressListItemModel} from "../models/addressListItem.model";
const BASE_URL:string ="http://localhost:8080/api/addresses"
@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http:HttpClient) { }
  createAddress(data:AddressFormDataModel){
    return this.http.post(BASE_URL,data)
  }
  updateAddress(data:AddressFormDataModel,id:number){
    return this.http.put(BASE_URL+"/"+id,data)
  }
  deleteAddressById(id:number){
    return this.http.delete(BASE_URL+"/"+id)
  }

  getAllAddressesByContactId(id:number):Observable<Array<AddressListItemModel>>{
    return this.http.get<Array<AddressListItemModel>>(BASE_URL+"/"+id)
}

  getAddressById(addressId: number) {
    return this.http.get<AddressListItemModel>(BASE_URL+"/forUpdate/"+addressId)
  }
}
