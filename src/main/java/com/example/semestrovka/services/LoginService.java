package com.example.semestrovka.services;

import com.example.semestrovka.DAO.TokenDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.Helper;
import com.example.semestrovka.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginService {
    public static User loginUser(String email,String password){
        User user = UserDAOImpl.getUserFromDb(new User(email));
        String hashedPassword = Helper.hashPassword(password);
        user.setPassword(hashedPassword);
        return user;
    }
    public static void saveCookies(HttpServletRequest req, HttpServletResponse resp){
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String sessionId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("exist",sessionId);
        User user = loginUser(email,password);
        int userId = UserDAOImpl.getUserFromDb(user).getId();
        cookie.setMaxAge(7 * 24 * 60 * 60);
        TokenDAOImpl.saveCookies(userId,sessionId);
        resp.addCookie(cookie);
    }
    public static void getCookies(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("exist")){
                System.out.println(cookie.getValue());
            }
        }
    }
    public static User getUserBySession(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("logged") != null){
            String email = (String)session.getAttribute("logged");
            User user = new User(email);
            return user;
        }
        return null;
    }
    public static User getUserByToken(HttpServletRequest req){
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = loginUser(email,password);
        int userId = user.getId();
        List<Integer> allUserIdFromCookies = TokenDAOImpl.getUserIdInCookiesFromDB();
        for (int i = 0; i < allUserIdFromCookies.size();i++){
            if (allUserIdFromCookies.get(i) == userId){
                return user;
            }
        }
        return null;
    }
    public static void deleteSession(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    public static void deleteCookies(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }

}
