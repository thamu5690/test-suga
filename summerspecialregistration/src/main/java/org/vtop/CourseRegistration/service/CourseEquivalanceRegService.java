package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegModel;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegPKModel;
import org.vtop.CourseRegistration.repository.CourseEquivalanceRegRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseEquivalanceRegRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseEquivalanceRegService {
		
	@Autowired private CourseEquivalanceRegRepository courseEquivalanceRegRepository;
	
	
	public CourseEquivalanceRegModel saveOne(CourseEquivalanceRegModel courseEquivalanceRegModel)
	{
		return courseEquivalanceRegRepository.save(courseEquivalanceRegModel);
	}
	
	public CourseEquivalanceRegModel getOne(CourseEquivalanceRegPKModel courseEquivalanceRegPKModel)
	{
		return courseEquivalanceRegRepository.findOne(courseEquivalanceRegPKModel);
	}
		
	public List<CourseEquivalanceRegModel> getAll(String semesterSubId)
	{
		return courseEquivalanceRegRepository.findBySemesterSubId(semesterSubId);
	}
		
	public List<CourseEquivalanceRegModel> getByRegisterNumber(String semesterSubId, String registerNumber)
	{
		return courseEquivalanceRegRepository.findByRegisterNumber(semesterSubId, registerNumber);
	}
	
	public List<CourseEquivalanceRegModel> getByRegisterNumberCourseId(String semesterSubId, 
			String registerNumber, String courseId)
	{
		return courseEquivalanceRegRepository.findByRegisterNumberCourseId(semesterSubId, 
				registerNumber, courseId);
	}
	
	public String getEquivCourseByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId)
	{
		return courseEquivalanceRegRepository.findEquivCourseByRegisterNumberAndCourseId(semesterSubId, registerNumber, courseId);
	}
	
	public void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		courseEquivalanceRegRepository.deleteByRegisterNumberCourseId(semesterSubId, 
				registerNumber, courseId);
	}
}
