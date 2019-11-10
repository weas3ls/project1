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

public class ManagerResolveServlet extends HttpServlet {
	ObjectMapper om = new ObjectMapper();
	ReimbursementService reimbursementService = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String info = req.getPathInfo();
		
		HttpSession session = req.getSession(false);
		Integer userId = null;
		if (session != null) {
			userId = Integer.valueOf((String) session.getAttribute("userid").toString());
		}
		
		if (info == null) {
			resp.setStatus(400);
			return;
		}
		
		/*
		 * I am not sure what the routing information for the manager resolve, so here is the information / variables needed to
		 * add the functionality to approve / deny tickets. So here is my idea:
		 * 
		 *  /resolve/{status ID of ticket}/{ticket ID}
		 *  
		 *  For an example, if the URL is localhost:4200/project1/resolve/2/10, it will set reimbursement in ers_reimbursements at id 10 to status
		 *  2 (approved) and set the current timestamp to the current date it was resolved
		 * 
		 */
		
		String[] parts = info.split("/");
		if (parts.length <= 0) {
			resp.setStatus(400);
			return;
		}
		int ticketId = 0;
		int statusId = 0;
		
		try {
			ticketId = Integer.parseInt(parts[2]);
			statusId = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) {
			resp.setStatus(400);
			return;
		}
		
		Reimbursement resolvedReimbursement = reimbursementService.getTicketById(ticketId);
		// Checks to see if the manager is not approving their own status
		if (userId != resolvedReimbursement.getRequestee_id()) {
			reimbursementService.resolveTicket(userId, statusId, resolvedReimbursement);
			resp.setStatus(200);
			resp.getWriter().write("Ticket successfully resolved!");
		} else {
			resp.setStatus(403);
			resp.getWriter().write("Manager cannot approve of their own tickets.");
			return;
		}
		
	}
	
}
