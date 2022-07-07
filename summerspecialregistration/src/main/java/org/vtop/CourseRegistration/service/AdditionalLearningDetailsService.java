package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.AdditionalLearningDetailsModel;
import org.vtop.CourseRegistration.repository.AdditionalLearningDetailsRepository;

@Service
@Transactional("academicsTransactionManager")
public class AdditionalLearningDetailsService
{	
	@Autowired private AdditionalLearningDetailsRepository additionalLearningDetailsRepository;
	
	
	public AdditionalLearningDetailsModel getOne(String Code)
	{
		return additionalLearningDetailsRepository.findOne(Code);
	}
	
	public List<AdditionalLearningDetailsModel> getByLearningType(String learnType)
	{
		return additionalLearningDetailsRepository.findByLearningType(learnType);
	}
	
	public List<AdditionalLearningDetailsModel> getAdditionalLearningHonours(String progCode)
	{
		return additionalLearningDetailsRepository.findAdditionalLearningHonours(progCode);
	}
}
