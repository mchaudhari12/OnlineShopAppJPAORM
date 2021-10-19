package com.cg.onlineshop.daos;
import java.util.List;
import java.util.Optional;

import com.cg.onlineshop.beans.Product;
public interface ProductDAO {
	public Product save(Product product);
	public Product update(Product product);
	public void delete(int productId);
	public List<Product> getAllProducts();
	public Optional<Product> getProduct(int productId);
	void insertBulkProducts(List<Product> products);	
}