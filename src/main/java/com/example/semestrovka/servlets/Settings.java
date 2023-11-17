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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/settings")
public class Settings extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
            Map<String,Object> data = new HashMap<>();
            data.put("user",user);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("settings.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String country = req.getParameter("country");

        User user = LoginService.getUserBySession(req);
        Helper.addUserAttributes(user,firstName,lastName,country);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.update(user);
        resp.sendRedirect(req.getContextPath() + "/settings");
    }
}
