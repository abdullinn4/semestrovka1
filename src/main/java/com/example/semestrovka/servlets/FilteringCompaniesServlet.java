package com.example.semestrovka.servlets;

import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.services.CompanyService;
import com.google.gson.Gson;
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

@WebServlet("/filtering_companies")
public class FilteringCompaniesServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> country = CompanyService.sortedCountries(req);
        List<String> businessSector = CompanyService.sortedBusinessSectors(req);
        List<Company> filteredCompanies = (List<Company>) req.getAttribute("filteredCompanies");
        Map<String,Object> data = new HashMap<>();
        data.put("companies",filteredCompanies);
        data.put("countries",country);
        data.put("businessSectors",businessSector);
        Template template = FreemarkerConfigSingleton.getCfg().getTemplate("filtering_companies.ftl");
        try {
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
