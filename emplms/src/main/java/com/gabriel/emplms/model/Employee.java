package com.gabriel.emplms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Employee{
	int id;
	String firstname;
	String lastname;
	String email;
	String phoneNumber;
	Date hireDate;
	int jobId;
	String jobName;
	int departmentId;
	String departmentName;
	int managerId;
	String managerName;
	int genderId;
	String genderName;
	int statusId;
	String statusName;
	String address;
	String country;
	String state;
	String city;
	Date dateOfBirth;
	double salary;
	@Override
	public String toString(){
		return firstname + " " + lastname;
	}
}
