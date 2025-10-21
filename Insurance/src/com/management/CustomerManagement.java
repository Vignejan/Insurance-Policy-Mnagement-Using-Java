package com.management;

import com.model.Customer;
import com.util.ApplicationUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerManagement {
	
	ApplicationUtil a=new ApplicationUtil();
	static int id;
	
	public int recordCount()
	{
		ResultSet r=null;
		try 
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("Select count(Customer_id) from customer;");
			
			r=pstmt.executeQuery();
			
			while(r.next())
			{
				id=r.getInt(1);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("No record found in the table");
		}
		
		return id;
	}
	
	public boolean insertRecord(Customer c)
	{
		try
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1,c.getCustomer_id());
			pstmt.setString(2,c.getCustomer_name());
			pstmt.setDate(3,c.getDob());
			pstmt.setInt(4,c.getAge());
			pstmt.setString(5,c.getGender());
			pstmt.setString(6,c.getOccupation());
			pstmt.setDouble(7,c.getAnnual_income());
			pstmt.setString(8,c.getMedical_history());
			pstmt.setString(9,c.getAddress());
			pstmt.setLong(10,c.getPhone_number());
			pstmt.setString(11,c.getEmail_id());
		
			int row=pstmt.executeUpdate();
			if(row>0)
				return true;
			else
				return false;
			
		}
		catch(Exception e)
		{
			return false;
		}
	
	}
	
	public boolean updateMobileRecord(long mob_num1,long mob_num2)
	{
		try 
		{
//			System.out.println("reached db code");
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("Update customer set phone_number=? where phone_number=?;");
			pstmt.setLong(1,mob_num2);
			pstmt.setLong(2,mob_num1);
			
			int row=pstmt.executeUpdate();
			if(row>0)
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	public boolean updateEmailRecord(String email1,String email2)
	{
		try 
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("Update customer set EMAIL_ID=? where EMAIL_ID=?;");
			pstmt.setString(1,email2);
			pstmt.setString(2,email1);
			
			int row=pstmt.executeUpdate();
			if(row>0)
				return true;
			else
				return false;
		
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public ArrayList<Customer> viewCustomerRecord(String cid)
	{
		ResultSet r=null;
		
		ArrayList<Customer> value=new ArrayList<>();
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement pstmt=connect.prepareStatement("Select * from customer where CUSTOMER_ID=?");
		
		pstmt.setString(1,cid);
		r=pstmt.executeQuery();
		
		while(r.next())
		{
			Customer c=new Customer(r.getString(1),r.getString(2),r.getDate(3),r.getInt(4),r.getString(5),r.getString(6),r.getDouble(7),r.getString(8),r.getString(9),r.getLong(10),r.getString(11));
			value.add(c);
		}
	
		
		}
		catch(Exception e)
		{
			System.out.println("No record found in the database to display");
			
		}
		return value;
	
	}
	
	public ArrayList<Customer> viewCustomerRecord()
	{
		ResultSet r=null;
		
		ArrayList<Customer> value=new ArrayList<>();
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement pstmt=connect.prepareStatement("Select * from customer;");
		
		r=pstmt.executeQuery();
		
			while(r.next())
			{
				Customer c=new Customer(r.getString(1),r.getString(2),r.getDate(3),r.getInt(4),r.getString(5),r.getString(6),r.getDouble(7),r.getString(8),r.getString(9),r.getLong(10),r.getString(11));
				value.add(c);
			
			}
	
		}
		catch(Exception e)
		{
			System.out.println("No record found in the database to display");
			
		}
		return value;

	}
	
}

	


