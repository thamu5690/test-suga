package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseEligibleModel;
import org.vtop.CourseRegistration.repository.CourseEligibleRepository;


@Service
@Transactional("academicsTransactionManager")
public class CourseEligibleService {
	
	@Autowired private CourseEligibleRepository courseEligibleRepository;
	
	
	public CourseEligibleModel getOne(Integer groupId){
		return courseEligibleRepository.findOne(groupId);
	}
	
	public boolean isExist(Integer groupId)
	{
		return courseEligibleRepository.exists(groupId);
	}
}

