package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.PatternTimeMasterModel;
import org.vtop.CourseRegistration.model.PatternTimeMasterPK;
import org.vtop.CourseRegistration.repository.PatternTimeMasterRepository;


@Service
@Transactional("academicsTransactionManager")
public class PatternTimeMasterService {
	
	@Autowired private PatternTimeMasterRepository patternTimeMasterRepository;
	
	
	public void deleteOne(PatternTimeMasterPK patternTimeMasterPK)
	{
		patternTimeMasterRepository.delete(patternTimeMasterPK);
	}
	
	public List<PatternTimeMasterModel>getAll()
	{
		return patternTimeMasterRepository.findAll();
	}	
	
	public PatternTimeMasterModel getOne(PatternTimeMasterPK patternTimeMasterPK)
	{
		return patternTimeMasterRepository.findOne(patternTimeMasterPK);
	}
	
	public void deleteByPatternIdSlot(Integer patternId)
	{
		patternTimeMasterRepository.deleteByPatternIdSlot(patternId);
	}
	
	public List<PatternTimeMasterModel> getByPatternId(Integer patternId)
	{
		return patternTimeMasterRepository.findAllOrderByDateAsc(patternId);
	}
	
	public Page<PatternTimeMasterModel> getAllPageable(Pageable pageable) {
		return patternTimeMasterRepository.findAll(pageable);
	}
	
	public Page<PatternTimeMasterModel> getByPatternTimeMasterPageable(Integer searchval, Pageable pageable) {
		return patternTimeMasterRepository.findByPatternTimeMasterPagination(searchval, pageable);
	}
	
	public List<PatternTimeMasterModel> getTheoryOnly(Integer patternId){
		return patternTimeMasterRepository.findTheoryOnly(patternId);
	}
	
	public List<PatternTimeMasterModel> getLabOnly(Integer patternId){
		return patternTimeMasterRepository.findLabOnly(patternId);
	}
	
	public List<Object[]> getByPatternId2(Integer patternId)
	{
		return patternTimeMasterRepository.findByPatternId2(patternId);
	}
	
	public List<String> getSlotSessions(Integer patternId){
		return patternTimeMasterRepository.getSlotSessions(patternId);
	}
	
	public List<Object[]> getSlotDetailByPatternId(Integer patternId)
	{
		return patternTimeMasterRepository.findSlotDetailByPatternId(patternId);
	}
}