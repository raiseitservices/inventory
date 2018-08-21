package com.raise.inventory.services.model;

import java.util.List;

public interface IProductResponse extends IResponse {

	List<IProduct> products();

	IResponseDetails getDetails();

}
