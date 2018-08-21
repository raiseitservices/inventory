package com.raise.inventory.services.model;

public interface IResponse {

	long getTotalCount();

	long getPageCount();

	long getCount();

	String getNextPageURL();
	
	String getPrevPageURL();
	
}
