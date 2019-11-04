package com.revature.models;

import java.math.BigDecimal;

public class Reimbursement {
	private int id;
	private BigDecimal amount;
	private int requestee_id;
	private String requestee_name;
	private int resolvee_id;
	private String resolvee_name;
	private int status_id;
	private String status;
	private int type_id;
	private String type;
	
	// Constructor
	public Reimbursement(int id, BigDecimal amount, int requestee_id, int resolvee_id,
			int status_id, int type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.requestee_id = requestee_id;
		this.requestee_name = requestee_name;
		this.resolvee_id = resolvee_id;
		this.resolvee_name = resolvee_name;
		this.status_id = status_id;
		this.status = status;
		this.type_id = type_id;
		this.type = type;
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
	 * @return the requestee_name
	 */
	public String getRequestee_name() {
		return requestee_name;
	}

	/**
	 * @param requestee_name the requestee_name to set
	 */
	public void setRequestee_name(String requestee_name) {
		this.requestee_name = requestee_name;
	}

	/**
	 * @return the resolvee_id
	 */
	public int getResolvee_id() {
		return resolvee_id;
	}

	/**
	 * @param resolvee_id the resolvee_id to set
	 */
	public void setResolvee_id(int resolvee_id) {
		this.resolvee_id = resolvee_id;
	}

	/**
	 * @return the resolvee_name
	 */
	public String getResolvee_name() {
		return resolvee_name;
	}

	/**
	 * @param resolvee_name the resolvee_name to set
	 */
	public void setResolvee_name(String resolvee_name) {
		this.resolvee_name = resolvee_name;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/* HashCode and Equal */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + id;
		result = prime * result + requestee_id;
		result = prime * result + ((requestee_name == null) ? 0 : requestee_name.hashCode());
		result = prime * result + resolvee_id;
		result = prime * result + ((resolvee_name == null) ? 0 : resolvee_name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + status_id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (requestee_name == null) {
			if (other.requestee_name != null)
				return false;
		} else if (!requestee_name.equals(other.requestee_name))
			return false;
		if (resolvee_id != other.resolvee_id)
			return false;
		if (resolvee_name == null) {
			if (other.resolvee_name != null)
				return false;
		} else if (!resolvee_name.equals(other.resolvee_name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (status_id != other.status_id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}

	/* toString method */

	@Override
	public String toString() {
		return (this.id + "\t" + this.amount + "\t" + this.requestee_id + "\t"
				+ this.requestee_name + "\t" + this.resolvee_id + "\t" + this.resolvee_name + "\t"
				+ this.status + "\t"  + type);
	}
	
	
	
}
