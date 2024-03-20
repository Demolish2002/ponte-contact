import { Component } from '@angular/core';
import {ContactListItemModel} from "../../models/contactListItem.model";
import {ContactService} from "../../service/contact.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-phonenumbers',
  templateUrl: './phonenumbers.component.html',
  styleUrls: ['./phonenumbers.component.css']
})
export class PhonenumbersComponent {
  contact!:ContactListItemModel
  contactId!:number
  phoneNumber!:FormGroup;

  constructor(private contactService:ContactService,private router:Router,private route:ActivatedRoute,private formBuilder:FormBuilder) {
    this.phoneNumber=this.formBuilder.group({
      number:['',Validators.required]
    })
    this.route.paramMap.subscribe({
  next:value => {
    this.contactId=Number(value.get("id"))
  }
})
  }



  ngOnInit(): void {
   this.contactService.getPhoneNumbersByContact(this.contactId).subscribe({
     next:value=>{
       this.contact=value
     }
   })
  }

  deletePhoneNumber(i: number) {
    this.contactService.deletePhoneNumberByContactAndId(this.contactId,i).subscribe({
      next:value => {
        this.ngOnInit()
      }
    })
  }

  submitPhoneNumberForm() {
    let data:string=this.phoneNumber.get("number")?.value
    this.contactService.addPhoneNumberByContactId(this.contactId,data).subscribe({
      next:value => {
        this.ngOnInit()
      }
    })
  }
}
