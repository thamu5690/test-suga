package org.vtop.CourseRegistration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.StudentsLoginDetailsModel;

@Repository
public interface StudentLoginDetailsRepository extends JpaRepository<StudentsLoginDetailsModel, String>
{
	StudentsLoginDetailsModel findByRegNoAndCostCentre(String registerNumber, Integer costCentre);
	
	/*@Query(value="select a.REG_NO, a.APPLICATION_NO, b.STUDENT_NAME, b.GENDER, a.PRGSPLPRGRM_SPECIALIZATION_ID "+
					"as prog_spec_id, d.CODE as prog_spec_code, d.DESCRIPTION as prog_spec_desc, "+
					"d.PRGRM_GROUP_PROGRAMME_GROUP_ID as prog_group_id, e.CODE as prog_group_code, e.DESCRIPTION "+
					"as prog_group_desc, e.PROGRAMME_MODE as prog_group_mode, e.PROGRAMME_DURATION as "+
					"prog_group_duration, e.PROGRAMME_LEVEL as prog_group_level, a.COST_CENTRE as centre_id, "+
					"f.CODE as centre_code, f.DESCRIPTION as centre_desc, to_char(a.STUDY_START_DATE,'YYYY') as "+
					"stud_year, nvl(a.STUDY_SYSTEM,'FFCS') as study_system, c.PASSWD, a.EDU_STATUS, a.LOCK_STATUS, "+
					"b.COLLEGE_FEES_FEE_ID "+
					"from ADMISSIONS.STUDENTS_LOGIN_DETAILS a, ADMISSIONS.STUDENT_BASE b, VTOPMASTER.USER_DETAILS c, "+
					"VTOPMASTER.PROGRAMME_SPECIALIZATION d, VTOPMASTER.PROGRAMME_GROUP e, VTOPMASTER.COST_CENTRE f "+
					"where a.REG_NO=?1 and a.STUDY_START_DATE is not null and a.APPLICATION_NO=b.APPLICATION_NUMBER "+
					"and a.REG_NO=c.USERID and a.PRGSPLPRGRM_SPECIALIZATION_ID=d.PROGRAMME_SPECIALIZATION_ID and "+
					"a.COST_CENTRE=f.CENTRE_ID and d.PRGRM_GROUP_PROGRAMME_GROUP_ID=e.PROGRAMME_GROUP_ID "+
					"order by a.REG_NO", nativeQuery=true)
	List<Object[]> findStudentDetailByRegisterNumber(String registerNumber);*/
	
	@Query(value="select a.REG_NO, a.APPLICATION_NO, b.STUDENT_NAME, b.GENDER, a.PRGSPLPRGRM_SPECIALIZATION_ID "+
					"as prog_spec_id, d.CODE as prog_spec_code, d.DESCRIPTION as prog_spec_desc, "+
					"d.PRGRM_GROUP_PROGRAMME_GROUP_ID as prog_group_id, e.CODE as prog_group_code, e.DESCRIPTION "+
					"as prog_group_desc, e.PROGRAMME_MODE as prog_group_mode, e.PROGRAMME_DURATION as "+
					"prog_group_duration, e.PROGRAMME_LEVEL as prog_group_level, a.COST_CENTRE as centre_id, "+
					"f.CODE as centre_code, f.DESCRIPTION as centre_desc, to_char(a.STUDY_START_DATE,'YYYY') as "+
					"stud_year, nvl(a.STUDY_SYSTEM,'FFCS') as study_system, c.PASSWD, a.EDU_STATUS, a.LOCK_STATUS, "+
					"b.COLLEGE_FEES_FEE_ID, g.EDU_EXPN, c.EMAIL, c.MOBILE "+
					"from ADMISSIONS.STUDENTS_LOGIN_DETAILS a, ADMISSIONS.STUDENT_BASE b, VTOPMASTER.USER_DETAILS c, "+
					"VTOPMASTER.PROGRAMME_SPECIALIZATION d, VTOPMASTER.PROGRAMME_GROUP e, VTOPMASTER.COST_CENTRE f, "+
					"VTOPMASTER.EDUCATION_STATUS g where a.REG_NO=?1 and a.STUDY_START_DATE is not null and "+
					"a.APPLICATION_NO=b.APPLICATION_NUMBER and a.REG_NO=c.USERID and "+
					"a.PRGSPLPRGRM_SPECIALIZATION_ID=d.PROGRAMME_SPECIALIZATION_ID and "+
					"a.COST_CENTRE=f.CENTRE_ID and d.PRGRM_GROUP_PROGRAMME_GROUP_ID=e.PROGRAMME_GROUP_ID and "+
					"a.EDU_STATUS=g.EDU_STATUS order by a.REG_NO", nativeQuery=true)
	List<Object[]> findStudentDetailByRegisterNumber(String registerNumber);
	
