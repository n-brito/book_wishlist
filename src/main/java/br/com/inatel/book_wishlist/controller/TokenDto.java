package br.com.inatel.book_wishlist.controller;

public class TokenDto {

	private String token;
	private String type;

	public TokenDto(String token, String type) {
		this.token = token;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
