package com.raise.inventory.services;

public class InventoryException extends Exception {

	final static int PRODUCT_NOT_FOUND = 1001; 

	private int code; 

	public InventoryException(int code, String message) {
		super(message);
		this.code = code;
	} 

	public int getCode() {
		return code;
	} 

}
