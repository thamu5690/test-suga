package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegModel;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegPKModel;

@Repository
public interface CourseEquivalanceRegRepository extends JpaRepository<CourseEquivalanceRegModel, CourseEquivalanceRegPKModel> {
	
	@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"order by a.courseEquivalanceRegPKId.registerNumber, a.courseEquivalanceRegPKId.courseId, "+
			"a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findBySemesterSubId(String semesterSubId);
	
	@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 order by a.courseEquivalanceRegPKId.courseId, "+
			"a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findByRegisterNumber(String semesterSubId, String registerNumber);
	
	@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 and a.courseEquivalanceRegPKId.courseId=?3 "+
			"order by a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
		String courseId);
	
	@Query("select distinct a.equivalanceCourseId from CourseEquivalanceRegModel a where "+
			"a.courseEquivalanceRegPKId.semesterSubId=?1 and a.courseEquivalanceRegPKId.registerNumber=?2 "+
			"and a.courseEquivalanceRegPKId.courseId=?3")
	String findEquivCourseByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId);
	
	@Modifying
	@Query("delete from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 and a.courseEquivalanceRegPKId.courseId=?3")
	void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
			String courseId);
	
	//For Course Substitution
	@Query(value="select decode(b.CODE,null,a.EQUIVALANCE_COURSE_ID,b.CODE) as course_code from "+
					"ACADEMICS.COURSE_EQUIVALANCE_REG a left outer join (select COURSE_ID, CODE from "+
					"ACADEMICS.COURSE_CATALOG) b on (b.COURSE_ID=a.EQUIVALANCE_COURSE_ID) where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and a.EQUIVALANCE_COURSE_ID is not null order by course_code", nativeQuery=true)
	List<String> findEquivCourseByRegisterNumber(String semesterSubId, List<String> registerNumber);
}
