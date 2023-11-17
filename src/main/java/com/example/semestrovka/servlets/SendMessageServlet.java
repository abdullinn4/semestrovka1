package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.MessageDAOImpl;
import com.example.semestrovka.DAO.UserDAOImpl;
import com.example.semestrovka.models.Message;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/send_message")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int forumId = Integer.parseInt(req.getParameter("forum_id"));
        User user = UserDAOImpl.getUserFromDb(LoginService.getUserBySession(req));
        String text = req.getParameter("text");
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = localTime.format(formatter);

        Message message = new Message();
        message.setForumId(forumId);
        message.setSender(user.getFirstName() + " " + user.getLastName());
        message.setText(text);
        message.setTimestamp(formattedTime);
        MessageDAOImpl.save(message);

        resp.sendRedirect("./forum?id=" + forumId);

    }
}
