package com.example.semestrovka.helpers;



import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public class Helper {
    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hashedBytes = md.digest(passwordBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addUserAttributes(User user, String firstName, String lastName, String country){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCountry(country);
    }
    public static String getCurrentPage(HttpServletRequest req){
        String reqURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String currentPage = reqURI.substring(contextPath.length());
        if (currentPage.startsWith("/")){
            currentPage = currentPage.substring(1);
        }
        int index = currentPage.lastIndexOf(".");
        if (index != -1){
            currentPage = currentPage.substring(0,index);
        }
        return currentPage;

    }
}
