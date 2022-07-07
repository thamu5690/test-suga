package org.vtop.CourseRegistration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseRegistrationWaitingMoveModel;
import org.vtop.CourseRegistration.model.CourseRegistrationWaitingMovePKModel;

@Repository
public interface CourseRegistrationWaitingMoveRepository extends JpaRepository<CourseRegistrationWaitingMoveModel, CourseRegistrationWaitingMovePKModel> {
	
	@Modifying
	@Transactional
	@Query(value="insert into academics.COURSE_REGISTRATION_WAIT_MOVE (select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
			"a.COURSE_ALLOCATION_CLASS_ID, a.STDNTSLGNDTLS_REGISTER_NUMBER, a.COURSE_CATALOG_COURSE_ID, "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.COURSE_OPTION_MASTER_CODE, a.REGISTRATION_STATUS_NUMBER, "+
			"a.REGISTRATION_COMPONENT_TYPE, ?4, a.LOG_TIMESTAMP, a.LOG_IPADDRESS, SYSTIMESTAMP, ?5 from "+
			"academics.COURSE_REGISTRATION_WAITING a where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=?3)", nativeQuery=true)
	void insertWaitingToWaitingMove(String semesterSubId, String registerNumber, String courseId, 
				int waitStatus, String ipaddress);

}
