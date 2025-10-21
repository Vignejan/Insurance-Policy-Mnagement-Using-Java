package com.model;

public class Allocation {

	private String ALLOCATION_ID;
	private Customer CUSTOMER_ID;
	private Policy POLICY_ID;
	private String NOMINEE_NAME;
	private double SUM_ASSURED;
	private int NO_OF_YEARS;
	private double PREMIUM_AMOUNT;
	private String PREMIUM_PAYMENT_CYCLE;
	private double TOTAL_PAYMENT;
	private String POLICY_STATUS;

	
	public Allocation() {
		super();
	}
	
	
	
	
	public String getALLOCATION_ID() {
		return ALLOCATION_ID;
	}

	public void setALLOCATION_ID(String aLLOCATION_ID) {
		ALLOCATION_ID = aLLOCATION_ID;
	}

	public Customer getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(Customer cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public Policy getPOLICY_ID() {
		return POLICY_ID;
	}

	public void setPOLICY_ID(Policy pOLICY_ID) {
		POLICY_ID = pOLICY_ID;
	}

	public String getNOMINEE_NAME() {
		return NOMINEE_NAME;
	}

	public void setNOMINEE_NAME(String nOMINEE_NAME) {
		NOMINEE_NAME = nOMINEE_NAME;
	}

	public double getSUM_ASSURED() {
		return SUM_ASSURED;
	}

	public void setSUM_ASSURED(double sUM_ASSURED) {
		SUM_ASSURED = sUM_ASSURED;
	}

	public int getNO_OF_YEARS() {
		return NO_OF_YEARS;
	}

	public void setNO_OF_YEARS(int nO_OF_YEARS) {
		NO_OF_YEARS = nO_OF_YEARS;
	}

	public double getPREMIUM_AMOUNT() {
		return PREMIUM_AMOUNT;
	}

	public void setPREMIUM_AMOUNT(double pREMIUM_AMOUNT) {
		PREMIUM_AMOUNT = pREMIUM_AMOUNT;
	}

	public String getPREMIUM_PAYMENT_CYCLE() {
		return PREMIUM_PAYMENT_CYCLE;
	}

	public void setPREMIUM_PAYMENT_CYCLE(String pREMIUM_PAYMENT_CYCLE) {
		PREMIUM_PAYMENT_CYCLE = pREMIUM_PAYMENT_CYCLE;
	}

	public double getTOTAL_PAYMENT() {
		return TOTAL_PAYMENT;
	}

	public void setTOTAL_PAYMENT(double tOTAL_PAYMENT) {
		TOTAL_PAYMENT = tOTAL_PAYMENT;
	}

	public String getPOLICY_STATUS() {
		return POLICY_STATUS;
	}

	public void setPOLICY_STATUS(String pOLICY_STATUS) {
		POLICY_STATUS = pOLICY_STATUS;
	}

	public Allocation(String aLLOCATION_ID,Customer cUSTOMER_ID, Policy pOLICY_ID, String nOMINEE_NAME,
			double sUM_ASSURED, int nO_OF_YEARS, double pREMIUM_AMOUNT, String pREMIUM_PAYMENT_CYCLE,
			double tOTAL_PAYMENT, String pOLICY_STATUS) {
		super();
		ALLOCATION_ID = aLLOCATION_ID;
		CUSTOMER_ID = cUSTOMER_ID;
		POLICY_ID = pOLICY_ID;
		NOMINEE_NAME = nOMINEE_NAME;
		SUM_ASSURED = sUM_ASSURED;
		NO_OF_YEARS = nO_OF_YEARS;
		PREMIUM_AMOUNT = pREMIUM_AMOUNT;
		PREMIUM_PAYMENT_CYCLE = pREMIUM_PAYMENT_CYCLE;
		TOTAL_PAYMENT = tOTAL_PAYMENT;
		POLICY_STATUS = pOLICY_STATUS;
	}

}
