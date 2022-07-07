package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseTypeComponentModel;

@Repository
public interface CourseTypeComponentRepository extends JpaRepository<CourseTypeComponentModel,String> {

		List<CourseTypeComponentModel> findAll();
		
		List<CourseTypeComponentModel> findByComponent(int comp);
		
}
