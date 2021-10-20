package br.com.inatel.book_wishlist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

//@Data
@Entity	
//@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "isbn13")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String isbn13;
	@Column(name = "wishlist_id")
	private String parentWishlist;
	
//	private String title;
//	private String subtitle;
//	private String authors;
//	private String year;
//	private String price;
//	private String url; //pode ser tipo "${it-bookstore.url}" + "isbn13" ? 
						//url da api ta defasada
	
//	@ManyToOne
//	@JsonBackReference //avoiding infinite recursion
//	private BookWishlist wishlist;
	
//	@JsonIgnore
//	private String parentWishlistName = wishlist.getName();
//	private List<String> parentWishlists;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentWishlist() {
		return parentWishlist;
	}

	public void setParentWishlist(String parentWishlist) {
		this.parentWishlist = parentWishlist;
	}

	public Book(String isbn13) {
		
		this.isbn13 = isbn13;
	}
	
//	public Book(String isbn13, String parentWishlist) {
////		this.parentWishlist = this.wishlist.getName();
//		this.parentWishlist = parentWishlist;
//	}
	
	public Book() {}
	
//	public String getParentWishlistName() {
//		parentWishlistName = this.wishlist.getName();
//		return parentWishlistName;
//	}
	
//	public void setParentWishlistName() {
//		this.parentWishlistName = this.wishlist.getName();
//	}



	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	

//	public BookWishlist getWishlist() {
//		return wishlist;
//	}
//
//	public void setWishlist(BookWishlist wishlist) {
//		this.wishlist = wishlist;
//	}

}
