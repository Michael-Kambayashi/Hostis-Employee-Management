package com.gabriel.emplms.serviceimpl;
import com.gabriel.emplms.entity.GenderData;
import com.gabriel.emplms.model.Gender;
import com.gabriel.emplms.repository.GenderDataRepository;
import com.gabriel.emplms.service.GenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class GenderServiceImpl implements GenderService {
	Logger logger = LoggerFactory.getLogger(GenderServiceImpl.class);
	@Autowired
	GenderDataRepository genderDataRepository;
	@Autowired
	@Override
	public Gender[] getAll() {
		List<GenderData> gendersData = new ArrayList<>();
		List<Gender> genders = new ArrayList<>();
		genderDataRepository.findAll().forEach(gendersData::add);
		Iterator<GenderData> it = gendersData.iterator();
		while(it.hasNext()) {
			GenderData genderData = it.next();
			Gender gender = new Gender();
			gender.setId(genderData.getId());
			gender.setName(genderData.getName());
			genders.add(gender);
		}
		Gender[] array = new Gender[genders.size()];
		for  (int i=0; i<genders.size(); i++){
			array[i] = genders.get(i);
		}
		return array;
	}
	@Override
	public Gender create(Gender gender) {
		logger.info(" add:Input " + gender.toString());
		GenderData genderData = new GenderData();
		genderData.setName(gender.getName());
		genderData = genderDataRepository.save(genderData);
		logger.info(" add:Input " + genderData.toString());
			Gender newGender = new Gender();
			newGender.setId(genderData.getId());
			newGender.setName(genderData.getName());
		return newGender;
	}
	@Override
	public Gender update(Gender gender) {
		GenderData genderData = new GenderData();
		genderData.setId(gender.getId());
		genderData.setName(gender.getName());
		genderData.setCreated(null);
		genderData = genderDataRepository.save(genderData);
		Gender newGender = new Gender();
		newGender.setId(genderData.getId());
		newGender.setName(genderData.getName());
		return newGender;
	}
	@Override
	public Gender get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Optional<GenderData> optional = genderDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			GenderData genderData = optional.get();
			Gender gender = new Gender();
			gender.setId(genderData.getId());
			gender.setName(genderData.getName());
			return gender;
		}
		logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		return null;
	}
	@Override
	public void delete(Integer id) {
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<GenderData> optional = genderDataRepository.findById(id);
		if( optional.isPresent()) {
			GenderData genderDatum = optional.get();
			genderDataRepository.delete(genderDatum);
			logger.info(" Success >> " + genderDatum.toString());
		}
		else {
			logger.info(" Failed >> unable to locategender id:" +  Integer.toString(id));
		}
	}
}
