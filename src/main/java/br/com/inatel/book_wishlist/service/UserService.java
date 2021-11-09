package br.com.inatel.book_wishlist.service;

import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public User createUser(User user) {
		
		//validation
		
		user.setPassword(encoder.encode(user.getPassword()));
		
        return userRepository.save(user);
	}
	
	public User findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return user.get();			
		}
		throw new UsernameNotFoundException("Invalid User");
	}

}
