package com.techhub.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.CartModel;
import com.techhub.demo.Repository.CartRepository;
@Service("cart")
public class CartService {
	@Autowired
	private CartRepository Crepo;
	 public boolean addToCart(CartModel cart) {
		return Crepo.addToCart(cart);
	 }
	 public CartModel getCartDetailsById(int cartId) {
		 return Crepo.getCartDetailsById(cartId);
	 }
}
