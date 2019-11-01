package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.*;

public class ReimbursementService {
	ReimbursementDao reimbursementDao = new ReimbursementDao();
	
	/*
	 * View Past / Current Tickets
	 * 
	 * REQUIRED: User
	 * MODIFIES: 
	 * EFFECTS: Returns list of past and pending reimbursement tickets. What displays depends on the 
	 * role of the user calling this function.
	 * 
	 */
	public List<Reimbursement> getPastTickets(User user) {
		List<Reimbursement> tickets = new ArrayList<>();
		
		/*
		 * First we want to know whether it is an employee or the manager who is viewing the list of tickets.
		 * If the user role ID is employee, then we want the employee to only see his or her past and pending
		 * tickets. However, if it's the manager viewing tickets, then he or she has access to all employee tickets.
		 */
		
		
	}
	
	
}