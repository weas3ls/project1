package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Reimbursement {
    private int id;
    private BigDecimal amount;
    private String description;
    private int requestee_id;
    private String reimb_receipt;
    private Integer resolver_id = null;
    private int status;
    private int type;
    private Date date_submitted;
    private Date date_resolved;

    // Constructor
    public Reimbursement() {
    }

    public Reimbursement(BigDecimal amount, String description, String reimb_receipt, int requestee_id, int resolver_id, int status, int type,
            Date date_submitted, Date date_resolved) {
        super();
        this.amount = amount;
        this.description = description;
        this.reimb_receipt = reimb_receipt;
        this.requestee_id = requestee_id;
        this.resolver_id = resolver_id;
        this.status = status;
        this.type = type;
        this.date_resolved = date_resolved;
        this.date_submitted = date_submitted;
    }

    /**
	 * @return the reimb_receipt
	 */
	public String getReimb_receipt() {
		return reimb_receipt;
	}

	/**
	 * @param reimb_receipt the reimb_receipt to set
	 */
	public void setReimb_receipt(String reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
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
     * @return the status
     */
    public int getstatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setstatus(int status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public int gettype() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void settype(int type) {
        this.type = type;
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
                && Objects.equals(resolver_id, reimbursement.resolver_id) && status == reimbursement.status
                && type == reimbursement.type && Objects.equals(date_submitted, reimbursement.date_submitted)
                && Objects.equals(date_resolved, reimbursement.date_resolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, requestee_id, resolver_id, status, type, date_submitted,
                date_resolved);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", amount='" + getAmount() + "'" + ", description='" + getDescription()
                + "'" + ", requestee_id='" + getRequestee_id() + "'" + ", resolver_id='" + getResolver_id() + "'"
                + ", status='" + getstatus() + "'" + ", type='" + gettype() + "'" + ", date_submitted='"
                + getDate_submitted() + "'" + ", date_resolved='" + getDate_resolved() + "'" + "}";
    }

}
