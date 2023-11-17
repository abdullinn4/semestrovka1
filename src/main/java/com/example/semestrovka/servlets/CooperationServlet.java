package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.*;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.*;
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

@WebServlet("/cooperation")
public class CooperationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        CooperationDAOImpl cooperationDAO = new CooperationDAOImpl();
        try {
            String id = req.getParameter("id");
            Cooperation cooperation = cooperationDAO.get(Long.parseLong(id));
            String supplyName = cooperation.getSupplierName();
            String partnerName = cooperation.getPartnerName();
            Supplier supplier = SupplierDAOImpl.getSupplyByName(supplyName);
            Company partner = CompanyDAOImpl.getCompanyByName(partnerName);
            Map<String,Object> data = new HashMap<>();
            data.put("supplier",supplier);
            data.put("company",partner);
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate("cooperation.ftl");
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
