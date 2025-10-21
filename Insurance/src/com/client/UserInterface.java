package com.client;
import com.util.*;
import java.util.*;

import com.model.Allocation;
import com.model.Claim;
import com.model.Customer;
import com.model.Payment;
import com.model.Policy;
import com.service.*;

public class UserInterface {

	public static void main(String[] args) {
		
		String RESET = "\u001B[0m";
		String RED = "\u001B[31m";
		String GREEN = "\u001B[32m";
		String BLUE = "\u001B[34m";
		String BOLD ="\u001B[1m";
		String YELLOW="\u001B[33m";
		String PURPLE="\u001B[35m";
		String UNDERLINE = "\u001B[4m";
		String ORANGE="\u001B[38;5;214m";
		String TEAL = "\u001B[38;5;37m";
		
		
		Scanner sc=new Scanner(System.in);
		String detail;
		boolean bool=true;
		
		CustomerService cs=new CustomerService();
		PaymentService ps=new PaymentService();
		ClaimService clObj=new ClaimService();
		PolicyService posObj=new PolicyService();
		
		System.out.println(BOLD+UNDERLINE+"*--------INSURANCE MANAGEMENT SYSTEM--------*"+RESET);
		System.out.println();
		while(bool)
		{
			
			try {
				
			
			System.out.println(RESET + BOLD + PURPLE + "+-------------------------+" + RESET);
			System.out.println(RESET + BOLD + PURPLE + "|       Main Menu         |" + RESET);
			System.out.println(RESET + BOLD + PURPLE + "+-------------------------+" + RESET);
			System.out.println(PURPLE+"| 	1. Customer       |");
			System.out.println("|	2. Policy         |");
			System.out.println("| 	3. Allocation     |");
			System.out.println("| 	4. Payment        |");
			System.out.println("| 	5. Claim          |");
			System.out.println("| 	6. Exit           |");
			System.out.println("+-------------------------+"+RESET);

			System.out.println(BOLD+"\nEnter your choice:"+RESET);
			
			int choice=Integer.parseInt(sc.nextLine());
			
			if(choice>6)
			{
				System.out.println(YELLOW+BOLD+"Please choose the correct option!"+RESET);
			}
			switch(choice)
			{
			case 1:
				
				System.out.println(PURPLE+" -----------------------------");
				System.out.println("|        CUSTOMER MENU        |");
				System.out.println(" -----------------------------");
				System.out.println("|  1. Add Customer detail     |");
				System.out.println("|  2. Update Customer detail  |");
				System.out.println("|  3. View Customer detail    |");
				System.out.println("|  4. Main menu               |");
				System.out.println("------------------------------"+RESET);
				System.out.println(BOLD+"\nEnter your choice:"+RESET);				
				int ch1=Integer.parseInt(sc.nextLine());
				switch(ch1)                                                                        
				{
					case 1:
						System.out.println(BOLD+"Enter the Customer details"+RESET);
						System.out.println("+-----------------------------------------------------------------------------------------------------------------------+");
						System.out.println("Example format-->NAME:DATE(dd/MM/YYYY):AGE:GENDER:OCCUPATION:ANNUAL_INCOME:MEDICAL_HISTORY:ADDRESS:PHONE_NUMBER:EMAIL_ID");				    
						System.out.println("+-----------------------------------------------------------------------------------------------------------------------+");
						detail=sc.nextLine();
						cs.addRecord(detail);
						break;
					
					case 2:
						
						System.out.println(PURPLE+"---------------------------------");
						System.out.println("|     CUSTOMER UPDATE MENU       |");
						System.out.println("---------------------------------");
						System.out.println("|  1. Update Mobile Number       |");
						System.out.println("|  2. Update Email Address       |");
						System.out.println("|  3. Main Menu                  |");
						System.out.println("---------------------------------"+RESET);
						System.out.println(BOLD+"\nEnter your choice:"+RESET);
						
						int update=Integer.parseInt(sc.nextLine());
						switch(update)
						{
							case 1:
								System.out.println(BOLD+"Enter the Mobile Number to be modified"+RESET);
								long mobile_number1=Long.parseLong(sc.nextLine());
								System.out.println(BOLD+"Enter the new Mobile Number"+RESET);
								long mobile_number2=Long.parseLong(sc.nextLine());
								boolean res=cs.updateRecord(mobile_number1,mobile_number2);
								if(res)
								{
									
								 System.out.println(BOLD+"\nMobile Number Updated Successfully"+RESET);
								}
								else
								{
									System.out.println(BOLD+RED+"\nCouldn't Update the mobile number, Try again"+RESET);
								}
								break;
								
							case 2:
								System.out.println(BOLD+"Enter the Email Address to be modified"+RESET);
								String email1=sc.nextLine();
								System.out.println(BOLD+"Enter the new Email Address"+RESET);
								String email2=sc.nextLine();
								boolean b=cs.updateRecord(email1, email2);
								if(b)
									System.out.println(BOLD+RED+"\nEmail Id Updated Successfully"+RESET);
								
								else
									System.out.println(BOLD+RED+"\nCouldn't Update the Email Id, Try again"+RESET);
								
								break;
							
							case 3:
								break;
								
							default:
								System.out.println(YELLOW+BOLD+"Please choose the correct option!"+RESET);
						}
						
						break;
						
					case 3:
					{
//						System.out.println("\n1.View a customer\n2.View all customer\n3.Main Menu");
						System.out.println(PURPLE+"-----------------------------");
						System.out.println("|        VIEW MENU           |");
						System.out.println("-----------------------------");
						System.out.println("|  1. View a Customer        |");
						System.out.println("|  2. View All Customers     |");
						System.out.println("|  3. Main Menu              |");
						System.out.println("-----------------------------"+RESET);
						System.out.println(BOLD+"\nEnter your choice:"+RESET);
						
						int view=Integer.parseInt(sc.nextLine());
						
						switch(view)
						{
						case 1:
							System.out.println(BOLD+"Enter the customer number to be searched"+RESET);
							String custId=sc.nextLine();
							ArrayList<Customer> selected_record=cs.viewRecord(custId);
							if(selected_record.isEmpty())
							{
								System.out.println(BOLD+RED+"No records found"+RESET);
							}
							else
							{
								System.out.println(BOLD+" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s | %-21s | %-15s|\n","Customer Id","Customer_Name","DOB","Age","Gender","Occupation","Annual_Income","Medical history","Address","Phone_number","Email_Id");
								System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
								
								for(Customer i : selected_record)
								{
									System.out.printf(BOLD+"| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s | %-21s | %-15s|\n",i.getCustomer_id(),i.getCustomer_name(),i.getDob(),i.getAge(),i.getGender(),i.getOccupation(),i.getAnnual_income(),i.getMedical_history(),i.getAddress(),i.getPhone_number(),i.getEmail_id());
									System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);												
								}
							}
							break;
						
							
						case 2:
							System.out.println(BOLD+"\t\t\t\t\t\t***Record of all Customer***\n"+RESET);
							ArrayList<Customer> all_record=cs.viewRecord();
							if(all_record.isEmpty())
							{
								System.out.println(BOLD+RED+"No records found"+RESET);
							}
							else
							{
								System.out.println(TEAL+BOLD+" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-15s | %-15s | %-15s|\n","Customer Id","Customer_Name","DOB","Age","Gender","Occupation","Annual_Income","Medical history","Address","Phone_number","Email_Id");
								System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
								
								for(Customer i : all_record)
								{
								
									System.out.printf(BOLD+"| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-15s | %-15s | %-15s|\n",i.getCustomer_id(),i.getCustomer_name(),i.getDob(),i.getAge(),i.getGender(),i.getOccupation(),i.getAnnual_income(),i.getMedical_history(),i.getAddress(),i.getPhone_number(),i.getEmail_id());
									System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);												
								}
//								System.out.println(i.getCustomer_id()+"\t"+i.getCustomer_name()+"\t"+i.getDob()+"\t"+i.getAge()+"\t"+i.getGender()+"\t"+i.getOccupation()+"\t"+i.getAnnual_income()+"\t"+i.getMedical_history()+"\t"+i.getAddress()+"\t"+i.getPhone_number()+"\t"+i.getEmail_id());
								
							}
							break;
						case 3:
							break;
						
						default:
							System.out.println(YELLOW+BOLD+"Please choose the correct option!"+RESET);
						}
					}
					
					case 4:
						break;
					
					
					default:
						System.out.println(YELLOW+BOLD+"Please choose the correct option!"+RESET);
				
				}
				
				break;
		
		//policy case
			case 2:
				
				System.out.println(PURPLE+" ---------------------------");
				System.out.println("|        POLICY MENU        |");
				System.out.println(" -----------------------------");
				System.out.println("|  1. Add Policy detail     |");
				System.out.println("|  2. Update Policy detail  |");
				System.out.println("|  3. View Policy detail    |");
				System.out.println("|  4. Delete Policy  detail |");
				System.out.println("----------------------------"+RESET);
				
						System.out.println(BOLD+"\nEnter your choice:"+RESET);
						int opt=Integer.parseInt(sc.nextLine());
						
						switch(opt)
						{
							//Add policy
							case 1:
							{		
								System.out.println("Enter policy details:");			
								System.out.println("+-------------------------------------------------------------------------------------------------------------+");
								System.out.println("Example format-->Scheme Number:Policy Name:Policy Type:Maximum Number of Years:Premium Rate:Maximum Sum Assured");				    
								System.out.println("+--------------------------------------------------------------------------------------------------------------+");
								
								String num=sc.nextLine();
								posObj.addPolicy(num);
								System.out.println(BOLD+RED+"Added Successfully"+RESET);
								break;
							}
							//Update policy
							case 2:
							{
								int opt1=0;
								while(opt1!=3)
								{
									System.out.println(PURPLE+"-------------------------------------");
									System.out.println("|     POLICY UPDATE MENU             |");
									System.out.println("-------------------------------------");
									System.out.println("|  1. Update Max Sum Assured         |");
									System.out.println("|  2. Update Maximun Number of Years |");
									System.out.println("|  3. Main Menu                      |");
									System.out.println("-------------------------------------"+RESET);
									
									System.out.println(BOLD+"\nEnter your choice:"+RESET);
									opt1=Integer.parseInt(sc.nextLine());
									
//									sc.nextLine();
									switch(opt1)
									{
									//Policy type update
									case 1:
									{
										System.out.println("\nEnter Policy Id");
										String id=sc.nextLine();
										System.out.println("Enter New Max Sum Assured");
										int type=Integer.parseInt(sc.nextLine());
									    
										boolean num= posObj.updateMaxSumAssured(id,type);
										if(num) 
										{
											System.out.println(RED+BOLD+"Updated Successfully"+RESET);
										}
										else
										{
											System.out.println(RED+BOLD+"Update Failed"+RESET);
										}
										break;
									}
									case 2:
									{
										System.out.println("\nEnter Policy Id");
										String id=sc.nextLine();
										System.out.println("Enter New Max No Of Year");
										int type=Integer.parseInt(sc.nextLine());
									    
										boolean num= posObj.updateMaxNoOfYear(id,type);
										if(num) 
										{
											System.out.println(RED+BOLD+"Updated Successfully"+RESET);
										}
										else
										{
											System.out.println(RED+BOLD+"Update Failed"+RESET);
										}
										break;	
									}
									
									case 3:
										break;
									
									default:
										System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
									}
									
									break;
								}
								break;
							}
							//Retrieve policy
							case 3:
							{
								int opt1=0;
								while(opt1!=3)
								{
									System.out.println(PURPLE+"-----------------------------");
									System.out.println("|        VIEW MENU           |");
									System.out.println("-----------------------------");
									System.out.println("|  1. View a Policy detail    |");
									System.out.println("|  2. View All Policy details |");
									System.out.println("|  3. Main Menu               |");
									System.out.println("-----------------------------"+RESET);
									
									opt1=Integer.parseInt(sc.nextLine());
									System.out.println(BOLD+"\nEnter your choice:"+RESET);
									
									switch(opt1)
									{
									
										case 1:
										{
											System.out.println(BOLD+"\nEnter Policy Id"+RESET);
											String id=sc.nextLine();
											ArrayList<Policy> Pol=posObj.viewPolicyRecord(id);
											
											if(Pol.isEmpty())
											{
												System.out.println(RED+BOLD+"No Policy Records found"+RESET);
											}
											else
											{
												System.out.println(TEAL+BOLD+" -----------------------------------------------------------------------------------------------------------------------------------");
												System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s |\n","Policy Id","Scheme Number","Policy Name","Policy Type","Maximum Number Of Years","Premium Rate","Maximum Sum Assured");
												System.out.println("------------------------------------------------------------------------------------------------------------------------------------"+RESET);
												
												for(Policy p:Pol)
												{
													System.out.printf(BOLD+"| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s |\n",p.getPolicyId(),p.getSchemeNumber(),p.getPolicyName(),p.getPolicyType(),p.getMaxNoOfYears(),p.getPremiumRate(),p.getMaxSumAssured());
													System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------"+RESET);
												}
												
											}
											
											break;
										}
									
										case 2:
										{
											ArrayList<Policy> Pol=posObj.getPolicyDetail();
											
											if(Pol.isEmpty())
											{
												System.out.println(RED+BOLD+"No Policy Records found"+RESET);
											}
											else
											{
												System.out.println(TEAL+BOLD+" -----------------------------------------------------------------------------------------------------------------------------------");
												System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s |\n","Policy Id","Scheme Number","Policy Name","Policy Type","Maximum Number Of Years","Premium Rate","Maximum Sum Assured");
												System.out.println("------------------------------------------------------------------------------------------------------------------------------------"+RESET);
												
												for(Policy p:Pol)
												{
													System.out.printf(BOLD+"| %-11s | %-16s | %-12s | %-12s | %-24s | %-15s | %-21s |\n",p.getPolicyId(),p.getSchemeNumber(),p.getPolicyName(),p.getPolicyType(),p.getMaxNoOfYears(),p.getPremiumRate(),p.getMaxSumAssured());
													System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------"+RESET);												
												}
												
												}
											break;
										}
										
										case 3:
										{
											break;
										}
										default:
											System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
									}
								}
								break;
							
						
							}
							//Delete policy
							case 4:
							{
								System.out.println(BOLD+"Enter Policy Id"+RESET);
								String type=sc.nextLine();
								
								System.out.println(BOLD+TEAL+"Do you want to delete the record? (Yes/No)"+RESET);
								String option=sc.nextLine();
								if(option.equalsIgnoreCase("yes"))
								{
									boolean num= posObj.deletePolicy(type);
								
								if(num) 
									System.out.println(RED+BOLD+"Deleted Successfully"+RESET);
								else
									System.out.println(RED+BOLD+"Delete Failed"+RESET);
								}
								else
								{
									System.out.println(RED+BOLD+"Deletion Cancelled"+RESET);
									break;
								}
							}
							break;
							
							case 5:	
							{
								break;
							}
							default:
								System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
							
						}
				break;
						
				
		
				
				
		//Allocation		
			case 3:
				System.out.println(PURPLE+"+--------------------------------+");
				System.out.println("          ALLOCATION MENU        ");
				System.out.println("+--------------------------------+");
				System.out.println("| 1. Add allocation details      |");
				System.out.println("| 2. Update allocation details   |");
				System.out.println("| 3. Retrieve allocation records |");
				System.out.println("| 4. Main Menu                 	 |");
				System.out.println("+--------------------------------+"+RESET);

				AllocationService allocservice = new AllocationService();
				System.out.println(BOLD+"Enter your choice:"+RESET);
				int ch=Integer.parseInt(sc.nextLine());
//				sc.nextLine();
				
				if(ch==1) 
				{
					System.out.println(BOLD+"Enter the Customer ID"+RESET);
					String custId=sc.nextLine();
					allocservice.setCustId(custId);
					
					System.out.println(BOLD+"Enter the Policy ID"+RESET);
					String polId=sc.nextLine();
					allocservice.setPollId(polId);
					System.out.println(BOLD+"Enter the details:"+RESET);
					System.out.println("+-----------------------------------------------------------------+");
					System.out.println("Example format-->NOMINEE_NAME:PREMIUM_PAYMENT_CYCLE:POLICY_STATUS");				    
					System.out.println("+-----------------------------------------------------------------+");
					String details = sc.nextLine();
					
					boolean result = allocservice.addAllocation(details);
					if (result) 
					{
						System.out.println(BOLD+RED+"Records are added successfully"+RESET);
					} 
					else 
					{
						System.out.println(BOLD+RED+"Records are not added"+RESET);
					}
				}
				
				else if(ch==2) 
				{   System.out.println(PURPLE+"+----------------------------------------+");
					System.out.println("            ALLOCATON UPDATE MENU         ");
					System.out.println("+----------------------------------------+");
					System.out.println("| 1. Update Total Amount in allocation   |");
					System.out.println("| 2. Update Policy Status in allocation  |");
					System.out.println("+----------------------------------------+"+RESET);
					
					System.out.println(BOLD+"Enter your choice:"+RESET);
					ch=Integer.parseInt(sc.nextLine());
//					sc.nextLine();
					if(ch==1) 
					{
						System.out.println(BOLD+"Enter the new amount");
						double TOTAL_PAYMENT=Double.parseDouble(sc.nextLine());
//						sc.nextLine();
						System.out.println("Enter the Allocation Id "+RESET);
						String ALLOCATION_ID=sc.nextLine();
//						AllocationService allocservice = new AllocationService();
						boolean result = allocservice.updateAllocation(ALLOCATION_ID);
							if(result) 
							{
								System.out.println(BOLD+RED+"Records are Updated Successfully"+RESET);
							}
							else 
							{
								System.out.println(BOLD+RED+"Records are not Updated"+RESET);
								break;
							}
					}
					else if(ch==2) 
					{
						System.out.println(BOLD+"Enter the Policy Status");
						String POLICY_STATUS=sc.nextLine();
						System.out.println("Enter the Allocation Id "+RESET);
						String ALLOCATION_ID=sc.nextLine();
//						AllocationService allocservice = new AllocationService();
						boolean result = allocservice.updateAllocation(POLICY_STATUS,ALLOCATION_ID);
						if(result)
						{
							System.out.println(BOLD+RED+"Records are Updated Successfully"+RESET);
						}
						else 
						{
							System.out.println(BOLD+RED+"Records are not Updated"+RESET);
							break;
						}
					}
					else
					{
					System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
					}
					
				}
				//next if
				else if(ch==3) 
				{
					System.out.println(PURPLE+"+--------------------------------------------------------------+");
					System.out.println("|                   Allocation Retrieval Menu                  |");
					System.out.println("+--------------------------------------------------------------+");
					System.out.println("| 1. Retrieve allocation detail based on 'Policy Id'           |");
					System.out.println("| 2. Retrieve allocation detail based on 'Policy Status'       |");
					System.out.println("| 3. Retrieve allocation detail based on 'Allocation Id'       |");
					System.out.println("+--------------------------------------------------------------+"+RESET);

					System.out.println(BOLD+"Enter your choice:"+RESET);
					ch=Integer.parseInt(sc.nextLine());
//					sc.nextLine();
							if(ch==1) 
							{
								System.out.println(BOLD+"Enter the Policy Id"+RESET);
								String POLICY_ID=sc.nextLine();
//								AllocationService allocservice = new AllocationService();
								List<Allocation> arlist=allocservice.retreiveBasedPolicyId(POLICY_ID);
									if(arlist.isEmpty()) 
									{
										System.out.println("No Record Found");
										break;
									}
									else
									{
										System.out.println(TEAL+BOLD+" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
										System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s| %15s|\n","Allocation_Id","Customer_Id","Policy_ID","Nominee_name","Sum_Assured","No of years","Premium_Amount","Premium_Payment_Cycle","Total_Payment","Policy_status");
										System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
										
										for(Allocation a:arlist)
										{
											System.out.printf("| %-13s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s |\n",a.getALLOCATION_ID(),a.getCUSTOMER_ID().getCustomer_id(),a.getPOLICY_ID().getPolicyId(),a.getNOMINEE_NAME(),a.getSUM_ASSURED(),a.getNO_OF_YEARS(),a.getPREMIUM_AMOUNT(),a.getPREMIUM_PAYMENT_CYCLE(),a.getPOLICY_STATUS());
											System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							    		  
										}
									
									}
							}
							else if(ch==2) 
							{
								System.out.println(BOLD+"Enter the Policy Status"+RESET);
								String POLICY_STATUS=sc.nextLine();
//								AllocationService allocservice = new AllocationService();
								List<Allocation> arlist=allocservice.retreiveBasedPolicyStatus(POLICY_STATUS);
								if(arlist.isEmpty()) {
									System.out.println("No Record Found");
									break;
								}
								else
								{
									
									System.out.println(TEAL+BOLD+" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
									System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s| %15s|\n","Allocation_Id","Customer_Id","Policy_ID","Nominee_name","Sum_Assured","No of years","Premium_Amount","Premium_Payment_Cycle","Total_Payment","Policy_status");
									System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
									
									for(Allocation a:arlist)
									{
										System.out.printf("| %-13s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s |\n",a.getALLOCATION_ID(),a.getCUSTOMER_ID().getCustomer_id(),a.getPOLICY_ID().getPolicyId(),a.getNOMINEE_NAME(),a.getSUM_ASSURED(),a.getNO_OF_YEARS(),a.getPREMIUM_AMOUNT(),a.getPREMIUM_PAYMENT_CYCLE(),a.getPOLICY_STATUS());
										System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						    		  
									}									
								
								}
							}
							else if(ch==3) 
							{
								System.out.println(BOLD+"Enter the Policy Allocation Id"+RESET);
								String ALLOCATION_ID=sc.nextLine();
								List<Allocation> arlist=allocservice.retreiveBasedAllocationId(ALLOCATION_ID);
								if(arlist.isEmpty()) {
									System.out.println(BOLD+RED+"No Record Found"+RESET);
									break;
								}
								else
								{
									System.out.println(TEAL+BOLD+" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
									System.out.printf("| %-11s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s| %15s|\n","Allocation_Id","Customer_Id","Policy_ID","Nominee_name","Sum_Assured","No of years","Premium_Amount","Premium_Payment_Cycle","Total_Payment","Policy_status");
									System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
									
									for(Allocation a:arlist)
									{
										System.out.printf("| %-13s | %-16s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s |\n",a.getALLOCATION_ID(),a.getCUSTOMER_ID().getCustomer_id(),a.getPOLICY_ID().getPolicyId(),a.getNOMINEE_NAME(),a.getSUM_ASSURED(),a.getNO_OF_YEARS(),a.getPREMIUM_AMOUNT(),a.getPREMIUM_PAYMENT_CYCLE(),a.getPOLICY_STATUS());
										System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						    		  
									}
								
								}
							}
							
							else
							{
								System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
							}
					
				}
				
				else
				{
					System.out.println(YELLOW+BOLD+"\nPlease choose the correct option!"+RESET);
				}
				break;
				
		
				//Payment
			case 4:
//				
			 System.out.println(BLUE+"+-------------------------------+");
					System.out.println("|        PAYMENT MENU           |");
					System.out.println("+-------------------------------+");
					System.out.println("|  1. Make The Payment          |");
					System.out.println("|  2. Retreive The Payment      |");
					System.out.println("|  3. Delete The Payment        |");	
					System.out.println("|  4. Main Menu                 |");
					System.out.println("+-------------------------------+"+RESET);
					System.out.println(BOLD+"\nEnter your choice:"+RESET);
		    	int paymentch=Integer.parseInt(sc.nextLine());
		    	switch(paymentch)
		    	{
					case 1:
						
						System.out.println(GREEN+BOLD+"Enter the customer Id"+RESET);
						String cut=sc.nextLine();
						ps.custAdd(cut);
						System.out.println(GREEN+BOLD+"Enter the Allocation Id"+RESET);
						String all=sc.nextLine();
						ps.allAdd(all);
		    	
		    		   System.out.println(GREEN+BOLD+"Enter details like this -->Payment Date:Payment Mode"+RESET);
		    		   System.out.println("enter date like this --> dd/MM/yyyy");
		    		   
		    		
		    		   String paydet=sc.nextLine();
		    		   if(ps.addPayment(paydet))
		    			   System.out.println(GREEN+BOLD+"\n------------------>Payment Added Successfully<--------------------\n"+RESET);
		    		   else 
		    			   System.out.println(RED+BOLD+"\n------------------>Payment Not Added<--------------------\n"+RESET);
		    		   
		    		   break;
		    		   
					case 2:
			    		   System.out.println(GREEN+BOLD+"Enter the ALLOCATION Id"+RESET);
			    		   String allid=sc.nextLine();
			    		   List<Payment> records= ps.retrievePayment(allid);
			    		   
			    		   if(records.isEmpty())
			    		   {
			    			   System.out.println(RED+BOLD+"**Record not found**"+RESET);
			    		   }
			           	   else
			           	   {
			         System.out.println(BLUE+"+---------------------------------------------------------------------------------------------------------------+");
				    		System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s | %-15s |\n","Payment Id","Customer Id","Allocation Id","Premium","Premium Date","Premium Mode","Installment Count");
				    	   System.out.println("+---------------------------------------------------------------------------------------------------------------+"+RESET);
			           		for(Payment c:records)
				    		   {
				      System.out.printf(GREEN+"| %-11s | %-16s | %-10s | ₹%-4s | %-8s | %-15s | %-15s |\n",c.getPayment_id(),c.getCustomer_id().getCustomer_id(),c.getAllocation_id().getALLOCATION_ID(),c.getPremium(),c.getPayment_date(),c.getPayment_mode(),c.getInstallment_count());
						  System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				    		   }
			           	   }
			           	break;
					
					case 3:
						System.out.println(RED+BOLD+"Do You Want Delete The Payment (YES/NO)"+RESET);
						String check=sc.nextLine();
						if("yes".equalsIgnoreCase(check)) {
							  System.out.println(GREEN+BOLD+"Enter Payment Id"+RESET);
				    		   String payid=sc.nextLine();
				    		   if(ps.deletePayment(payid)) 
				    			   System.out.println(RED+BOLD+"Deleted successfully"+RESET);
				    		   else
				    			   System.out.println(RED+BOLD+"not Deleted"+RESET);
				    		   
				    		   break;
						}else if("no".equalsIgnoreCase(check)) {
							System.out.println(GREEN+BOLD+"----------->Going To Menu<-----------"+RESET);
						}
						 break;
					
					case 4:
							break;
	    		   
		    	}
		    	break;
		
		    	//Claim 	
			case 5:
				System.out.println(PURPLE+"+-------------------------------------------+");
				System.out.println("|                 Claim Menu                |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 1. Add Claim detail                       |");
				System.out.println("| 2. Retrieve Claim detail                  |");
				System.out.println("| 3. Delete Claim detail                    |");
				System.out.println("| 4. Main menu                             |");
				System.out.println("+-------------------------------------------+"+RESET);

				System.out.println(BOLD+"\nEnter your choice:"+RESET);
				int ch2=Integer.parseInt(sc.nextLine());
				switch(ch2)
				{
				case 1:
					
					System.out.println(BOLD+"Enter the Customer Id"+RESET);
					String custId=sc.nextLine();
					clObj.setCustomerId(custId);
					
					System.out.println(BOLD+"Enter the Allocation Id"+RESET);
					String alloId=sc.nextLine();
					clObj.setAllocationId(alloId);
					
					System.out.println(BOLD+"Enter the Claim details to be added"+RESET);
					System.out.println("+-----------------------------------------------------------------+");
					System.out.println("Example format-->CLAIM_TYPE(Death/Maturity):DATE(dd/MM/YYYY)");				    
					System.out.println("+-----------------------------------------------------------------+");
					
					String claim_detail=sc.nextLine();
					
					boolean bo=clObj.addClaim(claim_detail);
					
					if(bo)
					{
			    		System.out.println(RED+BOLD+"Claim record added successfully"+RESET);
					}
					else
					{
						System.out.println(RED+BOLD+"Couldn't add the Claim record"+RESET);
					}
					break;
					
				case 2:
				{
					
					System.out.println(PURPLE+"+-------------------------------------------+");
					System.out.println("|          View Claim Records Menu          |");
					System.out.println("+-------------------------------------------+");
					System.out.println("| 1. View a particular record               |");
					System.out.println("| 2. View all claim records                 |");
					System.out.println("+-------------------------------------------+"+RESET);

					System.out.println(BOLD+"\nEnter your choice:"+RESET);
					int viewclaim=Integer.parseInt(sc.nextLine());
					switch(viewclaim)
					{
						case 1:
							System.out.println(BOLD+"Enter the Claim ID"+RESET);
							String id=sc.nextLine();
							ArrayList<Claim> list=clObj.viewClaim(id);
							if(list.isEmpty())
							{

								System.out.println(RED+BOLD+"No records found for the given claim Id"+RESET);
							}
							else
							{
								System.out.println(BOLD+GREEN+" --------------------------------------------------------------------------------------------------------------------------------------------");
					    		System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n","Claim ID","Customer ID","Allocation ID","Claim Type","Claim Amount","Claim Date");
					    		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
								for(Claim c:list)
								{
									System.out.printf(BOLD+GREEN+"| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n",c.getClaim_id(),c.getCustomer_id().getCustomer_id(),c.getAllocation_id().getALLOCATION_ID() ,c.getClaim_type(),c.getClaim_amount(),c.getClaim_date());
									System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
								}
							}
							break;
							
							
						case 2:
							ArrayList<Claim> list2=clObj.viewClaim();
							if(list2.isEmpty())
							{

								System.out.println(RED+BOLD+"No records found for the given claim Id"+RESET);
								break;
							}
							else
							{
								System.out.println(BOLD+GREEN+" -------------------------------------------------------------------------------------------------------------------------------------");
					    		System.out.printf("| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n","Claim ID","Customer ID","Allocation ID","Claim Type","Claim Amount","Claim Date");
					    		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
								for(Claim c:list2)
								{
									System.out.printf(BOLD+GREEN+"| %-11s | %-16s | %-10s | %-4s | %-8s | %-15s |\n",c.getClaim_id(),c.getCustomer_id().getCustomer_id(),c.getAllocation_id().getALLOCATION_ID() ,c.getClaim_type(),c.getClaim_amount(),c.getClaim_date());
									System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------------------"+RESET);
								}
								break;
							}
						
					}
					break;
				}
				case 3:
					System.out.println(BOLD+"Enter the claimId to be deleted"+RESET);
					String claimId=sc.nextLine();
					
					System.out.println(BOLD+"Do you want to delete the record? (Yes/No)"+RESET);
					String option=sc.nextLine();
					
					if(option.equalsIgnoreCase("yes"))
					{
						boolean op=clObj.deleteClaim(claimId);
						if(op)
							System.out.println(RED+BOLD+"Deleted Successfully"+RESET);
						else
							System.out.println(RED+BOLD+"Couldn't delete the record"+RESET);
					}
					else
					{
						System.out.println("Cancelled Deletion");
					}
					
					break;
							
				case 4:
					break;
						
				}
			     break;
			case 6:
				System.out.println(BOLD+"Thanks for using this application!!!"+RESET);
				bool=false;
				return;
				
			}
		}
		
		catch(Exception e)
		{
			System.out.println(BOLD+RED+"Please check the input and provide right information"+RESET);
//			e.printStackTrace();
		}
		
		}
		}
	}

