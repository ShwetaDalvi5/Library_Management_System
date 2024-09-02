import { Component } from '@angular/core';
import { UserModel } from '../user-model';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {

  user: UserModel = new UserModel();

  constructor(private userService: UserService, private router: Router, private authService: AuthService) { }

  adminLogin() {
    this.userService.adminLogin(this.user).subscribe(
      response => {
        if (response && response.status === true) {
          // Handle successful login
          alert("Login Successful!!!");
          this.authService.login('admin');
          this.router.navigate(['admin-dashboard']); // Navigate to admin dashboard or relevant component
        } else {
          // Handle login failure
          alert('Login failed');
        }
      },
      error => {
        console.error(error);
        alert('An error occurred');
      }
    );
  }

}
