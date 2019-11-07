package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {
    ReimbursementDao reimbursementDao = new ReimbursementDao();

    /*
     * View Past / Current Tickets
     * 
     * REQUIRED: User MODIFIES: EFFECTS: Returns list of past and pending
     * reimbursement tickets. What displays depends on the role of the user calling
     * this function.
     * 
     */
    public List<Reimbursement> getTickets(User user) {
        List<Reimbursement> tickets = new ArrayList<>();

        /*
         * First we want to know whether it is an employee or the manager who is viewing
         * the list of tickets. If the user role ID is employee, then we want the
         * employee to only see his or her past and pending tickets. However, if it's
         * the manager viewing tickets, then he or she has access to all employee
         * tickets. ID Role 1 Manager 2 Employee
         */

        int userRole = user.getRoleId();
        switch (userRole) {
        case 1:
            // Returns list of all employee past and pending ticket requests with exception of the employer's own request.
            List<Reimbursement> allManagerReimbursements = this.reimbursementDao.getAllReimbursements();
            
//            Iterator<Reimbursement> managerIt = allManagerReimbursements.iterator();
//            
//            while (managerIt.hasNext()) {
//            	if (managerIt.next().getRequestee_id() != user.getId()) {
//            		tickets.add(managerIt.next());
//            	}
//            }

            return allManagerReimbursements;
        case 2:
            // Returns all reimbursement tickets that are both pending and past by employee
            // ID
            List<Reimbursement> allEmployeeReimbursements = this.reimbursementDao.getAllReimbursements();

            Iterator<Reimbursement> employeeIt = allEmployeeReimbursements.iterator();

            while (employeeIt.hasNext()) {
                if (employeeIt.next().getRequestee_id() == user.getId()) {
                    tickets.add(employeeIt.next());
                }
            }

            return tickets;

        default:
            System.out
                    .println("Something has gone terribly wrong in the switch statement in the getPastTickets method");
            return null;
        }
    }

    /*
     * REQUIRED: Valid Reimbursement array list MODIFIES: Nothing EFFECTS: Prints
     * the container contents.
     * 
     * 
     * this.id + "\t" + this.amount + "\t" + this.requestee_id + "\t" +
     * this.requestee_name + "\t" + this.resolvee_id + "\t" + this.resolvee_name +
     * "\t" + this.status_id + "\t" + this.status + "\t" + this.type_id + "\t" +
     * type
     */
    public void printTickets(final List<Reimbursement> tickets) {
        Iterator<Reimbursement> ticketsIt = tickets.iterator();

        System.out.println("==================================================");
        System.out.println("=\t\tREIMBURSEMENT TICKETS");
        System.out.println("==================================================");
        System.out.println("ID\tRequesteeID\tRequestee Name\tResolveeID\tResolvee Name\tStatus\tType");

        int numEntries = 0;
        while (ticketsIt.hasNext()) {
            System.out.println(++numEntries + ticketsIt.toString());
        }

    }
}
