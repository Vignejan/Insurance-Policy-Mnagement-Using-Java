package com.service;
import java.sql.Date;
import com.model.*;
import com.util.*;
import java.util.*;
import com.management.*;
public class PaymentService {
	 static int count=0;
	 public String custId;
	 public String allId;
	
	 String GREEN = "\u001B[32m";
		String BOLD ="\u001B[1m";
		String RESET = "\u001B[0m";
		String BLUE = "\u001B[34m";
	 
	 public void custAdd(String custId) {
		 this.custId=custId;
	 }
	 public void allAdd(String allId) {
		 this.allId=allId;
	 }
       public boolean addPayment(String details) {
    	   
    	   ApplicationUtil au=new    ApplicationUtil();
    	   PaymentManagement pm=new PaymentManagement();
    	   String[] parse=details.split(":");
    	   
	         
	         Customer cId=pm.getCustId(custId);
	         Allocation aId=pm.getAId(allId);
	         
//		     String customerId=cId.getCustomer_id();
	         double premium=pm.getPremium(allId);
	         java.sql.Date date=au.stringToDate(parse[0]);
	         
	         String paymode=parse[1];
       int installcount=pm.getInstallment(allId);
       installcount++;
       
	         int payCount=pm.getPayId();
	         String payId=au.payId(payCount);
	         
	        
	         Payment payment=new Payment(payId,cId,aId,premium,date,paymode,installcount);
	        int record= pm.insertPayment(payment);
	        if(record>0) {
	        	 System.out.println(BLUE+BOLD+" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
	    		   System.out.printf(BLUE+BOLD+"| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s | %-15s |\n","Payment Id","Customer Id","Allocation Id","Premium","Premium Date","Premium Mode","Installment Count");
	    		   System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	    		   System.out.printf("| %-11s | %-16s | %-10s | ₹%-4s | %-8s | %-15s | %-15s |\n",payment.getPayment_id(),cId.getCustomer_id(),aId.getALLOCATION_ID(),payment.getPremium(),payment.getPayment_date(),payment.getPayment_mode(),payment.getInstallment_count());
					System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
	        	return true;
	        }
	           return false;
          }
 
 
        public List<Payment> retrievePayment(String paymentId){
        	PaymentManagement pm=new PaymentManagement();
        	List<Payment> records=pm.retrievePayment(paymentId);
        	
        	
        	
        	return records;
        }
      
      public boolean deletePayment(String paymentId) {
    	  PaymentManagement pm=new PaymentManagement();
    	 int result= pm.deletePayment(paymentId);
    	 if(result>0)
    		 return true;
    	  return false;
      }
 
}