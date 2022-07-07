package org.vtop.CourseRegistration.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, String>{
	
	List<Campus> findAll();
	
	@Query("SELECT c.code, c.campusName FROM Campus c order by c.code")
	List<Campus> findForMenu();
	
}


