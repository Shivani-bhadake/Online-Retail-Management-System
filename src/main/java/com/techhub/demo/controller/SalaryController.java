package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.SalaryModel;
import com.techhub.demo.Service.SalaryService;

@RestController
@RequestMapping("/Retail-M-System/Sal")
public class SalaryController {

	@Autowired
	@Qualifier("sal")
	SalaryService salService;

	@PostMapping("/addsal")
	public String createRUser(@RequestBody SalaryModel salary) {

		boolean b = salService.isAddNewSalaryUser(salary);
		if (b) {
			return "Employee salary is Added";
		} else {
			return "Employee salary is not Added";
		}
	}

	@PutMapping("/updateEmpSalary")
	public String updateUser(@RequestBody SalaryModel salary) {
		boolean b = salService.isUpdateEmpSalary(salary);
		if (b) {
			return "Salary of Employee is update" + salary;
		} else {
			throw new UserNotFoundException("Salary is not found");
		}
	}

	@GetMapping("/viewEmpInfo")
	public List<SalaryModel> getAllEmployeeInfp() {
		List<SalaryModel> lt = salService.getAllEmpInfo();
		if (lt.size() != 0) {
			return lt;
		} else {
			throw new UserNotFoundException("there is no data in database");
		}
	}
}
