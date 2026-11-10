package com.gabriel.emplms.controller;
import com.gabriel.emplms.model.Manager;
import com.gabriel.emplms.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ManagerController {
	Logger logger = LoggerFactory.getLogger( ManagerController.class);
	@Autowired
	private ManagerService managerService;
	@GetMapping("/api/manager")
	public ResponseEntity<?> listManager()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manager[] manager = managerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(manager);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/manager")
	public ResponseEntity<?> add(@RequestBody Manager manager){
		logger.info("Input >> " + manager.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manager newManager = managerService.create(manager);
			logger.info("created manager >> " + newManager.toString() );
			response = ResponseEntity.ok(newManager);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve manager with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/manager")
	public ResponseEntity<?> update(@RequestBody Manager manager){
		logger.info("Update Input >> manager.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manager newManager = managerService.update(manager);
			response = ResponseEntity.ok(manager);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve manager with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/manager/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input manager id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manager manager = managerService.get(id);
			response = ResponseEntity.ok(manager);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/manager/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			managerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
