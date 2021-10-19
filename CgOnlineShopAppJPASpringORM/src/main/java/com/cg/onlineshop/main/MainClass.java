package com.cg.onlineshop.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.onlineshop.beans.Product;
import com.cg.onlineshop.daos.ProductDAO;
import com.cg.onlineshop.daos.ProductDAOImpl;
import com.cg.onlineshop.exceptions.ProductDetailsNotFoundException;
import com.cg.onlineshop.services.ProductService;
import com.cg.onlineshop.services.ProductServiceImpl;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("onlineshop.xml");
		
		ProductService productService= applicationContext.getBean(ProductService.class,"productService" );
		
		
		Product product1= new Product(1 , 45 , 4 , "mnv" , "abc" );
		Product product2 = new Product(2 , 340 , 3 , "where the Jeans" , "Jeans");
		Product product3 = new Product(3 , 240 , 2 , "xyz" , "mnc");
		Product product4 = new Product(4 , 340 , 5 , "KJH" , "asd");

		product1 = productService.acceptProductDetails(product1);
		product2 = productService.acceptProductDetails(product2);
		product3 = productService.acceptProductDetails(product3);
		product4 = productService.acceptProductDetails(product4);


		System.out.println("Get Details "+productService.acceptProductDetails(product1));
		
		System.out.println("*********************");

		System.out.println("Get All Product Details : "+productService.getAllProductDetails());
		
		System.out.println("____*****______*****");
		
		try {
			System.out.println("Get Product Detail "+productService.getProductDetails(product1.getId()));
		} catch (ProductDetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("____***___***___***");
	
//		productService.removeProdcutDetails(2);
//		System.out.println("Get All Product Details : "+productService.getAllProductDetails());
		
		
//		Product product= new Product(1 , 45 , 4 , "mnv" , "KBC" );
//		Product update = productService.updateProdcutDetails(product);
//		System.out.println("Update"+update.getName());
//		System.out.println("  "+productService.getAllProductDetails());

		Product product5 = new Product(5 , 340 , 6 , "KJH" , "CSK");
		Product product6 = new Product(6 , 340 , 5 , "KJH" , "RCB");
		
		List<Product> productList = new ArrayList<>();
		productList.add(product5);
		productList.add(product6);
		productService.acceptBulkProductsDetails(productList);
		System.out.println("Get All Product Details : "+productService.getAllProductDetails());
	}
}