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
import com.techhub.demo.Model.SalaryModel;

@Repository
public class SalaryRepository {
	List<SalaryModel> list;
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public boolean isAddNewSalaryUser(SalaryModel sal) {
		int value = jdbcTemplate.update("insert into Salary values (?,(select Rid from RegistrationMaster where name=? and (role = 'Employee' OR role = 'Cashier')), ?)",new PreparedStatementSetter()
		{

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, sal.getSid());
				ps.setString(2, sal.getName());
				ps.setInt(3, sal.getSalary());
			
				
			}
	
		});

		return value>0?true:false;
	}
	public boolean isUpdateEmpSalary(SalaryModel sal)
	{
		int value = jdbcTemplate.update("update Salary set salary=?,(select Rid from RegistrationMaster where name=?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, sal.getSalary());
				ps.setString(2, sal.getName());
				
			}
			
		});
		return value>0?true:false;
	}
	
	public List<SalaryModel> getAllEmpInfo()
	{
		list=jdbcTemplate.query("SELECT r.Rid,r.Name, r.Email, r.Role,r.Address,r.PhoneNo ,s.Salary_Id,s.Salary,r.Password FROM Salary s INNER JOIN RegistrationMaster r ON s.Rid = r.Rid;\r\n",new RowMapper<SalaryModel>() {

			@Override
			public SalaryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				SalaryModel  pro = new SalaryModel();
				pro.setId(rs.getInt(1));;
				pro.setName(rs.getString(2));
				pro.setEmail(rs.getString(3));
				pro.setRole(rs.getString(4));
				pro.setAddress(rs.getString(5));
				pro.setContact(rs.getString(6));
				pro.setSid(rs.getInt(7));
				pro.setSalary(rs.getInt(8));
				pro.setPassword(rs.getString(9));
				return pro;
			}
			
		});
		return list; 
	}
}
