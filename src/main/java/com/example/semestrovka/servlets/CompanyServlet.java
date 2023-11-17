package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
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

@WebServlet("/company")
public class CompanyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            String companyName = req.getParameter("name");
            Company company = CompanyDAOImpl.getCompanyFromDB(new Company(companyName));
            Map<String,Object> data = new HashMap<>();
            data.put("request",req);
            data.put("company",company);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("company.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
