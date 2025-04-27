package com.techhub.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.productCategoryModel;
import com.techhub.demo.Repository.RegisterRepository;
import com.techhub.demo.Repository.prodCategoryRepository;

@Service("Cat")
public class prodCategoryService {
    @Autowired
    private prodCategoryRepository Repo;

    public boolean isAddNewCategory(productCategoryModel category) {
        return Repo.isAddNewCategory(category);
    }
    
    public List<productCategoryModel> getAllProductCategory()
	{
    	return Repo.getAllProductCategory();
	}
    public boolean isUpdateCategory(int CgId,productCategoryModel Category)
	{
    	return Repo.isUpdateCategory(CgId,Category);
	}
    public boolean removeCategory(int cId) {
    	return Repo.removeCategory(cId);
    }
}