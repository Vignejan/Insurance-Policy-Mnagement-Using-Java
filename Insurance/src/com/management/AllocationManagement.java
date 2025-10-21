package com.management;
import com.exception.InvalidAllocationException;
import com.model.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class AllocationManagement {
	
    public Customer getCustId(String custId) {
    	Customer c=null;
    	try {
    	Connection con = DBConnectionManager.getConnection();
		PreparedStatement pt = con.prepareStatement("select * from customer where  CUSTOMER_ID=?");
		pt.setString(1,custId);
		ResultSet r=pt.executeQuery();
		while(r.next())
		{
			 c=new Customer(r.getString(1),r.getString(2),r.getDate(3),r.getInt(4),r.getString(5),r.getString(6),r.getDouble(7),r.getString(8),r.getString(9),r.getLong(10),r.getString(11));
			
		
		}
		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return c;
    	
    }
    
    public Policy getPolId(String polId) {
    	Policy p=null;
    	try {
    	Connection con = DBConnectionManager.getConnection();
		PreparedStatement pt = con.prepareStatement("select * from policy where  Policy_ID=?");
		pt.setString(1,polId);
		ResultSet r=pt.executeQuery();
		while(r.next()) {
			 p=new Policy(r.getString(1),r.getInt(2),r.getString(3),r.getString(4),r.getInt(5),r.getDouble(6),r.getInt(7));
		}
    	
        }
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return p;
    }
    public int findTotalPay(String allId) {
    	int count=0;
    	try {
    		Connection c = DBConnectionManager.getConnection();
			PreparedStatement st = c.prepareStatement("select max(INSTALLMENT_COUNT) from payment where ALLOCATION_ID=?");
			st.setString(1, allId);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
    	}
    	catch(Exception e)
		{
			e.printStackTrace();
		}
    	return count;
    }
    
    public double totalPayment(String allId) {
    	double total=0;
  
    	try {
    		
    		Connection c = DBConnectionManager.getConnection();
			PreparedStatement st = c.prepareStatement("select PREMIUM_AMOUNT FROM allocation where ALLOCATION_ID=?");
			st.setString(1, allId);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				total=rs.getDouble(1);
			}
    }
    	catch(Exception e)
		{
			e.printStackTrace();
		}
    	return total;
    }
    
    
	public static int addAllocation(Allocation allocobj) throws InvalidAllocationException{
		
		try {
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement st = c.prepareStatement("Insert into Allocation values(?,?,?,?,?,?,?,?,?,?)");
           
			st.setString(1, allocobj.getALLOCATION_ID());
			st.setString(2, allocobj.getCUSTOMER_ID().getCustomer_id());
			st.setString(3, allocobj.getPOLICY_ID().getPolicyId());
			st.setString(4, allocobj.getNOMINEE_NAME());
			st.setDouble(5, allocobj.getSUM_ASSURED());
			st.setInt(6, allocobj.getNO_OF_YEARS());
			st.setDouble(7, allocobj.getPREMIUM_AMOUNT());
			st.setString(8, allocobj.getPREMIUM_PAYMENT_CYCLE());
			st.setDouble(9, allocobj.getTOTAL_PAYMENT());
			st.setString(10, allocobj.getPOLICY_STATUS());

			int result = st.executeUpdate();
			return result;
		} 
		catch(Exception e)
		{
			throw new InvalidAllocationException("Invalid Allocation--->Couldn't add the record");
		}

//		return 0;
	}
	public int getAlId(){
		ResultSet res=null;
		int count=0;
		try {
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement pt = c.prepareStatement("select count(ALLOCATION_ID) as a from Allocation");
			
			res=pt.executeQuery();
			while(res.next()) {
				count=res.getInt("a");
			}
		}
		 catch (Exception e) {
				e.getStackTrace();
			}
		return count;
	}
	public int updateAllocation(double TOTAL_PAYMENT,String ALLOCATION_ID) {
		try {
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement pt = c.prepareStatement("update Allocation set TOTAL_PAYMENT=? where ALLOCATION_ID=?");
			pt.setDouble(1,TOTAL_PAYMENT);
			pt.setString(2,ALLOCATION_ID);
			
			int result=pt.executeUpdate();
			return result;
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return 0;
	}
	public int updateAllocation(String POLICY_STATUS,String ALLOCATION_ID) {
		try {
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement pt = c.prepareStatement("update Allocation set POLICY_STATUS=? where ALLOCATION_ID=?");
			pt.setString(1,POLICY_STATUS);
			pt.setString(2,ALLOCATION_ID);
			
			int result=pt.executeUpdate();
			return result;
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return 0;
	}
	public ArrayList<Allocation> retreiveBasedPolicyId(String POLICY_ID){
		ArrayList<Allocation> arlist= new ArrayList<>();
		ResultSet res=null;
		
		Customer cust=new Customer();
		Policy p=new Policy();
		try {
			Connection con = DBConnectionManager.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from Allocation where POLICY_ID=?");
			pt.setString(1,POLICY_ID);
			
			res=pt.executeQuery();
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
				 
				 Allocation alloc=new Allocation(alid,cust,p, nname, sumas, nofyear, preamont, preamountcyc, totpay, postatus);
				 arlist.add(alloc);
			}
			
		}
		catch(Exception e) {
			e.getStackTrace();		
			}
		return arlist;
	
	}
	public ArrayList<Allocation> retreiveBasedPolicyStatus(String POLICY_STATUS){
		ArrayList<Allocation> arlist= new ArrayList<>();
		ResultSet res=null;
		try {
			Customer cust=new Customer();
			Policy p=new Policy();
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement pt = c.prepareStatement("select * from Allocation where  POLICY_STATUS=?");
			pt.setString(1,POLICY_STATUS);
		
			
			res=pt.executeQuery();
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
				 Allocation alloc=new Allocation(alid,cust,p, nname, sumas, nofyear, preamont, preamountcyc, totpay, postatus);
				 arlist.add(alloc);
			}
			
		}
		catch(Exception e) {
			e.getStackTrace();		
			}
		return arlist;
	
	}
	public ArrayList<Allocation> retreiveBasedAllocationId(String ALLOCATION_ID){
		ArrayList<Allocation> arlist= new ArrayList<>();
		ResultSet res=null;
		try {
			Customer cust=new Customer();
			Policy p=new Policy();
			Connection c = DBConnectionManager.getConnection();
			PreparedStatement pt = c.prepareStatement("select * from Allocation where  ALLOCATION_ID=?");
			pt.setString(1,ALLOCATION_ID);
		
			
			res=pt.executeQuery();
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
				 Allocation alloc=new Allocation(alid,cust,p, nname, sumas, nofyear, preamont, preamountcyc, totpay, postatus);
				 arlist.add(alloc);
			}
			
		}
		catch(Exception e) {
			e.getStackTrace();		
			}
		return arlist;
	
	}
	


}


