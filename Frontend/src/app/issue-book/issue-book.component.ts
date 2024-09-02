import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Book } from '../book';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-issue-book',
  templateUrl: './issue-book.component.html',
  styleUrls: ['./issue-book.component.css']
})
export class IssueBookComponent {

  issueBookForm: FormGroup;
  message: string | null = null;
  book: Book = new Book();

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private bookService: BookService,
    private fb: FormBuilder
  ) {
    this.issueBookForm = this.fb.group({
      id: [''],
      title: [''],
      copiesAvailable: [''],
      username: [''],
      issueDate: [''],
      returnDate: ['']
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
            this.issueBookForm.patchValue({
              id: this.book.id, // Ensure id is included
              title: this.book.title,
              copiesAvailable: this.book.copiesAvailable
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
    const issueBookData = this.issueBookForm.value;
    console.log('Submitting issue book data:', issueBookData); // Log form data
  
    this.bookService.issueBook(issueBookData).subscribe(
      response => {
        console.log('Response:', response);
        alert('Book borrowed successfully!!');
      },
      error => {
        console.error('Error:', error);
        if (error.status === 500 && error.error.includes('No copies available for this book')) {
          alert('No copies available for this book.');
        } else {
          alert('Successfull!!!');
        }
      }
    );
  }
  
  
}
