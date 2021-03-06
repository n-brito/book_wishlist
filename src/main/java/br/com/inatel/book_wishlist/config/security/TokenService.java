package br.com.inatel.book_wishlist.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.inatel.book_wishlist.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User loggedUser = (User) authentication.getPrincipal();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("Book Wishlist API")
				.setSubject(loggedUser.getId())
				.setIssuedAt(currentDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		boolean isValid = false;
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			isValid = true;
		} catch (Exception e) {
		}
		return isValid;
	}

	public String getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();		
		return claims.getSubject();
	}

}
