package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.models.Partnership;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet("/apply_partnership_servlet")
public class ApplyPartnershipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initiatorName = req.getParameter("initiatorName");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String partnerName = req.getParameter("company_name");
        String partnershipTitle = req.getParameter("partnership_title");


        Partnership partnership = new Partnership();
        partnership.setInitiator(initiatorName);
        partnership.setStartDate(Date.valueOf(startDate));
        partnership.setEndDate(Date.valueOf(endDate));
        partnership.setPartner(partnerName);
        partnership.setTitle(partnershipTitle);

        if (PartnershipDAOImpl.isExistingPartnership(partnership)){
            resp.sendRedirect(req.getContextPath() + "/apply_partnership_servlet?error=existing_partnership");
        }else {
            PartnershipDAOImpl.save(partnership);

            String encodedName = URLEncoder.encode(partnerName, StandardCharsets.UTF_8);
            resp.sendRedirect("./company?name=" + encodedName);
        }
    }
}
