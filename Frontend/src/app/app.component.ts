import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'LibraryManagement';

  sideBarOpen = true;
  ngOnInit(): void{}
  sidebarToggler(){
    this.sideBarOpen=!this.sideBarOpen;
  }
}
