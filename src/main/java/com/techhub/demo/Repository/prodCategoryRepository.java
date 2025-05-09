package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;

@Repository

public class prodCategoryRepository {
	List<productCategoryModel> list;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean isAddNewCategory(productCategoryModel category) {
		int value = jdbcTemplate.update("insert into ProdCategory values ('0',?)", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
//						ps.setInt(1, category.getPcid());
				ps.setString(1, category.getPcname());

			}

		});

		return value > 0 ? true : false;
	}

	public List<productCategoryModel> getAllProductCategory() {
		list = jdbcTemplate.query("select *from ProdCategory", new RowMapper<productCategoryModel>() {

			@Override
			public productCategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				productCategoryModel Reg = new productCategoryModel();
				Reg.setPcid(rs.getInt(1));
				Reg.setPcname(rs.getString(2));
				return Reg;
			}

		});
		return list; // RegService.getAllRegistration();
	}

	public boolean isUpdateCategory(int CgId, productCategoryModel Category) {
		int result = jdbcTemplate.update("UPDATE ProdCategory SET pcname = ? WHERE pcid = ?", Category.getPcname(),
				CgId);
		return result > 0;

	}

	public boolean removeCategory(int cId) {
		String sql = "DELETE FROM ProdCategory WHERE pcid = ?";
		int value = jdbcTemplate.update(sql, cId);
		return value > 0 ? true : false;
	}
}
