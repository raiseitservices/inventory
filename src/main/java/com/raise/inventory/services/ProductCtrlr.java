package com.raise.inventory.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.raise.inventory.services.model.IProduct;
import com.raise.inventory.services.model.IProductResponse;
import com.raise.inventory.services.model.Product;
import com.raise.inventory.services.model.ProductResponse;

@RestController
@RequestMapping("/product")
public class ProductCtrlr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCtrlr.class);

	Map<String, IProduct> tempProdRepo = new HashMap<String, IProduct>();

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String testing() {
		LOGGER.debug("Product info endpoint called.'");
		IProduct prod = new Product();
		prod.setId(101);
		prod.setName("Prod Name");
		prod.setType("ProdType");
		tempProdRepo.put(Integer.toString(prod.getId()), prod);
		return "This is a Product info endpoint.";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public IProduct createProduct(Product product) {
		LOGGER.debug("Create Product.");

		tempProdRepo.put(Integer.toString(product.getId()), product);

		return product;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public IProduct getProduct(@PathVariable("id") String id) throws InventoryException {
		LOGGER.debug("Get Product: " + id);
		IProduct product = tempProdRepo.get(id);
		if (product == null) {
			throw new InventoryException(InventoryException.PRODUCT_NOT_FOUND,
					String.format("Product=%s not found.", id));
		}
		return (product);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public IProduct searchProduct(@RequestParam("id") String id, @RequestParam("name") String prodName,
			@RequestParam("type") String prodType) {
		LOGGER.debug("Search Product :");
		IProduct prod = null;
		if (id != null) {
			prod = tempProdRepo.get(id);
		} else {
			// not supported.
		}
		return prod;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public IProductResponse updateProduct(@PathVariable("id") String id, IProduct product) {
		LOGGER.debug("Update Product.");
		tempProdRepo.put(Integer.toString(product.getId()), product);
		return new ProductResponse();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public IProduct deleteProduct(@PathVariable("id") String id) {
		LOGGER.debug("Delete Product.");
		IProduct prod = tempProdRepo.remove(id);
		return prod;
	}

	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod() {
		String msg = "In-appropriate API usage. Please check the API document.";
		LOGGER.warn(msg);

		return msg;
	}

	@ExceptionHandler({ InventoryException.class, Exception.class })
	public ResponseEntity<ErrorResponse> handleProductException(InventoryException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(ex.getMessage(), ex.getStackTrace());
		ResponseEntity<ErrorResponse> error = null;
		switch (ex.getCode()) {
		case InventoryException.PRODUCT_NOT_FOUND: {
			error = new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
			break;
		}
		default: {
			error = new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		return error;
	}

}
