package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.BasketDetailsModel;

@Repository
public interface BasketDetailsRepository extends JpaRepository<BasketDetailsModel, String>
{
	@Query("select a from BasketDetailsModel a order by a.code, a.basketVersion")
	List<BasketDetailsModel> findAll();
	
	@Query("select a from BasketDetailsModel a where a.code=?1 and a.basketVersion=?2")
	BasketDetailsModel findByBasketCodeAndVersion(String code, float basketVersion);
}
