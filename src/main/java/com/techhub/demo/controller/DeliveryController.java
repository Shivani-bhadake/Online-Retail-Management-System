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
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.deliveryModel;
import com.techhub.demo.Service.DeliveryService;

@RestController
@RequestMapping("/Retail-M-System/Deli")
public class DeliveryController {

	@Autowired
	@Qualifier("del")
	private DeliveryService dService;

	@PostMapping("/addDelivery")
	public String addNewDelivery(@RequestBody deliveryModel delivery) {
		boolean b = dService.AddNewDelivery(delivery);
		return b ? "New Delivery Added" : "New Delivery is not Added";
	}

	@PutMapping("/assignDPerson")
	public String assignDeliveryPerson(@RequestBody deliveryModel model) {
		boolean updated = dService.assignDeliPersonByDId(model);
		if (updated) {
			return "Assigned delivery to delivery person with ID: " + model.getDeliPersonId();
		} else {
			throw new UserNotFoundException("Delivery assignment failed");
		}
	}

	@GetMapping("/viewdelivery/{id}")
	public List<deliveryModel> viewDeliveryDetailsById(@PathVariable("id") Integer deliId) {
		List<deliveryModel> deliveries = dService.getDeliveryDetailsById(deliId);

		if (deliveries == null || deliveries.isEmpty()) {
			throw new UserNotFoundException("No delivery details found for ID: " + deliId);
		}

		return deliveries;
	}

	@PutMapping("/updateDeliveryInfo")
	public String updateDelivery(@RequestBody deliveryModel delivery) {
		boolean updated = dService.updateDeliveryInfo(delivery);
		if (updated) {
			return "Delivery information updated successfully.";
		} else {
			throw new UserNotFoundException("Delivery not found or not updated.");
		}
	}

	@GetMapping("/viewdelivery")
	public List<deliveryModel> viewDeliveryinfo() {
		List<deliveryModel> deliveries = dService.getDeliveryDetails();

		if (deliveries == null || deliveries.isEmpty()) {
			throw new UserNotFoundException("No delivery details found for ID: ");
		}

		return deliveries;
	}

}
