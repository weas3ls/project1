package com.revature.servlets;

import java.io.IOException;

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

public class ManagerResolveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ObjectMapper om = new ObjectMapper();
    ReimbursementService reimbursementService = new ReimbursementService();
    UserServices userService = new UserServices();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // String info = req.getPathInfo();

//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            userId = Integer.valueOf((String) session.getAttribute("userid").toString());
//        }

        // Obtains the reimbursement JSON object that contains the status ID of ticket
        // as well as the ticket ID itself
        Reimbursement jsonTicket = om.readValue(req.getReader(), Reimbursement.class);

        Reimbursement resolvedReimbursement = reimbursementService.getTicketById(jsonTicket.getId());

        int statusId = jsonTicket.getstatus();
        
        int userId = jsonTicket.getResolver_id();

//        User user = userService.getUserById(userId);

        // Checks to see if user is manager. If not, then returns 403 status
//        if (user.getRoleId() != 1) {
//            resp.setStatus(403);
//            resp.getWriter().write("You are not authorized to access this page.");
//            return;
//        }

        // Checks to see if the manager is not approving their own status
        if (userId != resolvedReimbursement.getRequestee_id()) {
            reimbursementService.resolveTicket(userId, statusId, resolvedReimbursement);
            resp.setStatus(200);
            resp.getWriter().write("Ticket successfully resolved!");
        } else {
            resp.setStatus(403);
            resp.getWriter().write("Manager cannot approve their own tickets.");
            return;
        }

    }

}
