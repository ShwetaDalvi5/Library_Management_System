import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from './user-model';
import { Observable } from 'rxjs';
import { LoginResponse } from './login-response';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  signup(user : UserModel): Observable<UserModel[]>
  {
    return this.http.post<UserModel[]>(`${this.baseUrl}/signup`, user);
  }

  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, { username, password });
  }


  adminLogin(user: UserModel): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/admin/login`, user);
  }

}

export class Login {
  username!: string;
  password!: string;
}
