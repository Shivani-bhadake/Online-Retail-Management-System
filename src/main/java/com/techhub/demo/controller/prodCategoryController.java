package com.techhub.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;
import com.techhub.demo.Service.RegistrationService;
import com.techhub.demo.Service.prodCategoryService;
import com.techhub.demo.Exceptions.ErrorMessage;
import com.techhub.demo.Exceptions.UserNotFoundException;

@RestController
public class prodCategoryController {
	@Autowired
	@Qualifier("Cat")
	prodCategoryService categoryService;

	@PostMapping("/AddCategory")
	public String createRUser(@RequestBody productCategoryModel category) {

		boolean b = categoryService.isAddNewCategory(category);
		if (b) {
			return "new category is added";
		} else {
			return "category is not added";
		}
	}
	@GetMapping("/viewAllCategory")
	public List<productCategoryModel> getAllCategory() {
		List<productCategoryModel> lt= categoryService.getAllProductCategory();
		if (lt.size() != 0) {
			return lt;
		} else {
			throw new UserNotFoundException("there is no data in database");
		}
	}
}
