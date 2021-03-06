package com.revature.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class NewRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ObjectMapper om = new ObjectMapper();
    ReimbursementService reimbursementService = new ReimbursementService();
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

    	super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement ticketEntry = om.readValue(req.getReader(), Reimbursement.class);

//        HttpSession session = req.getSession(false);
//        System.out.println(session);
//
//        if (session == null) {
//            resp.setStatus(400);
//            resp.getWriter().write("Error 400: Session does not exist. Exiting...");
//        }
        

//        System.out.println(session.getAttribute("userid"));
        
//        int userId = Integer.parseInt(session.getAttribute("userid").toString());
//        ticketEntry.setRequestee_id(userId);
    	System.out.println(ticketEntry);
    	
    	File file = new File(ticketEntry.getReimb_receipt());
    	
        if (reimbursementService.insertTicketRequest(ticketEntry)) {
            resp.setStatus(200);
            resp.getWriter().write("Ticket request successfully submitted!");
        } else {
            resp.setStatus(400);
            resp.getWriter().write("Ticket failed to submit. Please try again.");
        }
    }
}
