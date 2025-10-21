
package com.service;
import com.management.*;
import java.util.ArrayList;

import com.model.*;
import com.util.ApplicationUtil;

public class ClaimService {
	
	String RESET = "\u001B[0m";
	String RED = "\u001B[31m";
	String GREEN = "\u001B[32m";
	String BLUE = "\u001B[34m";
	String BOLD ="\u001B[1m";
	String TEAL = "\u001B[38;5;37m";
	
	ClaimManagement claimObj=new ClaimManagement();
	ApplicationUtil a=new ApplicationUtil();
	
	String customerId;
	String allocationId;
	
	public double calculateAmount(String type,String allId) {
		double amount=0;
		
		double bonus=0;
		Allocation a=claimObj.getAllocation(allId);
		double maxSum=a.getSUM_ASSURED();
        double premium=a.getPREMIUM_AMOUNT();
        int noYear=a.getNO_OF_YEARS();
        String polId=a.getPOLICY_ID().getPolicyId();
        String polType=claimObj.findPolType(polId);
		if("Maturity".equalsIgnoreCase(type)) {                   //////////////maturity check
			
	        if("Money Back".equals(polType)) {        ///////policy type
	            int times=noYear/5; 
	        	double benefits=(maxSum*0.20)*times;
	        	bonus=(50*(maxSum/1000))*noYear;
	        	amount=benefits+bonus;
	        }else {
	        	double FAB=200000;
	        	bonus=(50*(maxSum/1000))*noYear;
	        	amount =FAB+bonus;
	        }
			
		}else if("Death".equalsIgnoreCase(type)) {
			return maxSum;
			
		}else {
			System.out.println("Invalid Claim Type");
		}
		return amount;
	}
	
	public void setCustomerId(String customerId)
	{
		this.customerId=customerId;
	}
	
	public void setAllocationId(String allocationId)
	{
		this.allocationId=allocationId;
	}
	
	public boolean addClaim(String claim_detail)
	{
		String[] claimArray=claim_detail.split(":");
		
		
		//calling method to find the customer id from customer table
		Customer cObj=claimObj.findCustomerId(customerId);
		
		//calling method to find the allocation id from allocation table
		Allocation aObj=claimObj.findAllocationId(allocationId);
		
		double claim_Amount=calculateAmount(claimArray[0],allocationId);
		String claimid=a.claimId(claimObj.recordCount());
		
		java.sql.Date sqldate=a.stringToDate(claimArray[1]);
		
		if(sqldate==null)
		{
			System.out.println("Date is invalid, Please provide date in the format dd/MM/YYYY");
			return false;
		}
		else
		{
		Claim c=new Claim(claimid,cObj,aObj,claimArray[0],claim_Amount,sqldate);
		boolean bool=claimObj.addClaimRecord(c);
		
		if(bool)
		{
		System.out.println(TEAL+" ----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n","Claim Id","Customer Id","Allocation Id","Claim Type","Claim Amount","Claim Date");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n",claimid,customerId,allocationId,claimArray[0],claim_Amount,sqldate);
		System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
		}
		
		return bool;
		}
	}
	
	
	
	public boolean deleteClaim(String claimid)
	{
		boolean bool=claimObj.deleteClaimRecord(claimid);
		
		
		return bool;
	}
	
	public ArrayList<Claim> viewClaim(String id)
	{
		ArrayList<Claim> record=claimObj.viewCustomerRecord(id);
		return record;
	}
	
	public ArrayList<Claim> viewClaim()
	{
		ArrayList<Claim> all_record=claimObj.viewCustomerRecord();
		return all_record;
	}
	
	

}
