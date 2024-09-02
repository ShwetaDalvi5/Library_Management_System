import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { FooterComponent } from './footer/footer.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AddBookComponent } from './add-book/add-book.component';
import { DeleteBookComponent } from './delete-book/delete-book.component';
import { ViewBooksComponent } from './view-books/view-books.component';
import { IssueBookComponent } from './issue-book/issue-book.component';
import { ReturnBookComponent } from './return-book/return-book.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path : 'signup', component : SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'home', component: HomeComponent},
  { path: 'admin', component: AdminLoginComponent },
  {path: 'admin-dashboard', component: AdminDashboardComponent},
  {path: 'footer', component: FooterComponent},
  {path : 'user-dashboard', component: UserDashboardComponent},
  {path : 'add-book', component : AddBookComponent},
  {path: 'delete-book/:id', component: DeleteBookComponent },
  {path: 'view-books', component: ViewBooksComponent },
  {path: 'issue-book/:id', component: IssueBookComponent },
  {path: 'return-book', component: ReturnBookComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
