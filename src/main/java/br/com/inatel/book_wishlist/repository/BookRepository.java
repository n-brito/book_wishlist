package br.com.inatel.book_wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.book_wishlist.model.Book;
import br.com.inatel.book_wishlist.model.BookWishlist;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	Book findByIsbn13(String isbn13);
	Optional<Book> findById(String id);
	
	//keyword can be title, author, isbn13, etc.
//	List<Book> findByKeyword(String keyword);
	
}
