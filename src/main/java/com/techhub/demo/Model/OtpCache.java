package com.techhub.demo.Model;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OtpCache {
	  private static Map<String, String> otpCache = new HashMap<>();

	    public static void storeOtp(String email, String otp) {
	        otpCache.put(email, otp);
	    }

	    public static String getOtp(String email) {
	        return otpCache.get(email);
	    }
}
