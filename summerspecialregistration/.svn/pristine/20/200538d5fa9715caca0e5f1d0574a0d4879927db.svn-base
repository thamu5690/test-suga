package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseDropHistoryModel;
import org.vtop.CourseRegistration.model.CourseDropHistoryPKModel;

@Repository
public interface CourseDropHistoryRepository extends JpaRepository<CourseDropHistoryModel, CourseDropHistoryPKModel> {
	
	@Query("select a from CourseDropHistoryModel a where a.courseDropHistoryPKId.classId=?1 "+
			"order by a.courseDropHistoryPKId.registerNumber, a.courseDropHistoryPKId.logTimestamp")
	List<CourseDropHistoryModel> findByClassId(String classId);
	
	@Query("select a from CourseDropHistoryModel a where a.courseDropHistoryPKId.registerNumber=?1 "+
			"order by a.courseDropHistoryPKId.classId, a.courseDropHistoryPKId.logTimestamp")
	List<CourseDropHistoryModel> findByRegisterNumber(String registerNumber);
	
	@Query("select a from CourseDropHistoryModel a where a.courseDropHistoryPKId.registerNumber=?1 "+
			"and a.courseDropHistoryPKId.classId=?2 order by a.courseDropHistoryPKId.logTimestamp")
	List<CourseDropHistoryModel> findByRegisterNumberClassId(String registerNumber, String classId);
	
}
