package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Job;
public interface JobService {
	Job[] getAll() throws Exception;
	Job get(Integer id) throws Exception;
	Job create(Job job) throws Exception;
	Job update(Job job) throws Exception;
	void delete(Integer id) throws Exception;
}
