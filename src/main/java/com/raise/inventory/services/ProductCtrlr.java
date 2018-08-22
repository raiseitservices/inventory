package com.raise.inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.raise.inventory.services.model.IProduct;
import com.raise.inventory.services.model.IProductResponse;
import com.raise.inventory.services.model.Product;

@RestController
@RequestMapping("/product")
public class ProductCtrlr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCtrlr.class);

	private IProductService productService; 

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String testing() {
		LOGGER.debug("Product info endpoint called.'");
		
		return "This is a Product info endpoint.";
	} 

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public IProduct createProduct(Product product) {
		LOGGER.debug("Create Product.");
		boolean status = productService.create(product);

		return product;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public IProduct getProduct(@PathVariable("id") Integer id) throws InventoryException {
		LOGGER.debug("Get Product: " + id);
		IProduct product = productService.get(id);
		return (product);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public IProduct[] searchProduct(@RequestParam("id") String id, @RequestParam("name") String prodName,
			@RequestParam("type") String prodType) {
		LOGGER.debug("Search Product :");
		return productService.search(null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public IProductResponse updateProduct(@PathVariable("id") String id, IProduct product) {
		LOGGER.debug("Update Product.");
		boolean status = productService.update(product);
		//tempProdRepo.put(Integer.toString(product.getId()), product);
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public IProduct deleteProduct(@PathVariable("id") Integer id) {
		LOGGER.debug("Delete Product.");
		boolean status = productService.delete(id);
		return null;
	}

	/*@ RequestMapping("*")
	@ ResponseBody
	public String fallbackMethod() {
		String msg = "In-appropriate API usage. Please check the API document.";
		LOGGER.warn(msg);
		
		return msg;
	} */

	@ExceptionHandler({ InventoryException.class, Exception.class })
	public ResponseEntity<ErrorResponse> handleProductException(InventoryException ex, WebRequest request) {
		ErrorResponse errorDetails = null;
		ResponseEntity<ErrorResponse> error = null;
		switch (ex.getCode()) {
		case InventoryException.PRODUCT_NOT_FOUND: {
			errorDetails = new ErrorResponse(ex.getMessage());
			error = new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
			break;
		}
		default: {
			errorDetails = new ErrorResponse(ex.getMessage(), ex.getStackTrace());
			error = new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		return error;
	}

}
