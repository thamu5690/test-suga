package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.StudentCreditTransferModel;
import org.vtop.CourseRegistration.repository.StudentCreditTransferRepository;


@Service
@Transactional("academicsTransactionManager")
public class StudentCreditTransferService {

	@Autowired private StudentCreditTransferRepository studentCreditTransferRepository;
	
	
	public StudentCreditTransferModel getByRegno(String registerNo)
	{
		return studentCreditTransferRepository.findByRegisterNumber(registerNo);
	}
	
	public boolean isExist(String registerNo)
	{
		return studentCreditTransferRepository.exists(registerNo);
	}
}
