package br.com.inatel.book_wishlist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	private String isbn13;
	
	private String title;
	private String subtitle;
	private String authors;
	private String year;
	private String price;
	private String url;
	
	@ManyToOne
	private BookWishlist wishlist;
	
	public Book(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	public Book() {}

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

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
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

	public BookWishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(BookWishlist wishlist) {
		this.wishlist = wishlist;
	}

}
