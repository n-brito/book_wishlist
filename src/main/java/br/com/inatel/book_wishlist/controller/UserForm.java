package br.com.inatel.book_wishlist.controller;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.model.Profile;
import br.com.inatel.book_wishlist.model.User;

public class UserForm {

	private String id;
	
	@NotBlank(message = "Username must be between 4 to 15 characters and not be blank")
	@Size(min = 4, max = 15)
	private String username;
	
	@NotBlank(message = "Passwork must be between 4 to 15 characters and not be blank")
	@Size(min = 4, max = 15)
	private String password;	
	
	public User createUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

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
	
}
