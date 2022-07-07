package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CapStoneProjectConditionDetailModel;
import org.vtop.CourseRegistration.model.CapStoneProjectConditionDetailModelPK;

@Repository
public interface CapStoneProjectConditionDetailRepository extends 
	JpaRepository<CapStoneProjectConditionDetailModel,CapStoneProjectConditionDetailModelPK> {

	@Query("select a from CapStoneProjectConditionDetailModel a where "+
			"a.cspcdmPkId.programGroupId=?1 order by a.cspcdmPkId.studentBatch desc")
	List<CapStoneProjectConditionDetailModel> findByProgramGroupId(Integer programGroupId);
	
	@Query(value="select TOTAL_CREDITS, PROJECT_PERCENTAGE from ACADEMICS.CAP_STONE_PROJ_COND_DET "+
					"where PROGRAM_GROUP_ID=?1 and STUDENT_BATCH=?2 and STATUS=0", nativeQuery=true)
	List<Object[]> findProjectEligibilityByProgramGroupIdAndStudYear(Integer programGroupId, 
					Integer studYear);
	
	@Query(value="select STUDENT_BATCH, TOTAL_CREDITS, PROJECT_PERCENTAGE from ACADEMICS.CAP_STONE_PROJ_COND_DET "+
					"where PROGRAM_GROUP_ID=?1 and STATUS=0 and STUDENT_BATCH=(select max(STUDENT_BATCH) from "+
					"ACADEMICS.CAP_STONE_PROJ_COND_DET where PROGRAM_GROUP_ID=?1 and STATUS=0)", nativeQuery=true)
	List<Object[]> findStudentMaxYearProjectEligibilityByProgramGroupId(Integer programGroupId);
}
