package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Manager;
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
public class ManagerService {
	Logger logger = LoggerFactory.getLogger(ManagerService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/manager";

	protected static ManagerService service= null;
	public static ManagerService getService(){
		if(service == null){
			service = new ManagerService();
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

	public Manager get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manager> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Manager.class);
		return response.getBody();
	}

	public Manager[] getAll() {
		String url = endpointUrl;
		logger.info("getManagers: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manager[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Manager[].class);
		Manager[] managers = response.getBody();
		return managers;
	}

	public Manager create(Manager manager) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manager> request = new HttpEntity<>(manager, headers);
		final ResponseEntity<Manager> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Manager.class);
		return response.getBody();
	}
	public Manager update(Manager manager) {
		logger.info("update: " + manager.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manager> request = new HttpEntity<>(manager, headers);
		final ResponseEntity<Manager> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Manager.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + "/" + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manager> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manager> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Manager.class);
	}
}
