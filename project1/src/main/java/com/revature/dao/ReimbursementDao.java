package com.revature.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao {
    Connection conn;

    // Creates a connection to the database.
    public void setConnection(Connection conn) {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                System.out.println("Closing connection");
                this.conn.close();
            }
            this.conn = conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Establishes connection to database upon instantiation of ReimbursementDao
    // object.
    public ReimbursementDao() {
        this.conn = ConnectionUtil.getConnection();
    }

    /*
     * Required: None Modifies: None Effects: Returns a list of type User by
     * converting all entries
     * 
     */
    public List<Reimbursement> getAllReimbursements() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM ers_reimbursement";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                reimbursements.add(extractReimbursement(rs));
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in ReimbursementDao.getAllUsers()");
            return null;
        }

    }

    /*
     * REQUIRED: valid ResultSet MODIFIES: None EFFECTS: Extracts from result set
     * state in ers_reimbursement table and returns a Reimbursement object with same
     * state
     * 
     * 
     * Columns in ers_reimbursement reimb_id, reimb_amount, reimb_submitted,
     * reimb_resolved, reimb_description, reimb_receipt, reimb_author,
     * reimb_resolver, reimb_status_id, reimb_type_id
     * 
     */
    private Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("reimb_id");
        BigDecimal amount = rs.getBigDecimal("reimb_amount");
        Integer authorId = rs.getInt("reimb_author");
        Integer resolverId = rs.getInt("reimb_resolver");
        Integer statusId = rs.getInt("reimb_status_id");
        Integer typeId = rs.getInt("reimb_type_id");
        String description = rs.getString("reimb_description");

        Reimbursement reimbursement = new Reimbursement(id, amount, description, authorId, resolverId, statusId,
                typeId);
        return reimbursement;
    }
}
