package com.library.model;

public class Book {
	public int bookId;
	public String title, author;
	public int available;

	public Book(int bookId, String title, String author, int available) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.available = available;
	}
}
