package org.vtop.CourseRegistration.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.SlotTimeMasterModel;
import org.vtop.CourseRegistration.model.SlotTimeMasterPK;
import org.vtop.CourseRegistration.repository.SlotTimeMasterRepository;


@Service
@Transactional("academicsTransactionManager")
public class SlotTimeMasterService {
	
	@Autowired private SlotTimeMasterRepository slotTimeMasterRepository;
	
	
	public boolean isDataExist(String slot, String weekDays,Date startingTime,Date endingTime, Integer patternId)
	{
		boolean result=false;
		try
		{	
			int listSize=slotTimeMasterRepository.findByStmPkIdSlotAndStmPkIdWeekdaysAndStmPkIdSlotStartingTimeAndStmPkIdSlotEndingTimeAndStmPkIdPatternId(slot, weekDays, startingTime, endingTime, patternId).size();
			if(listSize!=0)
			{
				result=true;
			}
		}
		catch(NullPointerException e)
		{
			result=false;
		}
		
		return result;
	}
	
	public void deleteOne(SlotTimeMasterPK slotTimeMasterPK)
	{
		slotTimeMasterRepository.delete(slotTimeMasterPK);
	}
	
	public List<SlotTimeMasterModel>getAll()
	{
		return slotTimeMasterRepository.findAll();
	}	
	
	public SlotTimeMasterModel getOne(SlotTimeMasterPK slotTimeMasterPK)
	{
		return slotTimeMasterRepository.findOne(slotTimeMasterPK);
	}
	
	public List<SlotTimeMasterModel> getByPatternIdSlot(Integer patternId, String slot)
	{
		return slotTimeMasterRepository.findByPatternIdSlot(patternId, slot);
	}
	
	public List<Object[]> getByPatternIdSlot2(Integer patternId, String slot)
	{
		return slotTimeMasterRepository.findByPatternIdSlot2(patternId, slot);
	}
	
	public List<SlotTimeMasterModel> getByPatternId(Integer patternId)
	{
		return slotTimeMasterRepository.findAllOrderByDateAsc(patternId);
	}
	
	public void deleteByPatternIdSlot(Integer patternId, String slot)
	{
		slotTimeMasterRepository.deleteByPatternIdSlot(patternId, slot);
	}
	
	public Page<SlotTimeMasterModel> getAllPageable(Pageable pageable) {
		return slotTimeMasterRepository.findAll(pageable);
	}
	
	public Page<SlotTimeMasterModel> getBySlotTimeMasterPageable(Integer searchval, Pageable pageable) {
		return slotTimeMasterRepository.findBySlotTimeMasterPagination(searchval, pageable);
	}
	
	/* Time Table Details */
	
	public List<String> getByPatternIdSlotType(Integer patternId, String slotType, String session)
	{
		return slotTimeMasterRepository.findByPatternIdSlotType(patternId, slotType, session);
	}
	
	public Integer getPatternIdSlotCount(Integer patternId, String slotName)
	{
		return slotTimeMasterRepository.findPatternIdSlotCount(patternId, slotName);
	}
	
	public List<String> getByClash(Integer patternId, String weekdays, String slotStartingTime, String slotEndingTime)
	{
		return slotTimeMasterRepository.findByClash(patternId, weekdays, slotStartingTime, slotEndingTime);
	}
	
	public List<String> getByClashValue(Integer patternId, String weekdays, Long slotStartingTime, Long slotEndingTime)
	{
		return slotTimeMasterRepository.findByClashValue(patternId, weekdays, slotStartingTime, slotEndingTime);
	}
	
	public List<String> getByWeekdays(Integer patternId, String slotName)
	{
		return slotTimeMasterRepository.findByWeekdays(patternId, slotName);
	}
	
	public List<SlotTimeMasterModel> getMonTheoryOnly(Integer patternId)
	{
		return slotTimeMasterRepository.findMonTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getMonLabOnly(Integer patternId){
		return slotTimeMasterRepository.findMonLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getTueTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findTueTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getTueLabOnly(Integer patternId){
		return slotTimeMasterRepository.findTueLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getWedTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findWedTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getWedLabOnly(Integer patternId){
		return slotTimeMasterRepository.findWedLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getThuTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findThuTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getThuLabOnly(Integer patternId){
		return slotTimeMasterRepository.findThuLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getFriTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findFriTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getFriLabOnly(Integer patternId){
		return slotTimeMasterRepository.findFriLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getSatTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findSatTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getSatLabOnly(Integer patternId){
		return slotTimeMasterRepository.findSatLabOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getSunTheoryOnly(Integer patternId){
		return slotTimeMasterRepository.findSunTheoryOnly(patternId);
	}
	
	public List<SlotTimeMasterModel> getSunLabOnly(Integer patternId){
		return slotTimeMasterRepository.findSunLabOnly(patternId);
	}
	
	public List<Object[]> getWeekDayList(Integer patternId)
	{			
		return slotTimeMasterRepository.findWeekDayList(patternId);	
	}
	
	public List<Object[]> getWeekDaySessionList(Integer patternId)
	{			
		return slotTimeMasterRepository.findWeekDaySessionList(patternId);	
	}

	public List<Object[]> getSlotTimeDetailByPatternId(Integer patternId)
	{
		return slotTimeMasterRepository.findSlotTimeDetailByPatternId(patternId);
	}
} 
