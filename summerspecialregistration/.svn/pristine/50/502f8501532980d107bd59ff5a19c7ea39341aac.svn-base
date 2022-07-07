package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseTypeComponentModel;
import org.vtop.CourseRegistration.repository.CourseTypeComponentRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseTypeComponentRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseTypeComponentService {
	
	@Autowired private CourseTypeComponentRepository courseTypeComponentRepository;
	
	
	public List<CourseTypeComponentModel>getAll()
	{
		return courseTypeComponentRepository.findAll();
	}
	
	public List<CourseTypeComponentModel>getByComponent(int comp)
	{
		return courseTypeComponentRepository.findByComponent(comp);
	}
	
	public CourseTypeComponentModel getOne(String courseType)
	{
		return courseTypeComponentRepository.findOne(courseType);
	}
}
