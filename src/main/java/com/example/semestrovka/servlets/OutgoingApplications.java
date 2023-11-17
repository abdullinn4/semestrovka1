package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.InvestmentDAOImpl;
import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Investment;
import com.example.semestrovka.models.Partnership;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/outgoing_applications")
public class OutgoingApplications extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
            List<Partnership> applications = PartnershipDAOImpl.getAllOutgoingApplications(user);
            List<Investment> investments = InvestmentDAOImpl.getAllOutgoingApplications(user);
            Map<String,Object> data = new HashMap<>();


            data.put("applications", applications);
            data.put("investments",investments);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("outgoing_applications.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
