package br.com.inatel.bookwishlist.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
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
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import br.com.inatel.book_wishlist.service.BookWishlistService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=BookWishlistApplication.class)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
@SpringBootTest

@AutoConfigureMockMvc

@ActiveProfiles("test")
public class BookWishlistControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookWishlistService bookWishlistService;
	
	private static final String URL = "/wishlist/123";
	
//	@Test
//	public void shouldLoadWishlistByName() {
//		String wishlistName = "Test Wishlist";
//		BookWishlist wishlist = bookWishlistService.findByName(wishlistName);
//		assertNotNull(wishlist);
//		assertEquals(wishlistName, wishlist.getName());
//	}
	
	@Test
	public void shouldFindBookWishlistById() throws Exception {		
		
		mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldDeleteWishlistById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}
	
	@Test
	public void shouldExhibitWishlistByIdIfItExists() throws Exception {
		BookWishlist wishlist = new BookWishlist("test");
		bookWishlistService.createWishlist(wishlist);
		
		mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

}
