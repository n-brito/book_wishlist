package br.com.inatel.book_wishlist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity 
//@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "id")
public class BookWishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	
	//a wishlist belongs to only one user
	@ManyToOne
	private User owner;
	
	//a book can be added to different wishlists by the same user or by different users 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference //avoiding infinite recursion
	@JoinColumn(name = "wishlist_id")
	private List<Book> bookList = new ArrayList<>();
	
	
	public BookWishlist() {
//		this.id = UUID.randomUUID().toString();
	}
	
	public BookWishlist(String name) {
//		this.id = UUID.randomUUID().toString();
		this.name = name;
	}
	
//	public BookWishlist(String name, List<Book> bookList) {
////		this.id = UUID.randomUUID().toString();
//		this.name = name;
//		this.bookList = bookList;
//	}

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
