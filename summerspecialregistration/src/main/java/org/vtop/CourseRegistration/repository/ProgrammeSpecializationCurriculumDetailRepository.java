package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailModel;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailPKModel;

@Repository
public interface ProgrammeSpecializationCurriculumDetailRepository extends 
	JpaRepository<ProgrammeSpecializationCurriculumDetailModel, ProgrammeSpecializationCurriculumDetailPKModel> {
	
	@Query("select a from ProgrammeSpecializationCurriculumDetailModel a order by "+
			"a.psccdPkId.specializationId, a.psccdPkId.admissionYear desc, a.psccdPkId.curriculumVersion, "+
			"a.courseCategory, a.catalogType, a.psccdPkId.courseBasketId")
	List<ProgrammeSpecializationCurriculumDetailModel> findAll();
	
	@Query("select a from ProgrammeSpecializationCurriculumDetailModel a where a.psccdPkId.specializationId=?1 "+
			"order by a.psccdPkId.admissionYear desc, a.psccdPkId.curriculumVersion, a.courseCategory, "+
			"a.catalogType, a.psccdPkId.courseBasketId")
	List<ProgrammeSpecializationCurriculumDetailModel> findBySpecId(Integer specId);
	
	@Query("select a from ProgrammeSpecializationCurriculumDetailModel a where a.psccdPkId.specializationId=?1 "+
			"and a.psccdPkId.admissionYear=?2 order by a.psccdPkId.curriculumVersion, a.courseCategory, "+
			"a.catalogType, a.psccdPkId.courseBasketId")
	List<ProgrammeSpecializationCurriculumDetailModel> findBySpecIdAdmYear(Integer specId, Integer admissionYear);
	
	@Query("select a from ProgrammeSpecializationCurriculumDetailModel a where a.psccdPkId.specializationId=?1 "+
			"and a.psccdPkId.admissionYear=?2 and a.psccdPkId.curriculumVersion=?3 order by a.courseCategory, "+
			"a.catalogType, a.psccdPkId.courseBasketId")
	List<ProgrammeSpecializationCurriculumDetailModel> findBySpecIdAdmYearCCVersion(Integer specId, Integer admissionYear, 
			Float ccVersion);
	
	//Program Specialization & Year based Curriculum Category detail
	@Query(value="select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID)) "+
					"order by COURSE_CATEGORY, CATALOG_TYPE, COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearAndCCVersion(Integer specId, Integer admissionYear, Float ccVersion);
	
	@Query(value="select a.COURSE_CATEGORY, b.COURSE_CATEGORY_DESC, a.CATALOG_TYPE, a.COURSE_BASKET_ID, "+
					"a.COURSE_ID, a.CODE, b.ORDER_POSITION, a.BASKET_CATEGORY from "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, BASKET_CATEGORY from "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"'NONE' as BASKET_CATEGORY from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.BASKET_CATEGORY from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and d.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and a.COURSE_BASKET_ID=d.BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID))) a, "+
					"(select CODE, DESCRIPTION as COURSE_CATEGORY_DESC, ORDER_POSITION from ACADEMICS.LOOKUP_MASTER "+
					"where TABLE_NAME='PRG_SPLZTN_CURRICULUM_DETAILS' and COLUMN_NAME='COURSE_CATEGORY' "+
					"and LOCK_STATUS in (0,1)) b where a.COURSE_CATEGORY=b.CODE "+
					"order by b.ORDER_POSITION, a.CATALOG_TYPE, a.COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearAndCCVersion2(Integer specId, Integer admissionYear, Float ccVersion);
	
	//Program Specialization, Year & Course based Curriculum Category detail
	/*@Query(value="select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE=?4) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and c.CODE=?4)) "+
					"order by COURSE_CATEGORY, CATALOG_TYPE, COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearCCVersionAndCourseCode(Integer specId, Integer admissionYear, 
						Float ccVersion, String courseCode);*/
	
	
	@Query(value="select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, BASKET_CATEGORY, "+
					"CREDITS from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"'NONE' as BASKET_CATEGORY, b.CREDITS from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 "+
					"and a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE=?4) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, c.COURSE_CATALOG_COURSE_ID "+
					"as COURSE_ID, d.CODE, b.BASKET_CATEGORY, b.CREDITS from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_DETAILS b, ACADEMICS.BASKET_COURSE_CATALOG c, ACADEMICS.COURSE_CATALOG d "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and c.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_ID and a.COURSE_BASKET_ID=c.BASKET_DETAILS_BASKET_ID and "+
					"b.BASKET_ID=c.BASKET_DETAILS_BASKET_ID and c.COURSE_CATALOG_COURSE_ID=d.COURSE_ID and d.CODE=?4)) "+
					"order by COURSE_CATEGORY, CATALOG_TYPE, COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearCCVersionAndCourseCode(Integer specId, Integer admissionYear, 
						Float ccVersion, String courseCode);
	
	@Query(value="select COURSE_CODE from "+
					"((select b.CODE as COURSE_CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.COURSE_CATEGORY in (?4) and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select d.CODE as COURSE_CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_DETAILS b, "+
					"ACADEMICS.BASKET_COURSE_CATALOG c, ACADEMICS.COURSE_CATALOG d where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 "+
					"and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and a.COURSE_CATEGORY in (?4) and a.CATALOG_TYPE='BC' "+
					"and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and c.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_ID "+
					"and a.COURSE_BASKET_ID=c.BASKET_DETAILS_BASKET_ID and b.BASKET_ID=c.BASKET_DETAILS_BASKET_ID and "+
					"c.COURSE_CATALOG_COURSE_ID=d.COURSE_ID)) order by COURSE_CODE", nativeQuery=true)
	List<String> findNCCourseByYearAndCCVersion(Integer specId, Integer admissionYear, Float ccVersion, List<String> courseCategory);	
}
