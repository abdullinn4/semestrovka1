package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.InvestmentDAOImpl;
import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.Investment;
import com.example.semestrovka.models.Partnership;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/ajax_incoming_partnerships")
public class AjaxIncomingPartnerships extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
        List<Partnership> partnerships = PartnershipDAOImpl.getAllIncomingApplications(user);
        Gson gson = new Gson();
        String jsonPartnerships = gson.toJson(partnerships);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonPartnerships);

    }
}
