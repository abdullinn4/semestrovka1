package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.ForumDAOImpl;
import com.example.semestrovka.DAO.SupplierDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Forum;
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
import java.util.List;
import java.util.Map;

@WebServlet("/suppliers")
public class SuppliersServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Supplier> suppliers = SupplierDAOImpl.getAllSuppliers();
        Map<String,Object> data = new HashMap<>();
        data.put("suppliers", suppliers);
        Template template = FreemarkerConfigSingleton.getCfg().getTemplate("suppliers.ftl");
        try {
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
