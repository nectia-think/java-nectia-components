/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.tests;

import java.util.Date;

/**
 * Clase utilizada como prueba
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class DataTest {
    private String column1;
    private Double column2;
    private Date column3;

    public void setColumn1(String column1) {
	this.column1 = column1;
    }

    public void setColumn2(Double column2) {
	this.column2 = column2;
    }

    public void setColumn3(Date column3) {
	this.column3 = column3;
    }

    @Override
    public String toString() {
	return "DataTest [column1=" + column1 + ", column2=" + column2
		+ ", column3=" + column3 + "]";
    }
}
