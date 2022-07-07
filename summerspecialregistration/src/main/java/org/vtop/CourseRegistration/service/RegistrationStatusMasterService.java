package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.RegistrationStatusMasterModel;
import org.vtop.CourseRegistration.repository.RegistrationStatusMasterRepository;

/*@EnableJpaRepositories(basePackageClasses={RegistrationStatusMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class RegistrationStatusMasterService {
		
	@Autowired private RegistrationStatusMasterRepository registrationStatusMasterRepository;
	
	
	public List<RegistrationStatusMasterModel>getAll()
	{
		return registrationStatusMasterRepository.findAll();
	}
	
	public RegistrationStatusMasterModel getOne(Integer statusNumber)
	{
		return registrationStatusMasterRepository.findOne(statusNumber);
	}
}
