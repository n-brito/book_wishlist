package br.com.inatel.book_wishlist.controller;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.repository.BookRepository;
import br.com.inatel.book_wishlist.service.BookWishlistService;

@RestController 
@RequestMapping("/wishlist")
public class BookWishlistController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookWishlistService bookWishlistService;
	
	private JSONObject books;
	private JSONObject body;

	//lists all books in the wishlist
	@GetMapping()
	public ResponseEntity<?> listAllBooks() {
		
//			List<Stock> stockList = stockRepository.findAll(); // fazer como map
//			Map<String, Book> wishlist = 
		body = new JSONObject();		
		body.put("name", wishlist.getName());
		body.put("books", books);
		if (books.isEmpty()) {
			body = new JSONObject();
			body.put("status", HttpStatus.NOT_FOUND.toString());
			body.put("message", "Wishlist is empty");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
		}
		return ResponseEntity.status(HttpStatus.OK).body(body);
//		return ResponseEntity.ok(wishlist.map(BookWishlistDto::new).collect(Collectors.toList()));
	}
	
	//adds a new book to the wishlist
	@PostMapping
	@Transactional
	public ResponseEntity<?> addBook(@RequestBody BookWishlistForm form, UriComponentsBuilder uriBuilder) {
		
//		List<StockRegister> stockList = stockService.listStocks();
//		List<String> stockIdList = stockList.stream().map(StockRegister::getId).collect(Collectors.toList());
//		
		//allows quote addition to proceed only if stockId passed exists on stock-manager application
//		if (!stockIdList.contains(form.getStockId())) {
//			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Operation not allowed: No stock registered under the stockId " + form.getStockId());
//		}
							
		Book stock = new Book(form.getStockId());
		
		stock.setQuotes(form.generateStockQuoteList(stock));
			
		stockRepository.save(stock);
		
		URI uri = uriBuilder.path("/stocks/{id}").buildAndExpand(form.getStockId()).toUri();

		return ResponseEntity.created(uri).body(new StockDto(stock));		
	}
			
}
