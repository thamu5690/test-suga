package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseDropHistoryModel;
import org.vtop.CourseRegistration.model.CourseDropHistoryPKModel;
import org.vtop.CourseRegistration.repository.CourseDropHistoryRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseDropHistoryRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseDropHistoryService {
		
	@Autowired private CourseDropHistoryRepository courseDropHistoryRepository;
	
	
	public CourseDropHistoryModel getOne(CourseDropHistoryPKModel courseDropHistoryPKModel)
	{
		return courseDropHistoryRepository.findOne(courseDropHistoryPKModel);
	}
		
	public List<CourseDropHistoryModel> getByClassId(String classId)
	{
		return courseDropHistoryRepository.findByClassId(classId);
	}
	
	public List<CourseDropHistoryModel> getByRegisterNumber(String registerNumber)
	{
		return courseDropHistoryRepository.findByRegisterNumber(registerNumber);
	}
		
	public List<CourseDropHistoryModel> getByRegisterNumberClassId(String registerNumber, String classId)
	{
		return courseDropHistoryRepository.findByRegisterNumberClassId(registerNumber, classId);
	}
}
