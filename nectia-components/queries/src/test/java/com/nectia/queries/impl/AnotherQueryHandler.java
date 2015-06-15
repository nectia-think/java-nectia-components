/**
 * queries by Nectia 2015
 */
package com.nectia.queries.impl;

import com.nectia.queries.api.Handler;
import com.nectia.queries.api.QueryHandler;

/**
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
@Handler
public class AnotherQueryHandler {
    public AnotherQueryHandler() {
	System.out.println("QueryHandlerTest Construido");
    }

    /**
     * 
     * @return
     * @throws InterruptedException
     */
    @QueryHandler
    public String hello(AnotherQuery query) throws InterruptedException {
	return "Hola " + query.getName() + " " + System.nanoTime();
    }
}
