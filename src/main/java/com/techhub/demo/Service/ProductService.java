package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Repository.ProductRepository;

@Service(" pr")
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public boolean isAddNewProduct(ProductModel product) {
		return repo.isAddNewProduct(product);
	}

	public List<ProductModel> getAllProduct() {
		return repo.getAllProduct();

	}

	public boolean isDeleteProduct(String prodname) {
		return repo.isDeleteProduct(prodname);
	}

	public boolean isUpdateProduct(ProductModel Product) {
		return repo.isUpdateProduct(Product);
	}

	public ProductModel getProductByName(String Name) {
		return repo.getProductByName(Name);
	}

	public List<ProductModel> getProductByPattern(String pattern) {
		return repo.getProductByPattern(pattern);
	}
}
