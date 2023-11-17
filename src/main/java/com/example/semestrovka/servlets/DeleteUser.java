package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/delete_user")
public class DeleteUser extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try{
            Map<String, Object> data = new HashMap<>();
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("settings.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirmDeleteUserButton = req.getParameter("confirmDeleteUserButton");
        if (confirmDeleteUserButton!= null) {
            User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
            UserDAOImpl userDAO = new UserDAOImpl();
            userDAO.delete(user);

            LoginService.deleteSession(req);
            LoginService.deleteCookies(req,resp);
            resp.sendRedirect(req.getContextPath() + "/index");
        }
    }
}
