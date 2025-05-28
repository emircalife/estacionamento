package br.com.calife.estacionamento.core.exception;

import br.com.calife.estacionamento.core.http.response.ResponseErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

	private static final String API_ERROR = ">>> API - ";
	private final MessageSource messageSource;

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseErrorDTO> handleCnpqException(GenericException ex, HttpServletRequest request) {
		String message = ex.getMessage();
		log.error(message);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.BAD_REQUEST, message, request));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseErrorDTO> entityNotFoundException(EntityNotFoundException ex,
			HttpServletRequest request) {
		String message = messageSource.getMessage("exception.entityNotFoundException", null, request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ResponseErrorDTO> noResourceFoundException(NoResourceFoundException ex,
			HttpServletRequest request) {
		String message = messageSource.getMessage("exception.noResourceFoundException", null, request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResponseErrorDTO> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
		String message = messageSource.getMessage("exception.httpRequestMethodNotSupportedException", null,
				request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseErrorDTO> illegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) {
		String message = messageSource.getMessage("exception.illegalArgumentException", null, request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ResponseErrorDTO> emptyResultDataAccessException(EmptyResultDataAccessException ex,
			HttpServletRequest request) {
		String message = messageSource.getMessage("exception.emptyResultDataAccessException", null,
				request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseErrorDTO> dataIntegrityViolationExceptionException(DataIntegrityViolationException ex,
			HttpServletRequest request) {
		String message = messageSource.getMessage("exception.dataIntegrityException", null, request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.CONFLICT, ex.getMessage(), request));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			HttpServletRequest request, BindingResult result) {
		Map<String, String> errors = getErrors(result);

		Object[] params = new Object[] { errors };

		String message = messageSource.getMessage("exception.httpMessageNotReadableException", params,
				request.getLocale());
		log.error(API_ERROR + message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.NOT_FOUND, message, request));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		BindingResult result = ex.getBindingResult();

		Map<String, String> errors = getErrors(result);

		Object[] params = new Object[] { errors };

		String message = messageSource.getMessage("message.invalid.field", params, request.getLocale());

		ResponseErrorDTO responseError = new ResponseErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, message, request);
		responseError.setPayload(errors);

		log.error(API_ERROR + message);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
				.body(responseError);
	}

	private Map<String, String> getErrors(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(fieldError -> {
			String message = fieldError.getDefaultMessage().replace("{0}", fieldError.getField());
			errors.put(fieldError.getField(), message);
		});
		return errors;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseErrorDTO> internalServerErrorException(Exception ex, HttpServletRequest request) {
		log.error("Internal Server Error {} {} ", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request));
	}

}
