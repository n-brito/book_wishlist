package br.com.inatel.bookwishlist.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;

@ContextConfiguration(classes=BookWishlistApplication.class)
@DataJpaTest
@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@SpringBootTest //(classes=BookWishlistRepository.class)
public class BookWishlistRepositoryTest {
	
	@Autowired
	BookWishlistRepository bookWishlistRepository;
	
	@Test
	public void shouldReturnFalseIfWishlistIdIsInvalid()
    {
        boolean exists = bookWishlistRepository.existsById("123");
        assertFalse(exists);
    }
	
	@Test
	public void shouldReturnTrueIfWishlistIdIsValid()
    {
        boolean exists = bookWishlistRepository.existsById("2c9beeda7cb78e95017cb79059010000");
        assertTrue(exists);
    }
	

}
