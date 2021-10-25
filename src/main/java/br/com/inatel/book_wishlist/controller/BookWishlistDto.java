package br.com.inatel.book_wishlist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;

public class BookWishlistDto {

	private String id;
	private String name;
	
	private List<BookDto> books = new ArrayList<>();
	
	public BookWishlistDto(BookWishlist wishlist, List<BookDto> bookDtoList) {
		this.id = wishlist.getId();
		this.name = wishlist.getName();	
		this.books = bookDtoList;
//		 if (wishlist.getBookList() != null) {
//			 this.books = wishlist.getBookList().stream()
//			.map(BookDto::new).collect(Collectors.toList());
//		}
		
	}
	
	public BookWishlistDto(BookWishlist wishlist) {
		this.id = wishlist.getId();
		this.name = wishlist.getName();	
		 if (wishlist.getBookList() != null) {
			 this.books = wishlist.getBookList().stream()
			.map(BookDto::new).collect(Collectors.toList());
		}		
	}
	
	
	public List<BookDto> getBooks() {
		return books;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

//	public static Page<BookWishlistDto> buildWishlists(Page<BookWishlist> wishlist) {
//		
////		return wishlist.stream().map(BookWishlistDto::new).collect(Collectors.toList());
//		return wishlist.map(BookWishlistDto::new);
//	}
	
}
