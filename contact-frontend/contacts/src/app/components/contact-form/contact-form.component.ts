import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ContactService} from "../../service/contact.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactFormDataModel} from "../../models/contactFormData.model";
import {validationHandler} from "../../utility/validationHandler";

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit{
contactForm!:FormGroup
  contactId!:number
  constructor(private contactService:ContactService,private router:Router,
              private route:ActivatedRoute,
              private formBuilder:FormBuilder) {

    this.contactForm=this.formBuilder.group({
      name:['',Validators.required],
      mothersMaidenName:['',Validators.required],
      email:[],
      tajNumber:['',Validators.required],
      taxIdentification:['',Validators.required],
      dateOfBirth:['',Validators.required],
      phoneNumber:[],
    })

    this.route.paramMap.subscribe({
      next:value => {
        this.contactId=Number(value.get('id'))
        let id=this.contactId
        console.log(id)
        this.contactService.getPhoneNumbersByContact(id).subscribe({
          next:value1 => {
            this.contactForm.patchValue({
              name:value1.name,
              mothersMaidenName:value1.mothersMaidenName,
              email:value1.email,
              tajNumber:value1.tajNumber,
              taxIdentification:value1.taxIdentification,
              dateOfBirth:value1.dateOfBirth.slice(0,10),
            })
          }
        })
      }
    })
  }

  submitContactForm() {

  if (this.contactId){
    let data:ContactFormDataModel={
      name:this.contactForm.get("name")?.value,
      dateOfBirth:new Date(this.contactForm.get("dateOfBirth")?.value),
      mothersMaidenName:this.contactForm.get("mothersMaidenName")?.value,
      tajNumber:this.contactForm.get("tajNumber")?.value,
      taxIdentification:this.contactForm.get("taxIdentification")?.value,
      email:this.contactForm.get("email")?.value

    }
    this.contactService.updateContactById(this.contactId,data).subscribe({
      next:value=>{
        this.router.navigate(['/contacts'])
      },
      error:err => {
        validationHandler(err,this.contactForm)
      }
    })
  }else{
  let data:ContactFormDataModel={
    name:this.contactForm.get("name")?.value,
    dateOfBirth:new Date(this.contactForm.get("dateOfBirth")?.value),
    mothersMaidenName:this.contactForm.get("mothersMaidenName")?.value,
    tajNumber:this.contactForm.get("tajNumber")?.value,
    taxIdentification:this.contactForm.get("taxIdentification")?.value,
    email:this.contactForm.get("email")?.value

  }
  console.log(data)
    this.contactService.createNewContact(data).subscribe({
      next:value => {
        this.contactService.addPhoneNumberByContactId(value,this.contactForm.get("phoneNumber")?.value).subscribe({
          next:value1 => {
            this.router.navigate(['/contacts'])
          }
        })
      },error:err => {
        validationHandler(err,this.contactForm)
      }
    })}
  }

  ngOnInit(): void {
  }
}
