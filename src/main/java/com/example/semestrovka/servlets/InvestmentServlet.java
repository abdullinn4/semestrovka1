package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.InvestmentDAOImpl;
import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Investment;
import com.example.semestrovka.models.Partnership;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/investment")
public class InvestmentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        InvestmentDAOImpl investmentDAO = new InvestmentDAOImpl();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            Investment investment = investmentDAO.get(id);
            Map<String,Object> data = new HashMap<>();
            data.put("investment",investment);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("investment.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
