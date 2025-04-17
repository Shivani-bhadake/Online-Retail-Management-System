package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.techhub.demo.Model.RegistrationModel;

@Repository

public class RegisterRepository {
	List<RegistrationModel> list;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isAddNewRegUser(RegistrationModel Register)
	{
		int value = jdbcTemplate.update("insert into RegistrationMaster values (?,?, ?, ?, ?, ?, ?)",new PreparedStatementSetter()
				{

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, Register.getId());
						ps.setString(2, Register.getEmail());
						ps.setString(3, Register.getName());
						ps.setString(4, Register.getAddress());
						ps.setString(5, Register.getPassword());
						ps.setString(6, Register.getRole());
						ps.setString(7, Register.getContact());
						
					}
			
				});
		
				return value>0?true:false;
	}
	public List<RegistrationModel> getAllRegistration()
	{
		list=jdbcTemplate.query("select *from RegistrationMaster",new RowMapper<RegistrationModel>() {

			@Override
			public RegistrationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RegistrationModel  Reg = new RegistrationModel();
				Reg.setId(rs.getInt(1));
				Reg.setEmail(rs.getString(2));
				Reg.setName(rs.getString(3));
				Reg.setAddress(rs.getString(4));
				Reg.setPassword(rs.getString(5));
				Reg.setRole(rs.getString(6));
				Reg.setContact(rs.getString(7));
				return Reg;
			}
			
		});
		return list; //RegService.getAllRegistration();
	}
	
//	public RegistrationModel getUserByName(String Name)
//	{
//		list=jdbcTemplate.query("select *from RegistrationMaster where Name=?",new Object[] {Name},new RowMapper<RegistrationModel>() {
//
//			@Override
//			public RegistrationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//				RegistrationModel  Reg = new RegistrationModel();
//				Reg.setEmail(rs.getString(1));
//				Reg.setName(rs.getString(2));
//				Reg.setAddress(rs.getString(3));
//				Reg.setPassword(rs.getString(4));
//				Reg.setRole(rs.getString(5));
//				Reg.setContact(rs.getString(6));
//				return Reg;
//			}
//			
//		});
//		return list.size()>0?list.get(0):null; 
//	}
	
	public boolean isDeleteUserByName(String regname){
		  String sql = "DELETE FROM RegistrationMaster WHERE Name = ?";
		    int value = jdbcTemplate.update(sql, regname);
		return value>0?true:false;
	}
	
	public boolean isUpdateUser(RegistrationModel registration)
	{
		int value = jdbcTemplate.update("update RegistrationMaster set email=?,name=?,address=?,password=?,role=?,PhoneNo=? where Rid=?",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, registration.getEmail());
				ps.setString(2, registration.getName());
				ps.setString(3, registration.getAddress());
				ps.setString(4, registration.getPassword());
				ps.setString(5, registration.getRole());
				ps.setString(6, registration.getContact());
				ps.setInt(7, registration.getId());
			}
			
		});
		return value>0?true:false;
	}
	
	public RegistrationModel userLogin(String email, String password) {
	    try {
	        return jdbcTemplate.queryForObject(
	                "SELECT * FROM RegistrationMaster WHERE email = ? AND password = ?",
	                new Object[]{email, password},
	                new RowMapper<RegistrationModel>() {
	                    @Override
	                    public RegistrationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    	RegistrationModel user = new RegistrationModel();
	                        user.setId(rs.getInt("rid")); 
	                        user.setEmail(rs.getString("email"));
	                        user.setPassword(rs.getString("password"));
	                        return user;
	                    }
	                }
	        );
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}
	public List<RegistrationModel> getUserByPattern(String pattern)
	{
		list=jdbcTemplate.query("select *from RegistrationMaster where Name like '%"+pattern+"%'",new RowMapper<RegistrationModel>() {

			@Override
			public RegistrationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RegistrationModel  Reg = new RegistrationModel();
				Reg.setEmail(rs.getString(1));
				Reg.setName(rs.getString(2));
				Reg.setAddress(rs.getString(3));
				Reg.setPassword(rs.getString(4));
				Reg.setRole(rs.getString(5));
				Reg.setContact(rs.getString(6));
				return Reg;
			}
			
		});
		return list.size()>0?list:null; 
	}
	
	public boolean changePassword(String email,RegistrationModel model) {
		 int result = jdbcTemplate.update(
		            "UPDATE RegistrationMaster SET Password = ? WHERE email = ?",
		            model.getPassword(), email
		        );		return result>0;
	}
}
