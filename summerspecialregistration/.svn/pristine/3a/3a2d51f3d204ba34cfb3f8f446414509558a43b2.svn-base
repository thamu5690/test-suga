package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ActivityMasterModel;
import org.vtop.CourseRegistration.repository.ActivityMasterRepository;

/*@EnableJpaRepositories(basePackageClasses={ActivityMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ActivityMasterService {
	
	@Autowired private ActivityMasterRepository activitymasterrepository;
	
	
	public List<ActivityMasterModel> getAll(){
		return activitymasterrepository.findAll();
	}
	
	public ActivityMasterModel findOne(Integer activityId){
		return activitymasterrepository.findOne(activityId);
	}	
}
