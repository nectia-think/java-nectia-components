/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.nectia.io.spreadsheet.errors.SpreadSheetException;

/**
 * Interfaz utilizada para leer archivos
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0, 2 Mar 2015
 *
 */
public interface SpreadSheetReader {
    /**
     * Lee una hoja de calculo y retorna un listado de elementos
     * 
     * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
     * @param <TElement>
     *            Tipo de elemento a convertir
     * @param stream
     *            Archivo a leer
     * @param spreadSheetStructure
     *            Estructura de columnas de la hoja de calculo
     * @param responseClass
     *            Clase del elemento a convertir
     * @return Listado de elementos de tipo T
     * @throws IOException
     *             Excepción lanzada cuando no es posible leer el stream
     * @throws SpreadSheetException
     *             Excepción lanzada cuando hay un problema al procesar la hoja
     *             de calculo
     */
    public <TElement> List<TElement> readSpreadSheet(InputStream stream,
	    SpreadSheetStructure spreadSheetStructure,
	    Class<TElement> responseClass) throws IOException,
	    SpreadSheetException;
}
