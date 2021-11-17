package br.com.inatel.bookwishlist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

import static org.hamcrest.CoreMatchers.containsString;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.config.security.TokenService;
import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.controller.BookForm;
import br.com.inatel.book_wishlist.controller.BookWishlistDto;
import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.repository.UserRepository;
import br.com.inatel.book_wishlist.service.BookService;
import br.com.inatel.book_wishlist.service.BookWishlistService;
import br.com.inatel.book_wishlist.service.UserService;

@ContextConfiguration(classes=BookWishlistApplication.class)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookWishlistControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookWishlistService bookWishlistService;
	
	@Autowired
	BookWishlistRepository bookWishlistRepository;
	
//	@MockBean
//	private AuthenticationManager authManager;
//	
//	@MockBean
//	private Authentication auth;
//	
//	@Mock
//	private User userMock;
	
	@MockBean
	private BookService bookService;
	
//	@MockBean
//	private UserService userService;
	
	@MockBean
    private UserRepository userRepository;
	
//	@Autowired
//	private TokenService tokenService;
	
	private static final String URL = "/wishlist";
	private static final String isbn13 = "9780596009632"; 
	
	String book = "{"
			+ "\"username\": \"Testing\", "
			+ "\"password\": \"00000\""
			+ "}";
	
	String wishlist = "{"
	     + "\"id\": \"402860817d0a2a57017d0a602c940001\", "
	     + "\"name\": \"Java Study List - Naiara\", "
	     + "\"books\": [ "
	     +    "{"
	     +     "\"id\": \"402860817d0ee7ac017d0f32a54e0000\", "
         +     "\"isbn13\": \"9780672337949\","
         +     "\"title\": \"Sams Teach Yourself Java in 24 Hours, 8th Edition\","
         +     "\"subtitle\": \"Covering Java 9\","
         +     "\"authors\": \"Rogers Cadenhead\","
         +     "\"year\": \"2017\","
         +     "\"price\": \"$17.79\","
         +     "\"url\": \"https://api.itbook.store/1.0/books/9780672337949\""
         +   "}"
         +   "]"
         + "}";
	
	String wishlistToPost = "{"
		     + "\"name\": \"test\", "
		     + "\"books\": [ "
		     +    "{"
	         +     "\"isbn13\": \"9780672337949\""
	         +   "}"
	         +   "]"
	         + "}";
	
//	@BeforeEach
//	public void setUp() {
//		when(userService.findUserByUsername("username")).thenReturn(userMock);
//	}

	@Test
	public void shouldFindWishlistByIdIfItExists() throws Exception {
		BookWishlist wishlist = new BookWishlist("test");
		String id = "402860817d0a2a57017d0a602c940001";
		when(bookWishlistService.findBookWishlistById(id)).thenReturn(wishlist);
				
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void shouldNotFindWishlistByIdIfItDoesNotExists() throws Exception {
//		BookWishlist wishlist = new BookWishlist("test");
		String id = "123";
		when(bookWishlistService.findBookWishlistById(id)).thenThrow(new ModelException("id", "No wishlist found with this id: " + id));
				
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void shouldNotCreateWishlistIfAuthenticationFails() throws Exception {
		BookWishlist wishlist = new BookWishlist("test");
//		wishlist.setBookList(new ArrayList<>());
		String username = "username";
		
		when(bookWishlistService.createWishlist(wishlist)).thenReturn(wishlist);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(wishlistToPost).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}
	
	@Test
	public void shouldAddBookToWishlist() throws Exception {
		String wishlistId = "402860817d0a2a57017d0a602c940001";
		BookWishlist wishlist = new BookWishlist("test");
//		wishlist.setBookList(new ArrayList<>());
		String username = "username";
		wishlist.setId(wishlistId);
		when(bookService.findBook(isbn13)).thenReturn(new BookForm());
		when(bookWishlistService.addBookToWishlist(any(), any())).thenReturn(wishlist);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL + "/" + wishlistId + "/book/" + isbn13).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}
	
	
//	@Test
//	public void shouldCreateWishlist() throws Exception {
//		BookWishlist wishlist = new BookWishlist("test");
////		wishlist.setBookList(new ArrayList<>());
//		String username = "username";
//		
//		when(bookWishlistService.createWishlist(wishlist)).thenReturn(wishlist);
//		
//		mockMvc.perform(MockMvcRequestBuilders.post(URL).header(HttpHeaders.AUTHORIZATION, fakeAuth()).content(wishlistToPost).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());
//
//	}
	

//	@Test
//	public void shouldAddBookToWishlist() throws Exception {
//		BookWishlist wishlist = new BookWishlist("test");
//
//		when(bookWishlistService.createWishlist(wishlist)).thenReturn(wishlist);
//		String wishlistId = "402860817d0a2a57017d0a602c940001";
//		
//		mockMvc.perform(MockMvcRequestBuilders.post(URL + "/" + wishlist.getId() + "/book/" + isbn13).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());
//		
//	}
	
	@Test
	public void shouldDeleteBookfromWishlist() throws Exception {
		String wishlistId = "402860817d0a2a57017d0a602c940001";
		String bookId = "402860817d0ee7ac017d0f32a54e0000";
				
		mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/" + wishlistId + "/book/" + bookId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	@Test
	public void shouldDeleteWishlist() throws Exception {
		String id = "402860817d0a2a57017d0a602c940001";
				
		mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/" + id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	
	private String toJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	private String fakeAuth() {
//		User user = new User();
//		user.setUsername("username");
//		user.setId("fa469d9f-8785-4b9a-9561-7f4123b28788");
//		when(auth.getPrincipal()).thenReturn(user);
//        return "Bearer " + tokenService.generateToken(auth);
//	}

}
