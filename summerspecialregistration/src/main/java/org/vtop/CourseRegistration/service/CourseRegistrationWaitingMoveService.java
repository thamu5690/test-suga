package org.vtop.CourseRegistration.service;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.CourseRegistrationWaitingMoveRepository;

/*@EnableJpaRepositories(basePackageClasses={CourseRegistrationWaitingRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class CourseRegistrationWaitingMoveService {
		
	@Autowired private CourseRegistrationWaitingMoveRepository courseRegistrationWaitingMoveRepository;
			
	public void addWaitingToWaitingMove(String semesterSubId, String registerNumber, String courseId, 
			int waitStatus, String ipaddress)
	{		
		courseRegistrationWaitingMoveRepository.insertWaitingToWaitingMove(semesterSubId, registerNumber, 
				courseId, waitStatus, ipaddress);
	}
}
