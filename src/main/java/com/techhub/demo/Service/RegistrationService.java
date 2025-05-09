package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Repository.RegisterRepository;

@Service("Reg")
public class RegistrationService {
	@Autowired
	private RegisterRepository RegRepo;

	public boolean isAddNewRegUser(RegistrationModel Register) {
		return RegRepo.isAddNewRegUser(Register);
	}

	public List<RegistrationModel> getAllRegistration() {
		return RegRepo.getAllRegistration();
	}

//	public RegistrationModel getUserByName(String name)
//	{
//		return RegRepo.getUserByName(name);
//	}
	public boolean isDeleteUserByName(String regname) {
		return RegRepo.isDeleteUserByName(regname);
	}

	public boolean isUpdateUser(RegistrationModel registration) {
		return RegRepo.isUpdateUser(registration);
	}

	public RegistrationModel userLogin(String email, String password) {
		return RegRepo.userLogin(email, password);
	}

	public List<RegistrationModel> getUserByPattern(String pattern) {
		return RegRepo.getUserByPattern(pattern);
	}

	public boolean changePassword(String email, RegistrationModel model) {
		return RegRepo.changePassword(email, model);
	}
}
