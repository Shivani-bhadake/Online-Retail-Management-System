package com.techhub.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Model.LoginRequest;
import com.techhub.demo.Model.OtpCache;
import com.techhub.demo.Model.OTPRequest;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Service.EmailSenderService;
import com.techhub.demo.Service.RegistrationService;

import jakarta.servlet.http.HttpSession;

import com.techhub.demo.OTPGenerator;
import com.techhub.demo.Exceptions.ErrorMessage;
import com.techhub.demo.Exceptions.UserNotFoundException;

@RestController
@RequestMapping("/Retail-M-System/Reg")
@CrossOrigin("http://localhost:5173")
public class RegistrationController {

	@Autowired
	@Qualifier("Reg")
	private RegistrationService RegService;

	@Autowired
	private EmailSenderService emailSender;

	@PostMapping("/createRegUser")
	public String createRUser(@RequestBody RegistrationModel Register) {

		boolean b = RegService.isAddNewRegUser(Register);
		if (b) {
			return "new User Added";
		} else {
			return "User is not Added";
		}
	}

	@GetMapping("/viewAllReg")
	public List<RegistrationModel> getAllRegistration() {
		List<RegistrationModel> lt = RegService.getAllRegistration();
		if (lt.size() != 0) {
			return lt;
		} else {
			throw new UserNotFoundException("there is no data in database");
		}
	}

//	@GetMapping("/searchuserByName/{name}")
//	public RegistrationModel  searchUser(@PathVariable("name") String regname)
//	{
//		RegistrationModel rm= RegService.getUserByName(regname);
//		if(rm!=null)
//		{
//			return rm;
//		}
//		else {
//			throw new UserNotFoundException("User is not found");
//		}
//	}

	@GetMapping("/searchuserByPattern/{pattern}")
	public List<RegistrationModel> searchUser(@PathVariable("pattern") String pattern) {
		List<RegistrationModel> rm = RegService.getUserByPattern(pattern);
		if (rm != null) {
			return rm;
		} else {
			throw new UserNotFoundException("User is not found");
		}
	}

	@GetMapping("/deleteUserByID/{name}")
	public String deleteUserByName(@PathVariable("name") String regname) {
		boolean b = RegService.isDeleteUserByName(regname);
		if (b) {
			return "User is deleted";
		} else {
			throw new UserNotFoundException("User is not found");
		}
	}

	@PutMapping("/updateuser")
	public String updateUser(@RequestBody RegistrationModel registration) {
		boolean b = RegService.isUpdateUser(registration);
		if (b) {
			return "Registrated user is update" + registration;
		} else {
			throw new UserNotFoundException("User is not found");
		}
	}

	@PostMapping("/userLogin")
	public String userLogin(@RequestBody LoginRequest loginRequest, HttpSession session) {
		RegistrationModel user = RegService.userLogin(loginRequest.getEmail(), loginRequest.getPassword());
		if (user != null) {
			session.setAttribute("userId", user.getId()); // safer than static UserContext
			return "Logged in Successfully. Your User ID is: " + user.getId();
		} else {
			return "Login Failed !!";
		}
	}

	@PutMapping("/changePassword/{email}")
	public String changePassword(@PathVariable("email") String email, @RequestBody RegistrationModel model) {
		if (RegService.changePassword(email, model)) {
			return "Password Changed Successfully...";
		} else {
			return "Password not Changed !!! Please Try Again Later";
		}
	}

	private Map<String, String> otpCache = new HashMap<>();

	@PostMapping("/generate-otp/{email}")
	public String generateOtp(@PathVariable("email") String email) {
		String otp = OTPGenerator.generateOTP(6);
		OtpCache.storeOtp(email, otp);
		emailSender.sendEmail(email, "OTP for Verification", "Your OTP is: " + otp);
		return "OTP sent to your email";
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestBody OTPRequest otpRequest) {
		String storedOtp = OtpCache.getOtp(otpRequest.getEmail());
		if (storedOtp != null && storedOtp.equals(otpRequest.getOtp())) {
			return "OTP verified successfully";
		} else {
			return "Invalid OTP";
		}
	}

}
