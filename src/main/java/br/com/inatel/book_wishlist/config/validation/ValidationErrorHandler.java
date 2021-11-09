package br.com.inatel.book_wishlist.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorDto> handleErrors(MethodArgumentNotValidException exception) {
		List<ValidationErrorDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationErrorDto error = new ValidationErrorDto(e.getField(), message);
			dto.add(error);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ModelException.class)
	public List<ValidationErrorDto> handleErrors(ModelException exception) {
		List<ValidationErrorDto> dto = new ArrayList<>();
		
		dto.add(new ValidationErrorDto(exception.getField(), exception.getMessage()));
		
		return dto;
	}
	
}
