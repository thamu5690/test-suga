package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseSchedulingModel;
import org.vtop.CourseRegistration.repository.CourseSchedulingRepository;

@Service
@Transactional("academicsTransactionManager")
public class CourseSchedulingService {
	
	@Autowired private CourseSchedulingRepository courseSchedulingRepository;
	
	public List<CourseSchedulingModel> getAll()
	{
		return courseSchedulingRepository.findAll();
	}
}
