package com.gabriel.emplms.transform;
import com.gabriel.emplms.entity.EmployeeData;
import com.gabriel.emplms.model.Employee;
import org.springframework.stereotype.Service;
@Service
public class TransformEmployeeServiceImpl implements TransformEmployeeService {
	@Override
	public EmployeeData transform(Employee employee){
		EmployeeData employeeData = new EmployeeData();
		employeeData.setId(employee.getId());
		employeeData.setFirstname(employee.getFirstname());
		employeeData.setLastname(employee.getLastname());
		employeeData.setEmail(employee.getEmail());
		employeeData.setPhoneNumber(employee.getPhoneNumber());
		employeeData.setHireDate(employee.getHireDate());
		employeeData.setJobId(employee.getJobId());
		employeeData.setJobName(employee.getJobName());
		employeeData.setDepartmentId(employee.getDepartmentId());
		employeeData.setDepartmentName(employee.getDepartmentName());
		employeeData.setManagerId(employee.getManagerId());
		employeeData.setManagerName(employee.getManagerName());
		employeeData.setGenderId(employee.getGenderId());
		employeeData.setGenderName(employee.getGenderName());
		employeeData.setStatusId(employee.getStatusId());
		employeeData.setStatusName(employee.getStatusName());
		employeeData.setAddress(employee.getAddress());
		employeeData.setCountry(employee.getCountry());
		employeeData.setState(employee.getState());
		employeeData.setCity(employee.getCity());
		employeeData.setDateOfBirth(employee.getDateOfBirth());
		employeeData.setSalary(employee.getSalary());
		return employeeData;
	}
	@Override

	public Employee transform(EmployeeData employeeData){;
		Employee employee = new Employee();
		employee.setId(employeeData.getId());
		employee.setFirstname(employeeData.getFirstname());
		employee.setLastname(employeeData.getLastname());
		employee.setEmail(employeeData.getEmail());
		employee.setPhoneNumber(employeeData.getPhoneNumber());
		employee.setHireDate(employeeData.getHireDate());
		employee.setJobId(employeeData.getJobId());
		employee.setJobName(employeeData.getJobName());
		employee.setDepartmentId(employeeData.getDepartmentId());
		employee.setDepartmentName(employeeData.getDepartmentName());
		employee.setManagerId(employeeData.getManagerId());
		employee.setManagerName(employeeData.getManagerName());
		employee.setGenderId(employeeData.getGenderId());
		employee.setGenderName(employeeData.getGenderName());
		employee.setStatusId(employeeData.getStatusId());
		employee.setStatusName(employeeData.getStatusName());
		employee.setAddress(employeeData.getAddress());
		employee.setCountry(employeeData.getCountry());
		employee.setState(employeeData.getState());
		employee.setCity(employeeData.getCity());
		employee.setDateOfBirth(employeeData.getDateOfBirth());
		employee.setSalary(employeeData.getSalary());
		return employee;
	}
}
