package com.nectia.queries.impl;

import com.nectia.queries.api.Handler;
import com.nectia.queries.api.QueryHandler;

/**
 * 
 * @author Williams
 *
 */
@Handler
public class QueryHandlerTest {
    public QueryHandlerTest(){
	System.out.println("QueryHandlerTest Construido");
    }
    /**
     * 
     * @return
     * @throws InterruptedException 
     */
    @QueryHandler
    public String hello(QueryTest query) throws InterruptedException {
	Thread.sleep(10000);
	return "Hola " + query.getName() + " " +System.nanoTime();
    }
}
