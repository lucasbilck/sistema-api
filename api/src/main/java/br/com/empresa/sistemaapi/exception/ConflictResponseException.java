package br.com.empresa.sistemaapi.exception;

public class ConflictResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictResponseException(String mensagem) {
        super(mensagem);
    }
}
