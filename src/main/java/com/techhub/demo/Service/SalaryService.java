package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.SalaryModel;

import com.techhub.demo.Repository.SalaryRepository;
@Service("sal")
public class SalaryService {
	@Autowired
	private SalaryRepository salRepo;
	public boolean isAddNewSalaryUser(SalaryModel sal)
	{
		return salRepo.isAddNewSalaryUser(sal);
		
	}
	public boolean isUpdateEmpSalary(SalaryModel sal)
	{
		return salRepo.isUpdateEmpSalary(sal);
	}
	public List<SalaryModel> getAllEmpInfo()
	{
		return salRepo.getAllEmpInfo();
	}
	
}
