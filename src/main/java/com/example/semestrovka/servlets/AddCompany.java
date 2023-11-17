package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
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

@WebServlet("/add_company")
public class AddCompany extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            Company company = new Company();
            Map<String,Object> data = new HashMap<>();
            data.put("company",company);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("add_company.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String businessSector = req.getParameter("business_sector");
        String country = req.getParameter("country");
        String title = req.getParameter("title");
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
        Company company = new Company();
        company.setName(name);
        company.setBusinessSector(businessSector);
        company.setCountry(country);
        company.setTitle(title);
        if (CompanyDAOImpl.isExistCompany(company)){
            resp.sendRedirect("./add_company?error=company_exist");
        }else{
            CompanyDAOImpl.saveCompany(company,user);
            HttpSession session = req.getSession(true);
            session.setAttribute("company_name",company.getName());
            resp.sendRedirect(req.getContextPath() + "/add_company");
        }

    }
}
