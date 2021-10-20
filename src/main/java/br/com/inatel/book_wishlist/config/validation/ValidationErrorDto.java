package br.com.inatel.book_wishlist.config.validation;

public class ValidationErrorDto {

	private String field;
	private String error;
	
	public ValidationErrorDto(String field, String error) {
		this.field = field;
		this.error = error;
	}
		
	public String getField() {
		return field;
	}
	
	public String getError() {
		return error;
	}
	
}
