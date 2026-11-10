package com.gabriel.emplms.serviceimpl;
import com.gabriel.emplms.entity.ManagerData;
import com.gabriel.emplms.model.Manager;
import com.gabriel.emplms.repository.ManagerDataRepository;
import com.gabriel.emplms.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ManagerServiceImpl implements ManagerService {
	Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);
	@Autowired
	ManagerDataRepository managerDataRepository;
	@Autowired
	@Override
	public Manager[] getAll() {
		List<ManagerData> managersData = new ArrayList<>();
		List<Manager> managers = new ArrayList<>();
		managerDataRepository.findAll().forEach(managersData::add);
		Iterator<ManagerData> it = managersData.iterator();
		while(it.hasNext()) {
			ManagerData managerData = it.next();
			Manager manager = new Manager();
			manager.setId(managerData.getId());
			manager.setName(managerData.getName());
			managers.add(manager);
		}
		Manager[] array = new Manager[managers.size()];
		for  (int i=0; i<managers.size(); i++){
			array[i] = managers.get(i);
		}
		return array;
	}
	@Override
	public Manager create(Manager manager) {
		logger.info(" add:Input " + manager.toString());
		ManagerData managerData = new ManagerData();
		managerData.setName(manager.getName());
		managerData = managerDataRepository.save(managerData);
		logger.info(" add:Input " + managerData.toString());
			Manager newManager = new Manager();
			newManager.setId(managerData.getId());
			newManager.setName(managerData.getName());
		return newManager;
	}
	@Override
	public Manager update(Manager manager) {
		ManagerData managerData = new ManagerData();
		managerData.setId(manager.getId());
		managerData.setName(manager.getName());
		managerData.setCreated(null);
		managerData = managerDataRepository.save(managerData);
		Manager newManager = new Manager();
		newManager.setId(managerData.getId());
		newManager.setName(managerData.getName());
		return newManager;
	}
	@Override
	public Manager get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Optional<ManagerData> optional = managerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			ManagerData managerData = optional.get();
			Manager manager = new Manager();
			manager.setId(managerData.getId());
			manager.setName(managerData.getName());
			return manager;
		}
		logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		return null;
	}
	@Override
	public void delete(Integer id) {
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ManagerData> optional = managerDataRepository.findById(id);
		if( optional.isPresent()) {
			ManagerData managerDatum = optional.get();
			managerDataRepository.delete(managerDatum);
			logger.info(" Success >> " + managerDatum.toString());
		}
		else {
			logger.info(" Failed >> unable to locatemanager id:" +  Integer.toString(id));
		}
	}
}
