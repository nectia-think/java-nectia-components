/**
 * 
 */
package com.nectia.queries.impl;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.nectia.queries.api.Handler;
import com.nectia.queries.api.QueryGateway;
import com.nectia.queries.api.QueryHandler;
import com.nectia.queries.errors.QueryException;

/**
 * Implementación de la interfaz {@link QueryGateway} que utiliza el contenedor
 * de spring para buscar el handler
 * 
 * @author Williams
 *
 */
public class SpringQueryGateway implements QueryGateway,
	ApplicationContextAware {

    private static transient ApplicationContext applicationContext = null;

    private static final Logger log = LoggerFactory
	    .getLogger(SpringQueryGateway.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	SpringQueryGateway.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    private <T, R> R executeQuery(String[] beansDefinitions, Class<R> clazz,
	    T query) throws QueryException {
	for (String beanDefinition : beansDefinitions) {
	    Class<?> beanClass = applicationContext.getType(beanDefinition);
	    for (Method method : beanClass.getMethods()) {
		QueryHandler annotation = method
			.getAnnotation(QueryHandler.class);
		if (annotation != null) {

		    if (method.getParameterTypes().length == 1
			    && method.getParameterTypes()[0].getName() == query
				    .getClass().getName())
			try {
			    Object bean = applicationContext
				    .getBean(beanDefinition);
			    return (R) method.invoke(bean, query);

			} catch (Exception e) {
			    log.error(
				    "Method: handle - Error al ejecutar la consulta: "
					    + query.toString(), e);
			    throw new QueryException(e);
			}
		}
	    }
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.nectia.queries.api.QueryGateway#handle(java.lang.Class,
     * java.lang.Object)
     */
    @Override
    public <T, R> R handle(Class<R> clazz, T query) throws QueryException {

	R queryResult = null;

	String[] beans = applicationContext
		.getBeanNamesForAnnotation(Handler.class);

	if (beans != null && beans.length > 0) {
	    queryResult = executeQuery(beans, clazz, query);
	} else {
	    beans = applicationContext.getBeanDefinitionNames();
	    if (beans != null) {
		queryResult = executeQuery(beans, clazz, query);
	    }
	}
	if (queryResult == null) {
	    log.warn(
		    "Method: handle - handler para la consulta: {} no encontrado",
		    query);
	}

	return queryResult;
    }
}
