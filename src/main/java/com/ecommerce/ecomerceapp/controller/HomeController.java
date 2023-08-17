package com.ecommerce.ecomerceapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import com.ecommerce.ecomerceapp.service.*;
import com.ecommerce.ecomerceapp.dao.*;
import com.ecommerce.ecomerceapp.entity.*;
 
@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ProductService productService; 
	
	@GetMapping("/")
	    public String home(ModelMap map,  javax.servlet.http.HttpServletRequest request) 
	    {
		    HttpSession session = request.getSession();
			List<Product> list = productService.getAllProducts();
			 HashMap<Long, String> mapCats = new HashMap<Long, String>();
			  for(Product product: list) {
				  Category category = categoryService.getCategoryById(product.getCategoryId());
				  if (category != null)
					  mapCats.put(product.getID(), category.getName());
			  }
			  
			map.addAttribute("list", list);
			map.addAttribute("mapCats", mapCats);
		    map.addAttribute("pageTitle", "SPORTY SHOES - HOMEPAGE"); 
	        return "index"; 
	    }	
	  
	  
}
