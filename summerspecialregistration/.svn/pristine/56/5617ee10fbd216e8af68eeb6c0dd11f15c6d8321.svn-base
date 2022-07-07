package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CapStoneProjectConditionDetailModel;
import org.vtop.CourseRegistration.model.CapStoneProjectConditionDetailModelPK;
import org.vtop.CourseRegistration.repository.CapStoneProjectConditionDetailRepository;

@Service
@Transactional("academicsTransactionManager")
public class CapStoneProjectConditionDetailService {
	
	@Autowired private CapStoneProjectConditionDetailRepository capStoneProjectConditionDetailRepository;
	
	
	public CapStoneProjectConditionDetailModel getOne(CapStoneProjectConditionDetailModelPK capStoneProjectConditionDetailModelPK)
	{
		return capStoneProjectConditionDetailRepository.findOne(capStoneProjectConditionDetailModelPK);
	}
	
	public List<CapStoneProjectConditionDetailModel> getByProgramGroupId(Integer programGroupId)
	{
		return capStoneProjectConditionDetailRepository.findByProgramGroupId(programGroupId);
	}

	public List<Object[]> getProjectEligibilityByProgramGroupIdAndStudYear(Integer programGroupId, Integer studYear)
	{
		return capStoneProjectConditionDetailRepository.findProjectEligibilityByProgramGroupIdAndStudYear(
					programGroupId, studYear);
	}
	
	public List<Object[]> getStudentMaxYearProjectEligibilityByProgramGroupId(Integer programGroupId)
	{
		return capStoneProjectConditionDetailRepository.findStudentMaxYearProjectEligibilityByProgramGroupId(programGroupId);
	}
}
