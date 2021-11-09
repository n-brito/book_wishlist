package br.com.inatel.book_wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport //support for pagination
@EnableSwagger2
public class BookWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookWishlistApplication.class, args);
	}
	
//	log.info("BookWishlistApplication is running");
//	log.error("Something is wrong here");

}
