package com.example.semestrovka.servlets;

import com.example.semestrovka.DAO.PartnershipDAOImpl;
import com.example.semestrovka.helpers.FreemarkerConfigSingleton;
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
@WebServlet("/application_partnership")
public class ApplicationPartnership extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PartnershipDAOImpl partnershipDAO = new PartnershipDAOImpl();
        String idParam = req.getParameter("id");
        if(idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("/error?message=Id parameter is missing");
        }else {
            long id = Long.parseLong(idParam);
            try {
                Partnership partnership = partnershipDAO.get(id);
                Map<String, Object> data = new HashMap<>();
                data.put("request",req);
                data.put("partnership", partnership);
                Template template = FreemarkerConfigSingleton.getCfg().getTemplate("application_partnership.ftl");
                template.process(data, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PartnershipDAOImpl partnershipDAO = new PartnershipDAOImpl();
        long id = Long.parseLong(req.getParameter("id"));
        String statusDb = PartnershipDAOImpl.getStatusById((int) id);
        if (!statusDb.equals("Достигнуто") && !(statusDb.equals("Отклонено"))){
            String applyButton = req.getParameter("acceptPartnershipButton");

            Partnership partnership = new Partnership();
            partnership.setId(Math.toIntExact(id));

            String status;
            if (applyButton != null) {
                status = "Достигнуто";
            } else {
                status = "Отклонено";
            }
            partnership.setStatus(status);
            partnershipDAO.update(partnership);
            resp.sendRedirect("./application_partnership");
        }else{
            resp.sendRedirect(req.getContextPath() + "/application_partnership?error=exist_status");
        }
    }
}
