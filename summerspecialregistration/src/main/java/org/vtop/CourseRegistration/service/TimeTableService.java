package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.TimeTableModel;
import org.vtop.CourseRegistration.repository.TimeTableRepository;

/*@EnableJpaRepositories(basePackageClasses={TimeTableRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class TimeTableService {
		
	@Autowired private TimeTableRepository timeTableRepository;
	
				
	public List<TimeTableModel> getAll()
	{
		return timeTableRepository.findAll();
	}
	
	public List<TimeTableModel> getSlotnamePatternid(String slotName, Integer patternId)
	{
		return timeTableRepository.findBySlotNameAndPatternId(slotName, patternId);
	}
	
	public TimeTableModel getOne(Long slotId)
	{
		return timeTableRepository.findOne(slotId);
	}	
		
	public List<TimeTableModel> getAllByOrderBySlotIdAsc()
	{
		return timeTableRepository.findAllByOrderBySlotIdAsc();
	}	
	
	public List<TimeTableModel> getAllByOrderBySlotIdDesc()
	{			
		return timeTableRepository.findAllByOrderBySlotIdDesc();	
	}
}