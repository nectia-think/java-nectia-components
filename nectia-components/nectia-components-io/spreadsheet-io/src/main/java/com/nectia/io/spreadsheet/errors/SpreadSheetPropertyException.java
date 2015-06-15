/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.errors;

/**
 * Exepción lanzada cuando no es posible leer una propiedad, o cuando no se
 * puede acceder a la propiedad de una clase
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class SpreadSheetPropertyException extends Exception {

    private static final long serialVersionUID = -4658720784915408978L;

    /**
     * @param message
     * @param cause
     */
    public SpreadSheetPropertyException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public SpreadSheetPropertyException(String message) {
	super(message);
    }

}
