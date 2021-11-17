package br.com.inatel.book_wishlist.adapter;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.controller.BookForm;

@Component
public class ItBookstoreAdapter {
	
	@Value("${it-bookstore.url}")
	private String itBookstoreUrl;
	
	public BookForm findBook(String isbn13){
		
		if (!checkConnection()) {
			throw new ModelException("connection", "No connection found with IT Bookstore API");
		}

		try {
			BookForm bookForm = new RestTemplate().getForObject(itBookstoreUrl + "/books/{id}", BookForm.class, isbn13);
			System.out.println(bookForm);
		
			return bookForm;
	    
		} catch (Exception e) {
			throw new ModelException("isbn13", "The isbn13 code provided is invalid or does not exist, please check and try again");
		}

	}
	
	private boolean checkConnection() {
		try {
			URL url = new URL(itBookstoreUrl);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
