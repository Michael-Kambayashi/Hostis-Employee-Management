package com.gabriel.emplms.controller;
import com.gabriel.emplms.model.Job;
import com.gabriel.emplms.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class JobController {
	Logger logger = LoggerFactory.getLogger( JobController.class);
	@Autowired
	private JobService jobService;
	@GetMapping("/api/job")
	public ResponseEntity<?> listJob()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Job[] job = jobService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(job);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/job")
	public ResponseEntity<?> add(@RequestBody Job job){
		logger.info("Input >> " + job.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Job newJob = jobService.create(job);
			logger.info("created job >> " + newJob.toString() );
			response = ResponseEntity.ok(newJob);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve job with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/job")
	public ResponseEntity<?> update(@RequestBody Job job){
		logger.info("Update Input >> job.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Job newJob = jobService.update(job);
			response = ResponseEntity.ok(job);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve job with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/job/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input job id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Job job = jobService.get(id);
			response = ResponseEntity.ok(job);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/job/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			jobService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
