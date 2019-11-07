package com.revature.services;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

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

        // File file = new File("E:\\Revature\\Training\\Projects\\Project
        // 01\\MOCK_REIMBURSEMENT_DATA.json");
        File file = new File(
                "C:\\Users\\weas3ls\\Documents\\revature_training\\project1\\MOCK_REIMBURSEMENT_DATA.json");

        Reimbursement[] reimbursements;

        reimbursements = objectMapper.readValue(file, Reimbursement[].class);

        System.out.println("Java Reimbursement object created from JSON String :");

        for (Reimbursement reimbursement : reimbursements) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 25);
            reimbursement.setRequestee_id(randomNum);
            reimbursementDao.addTicket(reimbursement);
            System.out.println(reimbursement);
        }

    }

}
