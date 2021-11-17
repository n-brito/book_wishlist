package br.com.inatel.book_wishlist.controller;

import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookService;
import br.com.inatel.book_wishlist.service.BookWishlistService;
import br.com.inatel.book_wishlist.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/wishlist")
@Slf4j
public class BookWishlistController {
	
	@Autowired
	private BookWishlistService bookWishlistService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Value("${spring.datasource.url}")
	private String connectionUrl = "jdbc:mysql://${MYSQL_CONTAINER}:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true";
	
	Logger log = LoggerFactory.getLogger(BookWishlistController.class);

	//lists all wishlists 	
	@GetMapping
	@Cacheable(value = "listOfWishlists")
	public Page<BookWishlistDto> listAllWishlists(@PageableDefault(page = 0, size = 10) Pageable pagination){ //@RequestParam int page, 
		
//		try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "root")){
//			if (conn.isValid(0))
//			System.out.println("mysql running");
//		} catch (Exception e) {
//			throw new ModelException("mysql", "No connection found with MySQL database");
//		}
		
				
		if (pagination.getPageSize() > 20) {
			throw new ModelException("size", "Maximum number of items allowed per page is 20"); 
		}
		
		User authUser = userService.findUserByUsername(getAutehticatedUser().getUsername());
		
		Page<BookWishlist> listOfWishlists = bookWishlistService.findBookWishlist(pagination, authUser);
		System.out.println("lists of: " + authUser.getUsername());
		
		log.info("Listing all wishlists from user: " + authUser.getUsername());	
		
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
		
		log.info("Listing all books from wishlist with id: " + id);
		
		return new BookWishlistDto(wishlist, bookDto);
	}
	
	//creates a new wishlist
	@PostMapping
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> createWishlist(@RequestBody BookWishlistForm wishlistForm, UriComponentsBuilder uriBuilder) {
		
		BookWishlist wishlist = wishlistForm.convert();
		
		wishlist.setOwner(getAutehticatedUser());
						
		bookWishlistService.createWishlist(wishlist);
		
		List<BookDto> bookDto = wishlist.getBookList().stream()
				.map(b-> {
					BookForm form = bookService.findBook(b.getIsbn13());
					form.setId(b.getId());
					return new BookDto(form);
				})
				.collect(Collectors.toList());
		
		URI uri = uriBuilder.path("/wishlist/{id}").buildAndExpand(wishlist.getId()).toUri();
		
		log.info("Creating new wishlist: " + wishlist.getName());
		
		return ResponseEntity.created(uri).body(new BookWishlistDto(wishlist, bookDto));
	}
		
		
	//adds a new book to a wishlist
	@PostMapping("/{id}/book/{isbn13}")
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> addBookToWishlist(@PathVariable(name = "id") String wishlistId, @PathVariable String isbn13, UriComponentsBuilder uriBuilder) {
		
		if(isbn13 == null ) {
			throw new ModelException("isbn13", "Please provide a valid isbn13 code and try again");
		}
		
		BookForm bookForm = bookService.findBook(isbn13);
		Book book = bookForm.convertToBook();
		BookWishlist wishlist = bookWishlistService.addBookToWishlist(wishlistId, book);
		
		URI uri = uriBuilder.path("/{id}/book/{isbn13}").buildAndExpand(wishlist.getId(), isbn13).toUri();
		
		log.info("Adding book with isbn13 " + isbn13 + " to wishlist " + wishlist.getName());
				
		return ResponseEntity.created(uri).body(new BookWishlistDto(wishlist));
			
	}
	
	//deletes wishlist by wishlistId
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> deleteWishlist(@PathVariable(name = "id") String wishlistId) {
		
		log.info("Deleting wishlist by id " + wishlistId);
		
		bookWishlistService.deleteWishlist(wishlistId);
		
		return ResponseEntity.noContent().build();
	}
	
	//deletes book from wishlist by bookId 
	@DeleteMapping("/{id}/book/{bookId}")
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> deleteBookFromWishlist(@PathVariable(name = "id") String wishlistId, @PathVariable String bookId, UriComponentsBuilder uriBuilder) {
 
		log.info("Deleting book with id " + bookId + " from wishlist by id " + wishlistId);
		
		bookWishlistService.deleteBook(wishlistId, bookId);
		
		return ResponseEntity.noContent().build();
	}
	
	//shows book from wishlist by isbn13
	@GetMapping("/{id}/book/{isbn13}")
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> showBookFromWishlist(@PathVariable(name = "id") String wishlistId, @PathVariable String isbn13, UriComponentsBuilder uriBuilder) {
 
		log.info("Showing book with isbn13 " + isbn13 + " from wishlist by id " + wishlistId);
		
		BookWishlist wishlist = bookWishlistService.findBookWishlistById(wishlistId);
		Book book = bookWishlistService.showBookByIsbn13(wishlistId, isbn13);
		
		URI uri = uriBuilder.path("/{id}/book/{isbn13}").buildAndExpand(wishlist.getId(), isbn13).toUri();
				
		return ResponseEntity.created(uri).body(new BookDto(book));
			
	}
	
//	@RequestMapping(value = "/{id}/book/")
//	public ResponseEntity<?> handlingInvalidPath (@PathVariable(name = "id") String wishlistId) {
//		bookWishlistService.findBookWishlistById(wishlistId);
//		throw new ModelException("isbn13", "Please provide a valid isbn13 code and try again");
////		throw new ModelException("url", "Please check if you have type all url fields correctly");
//	}
	
	@RequestMapping(value = "/**/{regex:[-a-zA-Z0-9]*}")
	public ResponseEntity<?> handlingInvalidPaths () {
		log.info("An invalid endpoind path was typed");
//		bookWishlistService.findBookWishlistById(wishlistId);
//		throw new ModelException("isbn13", "Please provide a valid isbn13 code and try again");
		throw new ModelException("url", "Please check if you have type all url fields correctly");
	}
	
	//authenticating current user
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	private User getAutehticatedUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		log.info("Getting authenticated user: " + username);
		return userService.findUserByUsername(username);
	}
			
}
