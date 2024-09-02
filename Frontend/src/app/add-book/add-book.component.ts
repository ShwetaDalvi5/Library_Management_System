import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {

  selectedFile: File | null = null; 

  bookDto = {
    title: '',
    author: '',
    language: '',
    publisher: '',
    discription: '',
    copiesAvailable: 1  

  };

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {}

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    } else {
      this.selectedFile = null; // Handle case where no file is selected
    }
  }

  onSubmit() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('bookDto', JSON.stringify(this.bookDto));
      formData.append('image', this.selectedFile, this.selectedFile.name);

      this.http.post(`${this.baseUrl}/api/books/add`, formData).subscribe(response => {
        console.log('Book added', response);
        alert("Book added successfully!!!");
      }, error => {
        console.error('Error adding book', error);
      });
    } else {
      console.error('No file selected');
    }
  }
}
