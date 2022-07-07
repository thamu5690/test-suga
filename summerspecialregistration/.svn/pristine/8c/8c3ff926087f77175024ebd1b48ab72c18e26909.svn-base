package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.AdditionalLearningCourseCatalogModel;
import org.vtop.CourseRegistration.model.AdditionalLearningCourseCatalogPkModel;

@Repository
public interface AdditionalLearningCourseCatalogRepository extends 
			JpaRepository<AdditionalLearningCourseCatalogModel, AdditionalLearningCourseCatalogPkModel> {
	
	@Query("select a from AdditionalLearningCourseCatalogModel a where a.status=0 "+
			"order by a.alccPkId.code, a.alccPkId.courseId")
	List<AdditionalLearningCourseCatalogModel> findAdditionalLearningCourse();
	
	@Query("select a from AdditionalLearningCourseCatalogModel a where a.alccPkId.code=?1 "+
			"and a.status=0 order by a.alccPkId.courseId")
	List<AdditionalLearningCourseCatalogModel> findAdditionalLearningCode(String code);
	
	@Query("select distinct a.alccPkId.code, a.additionalLearningDetailsModel.description from "+
			"AdditionalLearningCourseCatalogModel a where a.courseCatalogModel.code=?1 and "+
			"(a.additionalLearningDetailsModel.description like '%('||?2||')' ) and a.status=0 and "+
			"a.additionalLearningDetailsModel.learningType='HON' and a.additionalLearningDetailsModel.status=0 "+
			"order by a.alccPkId.code")
	List<Object[]> findHonourTitleByCourseCode(String courseCode, String progSpecCode);

	@Query(value="select CODE, DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS where "+
					"LEARNING_TYPE='HON' and PRGRM_GROUP_PROGRAMME_GROUP_ID=?1 and "+
					"(PROGRAM_SPECIALIZATION=?2 or PROGRAM_SPECIALIZATION like ?2||'/%' or "+
					"PROGRAM_SPECIALIZATION like '%/'||?2||'/%' or PROGRAM_SPECIALIZATION like "+
					"'%/'||?2) and LOCK_STATUS=0 order by CODE", nativeQuery=true)
	List<Object[]> findHonourTitleByCourseCode2(Integer groupId, String specId);
	
	@Query(value="select distinct a.CODE, a.DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS a, "+
					"ACADEMICS.ADDITIONAL_LEARNING_CRS_CTLG b, ACADEMICS.COURSE_CATALOG c where "+
					"a.LEARNING_TYPE='HON' and a.PRGRM_GROUP_PROGRAMME_GROUP_ID=?1 and "+
					"(a.PROGRAM_SPECIALIZATION=?2 or a.PROGRAM_SPECIALIZATION like ?2||'/%' or "+
					"a.PROGRAM_SPECIALIZATION like '%/'||?2||'/%' or a.PROGRAM_SPECIALIZATION like "+
					"'%/'||?2) and a.LOCK_STATUS=0 and a.CODE=b.ADDTNL_LEARNING_DETAILS_CODE and "+
					"b.LOCK_STATUS=0 and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and c.CODE=?3 "+
					"order by a.CODE", nativeQuery=true)
	List<Object[]> findHonourTitleByCourseCode3(Integer groupId, String specId, String courseCode);
	
	@Query("select distinct a.alccPkId.code, a.additionalLearningDetailsModel.description from "+
			"AdditionalLearningCourseCatalogModel a where a.courseCatalogModel.code=?1 and a.status=0 and "+
			"a.additionalLearningDetailsModel.learningType='MIN' and a.additionalLearningDetailsModel.status=0 "+
			"order by a.alccPkId.code")
	List<Object[]> findMinorTitleByCourseCode(String courseCode);
	
	@Query(value="select distinct a.CODE, a.DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS a, "+
					"ACADEMICS.ADDITIONAL_LEARNING_CRS_CTLG b, ACADEMICS.COURSE_CATALOG c where "+
					"a.LEARNING_TYPE='MIN' and a.PRGRM_GROUP_PROGRAMME_GROUP_ID=?1 and "+
					"a.PROGRAM_SPECIALIZATION not in ('0') and (a.PROGRAM_SPECIALIZATION<>?2 and "+
					"a.PROGRAM_SPECIALIZATION not like ?2||'/%' and a.PROGRAM_SPECIALIZATION not like "+
					"'%/'||?2||'/%' and a.PROGRAM_SPECIALIZATION not like '%/'||?2) and a.LOCK_STATUS=0 "+
					"and a.CODE=b.ADDTNL_LEARNING_DETAILS_CODE and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and c.CODE=?3 order by a.CODE", 
					nativeQuery=true)
	List<Object[]> findMinorTitleByCourseCode2(Integer groupId, String specId, String courseCode);
	
	@Query(value="select distinct a.CODE, a.DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS a, "+
					"ACADEMICS.ADDITIONAL_LEARNING_CRS_CTLG b, ACADEMICS.COURSE_CATALOG c where "+
					"a.LEARNING_TYPE=?1 and a.PRGRM_GROUP_PROGRAMME_GROUP_ID=?2 and "+
					"(a.PROGRAM_SPECIALIZATION=?3 or a.PROGRAM_SPECIALIZATION like ?3||'/%' or "+
					"a.PROGRAM_SPECIALIZATION like '%/'||?3||'/%' or a.PROGRAM_SPECIALIZATION like '%/'||?3) "+
					"and a.LOCK_STATUS=0 and a.CODE=b.ADDTNL_LEARNING_DETAILS_CODE and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and c.CODE=?4 order by a.CODE", nativeQuery=true)
	List<Object[]> findTitleByLearnTypeGroupIdSpecIdAndCourseCode(String learningType, Integer groupId, 
						String specId, String courseCode);

	@Query(value="select CODE, DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS where LEARNING_TYPE=?1 and "+
					"PRGRM_GROUP_PROGRAMME_GROUP_ID=?2 and (PROGRAM_SPECIALIZATION=?3 or PROGRAM_SPECIALIZATION "+
					"like ?3||'/%' or PROGRAM_SPECIALIZATION like '%/'||?3||'/%' or PROGRAM_SPECIALIZATION "+
					"like '%/'||?3) and LOCK_STATUS=0 order by CODE", nativeQuery=true)
	List<Object[]> findTitleByLearnTypeGroupIdAndSpecId(String learningType, Integer groupId, String specializationId);
}
