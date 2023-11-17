package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
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
import java.util.*;

@WebServlet("/filtering")
public class FilterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedIndustry = req.getParameterValues("industry");
        String[] selectedCountry = req.getParameterValues("country");

        List<Company> companies = filterCompanies(selectedIndustry,selectedCountry,req);
        Gson gson = new Gson();
        String jsonCompanies = gson.toJson(companies);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonCompanies);
    }
    public static List<Company> filterCompanies(String[] industry,String[] country,HttpServletRequest req){
        List<Company> filterCompany = new ArrayList<>();
        List<Company> allCompany = CompanyService.sortedCompanies(req);

        if (industry != null && industry.length > 0){
            for (Company company: allCompany){
                if(Arrays.asList(industry).contains(company.getBusinessSector())){
                    filterCompany.add(company);
                }
            }
        }else{
            filterCompany.addAll(allCompany);
        }
        if (country != null && country.length > 0){
            List<Company> industryCompany = new ArrayList<>(filterCompany);
            filterCompany.clear();

            for (Company company: industryCompany){
                if (Arrays.asList(country).contains(company.getCountry())){
                    filterCompany.add(company);
                }
            }
        }
        return filterCompany;
    }
}
