package com.example.semestrovka.services;

import com.example.semestrovka.helpers.Helper;
import com.example.semestrovka.models.User;

public class RegistService {
    public static User registUser(String email,String password){
        User user = new User(email);
        String hashedPassword = Helper.hashPassword(password);
        user.setPassword(hashedPassword);
        return user;
    }
}
