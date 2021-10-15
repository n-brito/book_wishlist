package br.com.inatel.book_wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.book_wishlist.model.BookWishlist;

public interface BookRepository extends JpaRepository<String, BookWishlist> {

}
