package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseOptionModel;


@Repository
public interface CourseOptionRepository extends JpaRepository<CourseOptionModel, String>
{
	@Query("select a from CourseOptionModel a where a.code=?1")
	CourseOptionModel findOne(String code);
	
	@Query("select a from CourseOptionModel a order by a.code")
	List<CourseOptionModel> findAll();
	
	@Query("select a from CourseOptionModel a where a.code in (?1) order by a.code")
	List<CourseOptionModel> findByOptionCode(List<String> code);
	
	@Query("select a from CourseOptionModel a where a.code in (?1) and a.status in (?2) "+
			"order by a.code")
	List<CourseOptionModel> findByOptionCodeAndStatus(List<String> code, List<Integer> lockStatus);
}
