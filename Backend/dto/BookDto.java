package com.library.dto;

import java.time.LocalDate;

public class BookDto {
	
	private Long id;
    private String title;
    private String author;
    private String publisher;
    private String language;
    private String discription;
    private int totalCopies; 
	private int copiesAvailable;

    public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}
	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	

	public BookDto(Long id, String title, String author, String publisher, String language, String discription,
			boolean isBorrowed, int totalCopies, int copiesAvailable) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.language = language;
		this.discription = discription;
		this.totalCopies = totalCopies;
		this.copiesAvailable = copiesAvailable;
	}
	
	@Override
	public String toString() {
		return "BookDto [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", language=" + language + ", discription=" + discription + ", totalCopies=" + totalCopies
				+ ", copiesAvailable=" + copiesAvailable + "]";
	}
	
	
	
	
	
	
}
