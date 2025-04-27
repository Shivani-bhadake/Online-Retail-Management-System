package com.techhub.demo.Model;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OtpCache {
    private static final Map<String, String> otpMap = new ConcurrentHashMap<>();

    public static void storeOtp(String email, String otp) {
        otpMap.put(email, otp);
    }

    public static String getOtp(String email) {
        return otpMap.get(email);
    }

    public static void removeOtp(String email) {
        otpMap.remove(email);
    }
}
