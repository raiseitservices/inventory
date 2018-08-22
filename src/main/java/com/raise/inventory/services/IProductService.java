package com.raise.inventory.services;

import com.raise.inventory.services.model.IProduct;

public interface IProductService {

	IProduct get(Integer productId) throws InventoryException;

	IProduct[] search(String searchQuery);

	boolean delete(Integer id);

	boolean update(IProduct product);

	boolean create(IProduct product);

}