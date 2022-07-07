package org.vtop.CourseRegistration.repository.examinations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.MigrationStudentHistoryAcadModel;
import org.vtop.CourseRegistration.model.MigrationStudentHistoryAcadPKModel;

@Repository
public interface MigrationStudentHistoryAcadRepository extends 
							JpaRepository<MigrationStudentHistoryAcadModel, MigrationStudentHistoryAcadPKModel>
{
	@Query("select distinct a.migrationStudentHistoryAcadPKId.courseType from MigrationStudentHistoryAcadModel a "+
			"where a.migrationStudentHistoryAcadPKId.registerNumber in (?1) and a.courseCode=?2 and "+
			"a.grade='Y' and a.courseTypeComponentModel.component=2 "+
			"order by a.migrationStudentHistoryAcadPKId.courseType desc")
	List<String> findMigrationStudentHistoryCourseType(List<String> registerNumber, String courseCode);
	
	//Previous semester result published course list
	@Query(value="SELECT * FROM( "
			+ " SELECT  GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID,GNE.STDNTSLGNDTLS_REGISTER_NUMBER,CC.COURSE_ID, CC.CODE COURSE_CODE, "
			+ " CC.GENERIC_COURSE_TYPE,GNE.GRADE FROM EXAMINATIONS.GRADE_NON_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID) CA "
			+ " ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION, "
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1  "
			+ " and gne.stdntslgndtls_register_number in (?2) "
			+ " UNION ALL "
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID,GNE.STDNTSLGNDTLS_REGISTER_NUMBER,"
			+ " CC.COURSE_ID,CC.CODE COURSE_CODE,CC.GENERIC_COURSE_TYPE,GNE.GRADE "
			+ " FROM EXAMINATIONS.GRADE_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID "
			+ " ) CA ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION,"
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND "
			+ " GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and gne.stdntslgndtls_register_number in (?2) ) "
			+ " ORDER BY STDNTSLGNDTLS_REGISTER_NUMBER,COURSE_CODE ", nativeQuery=true)
	List<Object[]> getResultPublishedCourseDataBySemAndRegNo(String semesterSubId, List<String> regNoList);
	
	@Query(value="SELECT * FROM ("
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID, GNE.STDNTSLGNDTLS_REGISTER_NUMBER, CC.COURSE_ID, "
			+ " CC.CODE COURSE_CODE, CC.GENERIC_COURSE_TYPE, GNE.GRADE FROM EXAMINATIONS.GRADE_NON_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID) CA "
			+ " ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION, "
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1  "
			+ " and gne.stdntslgndtls_register_number in (?2) "
			+ " UNION ALL "
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID, GNE.STDNTSLGNDTLS_REGISTER_NUMBER, "
			+ " CC.COURSE_ID, CC.CODE COURSE_CODE, CC.GENERIC_COURSE_TYPE, GNE.GRADE "
			+ " FROM EXAMINATIONS.GRADE_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID "
			+ " ) CA ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION,"
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "
			+ " gne.stdntslgndtls_register_number in (?2)) where COURSE_CODE=?3 "
			+ " ORDER BY STDNTSLGNDTLS_REGISTER_NUMBER, COURSE_CODE", nativeQuery=true)
	List<Object[]> getResultPublishedCourseDataBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
						String courseCode);
	
	
	//Previous semester arrear result published course list
	@Query(value="select * from("
			+ " select  gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,cc.course_id, cc.code course_code,"
			+ " cc.generic_course_type,gne.grade from examinations.arrear_grade_non_embedded gne "
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld "
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd "
			+ " on gne.stdntslgndtls_register_number=sd.reg_no  "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id "
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description, "
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg "
			+ " inner join vtopmaster.programme_specialization pss on "
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps "
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id "
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2) union all "
			+ " select gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,"
			+ " cc.course_id,cc.code course_code,cc.generic_course_type,gne.grade"
			+ " from examinations.arrear_grade_embedded gne"
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld"
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd"
			+ " on gne.stdntslgndtls_register_number=sd.reg_no "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id"
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description,"
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg"
			+ " inner join vtopmaster.programme_specialization pss on"
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps"
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id"
			+ " where gne.result_declared_date is not null and"
			+ " gne.semstr_details_semester_sub_id=?1 and gne.stdntslgndtls_register_number in (?2))"
			+ " order by stdntslgndtls_register_number,course_code", nativeQuery=true)
	List<Object[]> getResultPublishedCourseDataForRARBySemAndRegNo(String semesterSubId, List<String> regNoList);
	
	@Query(value="select * from ("
			+ " select gne.semstr_details_semester_sub_id, gne.stdntslgndtls_register_number, cc.course_id,"
			+ " cc.code course_code, cc.generic_course_type, gne.grade from examinations.arrear_grade_non_embedded gne "
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld "
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd "
			+ " on gne.stdntslgndtls_register_number=sd.reg_no  "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id "
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description, "
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg "
			+ " inner join vtopmaster.programme_specialization pss on "
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps "
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id "
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2) union all "
			+ " select gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,"
			+ " cc.course_id,cc.code course_code,cc.generic_course_type,gne.grade"
			+ " from examinations.arrear_grade_embedded gne"
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld"
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd"
			+ " on gne.stdntslgndtls_register_number=sd.reg_no "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id"
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description,"
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg"
			+ " inner join vtopmaster.programme_specialization pss on"
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps"
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id"
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2)) where course_code=?3"
			+ " order by stdntslgndtls_register_number, course_code", nativeQuery=true)
	List<Object[]> getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
						String courseCode);
	
	
	//Student CGPA
	@Query(value="select TOTAL_CREDITS_REGISTERED, TOTAL_CREDITS_EARNED, CUMULATIVE_GRADE_POINT_AVERAGE,"
		      + " NUMBER_OF_S_GRADE, NUMBER_OF_A_GRADE,NUMBER_OF_B_GRADE, NUMBER_OF_C_GRADE, NUMBER_OF_D_GRADE,"
		      + " NUMBER_OF_E_GRADE,  NUMBER_OF_F_GRADE, NUMBER_OF_N_GRADE from EXAMINATIONS.STUDENT_CGPA_HISTORY"
		      + " where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and PROGRAMME_SPECIALIZATION_ID=?2 and LOCK_STATUS=0"
		      + " and MODIFIED_TIMESTAMP=(select max(MODIFIED_TIMESTAMP) from EXAMINATIONS.STUDENT_CGPA_HISTORY"
		      + " where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and PROGRAMME_SPECIALIZATION_ID=?2 and LOCK_STATUS=0)", 
		      nativeQuery=true)
	List<Object[]> getStaticStudentCGPAFromTable(String registerNumber, Integer specializationId);
	
	//Student graduation eligibility
	/*@Query(value="select count(*) from ("
			+ " (select STDNTSLGNDTLS_REGISTER_NUMBER from EXAMINATIONS.GRADUATE_STUDENT where"
			+ " STDNTSLGNDTLS_REGISTER_NUMBER in (?1)) UNION ALL"
			+ " (select STDNTSLGNDTLS_REGISTER_NUMBER from EXAMINATIONS.GRADUATE_RESEARCH_SCHOLAR"
			+ " where STDNTSLGNDTLS_REGISTER_NUMBER in (?1)))", nativeQuery=true)
	Integer getGraduationValue(List<String> registerNumber);*/
	
	//Student graduation eligibility
	@Query(value="select count(*) from ("+
					"select gs.stdntslgndtls_register_number from examinations.graduate_student gs where "+
					"gs.Stdntslgndtls_Register_Number in (?1) and NOT EXISTS "+
					"(SELECT * FROM examinations.graduate_student where Stdntslgndtls_Register_Number like '%MBI%' "+
					"and gs.stdntslgndtls_register_number=Stdntslgndtls_Register_Number AND gs.programme_specialization_id=27) "+
					"UNION ALL "+
					"select grs.stdntslgndtls_register_number from examinations.graduate_research_scholar grs where "+
					"grs.stdntslgndtls_register_number in (?1))", nativeQuery=true)
	Integer getGraduationValue(List<String> registerNumber);
}
