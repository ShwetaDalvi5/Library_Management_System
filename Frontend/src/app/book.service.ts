import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/view-books`);
  }

  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }

  addBook(book: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, book);
  }

  updateBook(id: number, book: FormData): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, book);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }

  issueBook(issueData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/issue`, issueData);
  }

  // returnBook(returnData: any): Observable<any> {
  //   return this.http.post(`${this.baseUrl}/return-book`, returnData);
  // }

  returnBook(returnData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/return-book`, returnData, { responseType: 'text' });
}


  
}
