package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/employee";

	protected static EmployeeService service= null;
	public static EmployeeService getService(){
		if(service == null){
			service = new EmployeeService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public Employee get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Employee> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Employee.class);
		return response.getBody();
	}

	public Employee[] getAll() {
		String url = endpointUrl;
		logger.info("getEmployees: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Employee[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Employee[].class);
		Employee[] employees = response.getBody();
		return employees;
	}

	public Employee create(Employee employee) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
		final ResponseEntity<Employee> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Employee.class);
		return response.getBody();
	}
	public Employee update(Employee employee) {
		logger.info("update: " + employee.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
		final ResponseEntity<Employee> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Employee.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + "/" + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Employee> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Employee.class);
	}
}
