package br.com.inatel.bookwishlist.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@ContextConfiguration(classes=BookWishlistApplication.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookWishlistServiceTest {
	
	@Mock
	BookWishlistRepository bookWishlistRepository;
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookWishlistService bookWishlistService;
	
	@Test
	public void shouldFindBookWishlistById() throws Exception {		
		//cenario
		BookWishlist wishlist = new BookWishlist("test");
		String id = "402860817d0a2a57017d0a602c940001";
		when(bookWishlistRepository.findById(id)).thenReturn(Optional.of(wishlist));
				
		//acao
		BookWishlist result = bookWishlistService.findBookWishlistById(id);
		
		//verificacao
		assertEquals(wishlist, result);
	}
	
	@Test
	public void shouldNotFindBookWishlistByWrongId() throws Exception {		
		//cenario
		String id = "123";
		when(bookWishlistRepository.findById(id)).thenReturn(Optional.empty());
						
		try {
			//acao
		bookWishlistService.findBookWishlistById(id);
		fail();
		} catch (ModelException e) {
			
		}		
		
	}
	
	@Test
	public void shouldFindBookWishlistByName() throws Exception {		
		//cenario
		BookWishlist wishlist = new BookWishlist("test");
		String name = wishlist.getName(); //"402860817d0a2a57017d0a602c940001";
		when(bookWishlistRepository.findByName(name)).thenReturn(Optional.of(wishlist));
				
		//acao
		BookWishlist result = bookWishlistService.findBookWishlistByName(name);
		
		//verificacao
		assertEquals(wishlist, result);
	}
	
	@Test
	public void shouldNotFindBookWishlistByWrongName() throws Exception {		
		//cenario
		String name = "123";
		when(bookWishlistRepository.findByName(name)).thenReturn(Optional.empty());
						
		try {
			//acao
		bookWishlistService.findBookWishlistById(name);
		fail();
		} catch (ModelException e) {
			
		}		
		
	}

}
