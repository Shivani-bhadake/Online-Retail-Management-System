package com.techhub.demo.Model;

import lombok.Data;

@Data
public class OrderItemModel {
	private int oItemlId ;
	private int orderId;
	private int prodId;
	private int quantity;
	private float Unit_price;
	private float price;
	private String fstimatedDeliveryDate;
	private String finalDeliveryDate;
	private String deliveryStatus;
}
