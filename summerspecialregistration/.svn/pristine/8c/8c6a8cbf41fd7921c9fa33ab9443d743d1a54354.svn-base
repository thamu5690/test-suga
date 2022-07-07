package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.BridgeCourseConditionDetailRepository;

@Service
@Transactional("academicsTransactionManager")
public class BridgeCourseConditionDetailService {
	
	@Autowired private BridgeCourseConditionDetailRepository bridgeCourseConditionDetailRepository;
	
	
	String getEPTResultByRegisterNumber(List<String> registerNumber)
	{
		return bridgeCourseConditionDetailRepository.findEPTResultByRegisterNumber(registerNumber);
	}
	
	String getPCMBStatusByRegisterNumber(List<String> registerNumber)
	{
		return bridgeCourseConditionDetailRepository.findPCMBStatusByRegisterNumber(registerNumber);
	}
	
	String getPCMBStatusFromAdmissionsByRegisterNumber(String registerNumber)
	{
		return bridgeCourseConditionDetailRepository.findPCMBStatusFromAdmissionsByRegisterNumber(registerNumber);
	}
	
	List<Object[]> getEPTDetailByRegisterNumber(List<String> registerNumber)
	{
		return bridgeCourseConditionDetailRepository.findEPTDetailByRegisterNumber(registerNumber);
	}
}
