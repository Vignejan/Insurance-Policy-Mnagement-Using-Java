package com.model;
import java.util.Date;

public class Claim {
	
	private String claim_id;
	private Customer customer_id;
	private Allocation allocation_id;
	private String claim_type;
	private Double claim_amount;
	private Date claim_date;
	
	public Claim(String claim_id,Customer customer_id,Allocation allocation_id,String claim_type,Double claim_amount,Date claim_date)
	{
		this.claim_id = claim_id;
		this.customer_id = customer_id;
		this.allocation_id = allocation_id;
		this.claim_type = claim_type;
		this.claim_amount = claim_amount;
		this.claim_date = claim_date;
	}
	
	public Claim()
	{
		
	}
	
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}
	
	public Customer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}
	
	public Allocation getAllocation_id() {
		return allocation_id;
	}
	public void setAllocation_id(Allocation allocation_id) {
		this.allocation_id = allocation_id;
	}
	
	public String getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
	}
	
	public Double getClaim_amount() {
		return claim_amount;
	}
	public void setClaim_amount(Double claim_amount) {
		this.claim_amount = claim_amount;
	}
	
	public Date getClaim_date() {
		return claim_date;
	}
	public void setClaim_date(Date claim_date) {
		this.claim_date = claim_date;
	}

	

}
