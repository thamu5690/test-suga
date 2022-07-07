package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.BridgeCourseConditionDetailModel;


@Repository
public interface BridgeCourseConditionDetailRepository extends JpaRepository<BridgeCourseConditionDetailModel,Integer> {
	
	//For EPT Result
	@Query(value="select nvl(result,'NONE') as ept_result from ACADEMICS.STUDENT_EPT_DETAILS where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1)", nativeQuery=true)
	String findEPTResultByRegisterNumber(List<String> registerNumber);
	
	//For PCMB status from Academics Schema (i.e. PCMB, PCM, PCB, etc.)
	@Query(value="select nvl(HSC_GROUP,'NONE') as hsc_grp from ACADEMICS.STUDENT_BRIDGE_COURSE_DETAIL "+
					"where REGNO in (?1)", nativeQuery=true)
	String findPCMBStatusByRegisterNumber(List<String> registerNumber);
	
	//For PCMB status from Admissions Schema (i.e. PCMB, PCM, PCB, etc.)
	@Query(value="select nvl(b.ELIGIBILITY_CODE,'NONE') as egb_code from ADMISSIONS.STUDENTS_LOGIN_DETAILS a, "+
					"ADMISSIONS.STUDENT_BASE b where a.REG_NO=?1 and a.APPLICATION_NO=b.APPLICATION_NUMBER", 
					nativeQuery=true)
	String findPCMBStatusFromAdmissionsByRegisterNumber(String registerNumber);

	//For SPT Result
	@Query(value="select MARK, STUDENT_SEMESTER from ACADEMICS.STUDENT_SPT_DETAILS where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and STUDENT_SEMESTER=?2", nativeQuery=true)
	List<Object[]> findSPTResultByRegisterNumberAndSemester(List<String> registerNumber, Integer studentSemester);
	
	@Query(value="select nvl(MARK,0) as spt_mark from ACADEMICS.STUDENT_SPT_DETAILS where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and STUDENT_SEMESTER=?2", nativeQuery=true)
	Integer findSPTResultByRegisterNumberAndSemester2(List<String> registerNumber, Integer studentSemester);
	
	@Query(value="select MARK, RESULT from ACADEMICS.STUDENT_EPT_DETAILS where STDNTSLGNDTLS_REGISTER_NUMBER "+
					"in (?1)", nativeQuery=true)
	List<Object[]> findEPTDetailByRegisterNumber(List<String> registerNumber);
}
