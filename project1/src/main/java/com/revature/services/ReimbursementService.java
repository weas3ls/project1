package com.revature.services;

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
    public List<Reimbursement> getUserTickets(User user) {
        List<Reimbursement> userTickets = reimbursementDao.getUserReimbursements(user.getId());
        return userTickets;

        // int userRole = user.getRoleId();
        // switch (userRole) {
        // case 1:
        // // Returns list of all employee past and pending ticket requests with
        // exception
        // // of the employer's own request.
        // List<Reimbursement> allManagerReimbursements =
        // this.reimbursementDao.getAllReimbursements();

        // return allManagerReimbursements;
        // case 2:
        // // Returns all reimbursement tickets that are both pending and past by
        // employee
        // // ID
        // List<Reimbursement> allEmployeeReimbursements =
        // this.reimbursementDao.getAllReimbursements();

        // Iterator<Reimbursement> employeeIt = allEmployeeReimbursements.iterator();

        // while (employeeIt.hasNext()) {
        // if (employeeIt.next().getRequestee_id() == user.getId()) {
        // tickets.add(employeeIt.next());
        // }
        // }

        // default:
        // System.out
        // .println("Something has gone terribly wrong in the switch statement in the
        // getPastTickets method");
        // return null;
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
