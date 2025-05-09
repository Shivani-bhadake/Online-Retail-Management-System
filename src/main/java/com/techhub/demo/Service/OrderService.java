package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.OrderItemModel;
import com.techhub.demo.Model.OrderModel;
import com.techhub.demo.Repository.OrderRepository;

@Service("order")
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	public boolean addToOrderandOrderItm(String userName, String paymentMethod) {
		// orderRepository.callPlaceOrder(userName, paymentMethod);
		return orderRepo.addToOrderandOrderItm(userName, paymentMethod);
	}

	public List<OrderModel> getAllOrders() {
		return orderRepo.getAllOrders();
	}

	public List<OrderModel> getAllOrdersByName(String name) {
		return orderRepo.getAllOrdersByName(name);
	}

	public boolean isUpdateOrderPayMethod(int orderId, OrderModel order) {
		return orderRepo.isUpdateOrderPayMethod(orderId, order);
	}

	public boolean isUpdateOrdItemDStatus(int orderDetailId, OrderItemModel oItom) {
		return orderRepo.isUpdateOrdItemDStatus(orderDetailId, oItom);
	}

};