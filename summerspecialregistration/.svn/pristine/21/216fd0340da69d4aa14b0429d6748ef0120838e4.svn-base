package org.vtop.CourseRegistration.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.SemesterDetailsModel;
import org.vtop.CourseRegistration.repository.SemesterDetailsRepository;

@EnableJpaRepositories(basePackageClasses={SemesterDetailsRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class SemesterDetailsService {
		
	@Autowired private SemesterDetailsRepository semesterDetailsRepository;
	
	
	public List<SemesterDetailsModel> getAll()
	{
		return semesterDetailsRepository.findAll();
	}
	
	public List<SemesterDetailsModel> getAllByOrderBySemesterSubIdAsc()
	{
		return semesterDetailsRepository.findAllByOrderBySemesterSubIdAsc();
	}	
	
	public List<SemesterDetailsModel> getAllByOrderBySemesterSubIdDesc()
	{			
		return semesterDetailsRepository.findAllByOrderBySemesterSubIdDesc();	
	}
	
	public SemesterDetailsModel getOne(String semesterSubId)
	{
		return semesterDetailsRepository.findOne(semesterSubId);
	}
	
	public List<SemesterDetailsModel>getByStatus(int status)
	{
	   return semesterDetailsRepository.findByStatus(status);
	}
	
	public boolean isExist(String semesterSubId)
	{
		return semesterDetailsRepository.exists(semesterSubId);
	}
}
