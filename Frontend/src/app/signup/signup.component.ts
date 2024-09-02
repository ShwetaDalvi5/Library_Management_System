import { Component } from '@angular/core';
import { UserModel } from '../user-model';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  result!: UserModel[];

  constructor(private userService  : UserService, private router: Router){}

  userModel : UserModel = new UserModel;

  signupMethod()
  {
    console.log(this.userModel);
    this.userService.signup(this.userModel).subscribe((res)=>{
      this.result= res;
      alert("Registration successful!!!");
         this.router.navigate(["login"]);
    });
  }

}
