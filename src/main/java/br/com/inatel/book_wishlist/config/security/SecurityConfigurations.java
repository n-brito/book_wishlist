package br.com.inatel.book_wishlist.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.repository.UserRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private JwtAuthenticationEntrypoint entrypoint;
	
	@Autowired
	private UserRepository userRepository;
	
	 private static final String[] AUTH_WHITELIST = {
	
	            // -- swagger ui
	            "/swagger-resources/**",
	            "/**",
	            "/v2/api-docs",
	            "/webjars/**"
	    };
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//authentication configuration
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(entrypoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthenticationViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	//static resources configuration (js, css, html, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
        .antMatchers("/**", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123"));
//	}
	// $2a$10$goaOjFyvwRU2BeUQIlU9h.OtpVMlcgLkpTPZ8XPBIu4qcy9CcuLA. = encoding for 123 password
}
