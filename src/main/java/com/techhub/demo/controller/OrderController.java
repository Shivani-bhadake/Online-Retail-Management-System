package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.OrderItemModel;
import com.techhub.demo.Model.OrderModel;
import com.techhub.demo.Model.productCategoryModel;
import com.techhub.demo.Service.OrderService;

@RestController
@RequestMapping("/Retail-M-System/orders")
public class OrderController {
	@Autowired
	@Qualifier("order")
	private OrderService orderS;

	@PostMapping("/addOrderItem/{name}/{paymentMethod}")
	public String addToOrderandOrderItm(@PathVariable String name, @PathVariable String paymentMethod) {
		boolean isSuccess = orderS.addToOrderandOrderItm(name, paymentMethod);

		if (isSuccess) {
			return "Order and order item placed successfully!";
		} else {
			throw new UserNotFoundException("Failed to place order.");
		}
	}

	@GetMapping("/viewAllOrder")
	public List<OrderModel> viewAllOrders() {
		List<OrderModel> orders = orderS.getAllOrders();

		if (orders.isEmpty()) {
			throw new UserNotFoundException("No orders found!");
		}

		return orders;
	}

	@GetMapping("/viewAllOrder/{name}")
	public List<OrderModel> viewAllOrdersByName(@PathVariable("name") String Username) {
		List<OrderModel> orders = orderS.getAllOrdersByName(Username);

		if (orders.isEmpty()) {
			throw new UserNotFoundException("No orders found!");
		}

		return orders;
	}

	@PutMapping("/updatePayMethod/{oId}")
	public String updatePayMethod(@PathVariable("oId") Integer id, @RequestBody OrderModel order) {
		boolean updated = orderS.isUpdateOrderPayMethod(id, order);
		if (updated) {
			return "Order PaymentMethod updated successfully: " + order;
		} else {
			throw new UserNotFoundException("Order not found with ID: " + id);
		}
	}

}
