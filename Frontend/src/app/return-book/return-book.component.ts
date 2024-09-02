import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Book } from '../book';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  returnBookForm: FormGroup;
  message: string | null = null;
  book: Book = new Book();

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private bookService: BookService,
    private fb: FormBuilder
  ) {
    this.returnBookForm = this.fb.group({
      id: [''],
      username: ['']
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const getId = parseInt(id, 10);
      if (!isNaN(getId)) {
        this.bookService.getBook(getId).subscribe(
          (res) => {
            this.book = res;
            this.returnBookForm.patchValue({
              id: this.book.id, // Ensure id is included
              username: '' // You can set the username here if needed
            });
          },
          error => {
            alert("Error fetching book");
          }
        );
      } else {
        alert("Invalid book id");
      }
    }
  }

  onSubmit() {
    const returnBookData = this.returnBookForm.value;
    console.log('Submitting return book data:', returnBookData); // Log form data
  
    this.bookService.returnBook(returnBookData).subscribe(
      response => {
        console.log('Response:', response);
        alert('Book returned successfully!!');
      },
      error => {
        console.error('Error:', error);
        if (error.status === 400) {
          alert('Bad Request. Please check the input.');
        } else if (error.status === 404) {
          alert('Book not found. Please check the Book ID.');
        } else {
          alert('An unexpected error occurred: ' + error.message);
        }
      }
    );
  }
}
