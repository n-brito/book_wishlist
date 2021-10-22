package br.com.inatel.bookwishlist.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;
import org.junit.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookWishlistControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private BookWishlistRepository bookWishlistRepository;
	
	@Test
	public void shouldLoadWishlistByName() {
		String wishlistName = "Test Wishlist";
		BookWishlist wishlist = bookWishlistRepository.findByName(wishlistName);
		Assert.assertNotNull(wishlist);
		Assert.assertEquals(wishlistName, wishlist.getName());
	}

}
