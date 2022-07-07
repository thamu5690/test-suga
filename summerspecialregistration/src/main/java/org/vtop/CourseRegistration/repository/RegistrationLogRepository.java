package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.RegistrationLogModel;


@Repository
@Transactional
public interface RegistrationLogRepository extends JpaRepository<RegistrationLogModel,String> {

	@Modifying
	@Query("update RegistrationLogModel a set a.logstatus =1, a.activeTimestamp =systimestamp,"
			+ " a.loginTimestamp =systimestamp,  a.loginIpaddress =?1	where a.regNo =?2")
	public void UpdateLoginTimeStamp(String ipAddress, String registerNumber);
	
	
	@Modifying
	@Query("update RegistrationLogModel a set a.logstatus =2, a.logoutTimestamp =systimestamp,"
			+ " a.logoutIpaddress=?1 where a.regNo =?2")
	public void UpdateLogoutTimeStamp(String ipAddress, String registerNumber);
	
	@Query(value="select to_char(ACTIVE_TIMESTAMP,'DD-MON-YYYY HH24:MI:SS') as active_tmstp, "+
					"LOG_STATUS, to_char(SYSTIMESTAMP,'DD-MON-YYYY HH24:MI:SS') as current_tmstp "+
					"from ACADEMICS.REGISTRATION_LOG where REGNO=?1", nativeQuery=true)
	List<Object[]> findRegistrationLogByRegisterNumber(String registerNumber);

	@Modifying
	@Query(value="INSERT INTO ACADEMICS.REGISTRATION_LOG (REGNO, LOG_STATUS, LOGIN_TIMESTAMP, "+
					"LOGIN_IPADDRESS, ACTIVE_TIMESTAMP) VALUES (?1, 1, SYSTIMESTAMP, ?2, "+
					"SYSTIMESTAMP)", nativeQuery=true)
	void AddRegistrationLog(String registerNumber, String ipAddress);

	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET ACTIVE_TIMESTAMP=SYSTIMESTAMP where "+
					"REGNO=?1 AND LOG_STATUS=1", nativeQuery=true)
	void UpdateActiveTimeStamp(String registerNumber);

	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET LOG_STATUS=1, ACTIVE_TIMESTAMP=SYSTIMESTAMP, "+
					"LOGIN_TIMESTAMP=SYSTIMESTAMP, LOGIN_IPADDRESS=?1 where REGNO=?2", nativeQuery=true)
	void UpdateLoginTimeStamp2(String ipAddress, String registerNumber);

	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET LOG_STATUS=2, LOGOUT_TIMESTAMP=SYSTIMESTAMP, "+
					"LOGOUT_IPADDRESS=?1 where REGNO=?2", nativeQuery=true)
	void UpdateLogoutTimeStamp2(String ipAddress, String registerNumber);
	
	@Modifying
	@Query(value="INSERT INTO ACADEMICS.ERROR_LOG (EXCEPTION_MESSAGE, ERROR_DATETIME, VERTICAL_NAME, "+
					"PACKAGE_NAME, PROGRAMME_NAME, LOG_USERID, LOG_IPADDRESS) VALUES (?1, SYSTIMESTAMP, "+
					"?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
	void InsertErrorLog(String exceptionMessage, String verticalName, String packageName, String programName, 
						String userId, String ipAddress);
	
	@Query(value="select LOG_STATUS, to_number(hrs||mts||scs) as time_diff from "+
					"(select LOG_STATUS, (case when (hrs < 10) then '0'||to_char(hrs) else to_char(hrs) end) as hrs, "+
					"(case when (mins < 10) then '0'||to_char(mins) else to_char(mins) end) as mts, "+
					"(case when (secs < 10) then '0'||to_char(secs) else to_char(secs) end) as scs from "+
					"(select LOG_STATUS, to_number(extract(hour from (systimestamp - ACTIVE_TIMESTAMP))) as hrs, "+
					"to_number(extract(minute from (systimestamp - ACTIVE_TIMESTAMP))) as mins, "+
					"trunc(to_number(extract(second from (systimestamp - ACTIVE_TIMESTAMP)))) as secs "+
					"from ACADEMICS.REGISTRATION_LOG where REGNO=?1))", nativeQuery=true)
	List<Object[]> findRegistrationLogTimeDifference(String registerNumber);
	
	@Query(value="select REASON_TYPE from ACADEMICS.REGISTRATION_EXEMPTION_DETAIL where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+ 
					"and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and LOCK_STATUS=0", nativeQuery=true)
	Integer findRegistrationExemptionReasonTypeBySemesterSubIdAndRegisterNumber(String semesterSubId, 
					String registerNumber);
}
