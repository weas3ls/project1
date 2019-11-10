package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Reimbursement {
    private int id;
    private BigDecimal amount;
    private String description;
    private int requestee_id;
    private Integer resolver_id = null;
    private int status_id;
    private int type_id;
    private Date date_submitted;
    private Date date_resolved;

    // Constructor
    public Reimbursement() {
    }

    public Reimbursement(BigDecimal amount, String description, int requestee_id, int resolver_id, int status_id,
            int type_id, Date date_submitted, Date date_resolved) {
        super();
        this.amount = amount;
        this.description = description;
        this.requestee_id = requestee_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
        this.date_resolved = date_resolved;
        this.date_submitted = date_submitted;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /* Getters / Setters */
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the requestee_id
     */
    public int getRequestee_id() {
        return requestee_id;
    }

    /**
     * @param requestee_id the requestee_id to set
     */
    public void setRequestee_id(int requestee_id) {
        this.requestee_id = requestee_id;
    }

    /**
     * @return the resolvee_id
     */
    public Integer getResolver_id() {
        return resolver_id;
    }

    /**
     * @param resolvee_id the resolvee_id to set
     */
    public void setResolver_id(Integer resolver_id) {
        this.resolver_id = resolver_id;
    }

    /**
     * @return the status_id
     */
    public int getStatus_id() {
        return status_id;
    }

    /**
     * @param status_id the status_id to set
     */
    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    /**
     * @return the type_id
     */
    public int getType_id() {
        return type_id;
    }

    /**
     * @param type_id the type_id to set
     */
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public Date getDate_submitted() {
        return this.date_submitted;
    }

    public void setDate_submitted(Date date_submitted) {
        this.date_submitted = date_submitted;
    }

    public Date getDate_resolved() {
        return this.date_resolved;
    }

    public void setDate_resolved(Date date_resolved) {
        this.date_resolved = date_resolved;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reimbursement)) {
            return false;
        }
        Reimbursement reimbursement = (Reimbursement) o;
        return id == reimbursement.id && Objects.equals(amount, reimbursement.amount)
                && Objects.equals(description, reimbursement.description) && requestee_id == reimbursement.requestee_id
                && Objects.equals(resolver_id, reimbursement.resolver_id) && status_id == reimbursement.status_id
                && type_id == reimbursement.type_id && Objects.equals(date_submitted, reimbursement.date_submitted)
                && Objects.equals(date_resolved, reimbursement.date_resolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, requestee_id, resolver_id, status_id, type_id, date_submitted,
                date_resolved);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", amount='" + getAmount() + "'" + ", description='" + getDescription()
                + "'" + ", requestee_id='" + getRequestee_id() + "'" + ", resolver_id='" + getResolver_id() + "'"
                + ", status_id='" + getStatus_id() + "'" + ", type_id='" + getType_id() + "'" + ", date_submitted='"
                + getDate_submitted() + "'" + ", date_resolved='" + getDate_resolved() + "'" + "}";
    }

}