	@Query(value="SELECT a.FEE_CATEGORY_ID, b.DESCRIPTION as Fee_Category, b.CATEGORY_STUDENT_CATEGORY_ID, "+
					"c.DESCRIPTION as Student_Category FROM FINANCE.COLLEGE_FEES a, FINANCE.FEES_CATEGORY b, "+
					"FINANCE.CATEGORY_STUDENT c where a.FEE_ID=?1 and a.PRGSPL_PRGRM_SPECIALIZATION_ID=?2 and "+
					"a.FEE_CATEGORY_ID=b.FEE_CATEGORY_ID AND b.CATEGORY_STUDENT_CATEGORY_ID=c.CATEGORY_ID", 
					nativeQuery=true)
	List<Object[]> findStudentCategoryByFeeIdAndSpecId(Integer feeId, Integer specId);
	
	@Query(value="select a.REG_NO, a.APPLICATION_NO, b.STUDENT_NAME, b.GENDER, a.PRGSPLPRGRM_SPECIALIZATION_ID "+
			"as prog_spec_id, d.CODE as prog_spec_code, d.DESCRIPTION as prog_spec_desc, "+
			"d.PRGRM_GROUP_PROGRAMME_GROUP_ID as prog_group_id, e.CODE as prog_group_code, e.DESCRIPTION "+
			"as prog_group_desc, e.PROGRAMME_MODE as prog_group_mode, e.PROGRAMME_DURATION as "+
			"prog_group_duration, e.PROGRAMME_LEVEL as prog_group_level, a.COST_CENTRE as centre_id, "+
			"f.CODE as centre_code, f.DESCRIPTION as centre_desc, to_char(a.STUDY_START_DATE,'YYYY') as "+
			"stud_year, nvl(a.STUDY_SYSTEM,'FFCS') as study_system, c.PASSWD, a.EDU_STATUS, a.LOCK_STATUS, "+
			"b.COLLEGE_FEES_FEE_ID, g.EDU_EXPN, c.EMAIL, c.MOBILE "+
			"from VTOPMASTER.USER_DETAILS c, ADMISSIONS.STUDENTS_LOGIN_DETAILS a, ADMISSIONS.STUDENT_BASE b, "+
			"VTOPMASTER.PROGRAMME_SPECIALIZATION d, VTOPMASTER.PROGRAMME_GROUP e, VTOPMASTER.COST_CENTRE f, "+
			"VTOPMASTER.EDUCATION_STATUS g where c.NICK_NAME=?1 and c.USERID=a.REG_NO and "+
			"a.STUDY_START_DATE is not null and a.APPLICATION_NO=b.APPLICATION_NUMBER and "+
			"a.PRGSPLPRGRM_SPECIALIZATION_ID=d.PROGRAMME_SPECIALIZATION_ID and "+
			"a.COST_CENTRE=f.CENTRE_ID and d.PRGRM_GROUP_PROGRAMME_GROUP_ID=e.PROGRAMME_GROUP_ID and "+
			"a.EDU_STATUS=g.EDU_STATUS order by a.REG_NO", nativeQuery=true)
	List<Object[]> findStudentDetailByUserName(String userName);
}
