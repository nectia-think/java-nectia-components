/**
 * 
 */
package com.nectia.queries.api;

import com.nectia.queries.errors.QueryException;

/**
 * Interfaz que define la estructura del command gateway, este es el encargado
 * de buscar el handler correspondiente a una consulta
 * 
 * @author Williams Torres
 *
 */
public interface QueryGateway {
	/**
	 * Busca el handler correspondiente a la consulta y ejecuta el método que
	 * contiene la anotación {@link QueryHandler}
	 * 
	 * @param clazz
	 *            Tipo de clase de respuesta
	 * @param query
	 *            Consulta a ejecutar
	 * @return Resultado de la consulta
	 * @throws QueryException
	 */
	<T, R> R handle(Class<R> clazz, T query) throws QueryException;
}
