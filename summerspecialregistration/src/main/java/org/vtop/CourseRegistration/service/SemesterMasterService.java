package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.SemesterMasterModel;
import org.vtop.CourseRegistration.repository.SemesterMasterRepository;


/*@EnableJpaRepositories(basePackageClasses={SemesterMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class SemesterMasterService {
		
	@Autowired private SemesterMasterRepository semesterMasterRepository;
	
			
	public List<SemesterMasterModel> getAll()
	{
		return semesterMasterRepository.findAll();
	}
	
	public SemesterMasterModel getOne(Integer semesterId)
	{
		return semesterMasterRepository.findOne(semesterId);
	}
		
	public List<SemesterMasterModel> getAllByOrderBySemesterIdAsc()
	{	
		return semesterMasterRepository.findAllByOrderBySemesterIdAsc();
	}	
	
	public List<SemesterMasterModel> getAllByOrderBySemesterIdDesc()
	{			
		return semesterMasterRepository.findAllByOrderBySemesterIdDesc();	
	}	
	
	public List<SemesterMasterModel>getByStatus(int status)
	{
		return semesterMasterRepository.findByStatus(status);
	}
}
