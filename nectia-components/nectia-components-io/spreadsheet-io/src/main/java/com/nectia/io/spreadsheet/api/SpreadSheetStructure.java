/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.api;

import java.util.List;

/**
 * Clase que contiene la estructura de la hoja de calculo a importar
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class SpreadSheetStructure {
    private final List<Column> columns;
    private final boolean firstRowIsHeader;

    /**
     * Crea la estructura de una carga, asume la que l aprimera fila no contiene
     * los titulos
     * 
     * @param columns
     *            Listado de columnas a cargar
     */
    public SpreadSheetStructure(List<Column> columns) {
	this.columns = columns;
	this.firstRowIsHeader = false;
    }

    /**
     * Crea la estructura de una carga
     * 
     * @param columns
     *            Listado de columnas a cargar
     * @param firstRowIsHeader
     *            Indica si se utiliza la primera fila como cabezera
     */
    public SpreadSheetStructure(List<Column> columns, boolean firstRowIsHeader) {
	this.columns = columns;
	this.firstRowIsHeader = firstRowIsHeader;
    }

    /**
     * @return Columnas a cargar
     */
    public List<Column> getColumns() {
	return columns;
    }

    /**
     * @return Indica si la primera fila contiene los titulos
     */
    public boolean isFirstRowIsHeader() {
	return firstRowIsHeader;
    }

}
