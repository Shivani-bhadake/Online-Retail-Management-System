package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;
import com.techhub.demo.Service.ProductServicece;
@RestController

public class ProductController {
	@Autowired
	@Qualifier("pr")
	ProductServicece prod;
	
	@PostMapping("/addProduct")
	public String createProduct(@RequestBody  ProductModel  product) {

		boolean b = prod.isAddNewProduct(product);
		if (b) {
			return "new Product Added";
		} else {
			return "Product is not Added";
		}
	}
	@GetMapping("/viewAllProduct")
	public List<ProductModel> getAllCategory() {
		List<ProductModel> lt= prod.getAllProduct();
		if (lt.size() != 0) {
			return lt;
		} else {
			throw new UserNotFoundException("there is no data in database");
		}
	}
	@GetMapping("/deleteProduct/{name}")
	public String deleteProductName(@PathVariable("name") String product){
		boolean b = prod.isDeleteProduct(product);
		if(b)
		{
			return "Product is deleted";
		}
		else {
			throw new UserNotFoundException("Product is not found");
		}
	}
	
	@PutMapping("/updateproduct")
	public String updateUser(@RequestBody ProductModel  product)
	{
		boolean b = prod.isUpdateProduct(product);
				if(b)
				{
					return "product user is update"+product;
				}
				else
				{
					throw new UserNotFoundException("product is not found");
				}
	}
	@GetMapping("/searchproductByName/{name}")
	public ProductModel  searchUser(@PathVariable("name") String product)
	{
		ProductModel rm= prod.getProductByName(product);
		if(rm!=null)
		{
			return rm;
		}
		else {
			throw new UserNotFoundException("product is not found");
		}
	}
	@GetMapping("/searchProductByPattern/{pattern}")
	public List<ProductModel> searchUserByPattern(@PathVariable("pattern") String pattern)
	{
		List<ProductModel> rm= prod.getProductByPattern(pattern);
		if(rm!=null)
		{
			return rm;
		}
		else {
			throw new UserNotFoundException("product is not found");
		}
	}
}
