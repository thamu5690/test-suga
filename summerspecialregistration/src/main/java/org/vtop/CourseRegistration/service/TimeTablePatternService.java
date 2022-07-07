package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.TimeTablePatternModel;
import org.vtop.CourseRegistration.repository.TimeTablePatternRepository;

/*@EnableJpaRepositories(basePackageClasses={TimeTablePatternRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class TimeTablePatternService {

	@Autowired private TimeTablePatternRepository timeTablePatternRepository;
	
		
	public List<TimeTablePatternModel> getAll()
	{
		return timeTablePatternRepository.findAll();
	}
	
	public TimeTablePatternModel getOne(Integer patternId)
	{
		return timeTablePatternRepository.findOne(patternId);
	}
	
	public List<TimeTablePatternModel> getAllByOrderByPatternIdAsc()
	{
		return timeTablePatternRepository.findAllByOrderByPatternIdAsc();
	}	
	
	public List<TimeTablePatternModel> getAllByOrderByPatternIdDesc()
	{					
		return timeTablePatternRepository.findAllByOrderByPatternIdDesc();	
	}
	
	public List<TimeTablePatternModel> getByStatus(int status)
	{
	    return timeTablePatternRepository.findByStatus(status);
	}	
	
	public TimeTablePatternModel getByPatternId(Integer patternId){
		return timeTablePatternRepository.findByPatternId(patternId);
	}
	
}
