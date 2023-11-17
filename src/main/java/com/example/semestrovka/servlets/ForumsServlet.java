package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.ForumDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Forum;
import com.example.semestrovka.services.CompanyService;
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

@WebServlet("/forums")
public class ForumsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Forum> forums = ForumDAOImpl.getAllForums();
        Map<String,Object> data = new HashMap<>();
        data.put("forums", forums);
        Template template = FreemarkerConfigSingleton.getCfg().getTemplate("forums.ftl");
        try {
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
