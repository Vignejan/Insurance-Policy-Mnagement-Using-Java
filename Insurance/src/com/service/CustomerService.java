package com.service;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.management.*;
import com.model.Customer;
import com.util.ApplicationUtil;

public class CustomerService {
	
	ApplicationUtil a=new ApplicationUtil();
	CustomerManagement cm=new CustomerManagement();
	
	String cid;
	
	
	public void addRecord(String detail)
	{
		String GREEN = "\u001B[32m";
		String BOLD ="\u001B[1m";
		String RESET = "\u001B[0m";
			String[] detailArray=detail.split(":");
			
			int age=Integer.parseInt(detailArray[2]);
			
			boolean validPhn=a.validatePhoneNumber(detailArray[8]);
			boolean validEmail=a.validateEmailId(detailArray[9]);
			if(validPhn)
			{
				if(validEmail)
				{
				long phno=Long.parseLong(detailArray[8]);
				double income=Double.parseDouble(detailArray[5]);
	
				cid=a.customerId(cm.recordCount());
				
				java.sql.Date sqldate=a.stringToDate(detailArray[1]);
				if(sqldate==null)
				{
					System.out.println("Date is invalid, Please provide date in the format dd/MM/YYYY");
				}
				else
				{
				
				Customer c=new Customer(cid, detailArray[0],sqldate, age, detailArray[3], detailArray[4], income, detailArray[6], detailArray[7], phno, detailArray[9]);
				
				boolean bool=cm.insertRecord(c);
						if(bool)
						{
		//					
							System.out.println(GREEN+BOLD+" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s | %-15s | %-17s | %-10s | %-12s | %-20s |\n","Customer Id","Customer Name","DOB","Age","Gender","Occupation","Annual Income","Medical History","Address","Phone Number","Email Id");
							System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							
		//						
								System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s | %-15s | %-17s | %-10s | %-12s | %-20s |\n",c.getCustomer_id(),c.getCustomer_name(),c.getDob(),c.getAge(),c.getGender(),c.getOccupation(),c.getAnnual_income(),c.getMedical_history(),c.getAddress(),c.getPhone_number(),c.getEmail_id());
								System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
							System.out.println(GREEN+BOLD+"**Record Inserted Successfully**"+RESET);
						}
						else
							System.out.println("Couldn't insert the record");
						}
				}
				else
				{
					System.out.println("Invalid email Address, Make sure the email is correct\nMinimum 12 character long");
				}
			}
			else
			{
				System.out.println("Invalid phone number, Please choose the option and provide valid phone number");
			}

	}
	public boolean updateRecord(long mobile_number1, long mobile_number2)
	{
		boolean bool=cm.updateMobileRecord(mobile_number1,mobile_number2);
		return bool;

		
	}
	public boolean updateRecord(String email1,String email2)
	{
		boolean bool=cm.updateEmailRecord(email1,email2);
		return bool;

	}

	public ArrayList<Customer> viewRecord(String cid)
	{
		ArrayList<Customer> selected_record=cm.viewCustomerRecord(cid);
		return selected_record;	
	}
	
	public ArrayList<Customer> viewRecord()
	{
		ArrayList<Customer> all_record=cm.viewCustomerRecord();
		return all_record;
	}
	

}

