package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeGroupBasketDetailsModel;
import org.vtop.CourseRegistration.model.ProgrammeGroupBasketDetailsPK;
import org.vtop.CourseRegistration.repository.ProgrammeGroupBasketDetailsRepository;

/*@EnableJpaRepositories(basePackageClasses={ProgrammeGroupBasketDetailsRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ProgrammeGroupBasketDetailsService {

	@Autowired private ProgrammeGroupBasketDetailsRepository programmeGroupBasketDetailsRepository;
	
	
	public ProgrammeGroupBasketDetailsModel getOne(ProgrammeGroupBasketDetailsPK programmeGroupBasketDetailsPK)
	{
		return programmeGroupBasketDetailsRepository.findOne(programmeGroupBasketDetailsPK);
	}
		
	public List<ProgrammeGroupBasketDetailsModel> getAll()
	{
		return programmeGroupBasketDetailsRepository.findAll();
	}
		
	public List<ProgrammeGroupBasketDetailsModel> getByProgGroup(Integer groupId)
	{
		return programmeGroupBasketDetailsRepository.findByProgGroup(groupId);
	}
}
