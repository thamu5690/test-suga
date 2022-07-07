package org.vtop.CourseRegistration.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.BasketCourseCatalogModel;
import org.vtop.CourseRegistration.model.BasketCourseCatalogPK;

@Repository
public interface BasketCourseCatalogRepository extends JpaRepository<BasketCourseCatalogModel, BasketCourseCatalogPK>
{	
	@Query("select a from BasketCourseCatalogModel a order by a.bccPkId.basketId, a.bccPkId.courseId")
	List<BasketCourseCatalogModel> findAll();
	
	@Query("select a from BasketCourseCatalogModel a where a.bccPkId.basketId=?1 order by a.bccPkId.courseId")
	List<BasketCourseCatalogModel> findByBasketId(String basketId);
	
	@Query("select a.courseCatalogModel.code from BasketCourseCatalogModel a where a.bccPkId.basketId=?1 "+
			"and a.status=0 order by a.bccPkId.courseId")
	List<String> findCourseCodeByBasketId(String basketId);
}
