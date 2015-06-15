/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nectia.io.spreadsheet.api.SpreadSheetReader;
import com.nectia.io.spreadsheet.api.SpreadSheetStructure;
import com.nectia.io.spreadsheet.errors.SpreadSheetException;
import com.nectia.io.spreadsheet.errors.SpreadSheetPropertyException;

/**
 * Implementación de {@link SpreadSheetReader} utilizada para leer hojas de calculo de tipo .xlsx
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
class XlsxExcelReader extends ExcelReader implements SpreadSheetReader {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.nectia.io.spreadsheet.api.SpreadSheetReader#readSpreadSheet(java.
     * io.InputStream, com.nectia.io.spreadsheet.api.SpreadSheetStructure)
     */
    @Override
    public <TElement> List<TElement> readSpreadSheet(InputStream stream,
	    SpreadSheetStructure spreadSheetStructure,
	    Class<TElement> responseClass) throws IOException,
	    SpreadSheetException {

	XSSFWorkbook workbook = new XSSFWorkbook(stream);

	try {
	    XSSFSheet  sheet = workbook.getSheetAt(0);
	    return process(responseClass, sheet, spreadSheetStructure);
	} catch (SpreadSheetPropertyException e) {
	    throw new SpreadSheetException(
		    "Ha ocurrido un error al procesar el stream", e);
	} finally {
	    workbook.close();
	}

    }

}
