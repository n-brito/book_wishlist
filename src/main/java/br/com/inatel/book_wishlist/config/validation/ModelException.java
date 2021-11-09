package br.com.inatel.book_wishlist.config.validation;

public class ModelException extends RuntimeException{
	
	private String field;
	
	public ModelException(String field, String error) {
		super(error);
		this.field = field;
	}
		
	public String getField() {
		return field;
	}
	
	
}
