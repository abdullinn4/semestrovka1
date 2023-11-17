package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.SupplierDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Supplier;
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

@WebServlet("/supplier")
public class SupplierServlet extends HttpServlet {
    private SupplierDAOImpl supplierDAO;
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        supplierDAO = new SupplierDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        Supplier supplier = supplierDAO.get(Long.parseLong(id));
        Map<String,Object> data = new HashMap<>();
        data.put("supplier", supplier);
        Template template = FreemarkerConfigSingleton.getCfg().getTemplate("supplier.ftl");
        try {
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
