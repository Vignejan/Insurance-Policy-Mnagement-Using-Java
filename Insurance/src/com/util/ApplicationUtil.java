package com.util;
//import java.util.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import com.management.*;
import com.model.*;

public class ApplicationUtil {
	
//	static int id;
	static int claimid;

	
	public String customerId(int count)
	{
		return "CUST"+(++count);	
	}
	
	public String policyId(int count)
	{   
		return "IPP20"+(++count);	
	}
	
	public String claimId(int claimid)
	{
		return "CLAIM"+(++claimid);	
	}
	
	//allocation id generate
	public String alId(int count) 
	{
		count++;
		String al="ALL"+count;
		return al;
	}
	
	//payment generate
	public String payId(int count) {
		count++;
		String pay="PAY"+count;
		return pay;
	}
	
	//validate emailId
	public boolean validatePhoneNumber(String phno)
	{
		String pattern="[789]{1}[0-9]{9}";
		if(phno.matches(pattern))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean validateEmailId(String mail)
	{
		String pattern="[[a-zA-z0-9]@gmail.com]{12,}";
		if(mail.matches(pattern))
		{
			return true;
		}
		
		return false;
	}
	
	public Date stringToDate(String str)
	{
		
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 
			 

        try 
        {
        	
        	
            // Convert string to Date
            java.util.Date date = sdf.parse(str);
            String newDate=sdf.format(date);
            java.sql.Date sqlDate=null;
            if(str.equals(newDate))  
            {
//         	  System.out.println("correct date"); 
            	sqlDate= new java.sql.Date(date.getTime());
            }
           
            return sqlDate;	
        }
		catch(Exception e)
		{
			System.out.println("Please provide the correct date");
			return null;
		}
		
		
		
	}
		

}

