package br.com.inatel.book_wishlist.controller;

import java.util.HashMap;
import java.util.Map;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;

public class BookWishlistDto {

	private String id;
	private String name;
	private Map<String, Book> books = new HashMap<>();
	
	public BookWishlistDto(BookWishlist wishlist) {
		this.id = wishlist.getId();
		this.name = wishlist.getName();	
		
		books.forEach((id, book) -> {
			this.books.put(book.getId().toString(), book);
		});		
		
	}
	
	public Map<String, Book> getBooks() {
		return books;
	}
	
}
