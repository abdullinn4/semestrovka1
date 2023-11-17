package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.CompanyDAOImpl;
import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Partnership;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;
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

@WebServlet("/partnership")
public class PartnershipServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PartnershipDAOImpl partnershipDAO = new PartnershipDAOImpl();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            Partnership partnership = partnershipDAO.get(id);
            Map<String,Object> data = new HashMap<>();
            data.put("partnership",partnership);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("partnership.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
