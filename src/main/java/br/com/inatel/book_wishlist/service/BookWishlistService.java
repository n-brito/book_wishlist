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
import br.com.inatel.book_wishlist.config.validation.ModelException;
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
		throw new ModelException("id", "No wishlist found with this id: " + id); 
	}
	
	public BookWishlist findBookWishlistByName(String name) {		
		Optional<BookWishlist> optional = wishlistRepository.findByName(name);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ModelException("name", "No wishlist found with this name: " + name); 
	}
	
	public BookWishlist createWishlist(BookWishlist wishlist) {
		Optional<BookWishlist> optional = wishlistRepository.findByName(wishlist.getName());
		
		if (!optional.isPresent()) {
			BookWishlist temporaryWishlist = wishlistRepository.save(wishlist);
						
			if (temporaryWishlist.getBookList() != null) {
				temporaryWishlist.getBookList().forEach(b -> {
					bookRepository.save(b);
				});
			}
			return temporaryWishlist;
		}
		
		throw new ModelException("name", "Wishlist by the name " + wishlist.getName() + " already exists"); 		
		
	}
	
	public BookWishlist addBookToWishlist(String id, Book book) {		
		BookWishlist temporaryWishlist = findBookWishlistById(id);
		
		if (temporaryWishlist.getListOfIsbn13().contains(book.getIsbn13())) {
			throw new ModelException("book", "Book by the isbn13 " + book.getIsbn13() + " already exists in this wishlist");
		}
				
		temporaryWishlist.getBookList().add(book);
		return wishlistRepository.save(temporaryWishlist);
	}

	public void deleteWishlist(String id) {
				
		Optional<BookWishlist> wishlist = Optional.of(findBookWishlistById(id));
		if (wishlist.isPresent()) {
			wishlistRepository.deleteById(id);
		} else {
			throw new ModelException("id", "No wishlist found with this id: " + id); 
		}
		
		
	}
	
	public BookWishlist deleteBook(String id, String bookId) {
		BookWishlist temporaryWishlist = findBookWishlistById(id);
		Optional<Book> book = temporaryWishlist.getBookList().stream().filter(b -> b.getId().equals(bookId)).findAny();
		if(book.isPresent()) {
			temporaryWishlist.getBookList().remove(book.get());
			return wishlistRepository.save(temporaryWishlist);
		}
		throw new ModelException("bookId", "No book found with this id: " + bookId); 		
	}
	
	public Book showBookByIsbn13(String id, String isbn13) {
		BookWishlist temporaryWishlist = findBookWishlistById(id);
		Optional<Book> book = temporaryWishlist.getBookList().stream().filter(b -> b.getIsbn13().equals(isbn13)).findAny();
		if(book.isPresent()) {
//			temporaryWishlist.getBookList().remove(book.get());
			return book.get();
		}
		throw new ModelException("isbn13", "No book found with this isbn13: " + isbn13);
	}

}
