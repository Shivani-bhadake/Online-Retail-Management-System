package com.techhub.demo.Model;

import lombok.Data;

@Data
public class OrderModel {
	private int orderId;
	private int rid;
	private int prodId;
	private String OrderDate;
	private float TotalAmount;
	private String PayMethod;
	private CartModel cart;
	private RegistrationModel registration;
	private ProductModel product;
	private OrderItemModel orderItom;
}
