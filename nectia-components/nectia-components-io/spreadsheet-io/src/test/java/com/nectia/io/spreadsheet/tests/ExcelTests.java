/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nectia.io.spreadsheet.api.Column;
import com.nectia.io.spreadsheet.api.SpreadSheetReader;
import com.nectia.io.spreadsheet.api.SpreadSheetReaderFactory;
import com.nectia.io.spreadsheet.api.SpreadSheetStructure;
import com.nectia.io.spreadsheet.api.SpreadSheetType;
import com.nectia.io.spreadsheet.errors.SpreadSheetException;

/**
 * Pruebas con archivos xls y xlsx
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class ExcelTests {
    @Test
    public void readSimpleFile() throws IOException, SpreadSheetException {
	InputStream stream = getClass().getResourceAsStream("/test.xls");

	SpreadSheetReader reader = SpreadSheetReaderFactory
		.create(SpreadSheetType.XLS);
	List<Column> columns = new ArrayList<Column>();
	columns.add(new Column("column1"));
	columns.add(new Column("column2"));
	columns.add(new Column("column3"));

	SpreadSheetStructure structure = new SpreadSheetStructure(columns);

	List<DataTest> response = reader.readSpreadSheet(stream, structure,
		DataTest.class);

	assertEquals(1, response.size());
    }

    @Test
    public void readXlsxWithHeadersFile() throws IOException,
	    SpreadSheetException {
	InputStream stream = getClass().getResourceAsStream("/prueba.xlsx");

	SpreadSheetReader reader = SpreadSheetReaderFactory
		.create(SpreadSheetType.XLSX);
	List<Column> columns = new ArrayList<Column>();
	columns.add(new Column("column1"));
	columns.add(new Column("column2"));
	columns.add(new Column("column3"));

	SpreadSheetStructure structure = new SpreadSheetStructure(columns, true);

	List<DataTest> response = reader.readSpreadSheet(stream, structure,
		DataTest.class);

	assertEquals(1, response.size());
    }
}
