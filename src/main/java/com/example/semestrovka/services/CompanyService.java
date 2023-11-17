package com.example.semestrovka.services;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    private static CompanyDAOImpl companyDAO = new CompanyDAOImpl();
     public static Company getCompany(HttpServletRequest req){
         String companyName = req.getParameter("name");
         Company company = new Company(companyName);
         return company;
     }
     public static List<Company> sortedCompanies(HttpServletRequest req){
         User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
         List<Company> userCompanies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
         List<Company> allCompanies = companyDAO.getAll();
         List<Company> filteredCompany = new ArrayList<>(allCompanies);

         filteredCompany.removeAll(userCompanies);
         return filteredCompany;

     }
     public static List<String> sortedCountries(HttpServletRequest req){
         User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
         List<Company> userCompanies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
         List<Company> allCompanies = companyDAO.getAll();
         List<Company> filteredCompany = new ArrayList<>(allCompanies);

         filteredCompany.removeAll(userCompanies);

         List<String> countries = new ArrayList<>();
         for (Company company : filteredCompany) {
             String country = company.getCountry();
             if (!countries.contains(country)) {
                 countries.add(country);
             }
         }
         return countries;
     }
    public static List<String> sortedBusinessSectors(HttpServletRequest req){
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
        List<Company> userCompanies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        List<Company> allCompanies = companyDAO.getAll();
        List<Company> filteredCompany = new ArrayList<>(allCompanies);

        filteredCompany.removeAll(userCompanies);

        List<String> businessSectors = new ArrayList<>();
        for (Company company : filteredCompany) {
            String businessSector = company.getBusinessSector();
            if (!businessSectors.contains(businessSector)) {
                businessSectors.add(businessSector);
            }
        }
        return businessSectors;
    }
}
