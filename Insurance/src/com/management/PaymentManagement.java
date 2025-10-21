package com.management;
import com.model.*;
import com.service.AllocationService;

import java.util.*;
import java.sql.*;

public class PaymentManagement {
	public int getPayId() {
		Connection con=DBConnectionManager.getConnection();
		String query="select count(PAYMENT_ID) as p from Payment";
		int count=0;
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt("p");
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
  public double getPremium(String allId){
	  Connection con=DBConnectionManager.getConnection();
		String query="select PREMIUM_AMOUNT from allocation where ALLOCATION_ID=?";
		double premium=0;
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,allId);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				premium=rs.getDouble(1);
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	  return premium;
  }
	
public Customer getCustId(String custId) {
	Connection con=DBConnectionManager.getConnection();
	String query="select * from customer where CUSTOMER_ID=?";
	Customer c=null;
	try {
		PreparedStatement ps=con.prepareStatement(query);
	      ps.setString(1,custId);
		ResultSet r=ps.executeQuery();
		while(r.next()) {
			
			c=new Customer(r.getString(1),r.getString(2),r.getDate(3),r.getInt(4),r.getString(5),r.getString(6),r.getDouble(7),r.getString(8),r.getString(9),r.getLong(10),r.getString(11));
		}
	}
	catch(SQLException e) {
		System.out.println("Customer Id not found in the record");
	}
	return c;
}
	
public Allocation getAId(String AllId) {
	Connection con=DBConnectionManager.getConnection();
	String query="select * from allocation where ALLOCATION_ID=?";
	Allocation alloc=null;
	Customer cust=new Customer();
	Policy p=new Policy();
	try {
		PreparedStatement ps=con.prepareStatement(query);
	      ps.setString(1,AllId);
		ResultSet res=ps.executeQuery();
		while(res.next()) {
			
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
	catch(SQLException e) {
		System.out.println("Allocation Id not found in the record");
	}
	return alloc;
}	
	
	
public int getInstallment(String allid) {
	Connection con=DBConnectionManager.getConnection();
	String query="select max(INSTALLMENT_COUNT) as i from Payment where ALLOCATION_ID=?";
	int count=0;
	try {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,allid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			count=rs.getInt("i");
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	
	return count;
	
}
	
	
	
	
	
	
public int insertPayment(Payment payment) {
	
	try {
	
		Connection con=DBConnectionManager.getConnection();
	    
		PreparedStatement ps=con.prepareStatement("insert into Payment values(?,?,?,?,?,?,?)");
		ps.setString(1,payment.getPayment_id());
		ps.setString(2,payment.getCustomer_id().getCustomer_id());
		ps.setString(3,payment.getAllocation_id().getALLOCATION_ID());
		ps.setDouble(4,payment.getPremium());
		ps.setDate(5,payment.getPayment_date());
		ps.setString(6,payment.getPayment_mode());
		ps.setInt(7,payment.getInstallment_count());
		int result=ps.executeUpdate();
		return result;
		
	} 
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Invalid Input");;
	}
	return 0;
}

public List<Payment> retrievePayment(String allId){
	List<Payment> lists = new ArrayList<>();
	Connection con=DBConnectionManager.getConnection();
	Customer cust=new Customer();
	Allocation all=new Allocation();
	
	String Query ="select * from Payment where ALLOCATION_ID=? and INSTALLMENT_COUNT=(select max(INSTALLMENT_COUNT) from Payment where ALLOCATION_ID=?);";
	try {
	PreparedStatement ps=con.prepareStatement(Query);
	ps.setString(1,allId);
	ps.setString(2,allId);
	
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
	String payId=	rs.getString("PAYMENT_ID");
	String customerId=	rs.getString("CUSTOMER_ID");
	String allocationId=	rs.getString("ALLOCATION_ID");
	double premium=	rs.getDouble("PREMIUM");
	java.sql.Date date=	rs.getDate("PREMIUM_DATE");
	String payMode=	rs.getString("PAYMENT_MODE");
	int instaCount=	rs.getInt("INSTALLMENT_COUNT");
	cust.setCustomer_id(customerId);
	
	all.setALLOCATION_ID(allocationId);
	
	
	lists.add(new Payment(payId,cust,all,premium,date,payMode,instaCount));
		
	}
	return lists;
	
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	
	return null;
}



public int deletePayment(String paymentId) {
	Connection con=DBConnectionManager.getConnection();
	String Query ="delete from Payment where PAYMENT_ID=?";
	int result=0;
	try {
	PreparedStatement ps=con.prepareStatement(Query);
	ps.setString(1,paymentId);
	
	 result=ps.executeUpdate();
	
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	
	return result;
	
	
}
	
}
