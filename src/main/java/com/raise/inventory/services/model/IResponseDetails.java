package com.raise.inventory.services.model;

public interface IResponseDetails {

	String getErrorMessage(); 
	
	StackTraceElement[] getExceptionStackTrace(); 
	
	long getDurationInMs();
	
}
