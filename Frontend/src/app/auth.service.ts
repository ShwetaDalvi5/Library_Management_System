import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private route : Router) { }

  private loggedIn = new BehaviorSubject<boolean>(false);
  private isAdmin = new BehaviorSubject<boolean>(false);

  login(role : 'user' | 'admin' ){
    this.loggedIn.next(true); // Update the loggedIn status to true
    if(role === 'admin'){
      this.isAdmin.next(true);
    }
    else{
      this.isAdmin.next(false);
    }
  }

  logout(){
    this.loggedIn.next(false);
    this.isAdmin.next(false);
  }

  isLoggedIn(): Observable <boolean>{
      return this.loggedIn.asObservable();
  }

  isAdminUser(): Observable<boolean>{
    return this.isAdmin.asObservable();
  }
}
