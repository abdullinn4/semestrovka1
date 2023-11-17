package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.ForumDAOImpl;
import com.example.semestrovka.DAO.MessageDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
import com.example.semestrovka.models.Forum;
import com.example.semestrovka.models.Message;
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

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
    private  ForumDAOImpl forumDAO;
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        forumDAO = new ForumDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        Forum forum = forumDAO.get(Long.parseLong(id));
        List<Message> messages = MessageDAOImpl.getAllMessages(Integer.parseInt(id));
        Map<String,Object> data = new HashMap<>();
        data.put("forum", forum);
        data.put("messages",messages);
        Template template = FreemarkerConfigSingleton.getCfg().getTemplate("forum.ftl");
        try {
            template.process(data,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
