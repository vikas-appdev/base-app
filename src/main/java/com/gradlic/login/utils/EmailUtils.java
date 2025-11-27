package com.gradlic.login.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
        return "Hello " + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n" + getVerificationUrl(host, token) +"\n\nThe Support Team";
    }

    public static String getResetPassword(String name, String host, String token){
        return "Hello " + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n" + getResetPasswordUrl(host, token) +"\n\nThe Support Team";
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/verify/account?token=" + token;
    }

    private static String getResetPasswordUrl(String host, String token) {
        return host + "/verify/password?token=" + token;
    }
}
