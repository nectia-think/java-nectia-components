package com.nectia.queries.errors;

/**
 * Excepción lanzada cuando no se puede realizar una consulta
 * 
 * @author Williams Torres
 *
 */
public class QueryException extends Exception {

	private static final long serialVersionUID = -5773299352766351850L;

	private static final String message = "Ha ocurrido un error al ejecutar la consulta";

	/**
	 * Crea una excepción con el mensaje:
	 * "Ha ocurrido un error al ejecutar la consulta"
	 */
	public QueryException() {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public QueryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public QueryException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QueryException(Throwable cause) {
		super(cause);
	}

}
