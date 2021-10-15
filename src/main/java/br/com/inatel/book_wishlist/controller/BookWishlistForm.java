package br.com.inatel.book_wishlist.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;



public class BookWishlistForm {

	private String name;
	
	private Map<String, Book> books;

	public String getName() {
		return name;
	}

	public Map<String, Book> getBooksList() {
		return books;
	}

	public List<Book> generateBooksList(BookWishlist wishlist) {
		List<Book> books = new ArrayList<>();
		
		for (Map.Entry<String, Book> entry : this.books.entrySet()) {
			Book book = new Book();
			book = entry.getValue();
			books.add(book);
		}
		
		//validation here?
		
		return books;
	}
	
}
