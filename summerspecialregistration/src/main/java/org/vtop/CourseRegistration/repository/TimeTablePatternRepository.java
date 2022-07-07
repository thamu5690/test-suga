package org.vtop.CourseRegistration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.TimeTablePatternModel;

@Repository
public interface TimeTablePatternRepository extends JpaRepository<TimeTablePatternModel, Integer> {

	List<TimeTablePatternModel> findAll();
	List<TimeTablePatternModel> findByStatus(Integer status);
	
	List<TimeTablePatternModel> findAllByOrderByPatternIdAsc();
	List<TimeTablePatternModel> findAllByOrderByPatternIdDesc();
	TimeTablePatternModel findByPatternId(Integer patternId);
	

}