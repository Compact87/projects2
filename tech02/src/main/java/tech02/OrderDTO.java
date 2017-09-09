package tech02;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long orderID;
   private Date creationDate;
   private String customerName;
   private Float totalAmount;
   
   /* 
    * Getters Setters
    */
   
public Long getOrderID() {
	return orderID;
}
public void setOrderID(Long orderID) {
	this.orderID = orderID;
}
public Date getCreationDate() {
	return creationDate;
}
public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public Float getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Float totalAmount) {
	this.totalAmount = totalAmount;
}

/* 
 * Constructors
 */
public OrderDTO(Long orderID, Date creationDate, String customerName,
		Float totalAmount) {
	super();
	this.orderID = orderID;
	this.creationDate = creationDate;
	this.customerName = customerName;
	this.totalAmount = totalAmount;
}
/*
 * (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("OrderDTO [orderID=");
	builder.append(orderID);
	builder.append(", creationDate=");
	builder.append(creationDate);
	builder.append(", customerName=");
	builder.append(customerName);
	builder.append(", totalAmount=");
	builder.append(totalAmount);
	builder.append("]");
	return builder.toString();
}



}
