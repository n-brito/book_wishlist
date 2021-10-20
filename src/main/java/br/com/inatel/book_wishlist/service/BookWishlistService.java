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
	
	public List<BookWishlist> findBookWishlist() {
		return wishlistRepository.findAll();
	}
	
	public BookWishlist findBookWishlistById(String id) {		
		Optional<BookWishlist> optional = wishlistRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ModelNotFoundException("id", "No wishlist found with this id: " + id); // devolver exceção
	}
	
	public BookWishlist createWishlist(BookWishlist wishlist) {
		BookWishlist temporaryWishlist = wishlistRepository.save(wishlist);
		if (temporaryWishlist.getBookList() != null) {
			temporaryWishlist.getBookList().forEach(b -> {
				bookRepository.save(b);
			});
		}
		return temporaryWishlist;
	}
	
//	@Autowired
//	public BookWishlistService(@Value("${it-bookstore.url}") String wishlistManagerURL) {
//		this.wishlistManagerURL = wishlistManagerURL;
//	}
//	
//	private static String readAll(Reader rd) throws IOException {
//	    StringBuilder sb = new StringBuilder();
//	    int cp;
//	    while ((cp = rd.read()) != -1) {
//	      sb.append((char) cp);
//	    }
//	    return sb.toString();
//	  }
//
//	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//	    InputStream is = new URL(url).openStream();
//	    try {
//	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//	      
//	      String jsonText = readAll(rd);
//	      JSONObject json = new JSONObject(jsonText);
//	      return json;
//	    } finally {
//	      is.close();
//	    }
//	  }


}
