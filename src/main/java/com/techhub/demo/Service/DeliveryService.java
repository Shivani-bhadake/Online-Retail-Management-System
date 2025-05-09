package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.deliveryModel;
import com.techhub.demo.Repository.DeliveryRepositoy;

@Service("del")
public class DeliveryService {

	@Autowired
	private DeliveryRepositoy repo;

	public boolean AddNewDelivery(deliveryModel model) {
		return repo.AddNewDelivery(model);
	}

	public boolean assignDeliPersonByDId(deliveryModel model) {
		return repo.assignDeliPersonByDId(model);
	}

	public List<deliveryModel> getDeliveryDetailsById(int id) {
		return repo.getDeliveryDetailsById(id);
	}

	public boolean updateDeliveryInfo(deliveryModel model) {
		return repo.updateDeliveryInfo(model);
	}

	public List<deliveryModel> getDeliveryDetails() {
		return repo.getDeliveryDetails();
	}

}
