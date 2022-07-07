package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CostCentre;
import org.vtop.CourseRegistration.repository.master.CostCentreRepository;

/*@EnableJpaRepositories(basePackageClasses={CostCentreRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CostCentreAPI {

	@Autowired private CostCentreRepository costCentreRepository;
	

	public List<CostCentre> getCostCentre()
	{
		return costCentreRepository.findAll();
	}
	
	public List<CostCentre> getCostCentreCampusStatus(String campus, Integer[] status)
	{
		return costCentreRepository.findByCostCentreCampusStatus(campus, status);
	}
	
	public List<CostCentre> getCostCentreCampusCentreStatus(String campus, String[] centreType, Integer[] status)
	{
		return costCentreRepository.findByCostCentreCampusCentreStatus(campus, centreType, status);
	}
	
}
