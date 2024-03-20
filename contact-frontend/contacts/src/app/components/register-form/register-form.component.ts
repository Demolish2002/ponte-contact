import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators,} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {RegisterFormDataModel} from "../../models/registerFormData.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
registerForm!:FormGroup

  constructor(private formBuilder:FormBuilder,private userService:UserService,private router:Router) {
    this.registerForm = this.formBuilder.group({
      username:['', Validators.required],
      password:['',[Validators.required,this.customPasswordValidator]],
    });
  }

  submitRegisterForm() {
    let data:RegisterFormDataModel=this.registerForm.value
    this.userService.saveNewUser(data).subscribe({
      next:value => {
        this.router.navigate(["/login"])
      }
    })
  }

  customPasswordValidator(control: FormControl): { tooWeak: boolean } | null {
    let result = null;
    if (control.value) {
      let selectedValue:string=control.value
      let  upper = /[A-Z]/.test(selectedValue)
      let containsNumber=/\d+/g.test(selectedValue)
      if(!(upper && containsNumber)|| selectedValue.length<8){
        result = {tooWeak: true};
      }
    }
    return result;
  }
}
