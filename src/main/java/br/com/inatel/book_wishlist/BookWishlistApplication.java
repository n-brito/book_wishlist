package br.com.inatel.book_wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookWishlistApplication.class, args);
	}

}
