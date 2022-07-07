package org.vtop.CourseRegistration.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.StudentHistoryModel;
import org.vtop.CourseRegistration.model.StudentHistoryPKModel;

@Repository
@Transactional
public interface StudentHistoryRepository extends JpaRepository<StudentHistoryModel, StudentHistoryPKModel> {

	@Query("select a from StudentHistoryModel a order by a.studentHistoryPKId.registerNumber, "+
			"a.studentHistoryPKId.courseId, a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findAll();
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) "+
			"order by a.studentHistoryPKId.courseId, a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findByRegisterNumber(List<String> registerNumber);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.studentHistoryPKId.courseId=?2 order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findByRegisterNumberCourseId(List<String> registerNumber, String courseId);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.courseCode=?2 and a.courseTypeComponentModel.component in (1,3) "+
			"order by a.studentHistoryPKId.courseType desc")
	StudentHistoryModel findStudentHistoryGrade(List<String> registerNumber, String courseCode);
	
	@Query("select distinct a.grade from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 and "+
			"a.courseTypeComponentModel.component in (1,3)")
	String findStudentHistoryDistinctGrade(List<String> registerNumber, String courseCode);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.courseTypeComponentModel.component in (1,3) and a.courseCode in "+
			"(select b.equivalentCourseCode from CourseEquivalancesModel b where "+
			"b.courseEquivalancesPkId.courseId=?2) order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryCEGrade(List<String> registerNumber, String courseId);
	
	@Query(value="select a.grade, a.COURSE_CATALOG_COURSE_ID, decode(a.grade,'S',1,'U',2,'P',3,'Pass',"+
				"4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
				"'W',14,'WWW',15,'AAA',16,17) as grade_order from academics.student_history a, "+
				"academics.COURSE_TYPE_COMPONENT_MASTER b where "+
				"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and (a.COURSE_CODE in "+
				"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
				"where COURSE_CODE=?2) or a.COURSE_CODE in (select COURSE_CODE from "+ 
				"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
				"order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade2(List<String> registerNumber, String courseCode);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.studentHistoryPKId.courseId in (select b.courseId from CourseCatalogModel b where "+
			"b.code in (?2)) and a.grade in ('A','B','C','D','E','S','U','R','F','Fail','P','Pass') "+
			"and a.courseTypeComponentModel.component in (1,3) "+
			"order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryPARequisite(List<String> registerNumber, String[] courseCode);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.grade in ('A','B','C','D','E','S','U','R','F','Fail','P','Pass','N','N1','N2','N3','N4') and "+
			"a.courseTypeComponentModel.component in (1,3) and (a.courseCode in (?2) or (a.courseCode in "+
			"(select c.equivalentCourseCode from CourseEquivalancesModel c where c.courseCode in (?2))) or "+
			"(a.courseCode in (select d.courseCode from CourseEquivalancesModel d where "+
			"d.equivalentCourseCode in (?2))))")
	List<StudentHistoryModel> findStudentHistoryPARequisite2(List<String> registerNumber, List<String> courseCode);
	
	@Query("select distinct a.studentHistoryPKId.courseType from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 and "+
			"a.grade in ('Y','N1','N2','N3','N4') and a.courseTypeComponentModel.component=2 "+
			"order by a.studentHistoryPKId.courseType desc")
	List<String> findStudentHistoryCourseType(List<String> registerNumber, String courseCode);
	
	@Query("select distinct a.studentHistoryPKId.courseType from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 "+
			"and a.courseTypeComponentModel.component not in (2)")
	String findStudentHistoryGenericCourseType(List<String> registerNumber, String courseCode);
	

	//For Course Substitution
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.courseCode=?2 and a.grade in ('Fail','F','N','N1','N2','N3','N4') and "+
			"a.courseTypeComponentModel.component in (1,3) order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryCS(List<String> registerNumber, String courseCode);
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) "+
					"and a.COURSE_CODE not in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
					"where COURSE_CODE in (?2)) and a.COURSE_CODE not in (select COURSE_CODE from "+ 
					"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE in (?2)) order by a.COURSE_CODE", 
					nativeQuery=true)
	List<Object[]> findStudentHistoryCS2(List<String> registerNumber, List<String> courseCode);
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) "+
					"and a.COURSE_CODE not in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
					"where COURSE_CODE in (?2)) and a.COURSE_CODE not in (select COURSE_CODE from "+ 
					"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE not in "+
					"(select b.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 "+
					"and a.CATALOG_TYPE='CC' and a.COURSE_CATEGORY in ('PC','UC') and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID) and a.COURSE_CODE not in (select c.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 "+
					"and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY in ('PC','UC') and "+
					"a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS3(List<String> registerNumber, List<String> courseCode, Integer specializationId, 
						Integer studentYear, Float curriculumVersion);
	
	/*@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ') and a.COURSE_CODE=b.CODE and "+
					"a.COURSE_CODE not in (select distinct COURSE_CODE from academics.STUDENT_HISTORY where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) "+
					"and a.COURSE_CODE not in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
					"where COURSE_CODE in (?2)) and a.COURSE_CODE not in (select COURSE_CODE from "+ 
					"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE not in "+
					"(select b.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 "+
					"and a.CATALOG_TYPE='CC' and a.COURSE_CATEGORY in ('PC','UC','PE','BC') and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID) and a.COURSE_CODE not in (select c.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 "+
					"and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY in ('PC','UC','PE','BC') "+
					"and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS4(List<String> registerNumber, List<String> courseCode, 
						Integer specializationId, Integer studentYear, Float curriculumVersion);*/
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) "+
					"and a.COURSE_CODE not in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
					"where COURSE_CODE in (?2)) and a.COURSE_CODE not in (select COURSE_CODE from "+ 
					"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE not in "+
					"(select b.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) and "+
					"a.COURSE_CODE not in (select c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 "+
					"and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 "+
					"and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) "+
					"order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS4(List<String> registerNumber, List<String> courseCode, 
						Integer specializationId, Integer studentYear, Float curriculumVersion);
	
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in (select distinct COURSE_CODE from "+
					"academics.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in "+
					"('S','A','B','C','D','E','U','P','Pass')) and a.COURSE_CODE not in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE in (?2)) "+
					"and a.COURSE_CODE not in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+
					"EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE in "+
					"(select c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 "+
					"and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY=?6 and "+
					"a.COURSE_BASKET_ID=?7 and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findCSCourseByCourseCategoryAndBasketId(List<String> registerNumber, List<String> courseCode, 
						Integer specializationId, Integer studentYear, Float curriculumVersion, String courseCategory, 
						String basketId);
	
	
	@Query("select a from StudentHistoryModel a where (a.studentHistoryPKId.registerNumber in ?1) "+
			"and a.grade in ('Fail','F') and a.courseTypeComponentModel.component in (1,3) "+
			"order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryFailCourse(List<String> registerNumber);
	
	@Query("select nvl(sum(a.credit),0) as FCredits from StudentHistoryModel a where "+
			"(a.studentHistoryPKId.registerNumber in ?1) and a.grade in ('Fail','F') "+
			"and a.courseTypeComponentModel.component in (1,3)")
	Integer findStudentHistoryFailCourseCredits(List<String> registerNumber);
	
	/*@Query("select nvl(sum(a.credit),0) as FCredits from StudentHistoryModel a where "+
			"(a.studentHistoryPKId.registerNumber in ?1) and a.grade in ('Fail','F','N','N1','N2','N3','N4') "+
			"and a.courseTypeComponentModel.component in (1,3)")*/
	@Query("select nvl(sum(a.credit),0) as FCredits from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.grade in ('Fail','F','N','N1','N3') "+
			"and a.courseTypeComponentModel.component in (1,3)")
	Integer findStudentHistoryFailCourseCredits2(List<String> registerNumber);
		
	@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, decode(GRADE,'S',1,'U',2,'P',3,"+
				"'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
				"'W',14,'WWW',15,'AAA',16,17) as grade_order, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, academics.COURSE_TYPE_COMPONENT_MASTER b "+ 
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where COURSE_CODE=?2 order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryGrade2(List<String> registerNumber, String courseCode);
	
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, to_char(nvl(a.EXAM_MONTH,SYSDATE),'DD-MON-YYYY') as examMonth, "+
				"decode(a.GRADE,'S',1,'U',2,'P',3,'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,"+
				"'N',13,'N1',13,'N2',13,'N3',13,'N4',13,'W',14,'WWW',15,'AAA',16,17) as grade_order from "+
				"academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_CODE=?2 and b.COMPONENT in (1,3) "+ 
				"and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryGrade3(List<String> registerNumber, String courseCode);
	
	@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, decode(GRADE,'S',1,'U',2,'P',3,"+
				"'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
				"'W',14,'WWW',15,'AAA',16,17) as grade_order, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, academics.COURSE_TYPE_COMPONENT_MASTER b "+ 
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where (COURSE_CODE in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
				"where COURSE_CODE=?2) or COURSE_CODE in (select COURSE_CODE from academics.COURSE_EQUIVALANCES "+
				"where EQUIVALENT_COURSE_CODE=?2)) order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade3(List<String> registerNumber, String courseCode);
	
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, to_char(nvl(a.EXAM_MONTH,SYSDATE),'DD-MON-YYYY') as examMonth, "+
				"decode(a.GRADE,'S',1,'U',2,'P',3,'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,"+
				"'N',13,'N1',13,'N2',13,'N3',13,'N4',13,'W',14,'WWW',15,'AAA',16,17) as grade_order from "+
				"academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b where "+
				"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and (a.COURSE_CODE in "+
				"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE=?2) "+
				"or a.COURSE_CODE in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+
				"EQUIVALENT_COURSE_CODE=?2)) order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade4(List<String> registerNumber, String courseCode);

	
	@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, "+
				"academics.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where GRADE in ('Fail','F','Y','N1','N2','N3','N4') order by COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryFailCourse2(List<String> registerNumber);
	
		
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
					"as GEN_COURSE_TYPE from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.GRADE in ('Fail','F','Y','N','N1','N2','N3','N4') "+
					"and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE order by a.COURSE_CODE", 
					nativeQuery=true)
	List<Object[]> findStudentHistoryGIAndFailCourse(List<String> registerNumber);
	
	@Query(value="select distinct a.CRSTYPCMPNTMASTER_COURSE_TYPE from academics.STUDENT_HISTORY a, "+
					"academics.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CATALOG_COURSE_ID=?2 and trunc(EXAM_MONTH)=to_date(?3,'DD-MON-YYYY') and "+
					"a.GRADE in ('Y','N1','N2','N3','N4') and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE "+
					"and b.COMPONENT=2 order by a.CRSTYPCMPNTMASTER_COURSE_TYPE desc", nativeQuery=true)
	List<String> findStudentHistoryFailComponentCourseType(List<String> registerNumber, String courseId, 
						String examMonth);
	
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
			"as GEN_COURSE_TYPE from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
			"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_CATALOG_COURSE_ID=?2 and "+
			"trunc(EXAM_MONTH)=to_date(?3,'DD-MON-YYYY') and a.GRADE in ('N2') and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE order by a.CRSTYPCMPNTMASTER_COURSE_TYPE desc", 
			nativeQuery=true)
	List<Object[]> findStudentHistoryNotAllowedGrade(List<String> registerNumber, String courseId, String examMonth);
	
	//For Arrears Registration
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and code=?2 "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, START_DATE from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and code=?2 "+
					"order by START_DATE desc, SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode2(List<String> registerNumber, 
						String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and REGISTRATION_TYPE not in ('FAR_RFAT','RAR_RFAT') "+
					"and code=?2 order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);

		
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, CODE as course_code from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and (CODE in (select EQUIVALENT_COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, CODE as course_code, START_DATE from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and (code in (select EQUIVALENT_COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by START_DATE desc, SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode2(List<String> registerNumber, 
						String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, CODE as course_code from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and REGISTRATION_TYPE not in ('FAR_RFAT','RAR_RFAT') "+
					"and (code in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) "+
					"or CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);
	
	@Query(value="select distinct COURSE_CODE from ACADEMICS.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and COURSE_CATALOG_COURSE_ID in (select EQUIVALANCE_COURSE_ID from ACADEMICS.COURSE_EQUIVALANCE_REG "+
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER in (?2)) "+
					"order by COURSE_CODE", nativeQuery=true)
	List<String> findCSCourseCodeByRegisterNo(String semesterSubId, List<String> registerNumber);
	
	@Query(value="select distinct COURSE_CODE from ACADEMICS.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and COURSE_CATALOG_COURSE_ID in (select EQUIVALANCE_COURSE_ID from ACADEMICS.COURSE_EQUIVALANCE_REG "+
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"COURSE_CATALOG_COURSE_ID in (?3)) order by COURSE_CODE", nativeQuery=true)
	List<String> findCSCourseCodeByRegisterNoAndCourseId(String semesterSubId, List<String> registerNumber, 
					List<String> courseId);
	
	
	//For Course Change or Course Substitution history
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"PRE_CRSCATALOG_COURSE_CODE=?2", nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode(String registerNumber, String courseCode);
	
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and PRE_CRSCATALOG_COURSE_CODE=?2", nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode2(List<String> registerNumber, 
						String courseCode);
	
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and PRE_CRSCATALOG_COURSE_CODE=?2 and COURSE_CHANGE_OPTION not in ('CVCC','CVNN')", 
					nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);
	
		
	//Procedure
	//To insert the fresh Data in Student History from Examination Schema
	@Procedure(name="student_history_insert_process", procedureName="EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS")
	String acad_student_history_insert_process(String pRegisterNumber, String pCourseSystem);
	
	//Method 2:  To insert the fresh Data in Student History from Examination Schema with N grade concept
	@Procedure(name="student_history_insert_process2", procedureName="EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS_N")
	String acad_student_history_insert_process2(String pRegisterNumber, String pCourseSystem);
		
		
	//To get the CAL Student CGPA & its related details from Examination Schema
	@Procedure(name="student_cgpa", procedureName="examinations.cgpa_cal")
	String student_cgpa_cal(String pRegisterNumber, String cgp1, String cgpa2, Date fromDate, Date toDate, 
				Integer specId);
	
	//To get the CAL Student CGPA & its related details from Examination Schema
	@Procedure(name="student_cgpa", procedureName="examinations.cgpa_ncal")
	String student_cgpa_ncal(String pRegisterNumber, String cgp1, String cgpa2, Date fromDate, Date toDate, 
				Integer specId);
	
	//Research Queries
	/*@Query(value="select distinct MEET_STATUS from RESEARCH.RESEARCH_MEETING where STDNTSLGNDTLS_REGISTER_NUMBER=?1 "+
					"and MEET_STATUS=1", nativeQuery=true)
	Integer findRPApprovalStatusByRegisterNumber(String registerNumber);*/
	
	@Query(value="select MEET_STATUS from RESEARCH.RESEARCH_MEETING where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"RSRCHMEETINGTYPE_MEET_TYPE_ID=1 and MEET_STATUS=1", nativeQuery=true)
	Integer findRPApprovalStatusByRegisterNumber(String registerNumber);
	
	@Query(value="select distinct b.CODE from RESEARCH.COURSEWORK_OPTION_ALLOCATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER=?1 and a.COURSE_TYPE='RHR' and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID order by b.CODE", nativeQuery=true)
	List<String> findRPCourseWorkByRegisterNumber(String registerNumber);
}