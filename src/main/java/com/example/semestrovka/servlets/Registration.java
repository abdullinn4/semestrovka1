package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.RegistService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try{
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("registration.ftl");
            Map<String,Object> data = new HashMap<>();
            data.put("req",req);
            data.put("email",req.getParameter("email"));
            data.put("password",req.getParameter("password"));
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (UserDAOImpl.isUsernameInDb(email)) {
            resp.sendRedirect(req.getContextPath() + "/registration?error=username_exists");
        }else{
            User user = RegistService.registUser(email,password);
            UserDAOImpl.saveUser(user);
            resp.sendRedirect(req.getContextPath() + "/authorization");
        }
    }
}
