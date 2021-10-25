package br.com.inatel.book_wishlist.controller;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookService;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@RestController 
@RequestMapping("/wishlist")
public class BookWishlistController {
	
//	@Autowired
//	private BookRepository bookRepository;
//	@Autowired
//	private BookWishlistRepository bookWishlistRepository;
	@Autowired
	private BookWishlistService bookWishlistService;
	@Autowired
	private BookService bookService;
	
	private JSONObject books;
	private JSONObject body;

	//lists all wishlists 	
	@GetMapping
	@Cacheable(value = "listOfWishlists")
	public Page<BookWishlistDto> listAllWishlists(@RequestParam int page, Pageable pagination) {
//			@RequestParam int itemsPerPage, @RequestParam String listingOrder) {
			
		
//		Pageable pagination = PageRequest.of(page, itemsPerPage, Direction.ASC, listingOrder);
		
		Page<BookWishlist> listOfWishlists = bookWishlistService.findBookWishlist(pagination);
			
		List<BookWishlist> list = listOfWishlists.getContent();
		
		Map<BookWishlist, List<BookDto>> booksMap = new HashMap<>();
		
		list.forEach(l -> {
			List<BookDto> bookDto = l.getBookList().stream()
					.map(b-> {
						BookForm form = bookService.findBook(b.getIsbn13());
						form.setId(b.getId());
						return new BookDto(form);
					})
					.collect(Collectors.toList());
			booksMap.put(l, bookDto);			
		});		
		
		return listOfWishlists.map(w -> new BookWishlistDto(w, booksMap.get(w)));
	}
	
	//shows a specific wishlist by id
	@GetMapping("/{id}")
	public BookWishlistDto showWishlistById(@PathVariable String id) {
		BookWishlist wishlist = bookWishlistService.findBookWishlistById(id);
		List<BookDto> bookDto = wishlist.getBookList().stream()
			.map(b-> {
				BookForm form = bookService.findBook(b.getIsbn13());
				form.setId(b.getId());
				return new BookDto(form);
			})
			.collect(Collectors.toList());
		
		return new BookWishlistDto(wishlist, bookDto);
	}
	
	//creates a new wishlist
	@PostMapping
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> createWishlist(@RequestBody BookWishlistForm wishlistForm, UriComponentsBuilder uriBuilder) {
		BookWishlist wishlist = wishlistForm.convert();
		bookWishlistService.createWishlist(wishlist);
		
		List<BookDto> bookDto = wishlist.getBookList().stream()
				.map(b-> {
					BookForm form = bookService.findBook(b.getIsbn13());
					form.setId(b.getId());
					return new BookDto(form);
				})
				.collect(Collectors.toList());
		
		URI uri = uriBuilder.path("/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new BookWishlistDto(wishlist, bookDto));
	}
		
	
	@PostMapping("/{id}/book/{isbn13}")
	public ResponseEntity<?> addBookToWishlist(@PathVariable(name = "id") String wishlistId, @PathVariable String isbn13, UriComponentsBuilder uriBuilder) {
		BookForm bookForm = bookService.findBook(isbn13);
		Book book = bookForm.convertToBook();
		BookWishlist wishlist = bookWishlistService.addBookToWishlist(wishlistId, book);
		
		URI uri = uriBuilder.path("/{id}/book/{isbn13}").buildAndExpand(wishlist.getId(), isbn13).toUri();
		
		return ResponseEntity.created(uri).body(new BookWishlistDto(wishlist));
			
	}
			
}
