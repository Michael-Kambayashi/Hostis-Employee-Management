package com.gabriel.emplms.serviceimpl;
import com.gabriel.emplms.entity.JobData;
import com.gabriel.emplms.model.Job;
import com.gabriel.emplms.repository.JobDataRepository;
import com.gabriel.emplms.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class JobServiceImpl implements JobService {
	Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
	@Autowired
	JobDataRepository jobDataRepository;
	@Autowired
	@Override
	public Job[] getAll() {
		List<JobData> jobsData = new ArrayList<>();
		List<Job> jobs = new ArrayList<>();
		jobDataRepository.findAll().forEach(jobsData::add);
		Iterator<JobData> it = jobsData.iterator();
		while(it.hasNext()) {
			JobData jobData = it.next();
			Job job = new Job();
			job.setId(jobData.getId());
			job.setName(jobData.getName());
			jobs.add(job);
		}
		Job[] array = new Job[jobs.size()];
		for  (int i=0; i<jobs.size(); i++){
			array[i] = jobs.get(i);
		}
		return array;
	}
	@Override
	public Job create(Job job) {
		logger.info(" add:Input " + job.toString());
		JobData jobData = new JobData();
		jobData.setName(job.getName());
		jobData = jobDataRepository.save(jobData);
		logger.info(" add:Input " + jobData.toString());
			Job newJob = new Job();
			newJob.setId(jobData.getId());
			newJob.setName(jobData.getName());
		return newJob;
	}
	@Override
	public Job update(Job job) {
		JobData jobData = new JobData();
		jobData.setId(job.getId());
		jobData.setName(job.getName());
		jobData.setCreated(null);
		jobData = jobDataRepository.save(jobData);
		Job newJob = new Job();
		newJob.setId(jobData.getId());
		newJob.setName(jobData.getName());
		return newJob;
	}
	@Override
	public Job get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Optional<JobData> optional = jobDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			JobData jobData = optional.get();
			Job job = new Job();
			job.setId(jobData.getId());
			job.setName(jobData.getName());
			return job;
		}
		logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		return null;
	}
	@Override
	public void delete(Integer id) {
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<JobData> optional = jobDataRepository.findById(id);
		if( optional.isPresent()) {
			JobData jobDatum = optional.get();
			jobDataRepository.delete(jobDatum);
			logger.info(" Success >> " + jobDatum.toString());
		}
		else {
			logger.info(" Failed >> unable to locatejob id:" +  Integer.toString(id));
		}
	}
}
