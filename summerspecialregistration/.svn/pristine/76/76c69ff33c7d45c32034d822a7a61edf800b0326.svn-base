package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.StudentCurrentCgpaModel;
import org.vtop.CourseRegistration.repository.StudentCurrentCgpaRepository;

@Service
@Transactional("academicsTransactionManager")
public class StudentCurrentCgpaService {

	@Autowired private StudentCurrentCgpaRepository studentCurrentCgpaRepository;
	
	
	public StudentCurrentCgpaModel getOne(String regNo)
	{
		return studentCurrentCgpaRepository.findOne(regNo);
	}
}
