package com.cg.onlineshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.onlineshop.beans.Product;
import com.cg.onlineshop.daos.ProductDAO;
import com.cg.onlineshop.exceptions.ProductDetailsNotFoundException;

@Component(value="productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Product acceptProductDetails(Product product) {	
		return productDAO.save(product);
	}

	@Override
	public List<Product> getAllProductDetails() {
		return productDAO.getAllProducts();
	}

	@Override
	public Product getProductDetails(int productId) throws ProductDetailsNotFoundException {
		return productDAO.getProduct(productId).orElseThrow(()->new ProductDetailsNotFoundException("Employee Details Not Found  :-  "+productId));
	}

	@Override
	public void acceptBulkProductsDetails(List<Product> products) {
		productDAO.insertBulkProducts(products);
	}

	@Override
	public void removeProdcutDetails(int productId) {
		 productDAO.delete(productId);		 
	}
	
	public Product updateProdcutDetails(Product product) {
		return productDAO.update(product);		 
	}
	
}
