package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.OrderItemModel;
import com.techhub.demo.Model.OrderModel;
import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;

import jakarta.annotation.PostConstruct;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleJdbcCall;

	@PostConstruct
	public void init() {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PlaceOrder");
	}

	public boolean addToOrderandOrderItm(String userName, String paymentMethod) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userName", userName);
			params.put("paymentMethod", paymentMethod);

			simpleJdbcCall.execute(params);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

//	public boolean addToOrder(OrderModel order) {
//	    int value = jdbcTemplate.update(
//	        "INSERT INTO Orders VALUES ('0', " +
//	        "(SELECT Rid FROM RegistrationMaster WHERE name=?), " +
//	        "CURDATE(), " +
//	        "(SELECT SUM(c.Price) FROM Cart c INNER JOIN RegistrationMaster r ON c.Rid = r.Rid WHERE r.name=?), ?)",
//	        new PreparedStatementSetter() {
//	            @Override
//	            public void setValues(PreparedStatement ps) throws SQLException {
//	                ps.setString(1, order.getRegistration().getName());
//	                ps.setString(2, order.getRegistration().getName());
//	                ps.setString(3, order.getPayMethod());
//	            }
//	        });
//
//	    return value > 0;
//	}

	public List<OrderModel> getAllOrders() {
		String sql = "SELECT o.OrderId, o.Rid, r.Name,r.email, r.Address, o.OrderDate, o.TotalAmount, o.PaymentMethod \r\n"
				+ "FROM Orders o JOIN RegistrationMaster r ON o.Rid = r.Rid   "; // where r.Name= ?";

		return jdbcTemplate.query(sql, new RowMapper<OrderModel>() {

			@Override
			public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderModel order = new OrderModel();

				order.setOrderId(rs.getInt("OrderId"));
				order.setOrderDate(rs.getString("OrderDate"));
				order.setTotalAmount(rs.getFloat("TotalAmount"));
				order.setPayMethod(rs.getString("PaymentMethod"));

				RegistrationModel reg = new RegistrationModel();
				reg.setId(rs.getInt("Rid"));
				reg.setName(rs.getString("Name"));
				reg.setEmail(rs.getString("email"));
				reg.setAddress(rs.getString("Address"));

				order.setRegistration(reg);

				return order;
			}
		});
	}

	public List<OrderModel> getAllOrdersByName(String name) {
		String sql = "SELECT \r\n"
				+ "    r.Rid, r.Name, r.Email,r.PhoneNo,r.Address, o.OrderId, o.OrderDate, oi.ProdId, p.ProdName,oi.Quantity,\r\n"
				+ "    oi.Unit_price, oi.Price,oi.FinalDeliveryDate, oi.DeliveryStatus,\r\n"
				+ "    o.TotalAmount,  o.PaymentMethod FROM Orders o JOIN OrderItems oi ON o.OrderId = oi.OrderId\r\n"
				+ "JOIN Product p ON oi.ProdId = p.ProdId JOIN RegistrationMaster r ON o.Rid = r.Rid\r\n"
				+ "WHERE r.Name =? ORDER BY o.OrderId;\r\n";

		return jdbcTemplate.query(sql, new Object[] { name }, new RowMapper<OrderModel>() {
			@Override
			public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderModel order = new OrderModel();

				order.setOrderId(rs.getInt("OrderId"));
				order.setOrderDate(rs.getString("OrderDate"));
				order.setTotalAmount(rs.getFloat("TotalAmount"));
				order.setPayMethod(rs.getString("PaymentMethod"));

				RegistrationModel reg = new RegistrationModel();
				reg.setId(rs.getInt("Rid"));
				reg.setName(rs.getString("Name"));
				reg.setEmail(rs.getString("Email"));
				reg.setContact(rs.getString("PhoneNo"));
				reg.setAddress(rs.getString("Address"));

				ProductModel prod = new ProductModel();
				prod.setProname(rs.getString("ProdName"));

				OrderItemModel item = new OrderItemModel();
				item.setProdId(rs.getInt("ProdId"));
				item.setQuantity(rs.getInt("Quantity"));
				item.setUnit_price(rs.getFloat("Unit_price"));
				item.setPrice(rs.getFloat("Price"));

				item.setFinalDeliveryDate(rs.getString("FinalDeliveryDate"));
				item.setDeliveryStatus(rs.getString("DeliveryStatus"));

				order.setRegistration(reg);
				order.setProduct(prod);
				order.setOrderItom(item);
				;
				;

				return order;
			}
		});
	}

	public boolean isUpdateOrderPayMethod(int orderId, OrderModel order) {
		int result = jdbcTemplate.update("UPDATE Orders SET PaymentMethod = ? WHERE OrderId = ?", order.getPayMethod(),
				orderId);
		return result > 0;
	}

	public boolean isUpdateOrdItemDStatus(int orderDetailId, OrderItemModel oItom) {
		int value = jdbcTemplate.update(
				"UPDATE orderItems SET FinalDeliveryDate = ?, DeliveryStatus = ? WHERE OrderDetailId = ?",
				oItom.getFinalDeliveryDate(), oItom.getDeliveryStatus(), orderDetailId);
		return value > 0;
	}

}
