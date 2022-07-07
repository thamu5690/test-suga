package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseEquivalancesModel;
import org.vtop.CourseRegistration.model.CourseEquivalancesPKModel;

@Repository
public interface CourseEquivalancesRepository extends JpaRepository<CourseEquivalancesModel, CourseEquivalancesPKModel> {
	
	@Query("select a from CourseEquivalancesModel a order by a.courseEquivalancesPkId.courseId, "+
			"a.courseEquivalancesPkId.equivalentCourseId")
	List<CourseEquivalancesModel> findAll();
	
	@Query("select a from CourseEquivalancesModel a where a.courseEquivalancesPkId.courseId=?1 "+
			"order by a.courseEquivalancesPkId.equivalentCourseId")
	List<CourseEquivalancesModel> findByCourseId(String courseId);
	
	@Query("select a from CourseEquivalancesModel a where a.courseEquivalancesPkId.equivalentCourseId=?1 "+
			"order by a.courseEquivalancesPkId.courseId")
	List<CourseEquivalancesModel> findByEquivalCourseId(String equivalCourseId);
	
	/*@Query(value="select a.equv_course_id, a.equv_course_code, b.course_id, b.code, b.GENERIC_COURSE_TYPE, "+
					"to_char(SYSDATE,'DD-MON-YYYY') as hist_date, b.COURSE_SYSTEM from "+
					"(select distinct equv_course_id, equv_course_code from "+
					"(select EQUIVALENT_COURSE_ID as equv_course_id, EQUIVALENT_COURSE_CODE as "+
					"equv_course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?1  and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE "+
					"union all "+
					"(select COURSE_CATALOG_COURSE_ID as equv_course_id, COURSE_CODE as equv_course_code "+
					"from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?1 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE)) where equv_course_id<>equv_course_code) a "+
					"left outer join ACADEMICS.COURSE_CATALOG b on (b.CODE=a.equv_course_code) "+
					"order by a.equv_course_id, b.course_id", nativeQuery=true)*/
	@Query(value="select a.equv_course_id, a.equv_course_code, b.course_id, b.code, b.GENERIC_COURSE_TYPE, "+
					"to_char(SYSDATE,'DD-MON-YYYY') as hist_date, b.COURSE_SYSTEM from "+
					"(select distinct equv_course_id, equv_course_code from "+
					"(select EQUIVALENT_COURSE_ID as equv_course_id, EQUIVALENT_COURSE_CODE as "+
					"equv_course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?1  and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE "+
					"union all "+
					"(select COURSE_CATALOG_COURSE_ID as equv_course_id, COURSE_CODE as equv_course_code "+
					"from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?1 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE))) a "+
					"left outer join ACADEMICS.COURSE_CATALOG b on (b.CODE=a.equv_course_code) "+
					"order by a.equv_course_id, b.course_id", nativeQuery=true)
	List<Object[]> findEquivalanceListByCourseCode(String courseCode);
}
