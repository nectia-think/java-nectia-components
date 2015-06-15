/**
 * spreadsheet-io by Nectia 2015
 */
package com.nectia.io.spreadsheet.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nectia.io.spreadsheet.api.Column;
import com.nectia.io.spreadsheet.api.SpreadSheetStructure;
import com.nectia.io.spreadsheet.errors.SpreadSheetPropertyException;

/**
 * Clase abstracta utilizada como base para la lectura de hojas de calculo de
 * tipo excel
 * 
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
abstract class ExcelReader {
    private static final Logger log = LoggerFactory
	    .getLogger(ExcelReader.class);

    /**
     * Genera la lista de elementos desde la hoja de estilo {@link Sheet}
     * 
     * @param responseClass
     *            Tipo de clase de respuesta
     * @param sheet
     *            Hoja de estilo
     * @param spreadSheetStructure
     *            Estructura de la hoja de estilo
     * @return Listado de elementos de tipo TElement
     * @throws SpreadSheetPropertyException
     *             Indica que ha ocurrido un error al leer una propiedad
     */
    public <TElement> List<TElement> process(Class<TElement> responseClass,
	    Sheet sheet, SpreadSheetStructure spreadSheetStructure)
	    throws SpreadSheetPropertyException {

	List<TElement> response = new ArrayList<TElement>();

	Iterator<Row> rowIterator = sheet.iterator();

	while (rowIterator.hasNext()) {

	    Row row = rowIterator.next();

	    boolean emptyRow = true;

	    if (spreadSheetStructure.isFirstRowIsHeader()
		    && row.getRowNum() > 0) {
		continue;
	    }

	    Iterator<Cell> cellIterator = row.cellIterator();

	    TElement element = null;
	    try {
		element = responseClass.newInstance();
	    } catch (InstantiationException e) {

	    } catch (IllegalAccessException e) {

	    }

	    while (cellIterator.hasNext()) {
		Cell cell = cellIterator.next();

		if (cell.getColumnIndex() == spreadSheetStructure.getColumns()
			.size()) {
		    break;
		}

		Column column = spreadSheetStructure.getColumns().get(
			cell.getColumnIndex());

		if (setValue(column, cell, element, responseClass)) {
		    emptyRow = false;
		}
	    }
	    if (!emptyRow)
		response.add(element);
	}
	log.debug("Lectura de excel terminada {}", response);
	return response;
    }

    /**
     * Obtiene el valor de una celda
     * 
     * @param cell
     *            Celda de la cual se va a obtener el valor
     * @return Valor de la celda
     */
    private Object getCellValue(Cell cell) {

	switch (cell.getCellType()) {
	case Cell.CELL_TYPE_STRING:
	    return cell.getStringCellValue();
	case Cell.CELL_TYPE_BOOLEAN:
	    return String.valueOf(cell.getBooleanCellValue());
	case Cell.CELL_TYPE_NUMERIC:
	    return String.valueOf(cell.getNumericCellValue());
	case Cell.CELL_TYPE_BLANK:
	case Cell.CELL_TYPE_FORMULA:
	default:
	    return null;
	}
    }

    /**
     * Convierte un valor excel a un tipo de dato primitivo encapsulado: String,
     * Boolean, Integer, Double, Date
     * 
     * @param responseClass
     *            Tipo de clase de respuesta
     * @param value
     *            Valor a convertir
     * @return Valor convertido al formato TResponse
     * @throws SpreadSheetPropertyException
     *             Excepción lanzada cuando no es posbile convertir el tipo de
     *             dato
     */
    @SuppressWarnings("unchecked")
    private <TResponse extends Object> TResponse convertValue(
	    Class<TResponse> responseClass, Object value)
	    throws SpreadSheetPropertyException {
	try {
	    if (value.getClass() == responseClass) {
		return (TResponse) value;
	    } else if (responseClass == Date.class) {
		return (TResponse) DateUtil.getJavaDate(Double.valueOf(value
			.toString()));
	    } else {
		Method valueOfMethod = responseClass.getMethod("valueOf",
			value.getClass());
		return (TResponse) valueOfMethod.invoke(responseClass, value);
	    }
	} catch (NoSuchMethodException e) {
	    throw new SpreadSheetPropertyException(
		    "Tipo de dato del objeto no soportado", e);
	} catch (SecurityException e) {
	    return null;
	} catch (IllegalAccessException e) {
	    return null;
	} catch (IllegalArgumentException e) {
	    return null;
	} catch (InvocationTargetException e) {
	    return null;
	}
    }

    /**
     * Establece el valor en una instancia del elemento de respuesta
     * 
     * @param column
     *            Columna de la hoja de calculo
     * @param cell
     *            Celda con el valor a establecer
     * @param element
     *            Elemento de respuesta
     * @param responseClass
     *            Tipo de elemento de respuesta
     * @return true si el valor se estableció y false si era un valor en blanco
     * @throws SpreadSheetPropertyException
     *             Excepción lanzada cuando no es posible establecer el valor
     */
    private <TElement> boolean setValue(Column column, Cell cell,
	    TElement element, Class<TElement> responseClass)
	    throws SpreadSheetPropertyException {

	boolean emptyCell = false;

	Method[] methods = responseClass.getMethods();

	/*
	 * Indica si se encontró el método set de la propiedad
	 */
	boolean methodFound = false;
	for (Method method : methods) {
	    if (method.getName().equalsIgnoreCase(
		    "set" + column.getPropertyName())) {

		methodFound = true;
		Object value = getCellValue(cell);
		if (value == null) {
		    emptyCell = true;
		} else {
		    try {
			method.invoke(
				element,
				convertValue(method.getParameterTypes()[0],
					value));
		    } catch (IllegalAccessException e) {
			throw new SpreadSheetPropertyException("El método "
				+ method.getName() + " no es público", e);
		    } catch (IllegalArgumentException e) {
			throw new SpreadSheetPropertyException("El método "
				+ method.getName()
				+ " no acepta una propiedad de tipo "
				+ cell.getCellType(), e);
		    } catch (InvocationTargetException e) {
			throw new SpreadSheetPropertyException(
				"Ha ocurrido un error al llamar al método "
					+ method.getName(), e);
		    }
		}
		
	    }
	}

	if (methodFound == false) {
	    throw new SpreadSheetPropertyException("Seter para la propiedad "
		    + column.getPropertyName() + " no encontrado");
	}

	return !emptyCell;
    }
}
