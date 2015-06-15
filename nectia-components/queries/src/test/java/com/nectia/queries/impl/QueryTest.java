package com.nectia.queries.impl;

import java.io.Serializable;

public class QueryTest implements Serializable {

    private static final long serialVersionUID = 435759491980184020L;

    private String name;

    /**
     * @param name
     */
    public QueryTest(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
