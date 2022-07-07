package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CompulsoryCourseConditionDetailModel;
import org.vtop.CourseRegistration.model.CompulsoryCourseConditionDetailModelPK;


@Repository
public interface CompulsoryCourseConditionDetailRepository extends JpaRepository<CompulsoryCourseConditionDetailModel,
					CompulsoryCourseConditionDetailModelPK>
{	
	@Query("select a from CompulsoryCourseConditionDetailModel a where a.cccdmPkId.semesterSubId=?1 and "+
			"a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 and a.cccdmPkId.courseId=?4")
	CompulsoryCourseConditionDetailModel findByProgGroupIdStudentBatchAndCourseId(String semesterSubId, 
											Integer progGroupId, Integer studentBatch, String courseId);
	
	@Query("select a from CompulsoryCourseConditionDetailModel a where a.cccdmPkId.semesterSubId=?1 and "+
			"a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 and a.status in (?4) "+
			"order by a.priorityNo, a.cccdmPkId.courseId")
	List<CompulsoryCourseConditionDetailModel> findByProgGroupIdAndStudentBatch(String semesterSubId, 
													Integer progGroupId, Integer studentBatch, 
													List<Integer> status);
	
	/*@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and "+
			"a.cccdmPkId.studentSemester=?3 and a.status=0 order by a.cccdmPkId.courseId")
	List<String> findCompulsoryCourseList(Integer progGroupId, Integer studentBatch, 
					Integer studentSemester);*/
	@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.semesterSubId=?1 and a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 "+
			"and a.status=0 order by a.cccdmPkId.courseId")
	List<String> findCompulsoryCourseList(String semesterSubId, Integer progGroupId, Integer studentBatch);
	
	/*@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and "+
			"a.cccdmPkId.studentSemester=?3 and a.programSpecialization is null and "+
			"a.status=0 order by a.cccdmPkId.courseId")
	List<String> findCompulsoryCourseListWithNullProgSpec(Integer progGroupId, Integer studentBatch, 
					Integer studentSemester);*/
			
	/*@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and "+
			"a.cccdmPkId.studentSemester=?3 and a.programSpecialization is not null and "+
			"(a.programSpecialization=?4 or a.programSpecialization like ?4||'/%' or "+
			"a.programSpecialization like '%/'||?4||'/%' or a.programSpecialization like '%/'||?4) "+
			"and a.status=0 order by a.cccdmPkId.courseId")
	List<String> findCompulsoryCourseListByProgSpecId(Integer progGroupId, Integer studentBatch, 
					Integer studentSemester, String progSpecId);*/	
	@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where a.cccdmPkId.semesterSubId=?1 "+
			"and a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 and ((a.offerType=1 and "+
			"a.programSpecialization is null) or (a.offerType=2 and (a.programSpecialization=?4 or "+
			"a.programSpecialization like ?4||'/%' or a.programSpecialization like '%/'||?4||'/%' or "+
			"a.programSpecialization like '%/'||?4)) or (a.offerType=3 and (a.programSpecialization=?5 or "+ 
			"a.programSpecialization like ?5||'/%' or a.programSpecialization like '%/'||?5||'/%' or "+ 
			"a.programSpecialization like '%/'||?5))) and a.status=0 order by a.cccdmPkId.courseId")
	List<String> findCompulsoryCourseListByProgSpecId(String semesterSubId, Integer progGroupId, 
					Integer studentBatch, String progSpecId, String costCenterId);
	
	/*@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and "+
			"a.cccdmPkId.studentSemester<?3 and (a.cccdmPkId.courseId like 'STS%') and "+
			"a.status=0 order by a.cccdmPkId.courseId")
	List<String> findSoftSkillCourseList(Integer progGroupId, Integer studentBatch, Integer studentSemester);*/
	@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.semesterSubId not in (?1) and a.cccdmPkId.programGroupId=?2 and "+
			"a.cccdmPkId.studentBatch=?3 and (a.cccdmPkId.courseId like 'STS%') and "+
			"a.status=0 order by a.cccdmPkId.courseId")
	List<String> findSoftSkillCourseList(String semesterSubId, Integer progGroupId, Integer studentBatch);
	
	/*@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and a.cccdmPkId.studentSemester=?3 and "+
			"a.programSpecialization is not null and (a.programSpecialization=?4 or a.programSpecialization like ?4||'/%' or "+
			"a.programSpecialization like '%/'||?4||'/%' or a.programSpecialization like '%/'||?4) and a.status=0 "+
			"order by a.cccdmPkId.courseId")
	List<Object[]> findCompulsoryCourseListByProgSpecId2(Integer progGroupId, Integer studentBatch, Integer studentSemester, 
						String progSpecId);*/
	@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam, a.priorityNo from "+
			"CompulsoryCourseConditionDetailModel a where a.cccdmPkId.semesterSubId=?1 and a.cccdmPkId.programGroupId=?2 "+
			"and a.cccdmPkId.studentBatch=?3 and ((a.offerType=1 and a.programSpecialization is null) or "+
			"(a.offerType=2 and (a.programSpecialization=?4 or a.programSpecialization like ?4||'/%' or "+
			"a.programSpecialization like '%/'||?4||'/%' or a.programSpecialization like '%/'||?4)) or "+
			"(a.offerType=3 and (a.programSpecialization=?5 or a.programSpecialization like ?5||'/%' or "+
			"a.programSpecialization like '%/'||?5||'/%' or a.programSpecialization like '%/'||?5))) and a.status=0 "+
			"order by a.priorityNo, a.cccdmPkId.courseId")
	List<Object[]> findCompulsoryCourseListByProgSpecId2(String semesterSubId, Integer progGroupId, Integer studentBatch, 
						String progSpecId, String costCenterId);
	
	/*@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and a.cccdmPkId.studentSemester=?3 and "+
			"a.programSpecialization is null and a.status=0 order by a.cccdmPkId.courseId")
	List<Object[]> findCompulsoryCourseListWithNullProgSpec2(Integer progGroupId, Integer studentBatch, 
						Integer studentSemester);*/
	
	/*@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and a.cccdmPkId.studentSemester=?3 and "+
			"a.programSpecialization is null and a.status=0 and a.cccdmPkId.courseId not in "+
			"(select b.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel b where b.cccdmPkId.programGroupId=?1 "+
			"and b.cccdmPkId.studentBatch=?2 and b.cccdmPkId.studentSemester=?3 and b.programSpecialization is not null "+
			"and (b.programSpecialization=?4 or b.programSpecialization like ?4||'/%' or b.programSpecialization like "+
			"'%/'||?4||'/%' or b.programSpecialization like '%/'||?4) and b.status=0) order by a.cccdmPkId.courseId")
	List<Object[]> findCompulsoryCourseListByProgSpecId3(Integer progGroupId, Integer studentBatch, Integer studentSemester, 
						String progSpecId);*/
	
	/*@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.programGroupId=?1 and a.cccdmPkId.studentBatch=?2 and a.cccdmPkId.studentSemester=?3 and "+
			"a.cccdmPkId.courseId=?4 and a.status=0")
	List<Object[]> findByCourseId(Integer progGroupId, Integer studentBatch, Integer studentSemester, String courseId);*/
	@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.semesterSubId=?1 and a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 and "+
			"a.cccdmPkId.courseId=?4 and a.status=0")
	List<Object[]> findByCourseId(String semesterSubId, Integer progGroupId, Integer studentBatch, String courseId);
}
