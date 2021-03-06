
package com.sps.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

/**
 * @author Jai1.Kumar
 *
 */


@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private int id;

    private int custId;

    private Date activityCreateDate;

    private String productType;

    private String weight;

    private String quantity;

    private String rate;

    private String memo;

    private String totalAmount;

    private String expPerItem;

    private String otherExpense;

    private String deductionPercent;

    private String totalExpense;

    private String dueAmount;

    private String status;

    private String closeDate;

    private String creationDate;

    private String updateDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Customer customer;

    public String finalDue;

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

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
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the activityCreateDate
     */
    public Date getActivityCreateDate() {
        return activityCreateDate;
    }

    /**
     * @param activityCreateDate the activityCreateDate to set
     */
    public void setActivityCreateDate(Date activityCreateDate) {
        this.activityCreateDate = activityCreateDate;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
     * @return the expPerItem
     */
    public String getExpPerItem() {
        return expPerItem;
    }

    /**
     * @param expPerItem the expPerItem to set
     */
    public void setExpPerItem(String expPerItem) {
        this.expPerItem = expPerItem;
    }

    /**
     * @return the otherExpense
     */
    public String getOtherExpense() {
        return otherExpense;
    }

    /**
     * @param otherExpense the otherExpense to set
     */
    public void setOtherExpense(String otherExpense) {
        this.otherExpense = otherExpense;
    }

    /**
     * @return the totalExpense
     */
    public String getTotalExpense() {
        return totalExpense;
    }

    /**
     * @param totalExpense the totalExpense to set
     */
    public void setTotalExpense(String totalExpense) {
        this.totalExpense = totalExpense;
    }

    /**
     * @return the dueAmount
     */
    public String getDueAmount() {
        return dueAmount;
    }

    /**
     * @param dueAmount the dueAmount to set
     */
    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    /**
     * @return the closeDate
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activityCreateDate == null) ? 0 : activityCreateDate.hashCode());
        result = prime * result + ((closeDate == null) ? 0 : closeDate.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + custId;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((dueAmount == null) ? 0 : dueAmount.hashCode());
        result = prime * result + ((expPerItem == null) ? 0 : expPerItem.hashCode());
        result = prime * result + id;
        result = prime * result + ((memo == null) ? 0 : memo.hashCode());
        result = prime * result + ((otherExpense == null) ? 0 : otherExpense.hashCode());
        result = prime * result + ((productType == null) ? 0 : productType.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
        result = prime * result + ((totalExpense == null) ? 0 : totalExpense.hashCode());
        result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (activityCreateDate == null) {
            if (other.activityCreateDate != null)
                return false;
        } else if (!activityCreateDate.equals(other.activityCreateDate))
            return false;
        if (closeDate == null) {
            if (other.closeDate != null)
                return false;
        } else if (!closeDate.equals(other.closeDate))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (custId != other.custId)
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (dueAmount == null) {
            if (other.dueAmount != null)
                return false;
        } else if (!dueAmount.equals(other.dueAmount))
            return false;
        if (expPerItem == null) {
            if (other.expPerItem != null)
                return false;
        } else if (!expPerItem.equals(other.expPerItem))
            return false;
        if (id != other.id)
            return false;
        if (memo == null) {
            if (other.memo != null)
                return false;
        } else if (!memo.equals(other.memo))
            return false;
        if (otherExpense == null) {
            if (other.otherExpense != null)
                return false;
        } else if (!otherExpense.equals(other.otherExpense))
            return false;
        if (productType == null) {
            if (other.productType != null)
                return false;
        } else if (!productType.equals(other.productType))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        if (totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        } else if (!totalAmount.equals(other.totalAmount))
            return false;
        if (totalExpense == null) {
            if (other.totalExpense != null)
                return false;
        } else if (!totalExpense.equals(other.totalExpense))
            return false;
        if (updateDate == null) {
            if (other.updateDate != null)
                return false;
        } else if (!updateDate.equals(other.updateDate))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", custId=" + custId + ", activityCreateDate=" + activityCreateDate
                + ", productType=" + productType + ", weight=" + weight + ", quantity=" + quantity + ", rate=" + rate
                + ", memo=" + memo + ", totalAmount=" + totalAmount + ", expPerItem=" + expPerItem + ", otherExpense="
                + otherExpense + ", totalExpense=" + totalExpense + ", dueAmount=" + dueAmount + ", closeDate="
                + closeDate + ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", customer=" + customer
                + "]";
    }

    /**
     * @return the deductionPercent
     */
    public String getDeductionPercent() {
        return deductionPercent;
    }

    /**
     * @param deductionPercent the deductionPercent to set
     */
    public void setDeductionPercent(String deductionPercent) {
        this.deductionPercent = deductionPercent;
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
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
     * @return the finalDue
     */
    public String getFinalDue() {
        return finalDue;
    }

    /**
     * @param finalDue the finalDue to set
     */
    public void setFinalDue(String finalDue) {
        this.finalDue = finalDue;
    }


}
