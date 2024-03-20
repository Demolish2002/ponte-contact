import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {validationHandler} from "../../utility/validationHandler";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {LoginFormModel} from "../../models/loginForm.model";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
loginForm!:FormGroup
  invalidLogin: boolean=false;


  constructor(private userService:UserService,private router:Router,private formBuilder:FormBuilder) {
    this.loginForm = this.formBuilder.group({
      username:['', Validators.required],
      password:['', Validators.required]
    });
  }

  submitLoginForm() {
    const data:LoginFormModel = {...this.loginForm.value};
    console.log(data)
    this.userService.login(data).subscribe(
        response => {
          this.invalidLogin=false
          localStorage.setItem('user', JSON.stringify(response));
          this.router.navigate(['/contacts']);
        },
        error => {
          this.invalidLogin=true
          validationHandler(error, this.loginForm);
        });

    return false;
  }
}
