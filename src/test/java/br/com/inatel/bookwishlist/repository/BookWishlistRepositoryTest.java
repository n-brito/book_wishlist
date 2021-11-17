package br.com.inatel.bookwishlist.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;

@ContextConfiguration(classes=BookWishlistApplication.class)
@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
//@SpringBootTest //(classes=BookWishlistRepository.class)
public class BookWishlistRepositoryTest {
	
	@Autowired
	BookWishlistRepository bookWishlistRepository;
	
//	@Test
//	public void shouldReturnFalseIfWishlistIdIsInvalid() {
//        boolean exists = bookWishlistRepository.existsById("123");
//        assertFalse(exists);
//    }
	
//	@Test
//	public void shouldReturnTrueIfWishlistIdIsValid() {
//		String wishlistName = "Test Wishlist";
//		Optional<BookWishlist> wishlist = bookWishlistRepository.findByName(wishlistName);
//		assertTrue(wishlist.isPresent());
////        boolean exists = bookWishlistRepository.existsById("402860817d09d36f017d09d8ab690000");
////        assertTrue(exists);
//    }
	
	

}
