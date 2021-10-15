package br.com.inatel.book_wishlist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class BookWishlist {

	@Id
	private String id;
	private String name;
	
	//a wishlist belongs to only one user
	@ManyToOne
	private List<User> owner;
	
	//a book can be added to different wishlists by the same user or by different users 
	@ManyToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
	private List<Book> bookList = new ArrayList<>();
	
	public BookWishlist() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
}
