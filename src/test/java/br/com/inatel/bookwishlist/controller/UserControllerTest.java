package br.com.inatel.bookwishlist.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.inatel.book_wishlist.BookWishlistApplication;
import br.com.inatel.book_wishlist.model.BookWishlist;
import br.com.inatel.book_wishlist.service.BookWishlistService;
import br.com.inatel.book_wishlist.service.UserService;

@ContextConfiguration(classes=BookWishlistApplication.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void shouldCreateValidUser() throws Exception {	
		URI URL = new URI("/signup");
		String json = "{"
				+ "\"username\": \"Testing\", "
				+ "\"password\": \"00000\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void shouldNotCreateInvalidUser_NullUsername() throws Exception {	
		URI URL = new URI("/signup");
		String json = "{"
				+ "\"username\": \"\", "
				+ "\"password\": \"00000\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldNotCreateInvalidUser_NullPassword() throws Exception {	
		URI URL = new URI("/signup");
		
		String json2 = "{"
				+ "\"username\": \"Testing\", "
				+ "\"password\": \"\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json2).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
				
	}
	
	@Test
	public void shouldNotCreateInvalidUser_UsernameWrongSize() throws Exception {	
		URI URL = new URI("/signup");
		
		String json3 = "{"
				+ "\"username\": \"Tes\", "
				+ "\"password\": \"00000\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json3).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
		String json3_1 = "{"
				+ "\"username\": \"Testingggggggggggggggggggggggggggggggggggggg\", "
				+ "\"password\": \"00000\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json3_1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldNotCreateInvalidUser_PasswordWrongSize() throws Exception {	
		URI URL = new URI("/signup");
		
		String json4 = "{"
				+ "\"username\": \"Testing\", "
				+ "\"password\": \"00\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json4).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
		String json4_1 = "{"
				+ "\"username\": \"Testing\", "
				+ "\"password\": \"0000000000000000000000000000000000000000000\""
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(json4_1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
		
}
