package br.com.inatel.book_wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.book_wishlist.model.BookWishlist;

@Repository
public interface BookWishlistRepository extends JpaRepository<BookWishlist, String> {

	BookWishlist findByName(String name);
	Optional<BookWishlist> findById(String id);
	
}
