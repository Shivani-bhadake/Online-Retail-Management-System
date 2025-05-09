package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.PaymentModel;

@Repository
public class PaymentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean addPayment(PaymentModel payment) {
		int value = jdbcTemplate.update(
				"INSERT INTO payments (orderid, paymentmethod, amount, status) VALUES (?, ?, ?, ?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, payment.getOrderId());
						ps.setString(2, payment.getPaymentMethod());
						ps.setBigDecimal(3, payment.getAmount());
						ps.setString(4, payment.getStatus());
					}
				});
		return value > 0;
	}

	public PaymentModel getPaymentById(int paymentId) {
		String sql = "SELECT paymentid, orderid, paymentmethod, amount, paymentdate, status FROM payments WHERE paymentid = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { paymentId }, (rs, rowNum) -> {
				PaymentModel model = new PaymentModel();
				model.setPaymentId(rs.getInt("paymentid"));
				model.setOrderId(rs.getInt("orderid"));
				model.setPaymentMethod(rs.getString("paymentmethod"));
				model.setAmount(rs.getBigDecimal("amount"));
				model.setStatus(rs.getString("status"));
				return model;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PaymentModel> getPaymentsByOrderId(int orderId) {
		String sql = "SELECT paymentid, orderid, paymentmethod, amount, paymentdate, status FROM payments WHERE orderid = ?";
		try {
			return jdbcTemplate.query(sql, new Object[] { orderId }, (rs, rowNum) -> {
				PaymentModel model = new PaymentModel();
				model.setPaymentId(rs.getInt("paymentid"));
				model.setOrderId(rs.getInt("orderid"));
				model.setPaymentMethod(rs.getString("paymentmethod"));
				model.setAmount(rs.getBigDecimal("amount"));
				model.setStatus(rs.getString("status"));
				return model;
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getPaymentStatus(int orderId) {
		String sql = "SELECT status FROM payments WHERE orderid = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { orderId }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return "No Payment Found";
		}
	}

	public boolean updatePaymentStatus(int orderId, String newStatus) {
		String sql = "UPDATE payments SET status = ? WHERE orderid = ?";
		return jdbcTemplate.update(sql, newStatus, orderId) > 0;
	}

}
