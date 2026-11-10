package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Status;
public interface StatusService {
	Status[] getAll() throws Exception;
	Status get(Integer id) throws Exception;
	Status create(Status status) throws Exception;
	Status update(Status status) throws Exception;
	void delete(Integer id) throws Exception;
}
