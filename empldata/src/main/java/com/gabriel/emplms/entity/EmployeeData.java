package com.gabriel.emplms.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_data")
public class EmployeeData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastUpdated;


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date created;

}
