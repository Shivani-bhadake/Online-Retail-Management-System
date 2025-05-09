package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.PaymentModel;
import com.techhub.demo.Model.deliveryModel;
import com.techhub.demo.Model.productCategoryModel;
import com.techhub.demo.Service.PaymentService;

@RestController
@RequestMapping("/Retail-M-System/payments")
public class PaymentController {

	@Autowired
	@Qualifier("py")
	private PaymentService paymentService;

	@PostMapping("/addPayment")
	public String addPayment(@RequestBody PaymentModel payment) {
		boolean added = paymentService.addPayment(payment);
		return added ? "Payment added successfully." : "Payment failed.";
	}

	@GetMapping("/getpayment/{id}")
	public PaymentModel getPaymentById(@PathVariable int id) {
		PaymentModel payment = paymentService.getPaymentById(id);

		if (payment == null) {
			throw new UserNotFoundException("No payment found for Order ID: " + id);
		}

		return payment;
	}

	@GetMapping("/getorder/{orderId}")
	public List<PaymentModel> getPaymentsByOrderId(@PathVariable int orderId) {
		List<PaymentModel> payments = paymentService.getPaymentsByOrderId(orderId);

		if (payments == null || payments.isEmpty()) {
			throw new UserNotFoundException("No payments found for Order ID: " + orderId);
		}

		return payments;
	}

	@PutMapping("/updatePaymentStatus/{orderId}")
	public String updatePaymentStatus(@PathVariable("orderId") int orderId, @RequestBody PaymentModel payment) {

		boolean updated = paymentService.updatePaymentStatus(orderId, payment.getStatus());

		if (updated) {
			return "Payment status updated successfully for Order ID: " + orderId;
		} else {
			throw new UserNotFoundException("Payment not found for Order ID: " + orderId);
		}
	}

	@GetMapping("/getPaymentStatus/{orderId}")
	public String getPaymentStatus(@PathVariable int orderId) {
		String status = paymentService.getPaymentStatus(orderId);

		if ("No Payment Found".equals(status)) {
			throw new UserNotFoundException("No payment found for Order ID: " + orderId);
		}

		return "Payment Status for Order ID " + orderId + ": " + status;
	}

}
