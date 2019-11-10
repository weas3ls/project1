package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserServices;

public class ManagerSelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReimbursementService reimbursementService = new ReimbursementService();
    private UserServices userService = new UserServices();

    ObjectMapper om = new ObjectMapper();
    List<Reimbursement> ticketList = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(400);
            resp.getWriter().write("Session not found in manager tools.");
            return;
        }
        int managerId = Integer.valueOf(session.getAttribute("userid").toString());
        System.out.println("ManagerServlet managerId: " + managerId);
        User isManager = userService.getUserById(managerId);
        isManager.setPassword("");
        isManager.setPasswordSalt("");
        if (isManager != null) {
            if (isManager.getRoleId() != 1) {
                resp.setStatus(403);
                resp.getWriter().write("Only manager has access to manager tools");
                return;
            }
        }

        ticketList = reimbursementService.getAllTickets();

        om.writeValue(resp.getWriter(), ticketList);
        resp.setStatus(200);
    }
}
