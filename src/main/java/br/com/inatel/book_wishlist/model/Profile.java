package br.com.inatel.book_wishlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Profile implements GrantedAuthority {

	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	private String profileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return profileName;
	}
	
}
