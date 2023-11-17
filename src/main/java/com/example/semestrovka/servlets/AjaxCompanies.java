package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.CompanyService;
import com.example.semestrovka.services.LoginService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ajax_companies")
public class AjaxCompanies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> filteredCompany = CompanyService.sortedCompanies(req);

        Gson gson = new Gson();
        String jsonCompanies = gson.toJson(filteredCompany);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonCompanies);
    }
}
