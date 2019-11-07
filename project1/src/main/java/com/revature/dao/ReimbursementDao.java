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
            System.out.println("SQLException in ReimbursementDao.getAllReimbursements()");
            return null;
        }

    }

    /**
     * REQUIRED: MODIFIES: EFFECTS:
     */
    public List<Reimbursement> getUserReimbursements(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                reimbursements.add(extractReimbursement(rs));
            }
            return reimbursements;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SQLException in ReimbursementDao.getUserReimbursements");
        }
        return null;
    }

    /*
     * REQUIRED: Valid Reimbursement reference MODIFIES: ers_reimbursements EFFECTS:
     * Adds reimbursement ticket to the ers_reimbursement table in the database.
     */
    public void addTicket(Reimbursement reimbursement) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)"
                    + "VALUES (?, ?, ?, null, ?, ?) RETURNING reimb_id;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBigDecimal(1, reimbursement.getAmount());
            statement.setString(2, reimbursement.getDescription());
            statement.setInt(3, reimbursement.getRequestee_id());
            statement.setInt(4, reimbursement.getStatus_id());
            statement.setInt(5, reimbursement.getType_id());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                reimbursement.setId(rs.getInt("reimb_id"));
            }

            System.out.println("Reimbursement has been requested.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in ReimbursementDao.addTicket method.");
            return;
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

        Reimbursement reimbursement = new Reimbursement(amount, description, authorId, resolverId, statusId, typeId);
        reimbursement.setId(id);
        reimbursement.setResolver_id(resolverId);
        return reimbursement;
    }
}
