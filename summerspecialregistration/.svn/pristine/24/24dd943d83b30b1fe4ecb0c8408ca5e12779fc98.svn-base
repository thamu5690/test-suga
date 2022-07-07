package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseModeMasterModel;
import org.vtop.CourseRegistration.repository.CourseModeMasterRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseModeMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseModeMasterService 
{
	@Autowired private CourseModeMasterRepository courseModeMasterrepository;
	
	
	public List<CourseModeMasterModel> getAllCourseMode()
	{
		return courseModeMasterrepository.findAll();
	}
	
	public CourseModeMasterModel editCourseMode(String code)
	{	
		return courseModeMasterrepository.findOne(code);
	}
}
