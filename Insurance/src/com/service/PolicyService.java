package com.service;

import java.util.ArrayList;
import com.management.PolicyManagement;
import com.model.Policy;
import com.util.ApplicationUtil;


public class PolicyService {

			private ArrayList<Policy> list=new ArrayList<>();
			
			PolicyManagement cd=new PolicyManagement();

			ApplicationUtil a=new ApplicationUtil();



	//String To Int,Long,double

	public int stringToInt(String str)
	{
		return Integer.parseInt(str);
	}
	public double stringToDouble(String str)
	{
		return Double.parseDouble(str);
	}
	public long stringToLong(String str)
	{
		return Long.parseLong(str);
	}
	

    public void addPolicy(String info)
    {
    	String [] det=info.split(":");
    	String policy_id=a.policyId(cd.recordCount());
    	int d1=stringToInt(det[0]);
    	int d3=stringToInt(det[3]);
    	double d4=stringToDouble(det[4]);
    	int d5=stringToInt(det[5]);
//    	System.out.println(policyId());
    	Policy policy=new Policy(policy_id,d1,det[1],det[2],d3,d4,d5);
    	
    	
       boolean bm=cd.addPolicy(policy);
    }
	
	//update policy
    public boolean updateMaxSumAssured(String type1,int type)
    {
    	return  cd.updateMaxSumAssured(type1, type);
    }
	
    public boolean updateMaxNoOfYear(String years1,int years2)
	{
	    	
	    	return cd.updateMaxNoOfYear(years1,years2);
	}
			 
	//Retrieve policy
	 public ArrayList<Policy> getPolicyDetail()
	 {
		return cd.getPolicyDetail();
	 }
	 
	 public ArrayList<Policy> viewPolicyRecord(String pid)
	 {
		 return cd.viewPolicyRecord(pid);
	 }
			 
	 //deletePolicy
	 public boolean deletePolicy(String dp) {
	 	
	 	return cd.deletePolicy(dp);
	 }

    
}

