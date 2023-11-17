package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.SupplierDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.Supplier;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create_supplier")
public class CreateSupplierServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("supplier_name");
        String title = req.getParameter("supplier_title");
        String contactInfo = req.getParameter("supplier_contacts");
        String category = req.getParameter("supplier_category");
        String country = req.getParameter("country");
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));

        Supplier supplier = new Supplier();
        supplier.setUserId(user.getId());
        supplier.setCompanyName(name);
        supplier.setTitle(title);
        supplier.setContactInfo(contactInfo);
        supplier.setCategory(category);
        supplier.setCountry(country);

        if (SupplierDAOImpl.isCompanyExistDB(supplier)){
            resp.sendRedirect("./suppliers?error=company_exist");
        }else{
            SupplierDAOImpl.save(supplier);
            resp.sendRedirect("./suppliers");
        }
    }
}
