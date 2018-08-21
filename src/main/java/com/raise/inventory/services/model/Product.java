package com.raise.inventory.services.model;

public class Product implements IProduct {

	int id;
	String name;
	String type;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}
	
}
