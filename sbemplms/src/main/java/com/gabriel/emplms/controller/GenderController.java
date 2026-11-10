package com.gabriel.emplms.controller;
import com.gabriel.emplms.model.Gender;
import com.gabriel.emplms.service.GenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class GenderController {
	Logger logger = LoggerFactory.getLogger( GenderController.class);
	@Autowired
	private GenderService genderService;
	@GetMapping("/api/gender")
	public ResponseEntity<?> listGender()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Gender[] gender = genderService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(gender);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/gender")
	public ResponseEntity<?> add(@RequestBody Gender gender){
		logger.info("Input >> " + gender.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Gender newGender = genderService.create(gender);
			logger.info("created gender >> " + newGender.toString() );
			response = ResponseEntity.ok(newGender);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve gender with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/gender")
	public ResponseEntity<?> update(@RequestBody Gender gender){
		logger.info("Update Input >> gender.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Gender newGender = genderService.update(gender);
			response = ResponseEntity.ok(gender);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve gender with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/gender/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input gender id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Gender gender = genderService.get(id);
			response = ResponseEntity.ok(gender);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/gender/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			genderService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
