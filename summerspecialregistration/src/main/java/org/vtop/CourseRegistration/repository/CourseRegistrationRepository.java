package org.vtop.CourseRegistration.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationPKModel;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistrationModel, CourseRegistrationPKModel> {
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"order by a.courseRegistrationPKId.registerNumber, a.courseRegistrationPKId.courseId, "+
			"a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findBySemesterSubId(String semesterSubId);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 order by a.courseRegistrationPKId.courseId, "+
			"a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumber(String semesterSubId, String registerNumber);
	
	/*@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseAllocationModel.clsGrpMasterGroupId "+
			"in (?3) and a.courseAllocationModel.lockStatus=0 order by a.courseRegistrationPKId.courseId, "+
			"a.courseRegistrationPKId.courseType desc")*/
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseAllocationModel.clsGrpMasterGroupId "+
			"in (?3) order by a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberByClassGroupId(String semesterSubId, String registerNumber, 
										String[] classGroupId);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 "+
			"order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
										String courseId);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 and "+
			"a.courseAllocationModel.clsGrpMasterGroupId in (?4) order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseIdByClassGroupId(String semesterSubId, 
										String registerNumber, String courseId, String[] classGroupId);
		
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseCatalogModel.code=?3 "+
			"order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseCode(String semesterSubId, String registerNumber, 
										String courseCode);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and (a.courseCatalogModel.code=?3 or "+
			"(a.courseCatalogModel.code in (select c.equivalentCourseCode from CourseEquivalancesModel c "+
			"where c.courseCode=?3)) or (a.courseCatalogModel.code in (select d.courseCode from "+
			"CourseEquivalancesModel d where d.equivalentCourseCode=?3))) "+
			"order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseCode2(String semesterSubId, String registerNumber, 
										String courseCode);
	
	@Query("select a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType, "+
			"a.courseCatalogModel.lectureCredits, a.courseCatalogModel.practicalCredits, "+
			"a.courseCatalogModel.projectCredits, a.courseCatalogModel.credits from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 order by a.courseRegistrationPKId.courseId, "+
			"a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findRegisterNumberCredits(String semesterSubId, String registerNumber);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 "+
			"and a.courseRegistrationPKId.courseType=?4")
	CourseRegistrationModel findByRegisterNumberCourseIdAndType(String semesterSubId, String registerNumber, 
								String courseId, String courseType);
			
	/*@Modifying
	@Query("update CourseRegistrationModel a set a.classId=?5, a.logUserId=?6, logTimestamp=?7, "+
			"logIpaddress=?8 where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 "+
			"and a.courseRegistrationPKId.courseType=?4")
	void updateClassIdByRegisterNumberCourseIdType(String semesterSubId, String registerNumber, 
		String courseId, String courseType, String classId, String userId, Date timeStamp, 
		String ipAddress);*/
	
	@Modifying
	@Query("update CourseRegistrationModel a set a.classId=?5, a.logUserId=?6, a.logTimestamp=?7, "+
			"a.logIpaddress=?8 where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 "+
			"and a.courseRegistrationPKId.courseType=?4")
	void updateClassIdByRegisterNumberCourseIdType(String semesterSubId, String registerNumber, 
				String courseId, String courseType, String classId, String userId, Date timestamp, 
				String ipaddress);
	
	@Modifying
	@Query("update CourseRegistrationModel a set a.statusNumber=?4 where "+
			"a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.courseRegistrationPKId.courseId=?3")
	void updateStatusNoByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
				String courseId, Integer statusNumber);
	
	@Modifying
	@Query("delete from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3")
	void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, String courseId);
	
	@Query("select nvl(count(distinct a.courseRegistrationPKId.courseId),0) as regcnt from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2")
	Integer findRegisterNumberTCCount(String semesterSubId, String registerNumber);
		
	@Query("select nvl(count(distinct a.courseRegistrationPKId.courseId),0) as regcnt from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	Integer findRegisterNumberTCCountByClassGroupId(String semesterSubId, String registerNumber, 
				String[] classGroupId);
	
	@Query("select nvl(count(distinct b.courseRegistrationPKId.courseId),0) as rgrcnt from "+
			"CourseRegistrationModel b where b.courseRegistrationPKId.semesterSubId=?1 "+
			"and b.courseRegistrationPKId.registerNumber=?2 and b.courseOptionCode in "+
			"('RGR','RGA','AUD','RGP','DM','HON','MIN','RGCE','RPCE','RGW','RWCE','RPEUE')")
	Integer findRegisterNumberRCCount(String semesterSubId, String registerNumber);
	
	@Query("select nvl(count(distinct b.courseRegistrationPKId.courseId),0) as rgrcnt from "+
			"CourseRegistrationModel b where b.courseRegistrationPKId.semesterSubId=?1 and "+
			"b.courseRegistrationPKId.registerNumber=?2 and b.courseOptionCode in "+
			"('RGR','RGA','AUD','RGP','DM','HON','MIN','RGCE','RPCE','RGW','RWCE','RPEUE') and "+
			"b.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	Integer findRegisterNumberRCCountByClassGroupId(String semesterSubId, String registerNumber, 
				String[] classGroupId);
	
	@Query("select nvl(count(distinct c.courseRegistrationPKId.courseId),0) as invcnt from "+
			"CourseRegistrationModel c where c.courseRegistrationPKId.semesterSubId=?1 "+
			"and c.courseRegistrationPKId.registerNumber=?2 and (c.courseRegistrationPKId.courseId "+
			"like 'INV%')")
	Integer findRegisterNumbeICCCount(String semesterSubId, String registerNumber);
	
	@Query("select nvl(count(distinct c.courseRegistrationPKId.courseId),0) as invcnt from "+
			"CourseRegistrationModel c where c.courseRegistrationPKId.semesterSubId=?1 "+
			"and c.courseRegistrationPKId.registerNumber=?2 and c.courseOptionCode='AUD'")
	Integer findRegisterNumbeAudCount(String semesterSubId, String registerNumber);
		
	@Query("select nvl(count(distinct c.courseRegistrationPKId.courseId),0) as invcnt from "+
			"CourseRegistrationModel c where c.courseRegistrationPKId.semesterSubId=?1 "+
			"and c.courseRegistrationPKId.registerNumber=?2 and c.courseOptionCode in ('GI','GICE')")
	Integer findRegisterNumbeGICount(String semesterSubId, String registerNumber);
	
	@Query("select nvl(count(distinct c.courseRegistrationPKId.courseId),0) as invcnt from "+
			"CourseRegistrationModel c where c.courseRegistrationPKId.semesterSubId=?1 "+
			"and c.courseRegistrationPKId.registerNumber=?2 and c.courseOptionCode in ('GI','GICE') "+ 
			"and c.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	Integer findRegisterNumbeGICountByClassGroup(String semesterSubId, String registerNumber, 
				String[] classGroupId);
	
	@Query("select nvl(count(distinct a.courseRegistrationPKId.courseId),0) as courseCount from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseType='PJT' "+
			"and a.courseOptionCode in ('RGR') and a.courseCatalogModel.evaluationType in ('CAPSTONE')")
	Integer findProjectCourseCountByRegisterNumber(String semesterSubId, String registerNumber);
	
	
	@Query(value="select count(distinct COURSE_ID) as COURSE_COUNT from "+ 
					"(select COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION "+ 
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"COURSE_OPTION_MASTER_CODE in (?3) "+ 
					"union all "+ 
					"(select COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION_WAITING "+ 
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"COURSE_OPTION_MASTER_CODE in (?3)))", nativeQuery=true)
	Integer findCourseCountByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
				List<String> courseOption);
	
	@Query(value="select count(distinct COURSE_ID) as COURSE_COUNT from "+ 
					"(select a.COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+ 
					"ACADEMICS.COURSE_ALLOCATION b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_OPTION_MASTER_CODE in (?3) and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID "+ 
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.CLSSGRP_MASTER_CLASS_GROUP_ID in (?4) "+ 
					"union all " + 
					"(select a.COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION_WAITING a, "+ 
					"ACADEMICS.COURSE_ALLOCATION b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_OPTION_MASTER_CODE in (?3) and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+ 
					"b.CLSSGRP_MASTER_CLASS_GROUP_ID in (?4)))", nativeQuery=true)
	Integer findCourseCountByRegisterNumberCourseOptionAndClassGroup(String semesterSubId, String registerNumber, 
				List<String> courseOption, List<String> classGroup);
		
	
	@Query(value="select nvl(sum(credit),0) as totalCredit from ("+
					"(select b.LECTURE_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.PRACTICAL_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.PROJECT_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE not "+
					"in ('ETH','ELA','EPJ','ECA') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.EVALUATION_TYPE not in ('STUDYTOUR')))", nativeQuery=true)
	Integer findRegisterNumberTotalCredits(String semesterSubId, String registerNumber);
	
	@Query(value="select nvl(sum(credit),0) as totalCredit from ("+
					"(select b.LECTURE_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE not in (?3)) union all "+
					"(select b.PRACTICAL_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE not in (?3)) union all "+
					"(select b.PROJECT_CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE not in (?3)) union all "+
					"(select b.CREDITS as credit from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE not "+
					"in ('ETH','ELA','EPJ','ECA') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.EVALUATION_TYPE not in ('STUDYTOUR') and b.CODE not in (?3)))", 
					nativeQuery=true)
	Integer findRegCreditByRegisterNumberAndNCCourseCode(String semesterSubId, String registerNumber, 
				List<String> ncCourseCode);
	
	
	@Query(value="select nvl(sum(credit),0) as totalCredit from ("+
					"(select b.LECTURE_CREDITS as credit, a.COURSE_OPTION_MASTER_CODE from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.PRACTICAL_CREDITS as credit, a.COURSE_OPTION_MASTER_CODE from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.PROJECT_CREDITS as credit, a.COURSE_OPTION_MASTER_CODE from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) union all "+
					"(select b.CREDITS as credit, a.COURSE_OPTION_MASTER_CODE from academics.COURSE_REGISTRATION a, "+ 
					"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE not "+
					"in ('ETH','ELA','EPJ') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) where "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')", nativeQuery=true)
	Integer findPSRegisteredTotalCreditsByRegisterNumber(String[] semesterSubId, String registerNumber);
	
	@Query(value="select nvl(sum(credit),0) as totalCredit from ("+
					"(select LECTURE_CREDITS as credit, COURSE_OPTION_MASTER_CODE from "+
					"academics.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"CRSTYPCMPNTMASTER_COURSE_TYPE='ETH') union all "+
					"(select PRACTICAL_CREDITS as credit, COURSE_OPTION_MASTER_CODE from "+
					"academics.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"CRSTYPCMPNTMASTER_COURSE_TYPE='ELA') union all "+
					"(select PROJECT_CREDITS as credit, COURSE_OPTION_MASTER_CODE from "+
					"academics.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ') union all "+
					"(select CREDITS as credit, COURSE_OPTION_MASTER_CODE from "+
					"academics.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ'))) "+ 
					"where COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')", nativeQuery=true)
	Integer findPSRegisteredTotalCreditsByRegisterNumber2(String registerNumber);
	
	
	@Query("select a.courseAllocationModel.timeTableModel.slotName from CourseRegistrationModel a "+
			"where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.courseRegistrationPKId.courseType not in ('EPJ','PJT','ECA') and "+
			"a.courseAllocationModel.slotId not in (0) order by a.courseAllocationModel.timeTableModel.slotName")
	List<String> findRegisteredSlots(String semesterSubId, String registerNumber);
	
	@Query("select a.courseAllocationModel.timeTableModel.slotName from CourseRegistrationModel a "+
			"where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.classId not in (?3) and a.courseRegistrationPKId.courseType not in ('EPJ','PJT','ECA') and "+ 
			"a.courseAllocationModel.slotId not in (0) order by a.courseAllocationModel.timeTableModel.slotName")
	List<String> findRegisteredSlotsforUpdate(String semesterSubId, String registerNumber, String oldClassId);
	
	@Query("select distinct a.courseRegistrationPKId.courseId from CourseRegistrationModel a "+
			"where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2")
	List<String> findRegisteredCourse(String semesterSubId, String registerNumber);
	
	@Query("select distinct a.courseRegistrationPKId.courseId from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	List<String> findRegisteredCourseByClassGroup(String semesterSubId, String registerNumber, 
						String[] classGroupId);
	
	//For previous semester course checking
	@Query("select distinct 'R' from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId "+
			"in (?1) and a.courseRegistrationPKId.registerNumber=?2 and a.courseCatalogModel.code=?3")
	String findStudentPrvSemRegCourseCheck(String[] semesterSubId, String registerNumber, String courseCode);
	
	@Query("select distinct a.courseCatalogModel.code from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.semesterSubId in (?1) and a.courseRegistrationPKId.registerNumber in (?2) "+
			"and (a.courseCatalogModel.code in (?3) or (a.courseCatalogModel.code in (select c.equivalentCourseCode "+
			"from CourseEquivalancesModel c where c.courseCode in (?3))) or (a.courseCatalogModel.code in "+
			"(select d.courseCode from CourseEquivalancesModel d where d.equivalentCourseCode in (?3))))")
	List<String> findStudentPrvSemPARequisite(String[] semesterSubId, List<String> registerNumber, 
					List<String> courseCode);
	
	@Query(value="select distinct CODE from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER "+
				"in (?1) and (CODE in (?2) or CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
				"where COURSE_CODE in (?2)) or CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where "+
				"EQUIVALENT_COURSE_CODE in (?2)))", nativeQuery=true)
	List<String> findPrevSemCourseRegistrationPARequisiteByRegisterNumber(List<String> registerNumber, 
				List<String> courseCode);
	
		
	@Modifying
	@Transactional
	@Query(value="insert into academics.COURSE_REGISTRATION (select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
			"a.COURSE_ALLOCATION_CLASS_ID, a.STDNTSLGNDTLS_REGISTER_NUMBER, a.COURSE_CATALOG_COURSE_ID, "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.COURSE_OPTION_MASTER_CODE, a.REGISTRATION_STATUS_NUMBER, "+
			"a.REGISTRATION_COMPONENT_TYPE, 0, 0, 0, ?2, SYSTIMESTAMP, a.LOG_IPADDRESS from "+
			"academics.COURSE_REGISTRATION_WAITING a where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=?3)", nativeQuery=true)
	void insertWaitingToRegistration(String semesterSubId, String registerNumber, String courseId);	 
	
	
	//Procedure
	//For Insert
	/*@Procedure(name="registration_insert_prc", procedureName="academics.registration_insert_prc")
	String registration_insert_prc(String psemsubid, String pclassid, String pregno, String pcourseid, 
			String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
			String ploguserid, String plogipaddress, String pregtype, String pold_course_code, 
			String pcalltype);*/
	@Procedure(name="registration_insert_prc", procedureName="academics.registration_insert_prc")
	String registration_insert_prc(String psemsubid, String pclassid, String pregno, String pcourseid, 
			String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
			String ploguserid, String plogipaddress, String pregtype, String pold_course_code, 
			String pcalltype, String pold_course_type, String pold_exam_month);
	
	//For Update
	/*@Procedure(name="registration_update_prc", procedureName="academics.registration_update_prc")
	String registration_update_prc(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
			String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
			Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code);*/
	@Procedure(name="registration_update_prc", procedureName="academics.registration_update_prc")
	String registration_update_prc(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
			String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
			Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code, 
			String pold_course_type, String pold_exam_month);
	
	//For Delete
	@Procedure(name="registration_delete_prc", procedureName="academics.registration_delete_prc")
	String registration_delete_prc(String psemsubid, String pregno, String pcourseid, String pcalltype, 
			String ploguserid, String plogipaddress, String pregtype, String poldcoursecode);
	
	@Query(value="select SLOT, week_day, CODE, CRSTYPCMPNTMASTER_COURSE_TYPE, ROOM_MASTER_ROOM_NUMBER, "+
			      "TIME_TABLE_SLOT_ID, SLOT_ID, SLOT_NAME, SLOT_TYPE from ("+
			      "(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+
			      "c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.course_registration a, "+
			      "academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+
			      "academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			      "a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and d.PATTERN_ID=?3 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+
			      "('ETH','TH','ELA','LO','SS') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
			      "a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+
			      "c.TIME_TABLE_SLOT_ID=d.SLOT_ID and e.SLOT IN (SELECT REGEXP_SUBSTR(d.SLOT_NAME, '[^+]+', 1, LEVEL) "+
			      "AS data FROM dual CONNECT BY REGEXP_SUBSTR(d.SLOT_NAME,'[^+]+', 1, LEVEL) IS NOT NULL)) "+
			      "union all "+
			      "(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+
			      "c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.COURSE_REGISTRATION_WAITING a, "+
			      "academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+
			      "academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			      "a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and d.PATTERN_ID=?3 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+
			      "('ETH','TH','ELA','LO','SS') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
			      "a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+
			      "c.TIME_TABLE_SLOT_ID=d.SLOT_ID and e.SLOT IN (SELECT REGEXP_SUBSTR(d.SLOT_NAME, '[^+]+', 1, LEVEL) "+
			      "AS data FROM dual CONNECT BY REGEXP_SUBSTR(d.SLOT_NAME,'[^+]+', 1, LEVEL) IS NOT NULL)))", 
			      nativeQuery=true)
	List<Object[]> findCourseRegWlSlotByStudent(String semesterSubId, String registerNumber, Integer patternId);
	
	/*@Query(value="select SLOT_TYPE, WEEK_DAY, SLOT, CODE, CRSTYPCMPNTMASTER_COURSE_TYPE, ROOM_MASTER_ROOM_NUMBER, "+
					"decode(SLOT_TYPE,'THEORY',1,'LAB',2,3) as slot_order, decode(WEEK_DAY,'MON',1,'TUE',2,'WED',3,"+
					"'THU',4,'FRI',5,'SAT',6,'SUN',7) as week_day_order from ("+
					"(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+
					"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.course_registration a, "+
					"academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+
					"academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+
					"('ETH','TH','ELA','LO','SS') and d.PATTERN_ID=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=c.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.CRSTYPCMPNTMASTER_COURSE_TYPE and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID "+
					"and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and d.PATTERN_ID=e.TMTBLPATTERN_MASTER_PATTERN_ID and "+
					"e.SLOT IN (SELECT REGEXP_SUBSTR(d.SLOT_NAME, '[^+]+', 1, LEVEL) AS data FROM dual CONNECT BY "+
					"REGEXP_SUBSTR(d.SLOT_NAME,'[^+]+', 1, LEVEL) IS NOT NULL)) "+
					"union all "+
					"(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+
					"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.COURSE_REGISTRATION_WAITING a, "+
					"academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+
					"academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+
					"('ETH','TH','ELA','LO','SS') and d.PATTERN_ID=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=c.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.CRSTYPCMPNTMASTER_COURSE_TYPE and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID "+
					"and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and d.PATTERN_ID=e.TMTBLPATTERN_MASTER_PATTERN_ID and "+
					"e.SLOT IN (SELECT REGEXP_SUBSTR(d.SLOT_NAME, '[^+]+', 1, LEVEL) AS data FROM dual "+
					"CONNECT BY REGEXP_SUBSTR(d.SLOT_NAME,'[^+]+', 1, LEVEL) IS NOT NULL))) "+
					"order by slot_order, week_day_order, SLOT", nativeQuery=true)*/
	@Query(value="select SLOT_TYPE, WEEK_DAY, SLOT, CODE, CRSTYPCMPNTMASTER_COURSE_TYPE, ROOM_MASTER_ROOM_NUMBER, "+
			"decode(SLOT_TYPE,'THEORY',1,'LAB',2,3) as slot_order, decode(WEEK_DAY,'MON',1,'TUE',2,'WED',3,"+
			"'THU',4,'FRI',5,'SAT',6,'SUN',7) as week_day_order from ("+
			"(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+
			"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.course_registration a, "+
			"academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+
			"academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+
			"('ETH','TH','ELA','LO','SS') and d.PATTERN_ID=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
			"a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=c.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
			"and a.COURSE_CATALOG_COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.CRSTYPCMPNTMASTER_COURSE_TYPE and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID "+
			"and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and d.PATTERN_ID=e.TMTBLPATTERN_MASTER_PATTERN_ID and "+
			"e.SLOT IN (SELECT REGEXP_SUBSTR(d.SLOT_NAME, '[^+]+', 1, LEVEL) AS data FROM dual CONNECT BY "+
			"REGEXP_SUBSTR(d.SLOT_NAME,'[^+]+', 1, LEVEL) IS NOT NULL))) "+
			"order by slot_order, week_day_order, SLOT", nativeQuery=true)
	List<Object[]> findCourseRegWlSlotByStudent2(String semesterSubId, String registerNumber, Integer patternId);
	
	
	//For cancelled course checking
	@Query(value="select distinct 'CL' from academics.course_registration_cancel a, academics.course_catalog b "+
			"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and (b.code=?2 or b.code in "+ 
			"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE=?2) or "+
			"b.code in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+ 
			"and (upper(a.remarks) like '%SUSPEND%' or upper(a.remarks) like '%DISCONTINUE%' or "+
			"upper(a.remarks) like '%BREAK%') and b.course_id=a.course_catalog_course_id", nativeQuery=true)
	String findStudentCourseCancelCheck(List<String> registerNumber, String courseCode);
	
	@Query(value="select hist_type, COURSE_CATALOG_COURSE_ID, CODE, GENERIC_COURSE_TYPE, hist_date from "+
					"(select 1 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_CANCEL a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"(upper(a.REMARKS) like '%SUSPEND%' or upper(a.REMARKS) like '%DISCONTINUE%' or "+
					"upper(a.REMARKS) like '%BREAK%') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.code=?2 "+
					"union all "+
					"(select 2 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_CANCEL a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"(upper(a.REMARKS) like '%SUSPEND%' or upper(a.REMARKS) like '%DISCONTINUE%' or "+
					"upper(a.REMARKS) like '%BREAK%') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (b.CODE in "+
					"(select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE) or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where EQUIVALENT_COURSE_CODE=?2 and COURSE_CODE<>EQUIVALENT_COURSE_CODE)))) "+
					"order by hist_type, COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findCancelCourseByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode);

	/*@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) order by SEMSTR_DETAILS_SEMESTER_SUB_ID", 
					nativeQuery=true)
	List<String> findPrevSemesterSubIdByRegisterNumber(List<String> registerNumber);*/
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID from ACADEMICS.COURSE_REGISTRATION "+
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID in (select SEMESTER_SUB_ID from "+
					"ACADEMICS.RESULT_UNPUB_SEM_DETAIL_VIEW) and STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID", nativeQuery=true)
	List<String> findPrevSemesterSubIdByRegisterNumber(List<String> registerNumber);
	
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and code=?2 order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc", 
					nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationByRegisterNumber(String registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, STDNTSLGNDTLS_REGISTER_NUMBER from "+
					"ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and code=?2 "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc", nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationByRegisterNumber2(List<String> registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, CODE from "+
					"ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findPrevSemCourseDetailByRegisterNumber(List<String> registerNumber);
	
	
	/*@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and (code in (select EQUIVALENT_COURSE_CODE "+
					"from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or code in (select COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID", nativeQuery=true)*/
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, CODE from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and (code in (select EQUIVALENT_COURSE_CODE "+
					"from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or code in (select COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc, CODE", nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationCEByRegisterNumber(String registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, CODE from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and (code in (select EQUIVALENT_COURSE_CODE "+
					"from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or code in (select COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc, CODE", nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationCEByRegisterNumber2(List<String> registerNumber, String courseCode);
	
	
	//For registration purpose
	@Query(value="select distinct reg_type, course_code, course_title, GENERIC_COURSE_TYPE from "+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE=?3 "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE=?3)) "+
					"order by reg_type, course_code", nativeQuery=true)
	List<Object[]> findRegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
						String courseCode);
	
	@Query(value="select distinct reg_type, course_code, course_title, GENERIC_COURSE_TYPE from "+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"(b.CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?3) "+
					"or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?3)) "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (b.CODE in "+
					"(select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?3) or b.CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?3)))) "+
					"order by reg_type, course_code", nativeQuery=true)
	List<Object[]> findCERegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
					String courseCode);
	
	@Query(value="select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, sum(credit) as "+
				"course_credit from ( "+
				"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, LECTURE_CREDITS as credit, "+
				"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
				"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' and "+
				"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
				"union all "+
				"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PRACTICAL_CREDITS as credit, "+
				"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
				"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' and "+
				"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
				"union all "+
				"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PROJECT_CREDITS as credit, "+
				"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
				"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' and "+
				"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
				"union all "+
				"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, CREDITS as credit, "+
				"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
				"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and CRSTYPCMPNTMASTER_COURSE_TYPE not in "+
				"('ETH','ELA','EPJ','ECA') and COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE'))) "+
				"group by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID "+
				"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findPSRegisteredCourseCreditsByRegisterNumber(String registerNumber);
	
	@Query(value="select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, sum(credit) as "+
					"course_credit from ( "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, LECTURE_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PRACTICAL_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PROJECT_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE not in "+
					"('ETH','ELA','EPJ','ECA') and COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE'))) "+
					"group by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findPSRegisteredCourseCreditsByRegisterNumber2(List<String> registerNumber);
	
	
	//For course substitution purpose
	@Query(value="select distinct course_code from "+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
					"order by course_code", nativeQuery=true)
	List<String> findRegistrationAndWLCourseByRegisterNumber(String semesterSubId, List<String> registerNumber);
	
	@Query(value="SELECT f.CLSSGRP_MASTER_CLASS_GROUP_ID, j.DESCRIPTION as CLASS_GROUP_DESCRIPTION, "+
					"a.COURSE_CATALOG_COURSE_ID, b.CODE, b.TITLE, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, d.DESCRIPTION as COURSE_OPTION_DESCRIPTION, "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.DESCRIPTION as COURSE_TYPE_DESCRIPTION, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_HOURS,'TH',b.LECTURE_HOURS,0) "+
					"as LECTURE_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.TUTORIAL_HOURS, "+
					"'TH',b.TUTORIAL_HOURS,0) as TUTORIAL_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"'ELA',b.PRACTICAL_HOURS,'LO',b.PRACTICAL_HOURS,0) as PRACTICAL_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'EPJ',b.PROJECT_HOURS,0) as PROJECT_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_CREDITS,'ELA',b.PRACTICAL_CREDITS, "+
					"'EPJ',b.PROJECT_CREDITS,b.CREDITS) as CREDIT, b.EVALUATION_TYPE, a.COURSE_ALLOCATION_CLASS_ID, "+
					"i.SLOT_NAME, f.ROOM_MASTER_ROOM_NUMBER, f.ERP_ID, g.FIRST_NAME, h.CODE as SCHOOL_CODE, "+
					"a.RGSTRTNSTSMSTR_STATUS_NUMBER, e.DESCRIPTION as STATUS_DESCRIPTION, e.FONT_COLOUR, "+
					"a.INVOICES_INVOICE_NUMBER, to_char(a.LOG_TIMESTAMP,'DD-Mon-YYYY') as reg_date, "+
					"decode(f.CLSSGRP_MASTER_CLASS_GROUP_ID,'ALL',1,'WEI',2,3) as CLASS_GROUP_ORDER, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',2,'ELA',3,'EPJ',4,1) as COURSE_TYPE_ORDER "+
					"FROM ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c, ACADEMICS.COURSE_OPTION_MASTER d, "+
					"ACADEMICS.REGISTRATION_STATUS_MASTER e, ACADEMICS.COURSE_ALLOCATION f, "+
					"HRMS.EMPLOYEE_PROFILE g, VTOPMASTER.COST_CENTRE h, ACADEMICS.TIME_TABLE i, "+
					"ACADEMICS.CLASS_GROUP_MASTER j where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and b.GENERIC_COURSE_TYPE not in ('ECA') and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.COURSE_TYPE and "+
					"a.COURSE_OPTION_MASTER_CODE=d.CODE and a.RGSTRTNSTSMSTR_STATUS_NUMBER=e.STATUS_NUMBER and "+
					"a.COURSE_ALLOCATION_CLASS_ID=f.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=f.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=f.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=f.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.COURSE_ID=f.COURSE_CATALOG_COURSE_ID and f.ERP_ID=g.EMPLOYEE_ID and "+
					"f.TIME_TABLE_SLOT_ID=i.SLOT_ID and f.CLSSGRP_MASTER_CLASS_GROUP_ID=j.CLASS_GROUP_ID and "+
					"g.COST_CENTRE_CENTRE_ID=h.CENTRE_ID "+
					"order by CLASS_GROUP_ORDER, f.CLSSGRP_MASTER_CLASS_GROUP_ID, a.COURSE_CATALOG_COURSE_ID, "+
					"COURSE_TYPE_ORDER", nativeQuery=true)
	List<Object[]> findByRegisterNumber3(String semesterSubId, String registerNumber);
	
	@Query(value="SELECT a.COURSE_CATALOG_COURSE_ID, b.CODE, b.TITLE, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, d.DESCRIPTION as COURSE_OPTION_DESCRIPTION, "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.DESCRIPTION as COURSE_TYPE_DESCRIPTION, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_HOURS,'TH',b.LECTURE_HOURS,0) "+
					"as LECTURE_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.TUTORIAL_HOURS, "+
					"'TH',b.TUTORIAL_HOURS,0) as TUTORIAL_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"'ELA',b.PRACTICAL_HOURS,'LO',b.PRACTICAL_HOURS,0) as PRACTICAL_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'EPJ',b.PROJECT_HOURS,0) as PROJECT_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_CREDITS,'ELA',b.PRACTICAL_CREDITS, "+
					"'EPJ',b.PROJECT_CREDITS,b.CREDITS) as CREDIT, b.EVALUATION_TYPE, a.COURSE_ALLOCATION_CLASS_ID, "+
					"i.SLOT_NAME, f.ROOM_MASTER_ROOM_NUMBER, f.ERP_ID, g.FIRST_NAME, h.CODE as SCHOOL_CODE, "+
					"a.RGSTRTNSTSMSTR_STATUS_NUMBER, e.DESCRIPTION as STATUS_DESCRIPTION, e.FONT_COLOUR, "+
					"a.INVOICES_INVOICE_NUMBER, to_char(a.LOG_TIMESTAMP,'DD-Mon-YYYY') as reg_date, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',2,'ELA',3,'EPJ',4,1) as COURSE_TYPE_ORDER "+
					"FROM ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c, ACADEMICS.COURSE_OPTION_MASTER d, "+
					"ACADEMICS.REGISTRATION_STATUS_MASTER e, ACADEMICS.COURSE_ALLOCATION f, "+
					"HRMS.EMPLOYEE_PROFILE g, VTOPMASTER.COST_CENTRE h, ACADEMICS.TIME_TABLE i where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"f.CLSSGRP_MASTER_CLASS_GROUP_ID in (?3) and b.GENERIC_COURSE_TYPE not in ('ECA') and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.COURSE_TYPE and "+
					"a.COURSE_OPTION_MASTER_CODE=d.CODE and a.RGSTRTNSTSMSTR_STATUS_NUMBER=e.STATUS_NUMBER and "+
					"a.COURSE_ALLOCATION_CLASS_ID=f.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=f.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=f.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=f.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.COURSE_ID=f.COURSE_CATALOG_COURSE_ID and f.ERP_ID=g.EMPLOYEE_ID and "+
					"f.TIME_TABLE_SLOT_ID=i.SLOT_ID and g.COST_CENTRE_CENTRE_ID=h.CENTRE_ID "+
					"order by a.COURSE_CATALOG_COURSE_ID, COURSE_TYPE_ORDER", nativeQuery=true)
	List<Object[]> findByRegisterNumberAndClassGroup(String semesterSubId, String registerNumber, String[] classGroupId);
		
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID "+
					"and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.LOCK_STATUS in (1,2) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForUpdate(String semesterSubId, String registerNumber);
	
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.COURSE_CATALOG c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID "+
					"and (a.CRSTYPCMPNTMASTER_COURSE_TYPE in ('PJT') or a.RGSTRTNSTSMSTR_STATUS_NUMBER in (2,3,4,5,6,13) "+
					"or b.LOCK_STATUS in (1,2)) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForUpdate2(String semesterSubId, String registerNumber);
	
	/*@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.COURSE_CATALOG c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID "+
					"and (a.RGSTRTNSTSMSTR_STATUS_NUMBER in (2,3,4,5,6,13) or b.LOCK_STATUS in (1) or "+
					"substr(c.CODE,1,3) in ('SET') or (a.COURSE_OPTION_MASTER_CODE in ('RGR') and "+
					"c.GENERIC_COURSE_TYPE in ('SS'))) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String registerNumber);*/
	
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.COURSE_CATALOG c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID "+
					"and (a.RGSTRTNSTSMSTR_STATUS_NUMBER in (2,3,4,5,6,13) or b.LOCK_STATUS in (1) or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE='PJT' and a.COURSE_OPTION_MASTER_CODE in ('RGR') "+
					"and c.EVALUATION_TYPE in ('CAPSTONE'))) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String registerNumber);
	
	/*@Query(value="select distinct course_code from "+
					"(select b.CODE as course_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+
					"union all "+
					"(select EQUIVALENT_COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
					"union all "+
					"(select COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) order by course_code", nativeQuery=true)
	List<String> findUECourseByRegisterNumber(List<String> registerNumber);*/
	
	@Query(value="select distinct course_code from "+
				"(select a.COURSE_CODE from ACADEMICS.STUDENT_HISTORY a, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
				"and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) "+
				"union all "+
				"(select b.CODE as course_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
				"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
				"union all "+
				"(select EQUIVALENT_COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE "+
				"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
				"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
				"union all "+
				"(select COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE "+
				"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
				"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) order by course_code", nativeQuery=true)
	List<String> findUECourseByRegisterNumber(List<String> registerNumber);	
	
	
	@Query(value="select distinct b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, a.RGSTRTNSTSMSTR_STATUS_NUMBER from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"(b.CODE=?3 or b.CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where COURSE_CODE=?3) or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where EQUIVALENT_COURSE_CODE=?3)) order by course_code", nativeQuery=true)
	List<Object[]> findCourseOptionByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
						String courseCode);
	
	@Query(value="select distinct course_code from "+
					"(select b.CODE as course_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+
					"union all "+
					"(select EQUIVALENT_COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
					"union all "+
					"(select COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) "+
					"order by course_code", nativeQuery=true)
	List<String> findUECourseByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
					List<String> courseOption);
	
	@Query("select a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType, a.classId, "+
			"a.courseOptionCode, a.statusNumber, a.componentType from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber in (?2) "+
			"and a.courseCatalogModel.code=?3 order by a.courseRegistrationPKId.courseType desc")
	List<Object[]> findByRegisterNumberCourseCode3(String semesterSubId, List<String> registerNumber, 
						String courseCode);
	
	@Query("select a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType, a.classId, "+
			"a.courseOptionCode, a.statusNumber, a.componentType from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.registerNumber in (?1) and a.courseCatalogModel.code=?2 and "+
			"a.courseRegistrationPKId.semesterSubId not in (?3) order by a.courseRegistrationPKId.courseType desc")
	List<Object[]> findByRegisterNumberCourseCodeAndExcludeSemesterSubId(List<String> registerNumber, 
						String courseCode, String semesterSubId);
}
