package com.techhub.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DBConfig {

	@Bean(name="jdbctemplate")
	public JdbcTemplate jdbcTemplate(DataSource dataSourse)
	{
		 return new JdbcTemplate(dataSourse);
	}
}