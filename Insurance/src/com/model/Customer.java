package com.model;
import java.sql.Date;

public class Customer {
		private String customer_id;
		private String customer_name;
		private Date dob;
		private int age;
		private String gender;
		private String occupation;
		private double annual_income;
		private String medical_history;
		private String address;
		private long phone_number;
		private String email_id;
	
		
		public Customer(String customer_id, String customer_name, Date dob, int age, String gender, String occupation,double annual_income, String medical_history, String address, long phone_number, String email_id)
		{
			super();
			this.customer_id = customer_id;
			this.customer_name = customer_name;
			this.dob = dob;
			this.age = age;
			this.gender = gender;
			this.occupation = occupation;
			this.annual_income = annual_income;
			this.medical_history = medical_history;
			this.address = address;
			this.phone_number = phone_number;
			this.email_id = email_id;
		}
		
		public Customer()
		{
			
		}

		public String getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(String customer_id) {
			this.customer_id = customer_id;
		}
		
		public String getCustomer_name() {
			return customer_name;
		}
		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}
		
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		
		public double getAnnual_income() {
			return annual_income;
		}
		public void setAnnual_income(double annual_income) {
			this.annual_income = annual_income;
		}
		
		public String getMedical_history() {
			return medical_history;
		}
		public void setMedical_history(String medical_history) {
			this.medical_history = medical_history;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public long getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(long phone_number) {
			this.phone_number = phone_number;
		}
		
		public String getEmail_id() {
			return email_id;
		}
		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}
		
		
		
		
}
