package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Gender;
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
public class GenderService {
	Logger logger = LoggerFactory.getLogger(GenderService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/gender";

	protected static GenderService service= null;
	public static GenderService getService(){
		if(service == null){
			service = new GenderService();
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

	public Gender get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Gender> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Gender.class);
		return response.getBody();
	}

	public Gender[] getAll() {
		String url = endpointUrl;
		logger.info("getGenders: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Gender[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Gender[].class);
		Gender[] genders = response.getBody();
		return genders;
	}

	public Gender create(Gender gender) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Gender> request = new HttpEntity<>(gender, headers);
		final ResponseEntity<Gender> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Gender.class);
		return response.getBody();
	}
	public Gender update(Gender gender) {
		logger.info("update: " + gender.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Gender> request = new HttpEntity<>(gender, headers);
		final ResponseEntity<Gender> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Gender.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + "/" + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Gender> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Gender> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Gender.class);
	}
}
