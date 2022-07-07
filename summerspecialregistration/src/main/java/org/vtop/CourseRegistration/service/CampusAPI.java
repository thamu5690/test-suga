package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.Campus;
import org.vtop.CourseRegistration.repository.master.CampusRepository;

@EnableJpaRepositories(basePackageClasses={CampusRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class CampusAPI {
	
	@Autowired private CampusRepository campusRepository;
	

	public List<Campus> getCampus()
	{
		return campusRepository.findAll();
	}	
}
