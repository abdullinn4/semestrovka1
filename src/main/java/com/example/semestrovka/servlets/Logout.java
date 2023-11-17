
package com.example.semestrovka.servlets;

import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.helpers.Helper;
import com.example.semestrovka.services.LoginService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try{
            Map<String, Object> data = new HashMap<>();
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate( "index_for_user.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirmLogoutButton = req.getParameter("confirmLogoutButton");
        if (confirmLogoutButton!= null) {
            LoginService.deleteSession(req);
            LoginService.deleteCookies(req,resp);
            resp.sendRedirect(req.getContextPath() + "/index");
        }
    }
}

