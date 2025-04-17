package com.techhub.demo.Model;

import lombok.Data;

@Data
public class CartModel {
	 private int cartId;
	 private int rid;
	 private int prodId;
	 private int quantity;
	
	 private RegistrationModel registration;
	 private ProductModel product;
}
