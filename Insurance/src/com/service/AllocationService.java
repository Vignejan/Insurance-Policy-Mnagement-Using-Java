package com.service;
import com.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.InvalidAllocationException;

import com.management.AllocationManagement;
import com.util.ApplicationUtil;

public class AllocationService {
     public String CustId;
     public String polId;
     
     public void setCustId(String cid) {
    	 CustId=cid;
     }
     public void setPollId(String polid) {
    	 polId=polid;
     }
     
     
	public boolean addAllocation(String details) {
		AllocationManagement am=new AllocationManagement();
		ApplicationUtil autil=new ApplicationUtil();
		String arr[] = details.split(":");
		
		int allcount=am.getAlId();
		String aid=autil.alId(allcount);
		Customer cid =am.getCustId(CustId);
		Policy pid = am.getPolId(polId);
		String nominame = arr[0];
		
//		double sumassur = Double.parseDouble(arr[1]);
		double sumassur=pid.getMaxSumAssured();
		
//		int noOfyear = Integer.parseInt(arr[2]);
		int noOfyear=pid.getMaxNoOfYears();
		
//		double preamoun = Double.parseDouble(arr[3]);
		double preamoun=pid.getPremiumRate();
		String preamountpaycycle = arr[1];
//		double totalpay = Double.parseDouble(arr[2]);
	double totalpay=0;
		int totalCount=am.findTotalPay(aid);
		if(totalCount>0) {
			totalpay=totalCount*preamoun;
		}
		
		String polstatus = arr[2];

		Allocation aloc = new Allocation(aid, cid, pid, nominame, sumassur, noOfyear, preamoun, preamountpaycycle,totalpay, polstatus);
//		AllocationManagement allmanage = new AllocationManagement();

		int res = 0;
		try {
			res = am.addAllocation(aloc);
		} 
		catch (InvalidAllocationException e)
		{
			System.out.println(e.getMessage());
		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
		if (res > 0) {
//				System.out.println("records are added successfulyy");
			return true;
		}
		return false;

	}
	public boolean updateAllocation(String ALLOCATION_ID) {
		AllocationManagement allmanage=new AllocationManagement();
	
		int count=allmanage.findTotalPay(ALLOCATION_ID);
//		System.out.println(count+"retr count");
		double totalPayment=allmanage.totalPayment(ALLOCATION_ID);
//		System.out.println(totalPayment);
		if(count>0) {
			totalPayment=count*totalPayment;
			int res=allmanage.updateAllocation(totalPayment, ALLOCATION_ID);
			if(res>0)
				return true;
		}
		return false;
		
	}
	public boolean updateAllocation(String POLICY_STATUS,String ALLOCATION_ID) {
		AllocationManagement allmanage=new AllocationManagement();
	
		int result=allmanage.updateAllocation(POLICY_STATUS, ALLOCATION_ID);
		if(result>0) {
			return true;
		}
		return false;
		
	}
	public List<Allocation> retreiveBasedPolicyId(String POLICY_ID) {
		AllocationManagement allmanage=new AllocationManagement();
	
		List<Allocation> arlist=allmanage.retreiveBasedPolicyId(POLICY_ID);
		
			return arlist;
		
		
	}
	public List<Allocation> retreiveBasedPolicyStatus(String POLICY_STATUS) {
		AllocationManagement allmanage=new AllocationManagement();
	
		List<Allocation> arlist=allmanage.retreiveBasedPolicyStatus(POLICY_STATUS);
		
			return arlist;
		
		
	}
	public List<Allocation> retreiveBasedAllocationId(String ALLOCATION_ID) {
		AllocationManagement allmanage=new AllocationManagement();
	
		List<Allocation> arlist=allmanage.retreiveBasedAllocationId(ALLOCATION_ID);
		
			return arlist;
		
		
	}



}
