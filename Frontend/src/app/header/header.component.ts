import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent  implements OnInit{
  @Output() toggleSidebarForMe: EventEmitter<any> = new EventEmitter();
  loggedIn = false;
  isAdmin = false;

  constructor(private authService: AuthService, private roter: Router){}

  ngOnInit(): void {
    this.authService.isLoggedIn().subscribe(status => {
      this.loggedIn = status;
    });

    this.authService.isAdminUser().subscribe(status => {
      this.isAdmin = status;
    }) 
  }

  logout()
  {
    this.authService.logout();
  }

  toggleSidebar(){
    this.toggleSidebarForMe.emit();
  }

}
