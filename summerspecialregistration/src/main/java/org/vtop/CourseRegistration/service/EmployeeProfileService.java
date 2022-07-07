package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.EmployeeProfile;
import org.vtop.CourseRegistration.repository.EmployeeProfileRepository;

@Service
@Transactional("academicsTransactionManager")
public class EmployeeProfileService {

	@Autowired private EmployeeProfileRepository employeeProfileRepository;
	
	
	public List<EmployeeProfile> getByCentreId(Integer costCentreId)
	{
		return employeeProfileRepository.findByCentreId(costCentreId);
	}
	
	public List<Object []>  getFacultyCostCentre(String campusCode)
	{
		return employeeProfileRepository.findFacultyCostCentre(campusCode);
	}
}
