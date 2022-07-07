package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.SemesterMasterModel;

@Repository
public interface SemesterMasterRepository extends JpaRepository<SemesterMasterModel,Integer> {

	List<SemesterMasterModel> findAll();	
	List<SemesterMasterModel> findByStatus(int status);
	public List<SemesterMasterModel> findAllByOrderBySemesterIdAsc();	
	public List<SemesterMasterModel> findAllByOrderBySemesterIdDesc();
	
}