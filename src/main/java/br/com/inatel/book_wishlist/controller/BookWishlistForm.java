package br.com.inatel.book_wishlist.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;



public class BookWishlistForm {

	private String name;
	
	private List<BookForm> books;

	public String getName() {
		return name;
	}
	
//	public String generateName(BookWishlist wishlist) { 
//		name = wishlist.getName();
//		return name;
//	}
//
//	public List<Book> generateBooksList(BookWishlist wishlist) {
//		books = new ArrayList<>();
//		books = wishlist.getBookList();
//		
//		//validation here?
//		
//		return books;
//	}

	public BookWishlist convert() {
		
		//validation: no  wishlists w/ the same name
		
		BookWishlist wishlist = new BookWishlist();
		wishlist.setName(name);
				
		if (books != null) {	
			List<Book> bookList = books.stream().map(b -> b.convertToBook()).collect(Collectors.toList());
			wishlist.setBookList(bookList);
		}		
		
		return wishlist;
	}

	public List<BookForm> getBooks() {
		return books;
	}

	public void setBooks(List<BookForm> books) {
		this.books = books;
	}
	
}
