package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.vtop.CourseRegistration.model.EmployeeProfile;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile ,String> {

	@Query ("select a from EmployeeProfile a where a.employmentType='FACULTY' and a.centreId=?1 and "+
			"a.campusCode='CHN' and a.categoryId in (1) and a.employmentStatus in (0) and a.leaveStatus "+
			"in (0) and a.employeeId not in ('ACAD','COE01','PAT','ETHNUS','FACE','SMART','CHANCEL') and "+
			"(a.employeeId not like 'T%') order by a.firstName, a.employeeId")
	public List<EmployeeProfile> findByCentreId(Integer costCentreId);
		
	@Query ("select distinct a.centreId, a.costCentre.code, a.costCentre.description from EmployeeProfile a "+
			"where a.campusCode=?1 and a.employmentType='FACULTY' and a.categoryId in (1) and "+
			"a.employmentStatus in (0) and a.leaveStatus in (0) and a.employeeId not in ('ACAD','COE01','PAT',"+
			"'ETHNUS','FACE','SMART','CHANCEL') and (a.employeeId not like 'T%') order by a.costCentre.code")
	public List<Object[]> findFacultyCostCentre(String campusCode);
}
