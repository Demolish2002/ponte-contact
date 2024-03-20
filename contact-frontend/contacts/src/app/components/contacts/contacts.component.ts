import {Component, OnInit} from '@angular/core';
import {ContactListItemModel} from "../../models/contactListItem.model";

import {ContactService} from "../../service/contact.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit{
  Contacts!:Array<ContactListItemModel>


  constructor(private contactService:ContactService,private router:Router) {

  }

  deleteContact(id: number) {
    this.contactService.deleteContactInfosById(id).subscribe({
      next:value => {
        this.ngOnInit()
      }
    })
  }

  updateContact(id: number) {
    this.router.navigate(['/contact-form',id])
  }

  ngOnInit(): void {
    this.contactService.getAllContactsByUser().subscribe({
      next:value => {
        console.log(value)
        this.Contacts=value
      }
    })
  }

  goToAddress(id: number) {
    this.router.navigate(['/address-list',id])
  }

  goToPhoneNumber(id: number) {
    this.router.navigate(['/phoneNumber-list',id])
  }
}
