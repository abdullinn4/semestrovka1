package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.ForumDAOImpl;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Forum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/create_forum")
public class CreateForumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("forum_name");
        String title = req.getParameter("forum_title");
        String topic = req.getParameter("forum_topic");
        String companyName = req.getParameter("create_company");
        Date createDate = Date.valueOf(req.getParameter("forum_date"));

        Forum forum = new Forum();
        forum.setName(name);
        forum.setTitle(title);
        forum.setTopic(topic);
        forum.setCompanyName(companyName);
        forum.setCreateDate(createDate);

        ForumDAOImpl.save(forum);
        resp.sendRedirect("./forums");
    }
}
