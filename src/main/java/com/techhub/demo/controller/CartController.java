package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.CartModel;
import com.techhub.demo.Service.CartService;
@RestController
public class CartController {
	@Autowired
	@Qualifier("cart")
	private CartService cartS;
	 @PostMapping("/addCart")
	    public String addToCart(@RequestBody CartModel cart) {
	        boolean result = cartS.addToCart(cart);
	        return result ? "Item added to cart!" : "Failed to add item.";
	    }
	 @GetMapping("/viewallCartInfo/{cartId}")
	 public CartModel viewCart(@PathVariable int cartId) {
	     CartModel cart = cartS.getCartDetailsById(cartId);
	     if (cart != null) {
	         return cart;
	     } else {
	         throw new UserNotFoundException("There is no data in the database");
	     }
	 }

}
