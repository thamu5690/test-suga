package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.RoomMaster;
import org.vtop.CourseRegistration.repository.estates.RoomMasterRepository;

@EnableJpaRepositories(basePackageClasses={RoomMasterRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class EstatesAPI {
	
	@Autowired private RoomMasterRepository roomMasterRepository;
	
	
	public List<RoomMaster> getAll()
	{
		return roomMasterRepository.findAll();
	}
}
