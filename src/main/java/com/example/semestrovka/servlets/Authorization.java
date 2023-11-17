package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.helpers.Helper;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Authorization extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try{
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("authorization.ftl");
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
        String rememberMe = req.getParameter("remember_me");
        User user = LoginService.loginUser(email,password);
        if (UserDAOImpl.isUserInDb(user)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("logged",email);
            if (rememberMe != null){
                LoginService.saveCookies(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/index_for_user");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?error=incorrect_password");
        }
    }
}
