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
import com.revature.services.UserServices;

public class NewRequestServlet extends HttpServlet {
	ObjectMapper om = new ObjectMapper();
	ReimbursementService reimbursementService = new ReimbursementService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement ticketEntry = om.readValue(req.getReader(), Reimbursement.class);
		
		HttpSession session = req.getSession(false);
		
		if (session == null) {
			resp.setStatus(400);
			resp.getWriter().write("Error 400: Session does not exist. Exiting...");
		}
		
		int userId = Integer.parseInt(session.getAttribute("userid").toString());
		ticketEntry.setRequestee_id(userId);
		
		if (reimbursementService.insertTicketRequest(ticketEntry)) {
			resp.setStatus(200);
			resp.getWriter().write("Ticket request successfully submitted!");
		} else {
			resp.setStatus(400);
			resp.getWriter().write("Ticket failed to submit. Please try again.");
		}
	}
}
