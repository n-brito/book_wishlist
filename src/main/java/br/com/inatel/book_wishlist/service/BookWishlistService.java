package br.com.inatel.book_wishlist.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.inatel.book_wishlist.adapter.ItBookstoreAdapter;
import br.com.inatel.book_wishlist.config.validation.ModelNotFoundException;
import br.com.inatel.book_wishlist.controller.BookForm;
import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.repository.BookWishlistRepository;

@Service
public class BookWishlistService {
	
	@Autowired
	private BookWishlistRepository wishlistRepository;
	@Autowired
	private BookRepository bookRepository;
	
	public Page<BookWishlist> findBookWishlist(Pageable pagination) {
		return wishlistRepository.findAll(pagination);
	}
	
	public BookWishlist findBookWishlistById(String id) {		
		Optional<BookWishlist> optional = wishlistRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ModelNotFoundException("id", "No wishlist found with this id: " + id); 
	}
	
	public BookWishlist createWishlist(BookWishlist wishlist) {
		BookWishlist temporaryWishlist = wishlistRepository.save(wishlist);
		
		//validate: not allowed if wishlist with the id already exists
		
		if (temporaryWishlist.getBookList() != null) {
			temporaryWishlist.getBookList().forEach(b -> {
				bookRepository.save(b);
			});
		}
		return temporaryWishlist;
	}
	
	public BookWishlist addBookToWishlist(String id, Book book) {		
		BookWishlist temporaryWishlist = findBookWishlistById(id);
		temporaryWishlist.getBookList().add(book);
		return wishlistRepository.save(temporaryWishlist);
	}

}
