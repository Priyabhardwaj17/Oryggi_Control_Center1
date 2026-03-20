package com.oryggi.utils;

public class EmailTest {
    public static void main(String[] args) {
        String otp = EmailUtils.getLatestOtp();
        if (otp != null) {
            System.out.println("✅ OTP Fetched Successfully: " + otp);
        } else {
            System.out.println("❌ OTP Not Found! Check email content & subject.");
        }
    }
}
