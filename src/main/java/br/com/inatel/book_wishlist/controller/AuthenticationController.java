package br.com.inatel.book_wishlist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.book_wishlist.config.security.AuthenticationService;
import br.com.inatel.book_wishlist.config.security.TokenService;
import br.com.inatel.book_wishlist.config.validation.ModelException;
import br.com.inatel.book_wishlist.config.validation.ValidationErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.spring.web.json.Json;

@RestController 
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@PostMapping
	@CacheEvict(value = "listOfWishlists", allEntries = true)
	public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken loginData = form.convert();
		log.info("Authenticating user: " + loginData.getName());
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			System.out.println("authent. " + token);
			log.info("User " + loginData.getName() + " autheticated with token " + token);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
//			return ResponseEntity.badRequest() //.contentType(MediaType.APPLICATION_JSON)
//					//.body(new ModelException("username or password", "Please check that both the username and password are typed correctly"));  
//					.build();
//			return new ResponseEntity<>(new Error("Please check that both the username and password are typed correctly"), HttpStatus.BAD_REQUEST);
			log.info("Failure to autheticate user: " + loginData.getName());
			throw new ModelException("username or password", "Please check that both the username and password are typed correctly");
		}
		
	}
	
}
