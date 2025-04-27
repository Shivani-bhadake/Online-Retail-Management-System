//package com.techhub.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.techhub.demo.OTPGenerator;
//import com.techhub.demo.Model.OTPRequest;
//import com.techhub.demo.Model.OtpCache;
//import com.techhub.demo.Service.EmailSenderService;
//
//@RestController
//public class OtpController {
//
//    @Autowired
//    @Qualifier("otp")
//    private EmailSenderService emailSender;
//
//    @PostMapping("/generate/{email}")
//    public String generateOtp(@PathVariable String email) {
//        String otp = OTPGenerator.generateOTP(6);
//        OtpCache.storeOtp(email, otp);
//        emailSender.sendEmail(email, "OTP for Verification", "Your OTP is: " + otp);
//        return "OTP sent to your email.";
//    }
//
//    @PostMapping("/verify")
//    public String verifyOtp(@RequestBody OTPRequest otpRequest) {
//        String storedOtp = OtpCache.getOtp(otpRequest.getEmail());
//        if (storedOtp != null && storedOtp.equals(otpRequest.getOtp())) {
//            OtpCache.removeOtp(otpRequest.getEmail());
//            return "OTP verified successfully!";
//        } else {
//            return "Invalid OTP!";
//        }
//    }
//}
