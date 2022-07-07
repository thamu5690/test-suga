package org.vtop.CourseRegistration.repository.estates;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RoomCategory;

@Repository
public interface RoomCategoryRepository extends CrudRepository<RoomCategory, Long> {

	List<RoomCategory> findAll();
	
	@Query("SELECT r.roomCategoryId,r.code FROM RoomCategory r")
	List<RoomCategory> findAllCategories();

	@Query("SELECT r.code FROM RoomCategory r WHERE r.roomCategoryId=?1")
	String findCodeByCategoryId(Long roomCategoryId);

}
