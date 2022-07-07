package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ActivityMasterModel;

@Repository
public interface ActivityMasterRepository extends JpaRepository<ActivityMasterModel,Integer> {
	
	@Query("select a from ActivityMasterModel a where a.lockStatus=0 order by a.activityId,"+
			"a.costCentreCentreId")
	List<ActivityMasterModel> findByStatus();

	
}
