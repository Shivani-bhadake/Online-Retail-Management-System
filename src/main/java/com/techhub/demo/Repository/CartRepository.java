package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.CartModel;
import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.SalaryModel;
import com.techhub.demo.Model.productCategoryModel;

@Repository
public class CartRepository {
	List<CartModel> list;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean addToCart(CartModel cart) {
		int value = jdbcTemplate.update("INSERT INTO Cart (CartId, Rid, ProdId, Unit_Price, Quantity) VALUES (" + "0, "
				+ "(SELECT Rid FROM RegistrationMaster WHERE name = ?), "
				+ "(SELECT ProdId FROM Product WHERE ProdName = ?), "
				+ "(SELECT Price FROM Product WHERE ProdName = ?), ?)", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, cart.getRegistration().getName());
						ps.setString(2, cart.getProduct().getProname());
						ps.setString(3, cart.getProduct().getProname());
						ps.setInt(4, cart.getQuantity());

					}

				});

		return value > 0 ? true : false;

	}

	public List<CartModel> getCartDetailsByUserName(String rName) {
		String sql = "SELECT c.CartId, r.Rid, r.Name, r.Email, r.Address, r.PhoneNo, "
				+ "p.ProdName, p.Description, p.Price, c.Quantity, c.Price AS CartPrice "
				+ "FROM Cart c JOIN RegistrationMaster r ON c.Rid = r.Rid "
				+ "JOIN Product p ON c.ProdId = p.ProdId WHERE r.Name = ?";

		return jdbcTemplate.query(sql, new Object[] { rName }, new RowMapper<CartModel>() {
			@Override
			public CartModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartModel cart = new CartModel();

				cart.setCartId(rs.getInt("CartId"));
				cart.setRid(rs.getInt("Rid"));
				cart.setUPrice(rs.getInt("CartPrice"));
				cart.setQuantity(rs.getInt("Quantity"));

				RegistrationModel reg = new RegistrationModel();
				reg.setId(rs.getInt("Rid"));
				reg.setName(rs.getString("Name"));
				reg.setEmail(rs.getString("Email"));
				reg.setAddress(rs.getString("Address"));
				reg.setContact(rs.getString("PhoneNo"));
				cart.setRegistration(reg);

				ProductModel prod = new ProductModel();
				prod.setProname(rs.getString("ProdName"));
				prod.setDescription(rs.getString("Description"));
				prod.setPrice(rs.getInt("Price"));

				cart.setProduct(prod);

				return cart;
			}
		});
	}

	public List<CartModel> getCartDetails() {
		String sql = "SELECT c.CartId, r.Rid, r.Name, r.Email, r.Address, r.PhoneNo, "
				+ "p.ProdName, p.Description, p.Price, c.Quantity, c.Price AS CartPrice "
				+ "FROM Cart c JOIN RegistrationMaster r ON c.Rid = r.Rid " + "JOIN Product p ON c.ProdId = p.ProdId ";

		return jdbcTemplate.query(sql, new RowMapper<CartModel>() {
			@Override
			public CartModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartModel cart = new CartModel();

				cart.setCartId(rs.getInt("CartId"));
				cart.setRid(rs.getInt("Rid"));
				cart.setUPrice(rs.getInt("CartPrice"));
				cart.setQuantity(rs.getInt("Quantity"));

				RegistrationModel reg = new RegistrationModel();
				reg.setId(rs.getInt("Rid"));
				reg.setName(rs.getString("Name"));
				reg.setEmail(rs.getString("Email"));
				reg.setAddress(rs.getString("Address"));
				reg.setContact(rs.getString("PhoneNo"));
				cart.setRegistration(reg);

				ProductModel prod = new ProductModel();
				prod.setProname(rs.getString("ProdName"));
				prod.setDescription(rs.getString("Description"));
				prod.setPrice(rs.getInt("Price"));

				cart.setProduct(prod);

				return cart;
			}
		});
	}

	public boolean removeCartItem(int cartId) {
		String sql = "DELETE FROM Cart WHERE CartId = ?";
		int value = jdbcTemplate.update(sql, cartId);
		return value > 0 ? true : false;
	}
//	    public boolean isUpdateCategory(int CgId,productCategoryModel Category)
//		{
//			 int result = jdbcTemplate.update(
//			            "UPDATE Cart SET pcname = ? WHERE CartId = ?",
//			            Category.getPcname(), CgId
//			        );		return result>0;
//			
//		}

}
