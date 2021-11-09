package br.com.inatel.bookwishlist.service;

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

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@ContextConfiguration(classes=BookWishlistApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookWishlistServiceTest {
	
	@Mock
	BookWishlistRepository bookWishlistRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@InjectMocks
	BookWishlistService bookWishlistService;
	
	@Autowired
	private MockMvc mockMvc;

	
	private static final String URL = "/wishlist/";
	
//	@Test
//	public void shouldFindBookWishlistById(String id) throws Exception {		
//		
//		mockMvc.perform(MockMvcRequestBuilders.get(URL + id).accept(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}
	

}
