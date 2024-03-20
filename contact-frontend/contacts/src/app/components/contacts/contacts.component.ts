import { Component } from '@angular/core';
import {ContactListItemModel} from "../../models/contactListItem.model";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent {
  Contacts!:Array<ContactListItemModel>
}
