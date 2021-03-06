package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class SelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ObjectMapper om = new ObjectMapper();
    ReimbursementService reimbursementService = new ReimbursementService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String info = req.getPathInfo();

        HttpSession session = req.getSession(false);
        System.out.println(session);
        Integer userId = 0;
        if (session != null) {
            userId = Integer.valueOf((String) session.getAttribute("userid").toString());
        }
        System.out.println("user id " + userId);
        if (info == null) {
            resp.setStatus(400);
            return;
        }

        String[] parts = info.split("/");
        System.out.println(parts[0] + "/" + parts[1]);
        if (parts.length <= 0) {
            resp.setStatus(400);
            return;
        }
        int id = 0;

        try {
            id = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            return;
        }
        ;

        System.out.println("Reimbursement ID returned: " + id);

        Reimbursement selectedReimbursement = reimbursementService.getTicketById(id);

        // if (selectedReimbursement.getRequestee_id() != userId) {
        // resp.setStatus(404);
        // resp.getWriter().write("Failed to retrieve reimbursement.");
        // } else {
        // }
        om.writeValue(resp.getWriter(), selectedReimbursement);
        resp.setStatus(200);

    }
}
