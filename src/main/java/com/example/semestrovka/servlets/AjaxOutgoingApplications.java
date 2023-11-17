package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
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

@WebServlet("/ajax_outgoing_applications")
public class AjaxOutgoingApplications extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
        List<Partnership> applications = PartnershipDAOImpl.getAllOutgoingApplications(user);
        Gson gson = new Gson();
        String jsonApplications = gson.toJson(applications);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonApplications);

    }
}
