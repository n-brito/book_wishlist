package br.com.inatel.book_wishlist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;

public class BookDto {
	
	private String id;
	private String isbn13;
	private String title;
	private String subtitle;
	private String authors;
	private String year;
	private String price;
	
	@Autowired
	@Value("${it-bookstore.url}")
	private String url = "https://api.itbook.store/1.0"; 
	
	
	public BookDto(Book book) {		//esse q ta sendo chamado p/ print
		this.id = book.getId();
		this.isbn13 = book.getIsbn13();
		this.title = book.getTitle();
		this.subtitle = book.getSubtitle();
		this.authors = book.getAuthors();
		this.year = book.getYear();
		this.price = book.getPrice();
		this.url = url + "/books/" + book.getIsbn13();
	}
	
	public BookDto(BookForm book) {
		this.id = book.getId();
		this.isbn13 = book.getIsbn13();
		this.title = book.getTitle();
		this.subtitle = book.getSubtitle();
		this.authors = book.getAuthors();
		this.year = book.getYear();
		this.price = book.getPrice();
		this.url = url + "/books/" + book.getIsbn13();
	}
	
	public List<BookDto> buildBookList(List<Book> list) {
		return list.stream().map(BookDto::new).collect(Collectors.toList());
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

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getAuthors() {
		return authors;
	}

	public String getYear() {
		return year;
	}

	public String getPrice() {
		return price;
	}

	public String getUrl() {
		return url;
	}

}
