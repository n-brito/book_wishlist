package br.com.inatel.book_wishlist.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.inatel.book_wishlist.controller.BookForm;

@Component
public class ItBookstoreAdapter {
	
	@Value("${it-bookstore.url}")
	private String itBookstoreUrl;
	
	public BookForm findBook(String isbn13){
        
		BookForm bookForm = new RestTemplate().getForObject(itBookstoreUrl + "/books/{id}", BookForm.class, isbn13);

		System.out.println(bookForm);
		
	    return bookForm;

	}
	
}
