package org.vtop.CourseRegistration.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RegistrationModelPermit;
import org.vtop.CourseRegistration.model.RegistrationModelPermitPK;


@Repository
@Transactional
public interface RegistrationPermitRepository extends JpaRepository<RegistrationModelPermit, 
						RegistrationModelPermitPK>
{
	@Query("select a from RegistrationModelPermit a where a.id.semesterSubId=?1 and "+
			"a.id.registerNumber=?2 and a.id.classGroupId=?3 and status=0 "+
			"order by a.id.semesterSubId, a.id.registerNumber")
	RegistrationModelPermit findByPermitRegisterNo(String semSubId, String registerNumber, 
								String classGroupId);
	
	@Query("select a from RegistrationModelPermit a where a.id.semesterSubId=?1 and "+
			"a.id.registerNumber=?2 and a.id.classGroupId in (?3) and status=0 "+
			"order by a.id.semesterSubId, a.id.registerNumber")
	RegistrationModelPermit findByPermitRegisterNo2(String semSubId, String registerNumber, 
								List<String> classGroupId);
	
	@Query(value="select to_char(PERMIT_DATE,'DD-MON-YYYY') as start_date, FROM_TIME, TO_TIME, "+ 
					"to_char((to_date(FROM_TIME,'HH24:MI:SS') - (5/1440)), 'HH24:MI:SS') as ALLOW_FROM_TIME, "+ 
					"LOCK_STATUS, PERMIT_DATE from ACADEMICS.REGISTRATION_PERMIT where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+ 
					"and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and CLSSGRP_MASTER_CLASS_GROUP_ID in (?3) "+
					"order by PERMIT_DATE desc", nativeQuery=true)
	List<Object[]> findByRegisterNumber(String semSubId, String registerNumber, List<String> classGroupId);
}
