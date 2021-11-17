package br.com.inatel.book_wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inatel.book_wishlist.adapter.ItBookstoreAdapter;
import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.controller.BookForm;
import br.com.inatel.book_wishlist.model.Book;

@Service
public class BookService {
	
	@Autowired
	private ItBookstoreAdapter adapter;
	
	public BookForm findBook(String isbn13) {	
		if(adapter.findBook(isbn13).getIsbn13() == null || adapter.findBook(isbn13).getTitle() == null) {
			throw new ModelException("isbn13", "The isbn13 code provided is invalid, please check and try again");
		}
		return adapter.findBook(isbn13);
	}

}
