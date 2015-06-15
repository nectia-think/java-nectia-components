/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.impl;

import com.nectia.io.spreadsheet.api.SpreadSheetReader;
import com.nectia.io.spreadsheet.api.SpreadSheetReaderFactory;

/**
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class XlsExcelReaderFactory extends SpreadSheetReaderFactory {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.nectia.io.spreadsheet.api.SpreadSheetReaderFactory#create(java.io
     * .InputStream, com.nectia.io.spreadsheet.api.SpreadSheetStructure,
     * java.lang.Class)
     */
    @Override
    public SpreadSheetReader create() {
	return new XlsExcelReader();
    }
}
