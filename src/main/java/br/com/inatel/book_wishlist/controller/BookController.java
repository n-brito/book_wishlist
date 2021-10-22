package br.com.inatel.book_wishlist.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@RestController 
@RequestMapping("/wishlist/{id}") //id ou name?
public class BookController {
	
//	@Autowired
//	private BookRepository bookRepository;
	@Autowired
	private BookWishlistRepository bookWishlistRepository;
	@Autowired
	private BookWishlistService bookService;
	
	

//	//lists all books in the wishlist	
//	@GetMapping
//	public List<BookDto> listAllBooks() {
//		bookService.findBookWishlistById(bookWishlistRepository.)
//		List<Book> bookList = bookRepository.findAll();
//		return BookDto.buildBookList(bookList);
//	}
//	
//	//adds a new book to the wishlist
//	@PostMapping
//	public ResponseEntity<?> addBookToWishlist(@RequestBody BookForm bookForm, UriComponentsBuilder uriBuilder) {
//		Book book = bookForm.convert(bookRepository);
//		bookRepository.save(book);
//		
//		URI uri = uriBuilder.path("/wishlist/{id}/{isbn13}").buildAndExpand(book.getIsbn13()).toUri();
//		
//		return ResponseEntity.created(uri).body(new BookDto(book));
//	}
	
	
			
}
