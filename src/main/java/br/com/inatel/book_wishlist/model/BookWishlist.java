package br.com.inatel.book_wishlist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity 
//@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "id")
public class BookWishlist {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String name;
	
	//a wishlist belongs to only one user
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	//a book can be added to different wishlists by the same user or by different users 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference //avoiding infinite recursion
	@JoinColumn(name = "wishlist_id")
	private List<Book> bookList = new ArrayList<>();
	
	
	public BookWishlist() {
//		this.id = UUID.randomUUID().toString();
	}
	
	public BookWishlist(String name) {
//		this.id = UUID.randomUUID().toString();
		this.name = name;
	}
	
	public List<String> getListOfIsbn13() {
		List<String> isbn13list = new ArrayList<>();
		this.bookList.forEach(b-> {
			isbn13list.add(b.getIsbn13());
		});
		return isbn13list;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
//	
//	public List<Book> addBookToBookList(Book book) {
//		return bookList.add(book);
//	}
	
}
