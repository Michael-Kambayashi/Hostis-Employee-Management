package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Manager;
public interface ManagerService {
	Manager[] getAll() throws Exception;
	Manager get(Integer id) throws Exception;
	Manager create(Manager manager) throws Exception;
	Manager update(Manager manager) throws Exception;
	void delete(Integer id) throws Exception;
}
