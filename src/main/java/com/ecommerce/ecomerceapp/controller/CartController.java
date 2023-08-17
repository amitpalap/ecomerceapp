package com.ecommerce.ecomerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.ecommerce.ecomerceapp.service.*;
import com.ecommerce.ecomerceapp.dao.*;
import com.ecommerce.ecomerceapp.entity.*;
@Controller
public class CartController {

	@Autowired
	private ProductService productService; 

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseItemService purchaseItemService; 

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	    public String cart(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before adding items to cart");
		  } else {

			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");


			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);
 		  }
		  
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
	        return "cart"; 
	    }

	@RequestMapping(value = "/cartadditem", method = RequestMethod.GET)
	    public String cartAddItem(ModelMap map, javax.servlet.http.HttpServletRequest request,
	    		@RequestParam(value="id", required=true) String productId) 
	    {

		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before adding items to cart");
		  } else {
			  
			  long idValue = Long.parseLong(productId);

			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  if (isItemInCart(cartItems, idValue)) {
				  map.addAttribute("error", "This item is already in your cart");
			  } else {
				  Product product = productService.getProductById(idValue);
				  CartItem item = new CartItem();
				  item.setProductId(idValue);
				  item.setQty(1);
				  item.setRate(product.getPrice());
				  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
				  item.setPrice(dprice); 
				  item.setName(product.getName()); 
				  cartItems.add(item);
				  
				  session.setAttribute("cart_items", cartItems);
			  }
		  }
		  
	        return "redirect:cart"; 
	    }	  
	  @RequestMapping(value = "/cartdeleteitem", method = RequestMethod.GET)
	    public String cartDeleteItem(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	    		@RequestParam(value="id", required=true) String id) 
	    {

		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before deleting items from cart");
		  } else {
			  long idValue = Long.parseLong(id);
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  	  
			  for(CartItem item: cartItems) {
				  if (item.getProductId() == idValue) {
					  cartItems.remove(item);
					  session.setAttribute("cart_items", cartItems);
					  break;
				  }
			   }
		  }	
	        return "redirect:cart"; 
	    }	

	  @RequestMapping(value = "/checkout", method = RequestMethod.GET)
	    public String checkout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {

		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before checking out");
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
	        return "checkout"; 
	    }

	  @RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
	    public String completePurchase(ModelMap map, javax.servlet.http.HttpServletRequest request)
	    {

		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing purchase");
		  } else {

			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);

			  long userId = (Long) session.getAttribute("user_id") ;

			  Purchase purchase = new Purchase();
			  purchase.setUserId(userId);
			  purchase.setDate(Calendar.getInstance().getTime());
			  purchase.setTotal(totalValue);
			  long purchaseId = purchaseService.updatePurchase(purchase);

			  for(CartItem item: cartItems) {
				  PurchaseItem pItem = new PurchaseItem();
				  pItem.setPurchaseId(purchaseId);
				  pItem.setProductId(item.getProductId());
				  pItem.setUserId(userId);
				  pItem.setRate(item.getRate());
				  pItem.setQty(item.getQty());
				  pItem.setPrice(item.getPrice());

				  purchaseItemService.updateItem(pItem);
			  }
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }

	        return "redirect:confirm";
	    }

	  @RequestMapping(value = "/gateway", method = RequestMethod.GET)
	    public String gateway(ModelMap map, javax.servlet.http.HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before making payment");
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }

		  map.addAttribute("pageTitle", "SPORTY SHOES - PAYMENT GATEWAY");
	        return "gateway";
	    }

	  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
	    public String confirm(ModelMap map, javax.servlet.http.HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing the purchase");
		  } else {

			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);


			  cartItems.clear();
			  session.setAttribute("cart_items", null);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
	        return "confirm";
	    }

	private boolean isItemInCart(List<CartItem> list, long item) {
		boolean retVal = false;

		for(CartItem thisItem: list) {
			if (item == thisItem.getProductId()) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	  private BigDecimal getCartValue(List<CartItem> list) {
		  BigDecimal total = new BigDecimal(0.0);

		  for(CartItem item: list) {
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty()));
			  total= total.add(dprice);
		   }
		  return total;
	  }

}
