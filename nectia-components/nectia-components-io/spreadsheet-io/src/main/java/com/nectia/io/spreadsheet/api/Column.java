/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.api;

import java.text.Format;

/**
 * Identifica datos de las celdas de una columna
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class Column {
    private final String propertyName;
    private final String format;

    /**
     * Crea una columna no formato nulo
     * 
     * @param propertyName
     *            Nombre de la propiedad del objeto con el que se va a asociar,
     *            ej: "nombre"
     */
    public Column(String propertyName) {
	this.propertyName = propertyName;
	this.format = null;
    }

    /**
     * Crea una columna
     * 
     * @param propertyName
     *            Nombre de la propiedad del objeto con el que se va a asociar,
     *            ej: "nombre"
     * @param format
     *            Formato de dato, en caso de ser fecha debe seguir el formato
     *            de {@link Format}
     */
    public Column(String propertyName, String format) {
	this.propertyName = propertyName;
	this.format = format;
    }

    /**
     * @return Nombre de la propiedad con la que se va a mapear
     */
    public String getPropertyName() {
	return propertyName;
    }

    /**
     * @return Formato de las celdas correspondientes a la columna
     */
    public String getFormat() {
	return format;
    }

}
