import { Component } from '@angular/core';
import { UserModel } from '../user-model';
import { UserService } from '../user.service';
import { LoginResponse } from '../login-response';
import { Login } from '../login';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService : UserService, private route: Router, private authService: AuthService){}

  username!: string;
  password!: string;

  loginResponse: LoginResponse = new LoginResponse;

  loginMethod() {
    console.log("usename: ", this.username);
    console.log("password: ", this.password);
    this.userService.login(this.username, this.password).subscribe((res:any)=>{
      this.loginResponse =res;
      console.log(res);
      if(res.message == "Login success")
      {
        alert("login successfull!")
        this.authService.login('user');
        this.route.navigate(['user-dashboard']);
      }
      else if(res.message == "login falied")
      alert("login failed");
      else if(res.message == "Incorrect password")
      alert("Incorrect password");
      else
      alert("Username not found");
    });
  } 
}