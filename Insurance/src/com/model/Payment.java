package com.model;
import java.sql.Date;
public class Payment {
 
    private String payment_id;
   private Customer customer_id;
   private Allocation allocation_id;
   private double premium;
   private Date payment_date;
   private String payment_mode;
   private int installment_count;
   
   
   
   //// 7 arg constructor
   public Payment(String payment_id, Customer customer_id,Allocation allocation_id, double premium, Date payment_date,
			String payment_mode, int installment_count) {
		super();
		this.payment_id = payment_id;
		this.customer_id = customer_id;
		this.allocation_id = allocation_id;
		this.premium = premium;
		this.payment_date = payment_date;
		this.payment_mode = payment_mode;
		this.installment_count = installment_count;
		
	}
   
   public Payment() {
	   super();
   }
   
   

   
   
 //getters and setters  
   
public String getPayment_id() {
	return payment_id;
}
public void setPayment_id(String payment_id) {
	this.payment_id = payment_id;
}



public double getPremium() {
	return premium;
}
public void setPremium(double premium) {
	this.premium = premium;
}
public Date getPayment_date() {
	return payment_date;
}
public void setPayment_date(Date payment_date) {
	this.payment_date = payment_date;
}
public String getPayment_mode() {
	return payment_mode;
}
public void setPayment_mode(String payment_mode) {
	this.payment_mode = payment_mode;
}
public int getInstallment_count() {
	return installment_count;
}
public void setInstallment_count(int installment_count) {
	this.installment_count = installment_count;
}



//foreign key getter and setter
public void setCustomer_id(Customer customer_id) {
	this.customer_id = customer_id;
}

public Customer getCustomer_id() {
	return customer_id;
}




public Allocation getAllocation_id() {
	return allocation_id;
}

public void setAllocation_id(Allocation allocation_id) {
	this.allocation_id = allocation_id;
}
}
