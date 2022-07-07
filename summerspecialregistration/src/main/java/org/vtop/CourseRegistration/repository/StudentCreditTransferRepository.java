package org.vtop.CourseRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.StudentCreditTransferModel;


@Repository
public interface StudentCreditTransferRepository extends JpaRepository<StudentCreditTransferModel,String> {
	
	@Query("select a from StudentCreditTransferModel a where a.registerNumber=?1")
	StudentCreditTransferModel findByRegisterNumber(String registerNumber);
	
	@Query(value="select OLD_REGISTER_NUMBER from ACADEMICS.STUDENT_CREDIT_TRANSFER "+
			"where STDNTSLGNDTLS_REGISTER_NUMBER=?1", nativeQuery=true)
	String findOldRegisterNumberByRegisterNumber(String registerNumber);

}
