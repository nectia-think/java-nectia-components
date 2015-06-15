package com.nectia.queries.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nectia.queries.api.QueryGateway;
import com.nectia.queries.errors.QueryException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTests.class)
@ComponentScan
public class SpringTests {

    /*
     * @Bean
     * 
     * @Scope("prototype") public QueryHandlerTest queryTest() { return new
     * QueryHandlerTest(); }
     */

    @Bean
    public QueryGateway queryGateway() {
	return new SpringQueryGateway();
    }

    @Autowired
    private QueryGateway queryGateway;

    @Test
    public void getQuery() throws QueryException {
	String result = queryGateway.handle(String.class, new QueryTest(
		"Williams"));

	// assertEquals("Hola Williams", result);

	result = queryGateway.handle(String.class, new QueryTest("Williams"));
    }

    @Test
    public void currencyTest() throws InterruptedException {
	long start = System.nanoTime();
	Request request = new Request(queryGateway);
	AnotherRequest anotherRequest = new AnotherRequest(queryGateway);

	Thread[] threads = new Thread[200];
	for (int i = 0; i < 100; i++) {
	    threads[i] = new Thread(request);
	    threads[i].start();
	}

	for (int i = 100; i < 200; i++) {
	    threads[i] = new Thread(anotherRequest);
	    threads[i].start();
	}

	for (Thread thread : threads) {
	    thread.join();
	}
	System.out.println("Procesamiento terminado en: "
		+ (System.nanoTime() - start));
    }

    public class Request implements Runnable {
	private QueryGateway queryGateway;

	/**
	 * @param queryGateway
	 */
	public Request(QueryGateway queryGateway) {
	    this.queryGateway = queryGateway;
	}

	@Override
	public void run() {
	    try {
		Thread.sleep(100L);
		String response = queryGateway.handle(String.class,
			new QueryTest("Williams"));
		System.out.println(response);
	    } catch (QueryException e) {
		e.printStackTrace();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public class AnotherRequest implements Runnable {
	private QueryGateway queryGateway;

	/**
	 * @param queryGateway
	 */
	public AnotherRequest(QueryGateway queryGateway) {
	    this.queryGateway = queryGateway;
	}

	@Override
	public void run() {
	    try {
		String response = queryGateway.handle(String.class,
			new AnotherQuery("Otra Consulta"));
		System.out.println(response);
	    } catch (QueryException e) {
		e.printStackTrace();
	    }
	}
    }
}
