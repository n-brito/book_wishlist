package br.com.inatel.book_wishlist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

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
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String isbn13;
	
	@Column(name = "wishlist_id")
	private String parentWishlist;
	
	private String title;
	private String subtitle;
	private String authors;
	private String year;
	private String price;
	private String url; 
	
	
	
	public Book(String isbn13) {
		
		this.isbn13 = isbn13;
	}
	
	
	public Book() {}


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


	public String getParentWishlist() {
		return parentWishlist;
	}


	public void setParentWishlist(String parentWishlist) {
		this.parentWishlist = parentWishlist;
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
