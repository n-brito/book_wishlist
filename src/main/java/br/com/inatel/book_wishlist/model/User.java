package br.com.inatel.book_wishlist.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String username;
	private String password;
	
	@ManyToOne
	private List<BookWishlist> listOfWishlists;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BookWishlist> getListOfWishlists() {
		return listOfWishlists;
	}

	public void setListOfWishlists(List<BookWishlist> listOfWishlists) {
		this.listOfWishlists = listOfWishlists;
	}

	public User() {
		this.id = UUID.randomUUID().toString();
	}

}
