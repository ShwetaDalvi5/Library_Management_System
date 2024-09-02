import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-book',
  templateUrl: './delete-book.component.html',
  styleUrls: ['./delete-book.component.css']
})
export class DeleteBookComponent {
  bookId: number = 0;

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.delete(`http://localhost:8080/api/books/delete/${this.bookId}`, { responseType: 'text' }).subscribe(
  response => {
    console.log(response);
    alert('Book deleted successfully!');
  },
  error => {
    console.error('Error deleting book', error);
    alert('An error occurred while deleting the book.');
  }
);

  }
}
