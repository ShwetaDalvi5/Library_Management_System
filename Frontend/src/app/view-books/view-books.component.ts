// src/app/view-books/view-books.component.ts

import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Book } from '../book';
import { HttpClient } from '@angular/common/http';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-view-books',
  templateUrl: './view-books.component.html',
  styleUrls: ['./view-books.component.css']
})
export class ViewBooksComponent implements OnInit {

  isUser: boolean = false;
  books: any[] = [];

  book: Book = new Book();

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/api/books/allBooks').subscribe(data => {
      this.books = data;
      console.log(data);
    });

    this.router.events.subscribe(()=>{
    this.isUser =this.router.url.includes('user-dashboard');
    } )
  }

  getBook(book:any){
    console.log(book.id);
    this.router.navigate(['issue-book', book.id]);
  }

  
}
