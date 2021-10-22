package br.com.inatel.book_wishlist.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
	
	
}
