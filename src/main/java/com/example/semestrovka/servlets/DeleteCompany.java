package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.services.CompanyService;
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

@WebServlet("/delete_company")
public class DeleteCompany extends HttpServlet {
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
            data.put("company",company);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("company_profile.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirmDeleteCompanyButton = req.getParameter("confirmDeleteCompanyButton");
        String companyName = req.getParameter("company_name");
        if (confirmDeleteCompanyButton != null){
            Company company = new Company(companyName);
            CompanyDAOImpl companyDAO = new CompanyDAOImpl();
            companyDAO.delete(company);
            resp.sendRedirect(req.getContextPath() + "/profile");
        }else{
            resp.sendRedirect(req.getContextPath() + "/company_profile");
        }
    }
}
