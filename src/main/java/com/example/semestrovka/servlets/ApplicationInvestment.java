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
@WebServlet("/application_investment")
public class ApplicationInvestment extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        InvestmentDAOImpl investmentDAO = new InvestmentDAOImpl();
        String idParam = req.getParameter("id");
        if(idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("/error?message=Id parameter is missing");
        }else {
            long id = Long.parseLong(idParam);
            try {
                Investment investment = investmentDAO.get(id);
                Map<String, Object> data = new HashMap<>();
                data.put("request",req);
                data.put("investment", investment);
                Template template = FreemarkerConfigSingleton.getCfg().getTemplate("application_investment.ftl");
                template.process(data, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvestmentDAOImpl investmentDAO = new InvestmentDAOImpl();
        long id = Long.parseLong(req.getParameter("id"));
        String statusDb = InvestmentDAOImpl.getStatusById((int) id);
        if (!statusDb.equals("Достигнуто") && !(statusDb.equals("Отклонено"))){
            String applyButton = req.getParameter("acceptInvestmentButton");

            Investment investment = new Investment();
            investment.setId(Math.toIntExact(id));

            String status;
            if (applyButton != null) {
                status = "Достигнуто";
            } else {
                status = "Отклонено";
            }
            investment.setStatus(status);
            investmentDAO.update(investment);
            resp.sendRedirect("./application_investment");
        }else{
            resp.sendRedirect(req.getContextPath() + "/application_investment?error=exist_status");
        }
    }
}
