package com.raise.inventory.services;

import java.util.HashMap;
import java.util.Map;

import com.raise.inventory.services.model.IProduct;
import com.raise.inventory.services.model.Product;

public class ProductService implements IProductService { 

	private Map<String, IProduct> tempProdRepo = new HashMap<String, IProduct>();
	
	public ProductService() { 
		IProduct prod = new Product();
		prod.setId(101);
		prod.setName("Prod Name");
		prod.setType("ProdType");
		tempProdRepo.put(Integer.toString(prod.getId()), prod);
	}

	@Override
	public boolean create(IProduct product) {
		tempProdRepo.put(Integer.toString(product.getId()), product);
		return true;
	}

	@Override
	public boolean update(IProduct product) {
		tempProdRepo.put(Integer.toString(product.getId()), product);
		return true;
	}

	@Override
	public boolean delete(Integer productId) {
		IProduct prod = tempProdRepo.remove(productId);
		return true;
	}

	@Override
	public IProduct[] search(String searchQuery) {
		// SearchCriteria productId
		IProduct prod = null;
		Integer id = 12; 
		if (id != null) {
			prod = tempProdRepo.get(id);
		} else {
			// not supported.
		}
		IProduct[] prods = {prod};
		return prods;
	}

	@Override
	public IProduct get(Integer productId) throws InventoryException {
		IProduct product = tempProdRepo.get(productId);
		if (product == null) {
			throw new InventoryException(InventoryException.PRODUCT_NOT_FOUND,
					String.format("Product=%s not found.", productId));
		}
		return product;
	}

} 
