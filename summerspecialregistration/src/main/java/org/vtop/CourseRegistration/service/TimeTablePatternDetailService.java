package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.TimeTablePatternDetailModel;
import org.vtop.CourseRegistration.model.TimeTablePatternDetailPK;
import org.vtop.CourseRegistration.repository.TimeTablePatternDetailRepository;


@Service
@Transactional("academicsTransactionManager")
public class TimeTablePatternDetailService {

	@Autowired private TimeTablePatternDetailRepository timeTablePatternDetailRepository;
	
	
	public TimeTablePatternDetailService(TimeTablePatternDetailRepository timeTablePatternDetailRepository)
	{
		this.timeTablePatternDetailRepository=timeTablePatternDetailRepository;
	}
		
	public List<TimeTablePatternDetailModel>getAll()
	{
		return timeTablePatternDetailRepository.findAll();
	}
	
	public TimeTablePatternDetailModel getOne(TimeTablePatternDetailPK timeTablePatternDetailPK)
	{
		return timeTablePatternDetailRepository.findOne(timeTablePatternDetailPK);
	}
	
	public void deleteOne(TimeTablePatternDetailPK timeTablePatternDetailPK)
	{
		timeTablePatternDetailRepository.delete(timeTablePatternDetailPK);
	}	
	
	public List<TimeTablePatternDetailModel>getByStatus(int status)
	{
		return timeTablePatternDetailRepository.findByStatus(status);
	}
	
	/* TT Pattern Time Master */
	public List<TimeTablePatternDetailModel> getByPatternId1(Integer patternId)
	{
		return timeTablePatternDetailRepository.findByPatternId1(patternId);
	}
	
	public List<Object[]> getMaxSlots(Integer patternId){
		return timeTablePatternDetailRepository.getMaxSlots(patternId);
	}

	public List<Object[]> getSessionSlotByPatternId(Integer patternId)
	{			
		return timeTablePatternDetailRepository.findSessionSlotByPatternId(patternId);	
	}
}