package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseEligibleModel;

@Repository
public interface CourseEligibleRepository extends JpaRepository<CourseEligibleModel,Integer> {

	//CourseEligibleModel findByProgtype(String progGroup);
	
	@Query(value="select PROGRAM_ELIGIBLE, PROGRAM_CGPA from ACADEMICS.COURSE_ELIGIBLE where "+
			"PRGRM_GROUP_PROGRAMME_GROUP_ID=?1", nativeQuery=true)
	List<Object[]> findEligibleCriteriaByProgGroupId(Integer progGroupId);
}
