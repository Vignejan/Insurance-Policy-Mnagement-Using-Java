package com.management;

import java.sql.*;
import java.util.ArrayList;

import com.model.*;
import com.util.ApplicationUtil;

public class ClaimManagement {
	
	ApplicationUtil a=new ApplicationUtil();
	Claim claimObj=new Claim();
	static int id;
	
	public Allocation getAllocation(String allId) {
		ResultSet rs=null;
		Allocation a=new Allocation();
	    Policy p=new Policy();
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement ps=connect.prepareStatement("Select * from Allocation where ALLOCATION_ID=?");
		ps.setString(1,allId);
		rs=ps.executeQuery();
		while(rs.next()) {
			String polId=rs.getString(3);
			double maxSum=rs.getDouble(5);
			int noYears=rs.getInt(6);
			double premium=rs.getDouble(7);
			double totpremium=rs.getDouble(9);	
			
			a.setSUM_ASSURED(maxSum);
			a.setNO_OF_YEARS(noYears);
			a.setPREMIUM_AMOUNT(premium);
			a.setTOTAL_PAYMENT(totpremium);
			p.setPolicyId(polId);
			a.setPOLICY_ID(p);
		}
		
		}
		catch(SQLException e) {
			System.out.println("Couldn't find the allocation ID");
		}
		return a;
	}
	
	public String findPolType(String polId) {
		String type=null;
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement ps=connect.prepareStatement("Select POLICY_TYPE from policy where POLICY_ID=?");
		ps.setString(1,polId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			type=rs.getString(1);
		}
		}
		catch(SQLException e) {
			System.out.println("Couldn't find the policy ID");
		}
		return type;
	}
	
	
	public Customer findCustomerId(String custid)
	{
		ResultSet r=null;
		Customer c=null;

		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement pstmt=connect.prepareStatement("Select * from customer where CUSTOMER_ID=?");
		
		pstmt.setString(1, custid);
		r=pstmt.executeQuery();
		while(r.next())
		{
			c=new Customer(r.getString(1),r.getString(2),r.getDate(3),r.getInt(4),r.getString(5),r.getString(6),r.getDouble(7),r.getString(8),r.getString(9),r.getLong(10),r.getString(11));

		}
//		System.out.println(c.getCustomer_id());
		}
		catch(Exception e)
		{
			System.out.println("Customer ID not found");
		}
		return c;
	}
	
	public Allocation findAllocationId(String allocid)
	{
		Connection con=DBConnectionManager.getConnection();
		
		Customer cust=new Customer();
		Policy p=new Policy();
		Allocation alloc=null;
		try {
			PreparedStatement ps=con.prepareStatement("select * from allocation where ALLOCATION_ID=?");
			
			
		    ps.setString(1,allocid);
			ResultSet res=ps.executeQuery();
			while(res.next()) 
			{
				 String alid=res.getString("ALLOCATION_ID");
				 String cuid=res.getString("CUSTOMER_ID");
				 String poid=res.getString("POLICY_ID");
				 String nname=res.getString("NOMINEE_NAME");
				 double sumas=res.getDouble("SUM_ASSURED");
				 int nofyear=res.getInt("NO_OF_YEARS");
				 double preamont=res.getDouble("PREMIUM_AMOUNT");
				 String preamountcyc=res.getString("PREMIUM_PAYMENT_CYCLE");
				 double totpay=res.getDouble("TOTAL_PAYMENT");
				 String postatus=res.getString("POLICY_STATUS");
				 cust.setCustomer_id(cuid);
				 p.setPolicyId(poid);
				 alloc=new Allocation(alid,cust,p, nname, sumas, nofyear, preamont, preamountcyc, totpay, postatus);
			}
		}
		catch(Exception e) 
		{
			System.out.println("Allocation Id not found");
		}
		return alloc;
	}
	
	public int recordCount()
	{
		ResultSet r=null;
		try 
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("Select count(claim_id) from claim");
			
			r=pstmt.executeQuery();
			
			while(r.next())
			{
				id = r.getInt(1);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("No record found in the table");
		}
		
		return id;
	}
	
	public boolean addClaimRecord(Claim c)
	{
		try
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("insert into claim values(?,?,?,?,?,?)");
			
			pstmt.setString(1,c.getClaim_id());
			pstmt.setString(2, c.getCustomer_id().getCustomer_id());
			pstmt.setString(3, c.getAllocation_id().getALLOCATION_ID());
			pstmt.setString(4, c.getClaim_type());
			pstmt.setDouble(5, c.getClaim_amount());
			pstmt.setDate(6, (Date) c.getClaim_date());
			
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
	
	public boolean deleteClaimRecord(String claimid)
	{
		try
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("delete from claim where claim_id=?");
			
			pstmt.setString(1,claimid);
			
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
	
	public ArrayList<Claim> viewCustomerRecord(String claimid)
	{
		ResultSet r=null;
		
		ArrayList<Claim> value=new ArrayList<>();
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement pstmt=connect.prepareStatement("Select * from claim where claim_id=?;");
		
		Customer cust=new Customer();
		Allocation alloc=new Allocation();
		
		pstmt.setString(1, claimid);
		r=pstmt.executeQuery();
		
			while(r.next())
			{
				
				cust.setCustomer_id(r.getString(2));
				
				
				alloc.setALLOCATION_ID(r.getString(3));
				
				Claim cl=new Claim(r.getString(1),cust,alloc,r.getString(4),r.getDouble(5),r.getDate(6));
				value.add(cl);
			
			}
	
		}
		catch(Exception e)
		{
			System.out.println("No record found in the database to display");
			
		}
		return value;

	}
	
	public ArrayList<Claim> viewCustomerRecord()
	{
		ResultSet r=null;
		
		Customer cust=new Customer();
		Allocation alloc=new Allocation();
		
		ArrayList<Claim> value=new ArrayList<>();
		try
		{
		Connection connect=DBConnectionManager.getConnection();
		PreparedStatement pstmt=connect.prepareStatement("Select * from claim;");
		
		r=pstmt.executeQuery();
		
			while(r.next())
			{
				
				cust.setCustomer_id(r.getString(2));
		
				alloc.setALLOCATION_ID(r.getString(3));
				
				Claim cl=new Claim(r.getString(1),cust,alloc,r.getString(4),r.getDouble(5),r.getDate(6));
				value.add(cl);
			
			}
	
		}
		catch(Exception e)
		{
			System.out.println("No record found in the database to display");
			
		}
		return value;

	}

	

}

