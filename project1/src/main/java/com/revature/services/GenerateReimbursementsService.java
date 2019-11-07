package com.revature.services;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import java.util.concurrent.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.services.ReimbursementService;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.passwordhash.PasswordHashing;


public class GenerateReimbursementsService {
	
	static ReimbursementDao reimbursementDao = new ReimbursementDao();
	static ReimbursementService reimbursementService = new ReimbursementService();
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("E:\\Revature\\Training\\Projects\\Project 01\\MOCK_REIMBURSEMENT_DATA.json");

        Reimbursement[] reimbursements;

        reimbursements = objectMapper.readValue(file, Reimbursement[].class);
        
        System.out.println("Java Reimbursement object created from JSON String :");

        for (Reimbursement reimbursement : reimbursements) {
        	int randomNumber = ThreadLocalRandom.current().nextInt(1, 26);
        	reimbursement.setRequestee_id(randomNumber);
        	System.out.println(reimbursement);
        	//reimbursementDao.addTicket(reimbursement);
        }
        /*
        // Adds additional reimbursement tickets from managers
        Reimbursement managerReimbursement1 = new Reimbursement(BigDecimal.valueOf(455.76), "Manager Reimbursement for Joey", 24, 1, 2);
        Reimbursement managerReimbursement2 = new Reimbursement(BigDecimal.valueOf(343.82), "Manager Reimbursement for Ahmad", 25, 1, 2);
        Reimbursement managerReimbursement3 = new Reimbursement(BigDecimal.valueOf(103.43), "Manager Reimbursement for Yue", 26, 1, 2);
        
        Reimbursement employeeReimbursement = new Reimbursement(BigDecimal.valueOf(234.89), "Employee Reimbursement for 14", 14, 1, 3);
        
        reimbursementDao.addTicket(managerReimbursement1);
        reimbursementDao.addTicket(managerReimbursement2);
        reimbursementDao.addTicket(managerReimbursement3);
        reimbursementDao.addTicket(employeeReimbursement);
        
        */
        
        
        
        // Tests the getTickets function for manager 1
        List<Reimbursement> manager1TicketView = reimbursementService.getTickets(new User("manager1", "password123", "Joey", "Lepiscopo", "joeycentral@gmail.com", 1));
        List<Reimbursement> employeeTicketView = reimbursementService.getTickets(new User("ccayd", "PYMYxy0O12s", "Carolus", "Cay", "ccayd@mac.com", 2));
        
        System.out.println("Manager ticket view");
        reimbursementService.printTickets(manager1TicketView);
        System.out.println("Employee Ticket View");
        reimbursementService.printTickets(employeeTicketView);
        

	}
	
}
