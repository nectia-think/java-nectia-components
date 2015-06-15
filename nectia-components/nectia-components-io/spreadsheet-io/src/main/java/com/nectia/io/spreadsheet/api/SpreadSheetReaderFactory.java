/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.api;

import java.security.InvalidParameterException;

import com.nectia.io.spreadsheet.impl.XlsExcelReaderFactory;
import com.nectia.io.spreadsheet.impl.XlsxExcelReaderFactory;

/**
 * Clase abstracta utilizada para crear un a implementación de
 * {@link SpreadSheetReader}
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public abstract class SpreadSheetReaderFactory {
    /**
     * Crea una nueva instancia de una implementacion de
     * {@link SpreadSheetReader}
     * 
     * @return Implementación de {@link SpreadSheetReader}
     */
    public abstract SpreadSheetReader create();

    /**
     * Crea una instancia dado cierto tipo de hoja de calculo
     * {@link SpreadSheetType}
     * 
     * @param spreadSheetType
     *            Tipo de hoja de calculo
     * @return Lector de hojas de calculo
     */
    public static SpreadSheetReader create(SpreadSheetType spreadSheetType) {
	switch (spreadSheetType) {
	case XLS:
	    return new XlsExcelReaderFactory().create();
	case XLSX:
	    return new XlsxExcelReaderFactory().create();
	default:
	    throw new InvalidParameterException("No existe factory para "
		    + spreadSheetType);
	}
    }
}
