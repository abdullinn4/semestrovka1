package com.example.semestrovka.filters;

import com.example.semestrovka.helpers.Helper;
import com.example.semestrovka.models.User;
import com.example.semestrovka.services.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/*","!/authorization","!/registration","!/index"})
public class AuthenficationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = LoginService.getUserBySession(req);
        if (user == null){
            user = LoginService.getUserByToken(req);
            if (user == null){
                resp.sendRedirect(req.getContextPath() + "/index");
            }
        }
        filterChain.doFilter(req,resp);
    }
}
