package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CurriculumProgramModel;
import org.vtop.CourseRegistration.model.CurriculumProgramModelPK;



@Repository
public interface CurriculumProgramRepository extends JpaRepository<CurriculumProgramModel,CurriculumProgramModelPK> {

	List<CurriculumProgramModel> findAll();
	CurriculumProgramModel findByCpmPkIdAndStatus(CurriculumProgramModelPK curriculumProgramPK, int status);
	
	@Query(value="select COURSE_SYSTEM from ACADEMICS.CURRICULUM_PROGRAM where PROGRAM_SPECIALIZATION_ID=?1 "+
					"and YEAR=?2 and STATUS=0", nativeQuery=true)
	String findCourseSystemBySpecIdAndCurProgYear(Integer specId, Integer studyStartYear);
	
	@Query(value="select nvl(min(YEAR),0) from ACADEMICS.CURRICULUM_PROGRAM where PROGRAM_SPECIALIZATION_ID=?1 "+
					"and COURSE_SYSTEM='CAL'", nativeQuery=true)
	Integer findCALStartYearBySpecId(Integer specId);
}
