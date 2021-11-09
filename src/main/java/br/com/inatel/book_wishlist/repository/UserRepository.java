package br.com.inatel.book_wishlist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.book_wishlist.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByUsername(String username);
	
}
