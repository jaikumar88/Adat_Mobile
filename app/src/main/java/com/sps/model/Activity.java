package com.sps.model;




public class Activity {
	
	private int id;
	
	private int custId;
	
	private String activityCreateDate;
	
	private String activityType;
	
	private String intrestrate;
	
	private String status;
	
	private String memo;
	
	private String amount;
	
	private String intrest;
	
	private String closingDate;
	
	private String creationDate;
	
	private String updateDate;

	private int transId;
	
	private int partnerTransId;
	
	private Customer owner;
	
	
	private String intrestAmount;
	
	private String totalAmount;
	private String totalIntrest;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getActivityCreateDate() {
		return activityCreateDate;
	}

	public void setActivityCreateDate(String activityCreateDate) {
		this.activityCreateDate = activityCreateDate;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public String getIntrestrate() {
		return intrestrate;
	}

	public void setIntrestrate(String intrestrate) {
		this.intrestrate = intrestrate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityCreateDate == null) ? 0 : activityCreateDate.hashCode());
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + custId;
		result = prime * result + id;
		result = prime * result + ((intrestrate == null) ? 0 : intrestrate.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
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
		Activity other = (Activity) obj;
		if (activityCreateDate == null) {
			if (other.activityCreateDate != null)
				return false;
		} else if (!activityCreateDate.equals(other.activityCreateDate))
			return false;
		if (activityType == null) {
			if (other.activityType != null)
				return false;
		} else if (!activityType.equals(other.activityType))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (custId != other.custId)
			return false;
		if (id != other.id)
			return false;
		if (intrestrate == null) {
			if (other.intrestrate != null)
				return false;
		} else if (!intrestrate.equals(other.intrestrate))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", custId=" + custId + ", activityCreateDate=" + activityCreateDate
				+ ", activityType=" + activityType + ", intrestrate=" + intrestrate + ", status=" + status + ", memo="
				+ memo + ", amount=" + amount + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", owner=" + owner + "]";
	}

	

	/**
	 * @return the closingDate
	 */
	public String getClosingDate() {
		return closingDate;
	}

	/**
	 * @param closingDate the closingDate to set
	 */
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * @return the intrestAmount
	 */
	public String getIntrestAmount() {
		return intrestAmount;
	}

	/**
	 * @param intrestAmount the intrestAmount to set
	 */
	public void setIntrestAmount(String intrestAmount) {
		this.intrestAmount = intrestAmount;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the totalIntrest
	 */
	public String getTotalIntrest() {
		return totalIntrest;
	}

	/**
	 * @param totalIntrest the totalIntrest to set
	 */
	public void setTotalIntrest(String totalIntrest) {
		this.totalIntrest = totalIntrest;
	}

	/**
	 * @return the intrest
	 */
	public String getIntrest() {
		return intrest;
	}

	/**
	 * @param intrest the intrest to set
	 */
	public void setIntrest(String intrest) {
		this.intrest = intrest;
	}

	/**
	 * @return the transId
	 */
	public int getTransId() {
		return transId;
	}

	/**
	 * @param transId the transId to set
	 */
	public void setTransId(int transId) {
		this.transId = transId;
	}

	/**
	 * @return the partnerTransId
	 */
	public int getPartnerTransId() {
		return partnerTransId;
	}

	/**
	 * @param partnerTransId the partnerTransId to set
	 */
	public void setPartnerTransId(int partnerTransId) {
		this.partnerTransId = partnerTransId;
	}
	
	
	
	
	
	
	
	

}
