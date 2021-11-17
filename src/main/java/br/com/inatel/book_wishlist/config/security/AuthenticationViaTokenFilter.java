package br.com.inatel.book_wishlist.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.repository.UserRepository;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	private UserRepository userRepository;
		
	public AuthenticationViaTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = obtainToken(request);
//		System.out.println(token);
		
		boolean valid = tokenService.isTokenValid(token);
		System.out.println("token valid? " + valid);
		if (valid) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);		
	}

	private void authenticateUser(String token) {
		String userId = tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		System.out.println("auth user: " + user.getUsername());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String obtainToken(HttpServletRequest request) {
		String tokenFromHeader = request.getHeader("Authorization");
		System.out.println("from header: " + tokenFromHeader);
		if (tokenFromHeader == null || tokenFromHeader.isEmpty() || !tokenFromHeader.startsWith("Bearer ")) {
			return null;
		}
		return tokenFromHeader.substring(7, tokenFromHeader.length()); // "Bearer " = 7 
	}

}
