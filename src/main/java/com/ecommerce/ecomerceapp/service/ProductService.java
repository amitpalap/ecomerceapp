package com.ecommerce.ecomerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecomerceapp.dao.*;
import com.ecommerce.ecomerceapp.entity.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {

	 @Autowired
	 private ProductDAO productDAO;


		@Transactional
		public Product getProductById(long id) {
			return productDAO.getProductById(id);
		}
		
		
		@Transactional
		public void updateProduct(Product product) {
			productDAO.updateProduct(product);
		}
		

		@Transactional
		public void deleteProduct(long id) {
			productDAO.deleteProduct(id);
		}

		@Transactional
		public List<Product> getAllProducts() {
			return productDAO.getAllProducts();
		}
	 
		@Transactional
		public List<Object> getAllProductsWithJoin() {
			//return productDAO.getAllProductsWithJoin();
			List<Object> list = new ArrayList<>();
			return list;
		}
	 		
}
