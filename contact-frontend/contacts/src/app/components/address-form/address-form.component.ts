import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddressService} from "../../service/address.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AddressFormDataModel} from "../../models/addressFormData.model";


@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent {
  adressForm!:FormGroup
  contactId!:number
  addressId!:number
  constructor(private addressService:AddressService,private formBuilder:FormBuilder,private router:Router,private route:ActivatedRoute) {
    this.adressForm=this.formBuilder.group({
      postalCode:['',Validators.required],
      city:['',Validators.required],
      street:['',Validators.required],
      houseNumber:['',Validators.required]
    })
    this.route.paramMap.subscribe({
      next:value => {
        this.contactId=Number(value.get("id"))
        this.addressId=Number(value.get("addressId"))
        if (this.addressId!==0){
          this.addressService.getAddressById(this.addressId).subscribe({
            next:value1 => {
              this.adressForm.patchValue({
                postalCode:value1.postalCode,
                city:value1.city,
                street:value1.city,
                houseNumber:value1.houseNumber
              })
            }
          })
        }
      }
    })
  }

  submitaddressForm() {
    if(this.addressId!==0){
      let data:AddressFormDataModel={
        contactId:this.contactId,
        postalCode:this.adressForm.get("postalCode")?.value,
        city:this.adressForm.get("city")?.value,
        street:this.adressForm.get("street")?.value,
        houseNumber:this.adressForm.get("houseNumber")?.value
      }
      this.addressService.updateAddress(data,this.addressId).subscribe({next:value => {
          this.router.navigate(['/address-list',this.contactId])
        }})
    }else {
    let data:AddressFormDataModel={
      contactId:this.contactId,
      postalCode:this.adressForm.get("postalCode")?.value,
      city:this.adressForm.get("city")?.value,
      street:this.adressForm.get("street")?.value,
      houseNumber:this.adressForm.get("houseNumber")?.value
    }

    this.addressService.createAddress(data).subscribe({
      next:value => {this.router.navigate(['/address-list',this.contactId])}
    })
    }
  }


}
