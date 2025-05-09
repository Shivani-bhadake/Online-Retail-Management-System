package com.techhub.demo.Model;

import lombok.Data;

@Data
public class deliveryModel {

	private int deliId;
	private  int orderId;
	private String deliDate;
	private String deliAddress;
	private String deliMode;
	private int deliPersonId;
	private RegistrationModel registration;
	private OrderModel order;
}
