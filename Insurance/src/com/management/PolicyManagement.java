package com.management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.model.Policy;


	public class PolicyManagement {
		
		static int id;
		
		public boolean addPolicy(Policy p)
		{
			try 
			{
				Connection con=DBConnectionManager.getConnection();
				
				PreparedStatement ps=con.prepareStatement("insert into policy values(?,?,?,?,?,?,?)");
					ps.setString(1, p.getPolicyId());
					ps.setInt(2, p.getSchemeNumber());
					ps.setString(3, p.getPolicyName());
					ps.setString(4, p.getPolicyType());
					ps.setInt(5,p.getMaxNoOfYears());
					ps.setDouble(6,p.getPremiumRate());
					ps.setInt(7,p.getMaxSumAssured());
					
	 			int row=ps.executeUpdate();
				if(row>0)
					return true;
				else
					return false;
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
			return false;
		}
		
	public boolean updateMaxSumAssured(String id,int type2)
	{
		try 
		{
			Connection con=DBConnectionManager.getConnection();
			
			PreparedStatement ps=con.prepareStatement("Update policy set MAX_SUM_ASSURED=? where POLICY_ID=?");
			ps.setInt(1,type2);
			ps.setString(2, id);
			int row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean updateMaxNoOfYear(String id,int max1)
	{
		try {
			Connection con=DBConnectionManager.getConnection();
			
			PreparedStatement ps=con.prepareStatement("Update policy set MAX_NO_OF_YEARS=? where POLICY_ID=?");
			ps.setInt(1,max1);
			ps.setString(2,id);
			int row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}catch(Exception e)
		{
			return false;
		}
	}
	public ArrayList<Policy> viewPolicyRecord(String pid)
	{
		ResultSet r=null;
		ArrayList<Policy> hi=new ArrayList<>();
		try
		{
			Connection con=DBConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("Select * from policy where POLICY_ID=?");		
			ps.setString(1,pid);
			r=ps.executeQuery();
			while(r.next())
			{
				Policy p=new Policy(r.getString(1),r.getInt(2),r.getString(3),r.getString(4),r.getInt(5),r.getDouble(6),r.getInt(7));
				hi.add(p);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return hi;
	}

	public ArrayList<Policy> getPolicyDetail()
	{
		ResultSet r=null;
		ArrayList<Policy> hi=new ArrayList<>();
		try
		{
			Connection con=DBConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("Select * from policy;");		
			r=ps.executeQuery();
			while(r.next())
			{
				Policy p=new Policy(r.getString(1),r.getInt(2),r.getString(3),r.getString(4),r.getInt(5),r.getDouble(6),r.getInt(7));
				hi.add(p);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return hi;
	}
	public boolean  deletePolicy(String d)
	{
		try {
			Connection con=DBConnectionManager.getConnection();
			
			PreparedStatement ps=con.prepareStatement("delete from policy where policy_Id=?");
			ps.setString(1,d);
			
			int row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}catch(Exception e)
		{
			return false;
		}
	}

	
	public int recordCount()
	{
		ResultSet r=null;
		try 
		{
			Connection connect=DBConnectionManager.getConnection();
			PreparedStatement pstmt=connect.prepareStatement("Select count(POLICY_ID) from policy;");
			
			r=pstmt.executeQuery();
			
			while(r.next())
			{
				id=r.getInt(1);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		System.out.println(id);
		return id;
	}
	}
	