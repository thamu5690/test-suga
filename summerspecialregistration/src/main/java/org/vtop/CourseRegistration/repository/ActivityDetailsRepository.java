package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ActivityDetailsModel;
import org.vtop.CourseRegistration.model.ActivityDetailsModelPK;

@Repository
public interface ActivityDetailsRepository extends JpaRepository<ActivityDetailsModel, ActivityDetailsModelPK> {
	
	@Query("select a from ActivityDetailsModel a ORDER BY a.fromDate desc")
	List<ActivityDetailsModel> getAllDesc();
	
	@Query("select a from ActivityDetailsModel a where a.lockStatus=0 and a.toDate<=CURRENT_DATE "+
				"and (a.extensionDate is null or a.extensionDate<=CURRENT_DATE)")
	List<ActivityDetailsModel> findByLastDate();
	
	@Query("select a from ActivityDetailsModel a where a.admPkId.activityId=1 and a.lockStatus=0")
	ActivityDetailsModel findByAcvityIdAndStatus();
		 
}
