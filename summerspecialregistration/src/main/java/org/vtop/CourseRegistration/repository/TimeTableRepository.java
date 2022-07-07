package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.TimeTableModel;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTableModel, Long> {

	List<TimeTableModel> findAll();
	public List<TimeTableModel> findAllByOrderBySlotIdAsc();	
	public List<TimeTableModel> findAllByOrderBySlotIdDesc();	
	List<TimeTableModel> findBySlotNameAndPatternId(String slotName, Integer patternId);
	
}