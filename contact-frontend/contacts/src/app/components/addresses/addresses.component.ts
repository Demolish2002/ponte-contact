import {Component, OnInit} from '@angular/core';
import {AddressService} from "../../service/address.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AddressListItemModel} from "../../models/addressListItem.model";

@Component({
  selector: 'app-addresses',
  templateUrl: './addresses.component.html',
  styleUrls: ['./addresses.component.css']
})
export class AddressesComponent implements OnInit{
  addresses!:Array<AddressListItemModel>
  contactId!:number
  constructor(private addressService:AddressService,private router:Router,
              private route:ActivatedRoute) {
    this.route.paramMap.subscribe({
      next:value => {
        this.contactId=Number(value.get('id'))
      }
    })
  }

  ngOnInit(): void {
   this.addressService.getAllAddressesByContactId(this.contactId).subscribe({
     next:value => {
       this.addresses=value
     }
   })
  }

  deleteAddress(addressId: number) {
    this.addressService.deleteAddressById(addressId).subscribe({
      next:value => {
        this.ngOnInit()
      }
    })
  }

  createNewAddress(contactId: number) {
    this.router.navigate(['/address-form',contactId])
  }

  editAddress(addressId: number) {
    this.router.navigate(['/address-form',this.contactId,addressId])
  }
}
