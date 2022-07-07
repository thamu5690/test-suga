package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseTypeMasterModel;
import org.vtop.CourseRegistration.model.CourseTypeMasterModelPK;

@Repository
public interface CourseTypeMasterRepository extends JpaRepository<CourseTypeMasterModel,CourseTypeMasterModelPK>{

	@Query("select a from CourseTypeMasterModel a where a.ctmpk.ccGenericType=?1 "+
			"order by a.ctmpk.cCCourseType") 
	List<CourseTypeMasterModel> findByCrsTypCmpntMstrCourseType(String courseGenericType);
	
	/*@Query("select a.ctmpk.cCCourseType from CourseTypeMasterModel a where a.ctmpk.ccGenericType=?1 "+
			"order by a.ctmpk.cCCourseType desc")*/ 
	@Query("select a.ctmpk.cCCourseType from CourseTypeMasterModel a where a.ctmpk.ccGenericType=?1 "+
			"order by decode(a.ctmpk.cCCourseType,'ETH',2,'ELA',3,'EPJ',4,1)")
	List<String> findCourseTypeComponent(String courseGenericType);
	
	@Query("select a from CourseTypeMasterModel a where a.ctmpk.ccGenericType=?1 and "+
			"a.ctmpk.cCCourseType in (?2) order by a.ctmpk.cCCourseType") 
	List<CourseTypeMasterModel> findByCrsTypCmpntMstrCourseType2(String courseGenericType, 
			String[] courseType);
	
}
