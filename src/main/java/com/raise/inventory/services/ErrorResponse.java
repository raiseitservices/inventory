package com.raise.inventory.services;

import java.util.Date;

public class ErrorResponse {
	private Date timestamp;
	private String message;
	private StackTraceElement[] details;

	public ErrorResponse(String message) {
		super();
		this.timestamp = new Date();
		this.message = message;
	}
	
	public ErrorResponse(String message, StackTraceElement[] details) {
		this(message);
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StackTraceElement[] getDetails() {
		return details;
	}

	public void setDetails(StackTraceElement[] details) {
		this.details = details;
	}
	
}
