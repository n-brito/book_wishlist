package br.com.inatel.book_wishlist.controller;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.repository.BookRepository;

public class BookForm {
	
	private String id;
	private String isbn13;
	
	private String title;
	private String subtitle;
	private String authors;
	private String year;
	private String price;
	private String url;
	
	
	public Book convertToBook() {
		
		Book book = new Book(isbn13);
//		book.setAuthors(authors);
//		book.setTitle(title);
//		book.setSubtitle(subtitle);
//		book.setAuthors(authors);
//		book.setYear(year);
//		book.setPrice(price);
//		book.setUrl(url);
		return book;
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getIsbn13() {
		return isbn13;
	}


	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
