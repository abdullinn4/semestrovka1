package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.InvestmentDAOImpl;
import com.example.semestrovka.models.Investment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet("/apply_investment_servlet")
public class ApplyInvestmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initiatorName = req.getParameter("initiator_company");
        String amount = req.getParameter("sum_investment");
        String investDate = req.getParameter("invest_date");
        String partnerName = req.getParameter("company_name");
        String partnershipTitle = req.getParameter("investment_title");

        Investment investment = new Investment();
        investment.setInvestorName(initiatorName);
        investment.setAmount(amount);
        investment.setInvestmentDate(Date.valueOf(investDate));
        investment.setRecipientName(partnerName);
        investment.setTitle(partnershipTitle);

        InvestmentDAOImpl.save(investment);
        String encodedName = URLEncoder.encode(partnerName, StandardCharsets.UTF_8);
        resp.sendRedirect("./company?name=" + encodedName);

    }
}
