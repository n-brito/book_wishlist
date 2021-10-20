package br.com.inatel.book_wishlist.config.validation;

public class ModelNotFoundException extends RuntimeException{
	
	private String field;
	
	public ModelNotFoundException(String field, String error) {
		super(error);
		this.field = field;
	}
		
	public String getField() {
		return field;
	}
	
	
}
