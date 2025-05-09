package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.deliveryModel;

@Repository
public class DeliveryRepositoy {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean AddNewDelivery(deliveryModel model) {

		int value = jdbcTemplate.update(
				"INSERT INTO Delivery VALUES ('0',?, ?, ?, ?,(select Rid from RegistrationMaster where Email=?))",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, model.getOrderId());
						ps.setString(2, model.getDeliDate());
						ps.setString(3, model.getDeliAddress());
						ps.setString(4, model.getDeliMode());
						ps.setString(5, model.getRegistration().getEmail());
					}
				});

		return value > 0;
	}

	public boolean assignDeliPersonByDId(deliveryModel model) {
		int result = jdbcTemplate.update("UPDATE Delivery SET DeliPersonId = ? WHERE DeliId = ?",
				model.getDeliPersonId(), model.getDeliId());
		return result > 0;
	}

	public List<deliveryModel> getDeliveryDetailsById(int deliId) {
		String sql = "SELECT D.DeliId, D.DeliDate, D.DeliAddress, D.DeliMode, "
				+ "R.Name AS DeliveryPerson, R.PhoneNo AS DeliveryPersonContact "
				+ "FROM Delivery D JOIN RegistrationMaster R ON D.DeliPersonId = R.Rid " + "WHERE D.DeliId = ?";

		return jdbcTemplate.query(sql, new Object[] { deliId }, (rs, rowNum) -> {
			deliveryModel dto = new deliveryModel();
			dto.setDeliId(rs.getInt("DeliId"));
			dto.setDeliDate(rs.getString("DeliDate"));
			dto.setDeliAddress(rs.getString("DeliAddress"));
			dto.setDeliMode(rs.getString("DeliMode"));

			RegistrationModel reg = new RegistrationModel();
			reg.setName(rs.getString("DeliveryPerson"));
			reg.setContact(rs.getString("DeliveryPersonContact"));
			dto.setRegistration(reg);

			return dto;
		});
	}

	public boolean updateDeliveryInfo(deliveryModel model) {
		int result = jdbcTemplate.update("UPDATE Delivery SET DeliAddress = ?, DeliMode = ? WHERE DeliId = ?", ps -> {
			ps.setString(1, model.getDeliAddress());
			ps.setString(2, model.getDeliMode());
			ps.setInt(3, model.getDeliId());
		});
		return result > 0;
	}

	public List<deliveryModel> getDeliveryDetails() {
		String sql = "SELECT  D.DeliId,   D.DeliDate, D.DeliAddress,  D.DeliMode,R.Name AS DeliveryPerson, R.PhoneNo AS DeliveryPersonContact,\r\n"
				+ "    O.OrderId, O.OrderDate,  O.TotalAmount FROM Delivery D JOIN RegistrationMaster R ON D.DeliPersonId = R.Rid\r\n"
				+ "JOIN Orders O ON D.OrderId = O.OrderId;\r\n";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			deliveryModel dto = new deliveryModel();
			dto.setDeliId(rs.getInt("DeliId"));
			dto.setDeliDate(rs.getString("DeliDate"));
			dto.setDeliAddress(rs.getString("DeliAddress"));
			dto.setDeliMode(rs.getString("DeliMode"));

			RegistrationModel reg = new RegistrationModel();
			reg.setName(rs.getString("DeliveryPerson"));
			reg.setContact(rs.getString("DeliveryPersonContact"));
			dto.setRegistration(reg);

			return dto;
		});
	}

}
