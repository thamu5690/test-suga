package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseTypeMasterModel;
import org.vtop.CourseRegistration.model.CourseTypeMasterModelPK;
import org.vtop.CourseRegistration.repository.CourseTypeMasterRepository;

@EnableJpaRepositories(basePackageClasses={CourseTypeMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class CourseTypeMasterService {
	
	@Autowired private CourseTypeMasterRepository courseTypeMasterRepository;
	
	
	public List<CourseTypeMasterModel> getAll()
	{
		return courseTypeMasterRepository.findAll();
	}

	public CourseTypeMasterModel findOne(CourseTypeMasterModelPK courseTypeMasterModelPK)
	{	
		return courseTypeMasterRepository.findOne(courseTypeMasterModelPK);
	}
	
	public List<CourseTypeMasterModel> getByCrsTypCmpntMstrCourseType(String courseGenericType)
	{
		return courseTypeMasterRepository.findByCrsTypCmpntMstrCourseType(courseGenericType);
	}
	
	public List<String> getCourseTypeComponent(String courseGenericType)
	{
		return courseTypeMasterRepository.findCourseTypeComponent(courseGenericType);
	}

	public List<CourseTypeMasterModel> getByCrsTypCmpntMstrCourseType2(String courseGenericType, String[] courseType)
	{
		return courseTypeMasterRepository.findByCrsTypCmpntMstrCourseType2(courseGenericType, courseType);
	}
}
