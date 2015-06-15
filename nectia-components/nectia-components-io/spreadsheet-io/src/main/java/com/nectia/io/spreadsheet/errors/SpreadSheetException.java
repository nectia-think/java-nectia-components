/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.errors;

/**
 * Excepción lanzada cuando ocurre un error al leer una hoja de calulo
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class SpreadSheetException extends Exception {

    private static final long serialVersionUID = -6443515617834836187L;

    /**
     * @param message
     * @param cause
     */
    public SpreadSheetException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public SpreadSheetException(String message) {
	super(message);
    }

}
