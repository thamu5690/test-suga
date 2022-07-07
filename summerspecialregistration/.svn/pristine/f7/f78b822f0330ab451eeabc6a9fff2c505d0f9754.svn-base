package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseEquivalancesModel;
import org.vtop.CourseRegistration.model.CourseEquivalancesPKModel;
import org.vtop.CourseRegistration.repository.CourseEquivalancesRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseEquivalancesRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseEquivalancesService {
	
	@Autowired private CourseEquivalancesRepository courseEquivalancesRepository;
	
	
	public CourseEquivalancesModel getOne(CourseEquivalancesPKModel courseEquivalancesPKModel)
	{
		return courseEquivalancesRepository.findOne(courseEquivalancesPKModel);
	}
		
	public List<CourseEquivalancesModel> getAll()
	{
		return courseEquivalancesRepository.findAll();
	}
		
	public List<CourseEquivalancesModel> getByCourseId(String courseId)
	{
		return courseEquivalancesRepository.findByCourseId(courseId);
	}
	
	public List<CourseEquivalancesModel> getByEquivalCourseId(String equivalCourseId)
	{
		return courseEquivalancesRepository.findByEquivalCourseId(equivalCourseId);
	}

	public List<Object[]> getEquivalanceListByCourseCode(String courseCode)
	{
		return courseEquivalancesRepository.findEquivalanceListByCourseCode(courseCode);
	}
}
