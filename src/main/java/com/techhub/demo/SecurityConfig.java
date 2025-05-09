package com.techhub.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled =true)
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeHttpRequests(auth -> auth.requestMatchers("/Retail-M-System/Reg/**")
				.permitAll().requestMatchers("/Retail-M-System/orders/**").permitAll()
				.requestMatchers("/Retail-M-System/products/**").permitAll()
				.requestMatchers("/Retail-M-System/prodCat/**").permitAll().requestMatchers("/Retail-M-System/Cart/**")
				.permitAll().requestMatchers("/Retail-M-System/Sal/**").permitAll()
				.requestMatchers("/Retail-M-System/Deli/**").permitAll().requestMatchers("/Retail-M-System/payments/**")
				.permitAll().requestMatchers("/Retail-M-System/review/**").permitAll()

				.anyRequest().authenticated());
		return http.build();

	}

}
