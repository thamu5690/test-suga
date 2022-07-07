package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RegistrationStatusMasterModel;


@Repository
public interface RegistrationStatusMasterRepository extends JpaRepository<RegistrationStatusMasterModel,Integer>{
	
	List<RegistrationStatusMasterModel> findAll();
	
	List<RegistrationStatusMasterModel> findByStatus(int status);
	
}
