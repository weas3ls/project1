package com.revature.services;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import java.util.concurrent.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class GenerateReimbursementsService {

    static ReimbursementDao reimbursementDao = new ReimbursementDao();
    static ReimbursementService reimbursementService = new ReimbursementService();

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

         File file = new File("E:\\Revature\\Training\\Projects\\Project 01\\MOCK_REIMBURSEMENT_DATA.json");
        //File file = new File(
         //       "C:\\Users\\weas3ls\\Documents\\revature_training\\project1\\MOCK_REIMBURSEMENT_DATA.json");

        Reimbursement[] reimbursements;

        reimbursements = objectMapper.readValue(file, Reimbursement[].class);

        System.out.println("Java Reimbursement object created from JSON String :");

        // for (Reimbursement reimbursement : reimbursements) {
        //     int randomNum = ThreadLocalRandom.current().nextInt(1, 25);
        //     reimbursement.setRequestee_id(randomNum);
        //     reimbursementDao.addTicket(reimbursement);
        //     System.out.println(reimbursement);
        // }

        // Testing the getTicketById method in reimbursementService.

        Reimbursement testReimb = reimbursementService.getTicketById(15); // Should contain state from entry in ers_reimbursement with id = 15

        System.out.println("ID, Amount, Description, requester ID, status ID, type ID");
        System.out.println(testReimb); // Expected: For information to match that of that in ers_reimbursement table in SQL PostgreSQL
        


    }

}
