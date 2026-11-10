package com.gabriel.emplms.service;
import com.gabriel.emplms.model.Gender;
public interface GenderService {
	Gender[] getAll() throws Exception;
	Gender get(Integer id) throws Exception;
	Gender create(Gender gender) throws Exception;
	Gender update(Gender gender) throws Exception;
	void delete(Integer id) throws Exception;
}
