package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.PaymentModel;
import com.techhub.demo.Repository.PaymentRepository;

@Service("py")
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public boolean addPayment(PaymentModel model) {
		return paymentRepository.addPayment(model);
	}

	public PaymentModel getPaymentById(int id) {
		return paymentRepository.getPaymentById(id);
	}

	public List<PaymentModel> getPaymentsByOrderId(int orderId) {
		return paymentRepository.getPaymentsByOrderId(orderId);
	}

	public boolean updatePaymentStatus(int orderId, String newStatus) {
		return paymentRepository.updatePaymentStatus(orderId, newStatus);
	}

	public String getPaymentStatus(int orderId) {
		return paymentRepository.getPaymentStatus(orderId);
	}
}
