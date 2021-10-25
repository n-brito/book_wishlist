package br.com.inatel.book_wishlist.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookService;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@RestController 
@RequestMapping("/wishlist/{id}") //id ou name?
public class BookController {
	
//	@Autowired
//	private BookRepository bookRepository;
	@Autowired
	private BookWishlistRepository bookWishlistRepository;
	@Autowired
	private BookService bookService;
	
//	@PostMapping //("/{isbn13}")
//	public ResponseEntity<?> addBookToWishlist(@RequestBody String isbn13, @RequestBody String wishlistId, UriComponentsBuilder uriBuilder) {
//		
//		BookForm bookForm = bookService.findBook(isbn13);
//		Book book = bookForm.convertToBook();
//		BookWishlist wishlist = bookWishlistRepository.findById(wishlistId);
////		BookWishlist wishlist = bookWishlistService.findBookWishlistById(wishlistId);
////		wishlist.setBookList(wishlist.getBookList().add(book));
//		List<Book> list = wishlist.getBookList();
//		list.add(book);
//		wishlist.setBookList(list);
//		
//		List<BookDto> bookDto = wishlist.getBookList().stream()
//				.map(b-> {
//					BookForm form = bookService.findBook(b.getIsbn13());
//					form.setId(b.getId());
//					return new BookDto(form);
//				})
//				.collect(Collectors.toList());
//			
////			return new BookWishlistDto(wishlist, bookDto);
//		
//		URI uri = uriBuilder.path("/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();
//		
////		URI uri = uriBuilder.path("/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();
////		URI uri2 = uriBuilder.path(uri.getPath() + "/{isbn13}").buildAndExpand(isbn13).toUri();
////							.buildAndExpand(wishlist.getBookList()
////							.get(wishlist.getBookList().size()-1).getIsbn13()).toUri();
//		
//		return ResponseEntity.created(uri).body(new BookWishlistDto(wishlist, bookDto));
//	}
	

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
