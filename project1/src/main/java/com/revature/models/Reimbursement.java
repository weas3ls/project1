package com.revature.models;

import java.math.BigDecimal;

public class Reimbursement {
    private int id;
    private BigDecimal amount;
    private String description;
    private int requestee_id;
    private Integer resolver_id = null;
    private int status_id;
    private int type_id;

    // Constructor
    public Reimbursement() {
    }

    public Reimbursement(BigDecimal amount, String description, int requestee_id, int resolver_id, int status_id,
            int type_id) {
        super();
        this.amount = amount;
        this.description = description;
        this.requestee_id = requestee_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
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

    /* HashCode and Equal */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + id;
        result = prime * result + requestee_id;
        result = prime * result + resolver_id;
        result = prime * result + status_id;
        result = prime * result + type_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reimbursement other = (Reimbursement) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (id != other.id)
            return false;
        if (requestee_id != other.requestee_id)
            return false;
        if (resolver_id != other.resolver_id)
            return false;
        if (status_id != other.status_id)
            return false;
        if (type_id != other.type_id)
            return false;
        return true;
    }

    /* toString method */

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", amount='" + getAmount() + "'" + ", description='" + getDescription()
                + "'" + ", requestee_id='" + getRequestee_id() + "'" + ", resolver_id='" + getResolver_id() + "'"
                + ", status_id='" + getStatus_id() + "'" + ", type_id='" + getType_id() + "'" + "}";
    }

}
