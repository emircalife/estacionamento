package br.com.calife.estacionamento.core.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(String message) {
		super(message);
	}
	
	public GenericException(String message, IllegalArgumentException illegalArgumentException) {
		super(message, illegalArgumentException);
	}
	
}
