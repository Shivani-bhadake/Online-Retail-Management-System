package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.CartModel;
import com.techhub.demo.Model.ProductModel;
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
	 @GetMapping("/viewallCartInfo/{Name}")
	 public List<CartModel> viewCartInfoByName(@PathVariable("Name") String rName) {
	     List<CartModel> cartList = cartS.getCartDetailsByUserName(rName);

	     if (cartList != null && !cartList.isEmpty()) {
	         return cartList;
	     } else {
	         throw new UserNotFoundException("There is no cart data for this user in the database.");
	     }
	 }

	 @GetMapping("/deleteCartItem/{cartId}")
		public String deleteCart(@PathVariable("cartId") Integer id){
			boolean b = cartS.removeCartItem(id);
			if(b)
			{
				return "Cart is deleted";
			}
			else {
				throw new UserNotFoundException("Cart is not found");
			}
		}
	

}
