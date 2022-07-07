package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RegistrationScheduleModel;


@Repository
public interface RegistrationScheduleRepository extends JpaRepository<RegistrationScheduleModel, String>
{
	@Query(value="select to_char(reg_date,'DD-MON-YYYY') as regdate, FROM_TIME, TO_TIME, "+
					"to_char((to_date(FROM_TIME,'HH24:MI:SS') - (5/1440)), 'HH24:MI:SS') as ALLOW_FROM_TIME, "+
					"STATUS from ACADEMICS.REGISTRATION_SCHEDULE where REGNO=?1", nativeQuery=true)
	List<Object[]> findByRegisterNumber(String registerNumber);
}
