package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CooperationDAOImpl;
import com.example.semestrovka.DAO.SupplierDAOImpl;
import com.example.semestrovka.models.Cooperation;
import com.example.semestrovka.models.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/apply_cooperation")
public class ApplyCooperationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("company_name");
        String idSupplier = req.getParameter("supplier_id");
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = supplierDAO.get(Long.parseLong(idSupplier));
        Cooperation cooperation = new Cooperation();
        cooperation.setSupplierName(supplier.getCompanyName());
        cooperation.setPartnerName(name);
        if (CooperationDAOImpl.isSameCreateUser(cooperation)){
            resp.sendRedirect("/apply_cooperation?error=same_createUser");
        }else{
            if (CooperationDAOImpl.isExistCooperation(cooperation)){
                resp.sendRedirect("/apply_cooperation?error=exist_cooperation");
            }else{
                CooperationDAOImpl.save(cooperation);
                resp.sendRedirect("./supplier");
            }
        }


    }
}
