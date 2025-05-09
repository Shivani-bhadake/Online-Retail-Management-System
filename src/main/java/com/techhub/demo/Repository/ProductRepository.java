package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;

@Repository
public class ProductRepository {
	List<ProductModel> list;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean isAddNewProduct(ProductModel product) {
		int value = jdbcTemplate.update(
				"insert into Product values (?,?,?, ?, ?, (select pcid from ProdCategory where pcname =?))",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, product.getPid());
						ps.setString(2, product.getProname());
						ps.setString(3, product.getDescription());
						ps.setInt(4, product.getPrice());
						ps.setInt(5, product.getStock());
						ps.setString(6, product.getPcname());

					}

				});

		return value > 0 ? true : false;
	}

	public List<ProductModel> getAllProduct() {
		list = jdbcTemplate.query("select *from Product", new RowMapper<ProductModel>() {

			@Override
			public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductModel pro = new ProductModel();
				pro.setPid(rs.getInt(1));
				pro.setProname(rs.getString(2));
				pro.setDescription(rs.getString(3));
				pro.setPrice(rs.getInt(4));
				pro.setStock(rs.getInt(5));

				return pro;
			}

		});
		return list; // RegService.getAllRegistration();
	}

	public boolean isDeleteProduct(String prodname) {
		String sql = "DELETE FROM Product WHERE ProdName = ?";
		int value = jdbcTemplate.update(sql, prodname);
		return value > 0 ? true : false;
	}

	public boolean isUpdateProduct(ProductModel Product) {
		int value = jdbcTemplate.update(
				"update Product set ProdName=?,Description=?,Description=?,Stock=?,pcid=? where ProdId=?",
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, Product.getProname());
						ps.setString(2, Product.getDescription());
						ps.setInt(3, Product.getPrice());
						ps.setInt(4, Product.getStock());

						ps.setInt(6, Product.getPid());
					}

				});
		return value > 0 ? true : false;
	}

	public ProductModel getProductByName(String Name) {
		list = jdbcTemplate.query("select *from Product where ProdName=?", new Object[] { Name },
				new RowMapper<ProductModel>() {

					@Override
					public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProductModel prod = new ProductModel();
						prod.setPid(rs.getInt(1));
						prod.setProname(rs.getString(2));
						prod.setDescription(rs.getString(3));
						prod.setPrice(rs.getInt(4));
						prod.setStock(rs.getInt(5));
//				prod.setPcname(rs.getString(6));
						return prod;
					}

				});
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<ProductModel> getProductByPattern(String pattern) {
		list = jdbcTemplate.query("select *from Product where ProdName like '%" + pattern + "%'",
				new RowMapper<ProductModel>() {

					@Override
					public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {

						ProductModel prod = new ProductModel();
						prod.setPid(rs.getInt(1));
						prod.setProname(rs.getString(2));
						prod.setDescription(rs.getString(3));
						prod.setPrice(rs.getInt(4));
						prod.setStock(rs.getInt(5));
//				prod.setPcname(rs.getString(6));
						return prod;
					}

				});
		return list.size() > 0 ? list : null;
	}
}
