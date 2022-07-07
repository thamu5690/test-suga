package org.vtop.CourseRegistration.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.RegistrationScheduleModel;
import org.vtop.CourseRegistration.repository.RegistrationScheduleRepository;

@Service
@Transactional("academicsTransactionManager")
public class RegistrationScheduleService
{	
	@Autowired private RegistrationScheduleRepository  registrationScheduleRepository;
	
	
	public RegistrationScheduleModel getOne(String registerNumber)
	{
		return registrationScheduleRepository.findOne(registerNumber);
	}
		
	public boolean isExist(String registerNumber)
	{
		return registrationScheduleRepository.exists(registerNumber);
	}	
}
