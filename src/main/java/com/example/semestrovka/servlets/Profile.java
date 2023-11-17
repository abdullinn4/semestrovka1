package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.*;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.*;
import com.example.semestrovka.services.LoginService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
            List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
            List<Partnership> partnerships = PartnershipDAOImpl.getAllPartnerships(user);
            List<Investment> investments = InvestmentDAOImpl.getAllInvestments(user);
            List<Cooperation> cooperations = CooperationDAOImpl.getAllCooperations(user);
            Map<String,Object> data = new HashMap<>();
            data.put("user",user);
            data.put("companies",companies);
            data.put("partnerships",partnerships);
            data.put("investments",investments);
            data.put("cooperations",cooperations);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("profile.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
