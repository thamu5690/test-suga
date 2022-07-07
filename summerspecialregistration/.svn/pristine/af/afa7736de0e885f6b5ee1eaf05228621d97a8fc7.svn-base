package org.vtop.CourseRegistration.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CostCentre;

@Repository
public interface CostCentreRepository extends JpaRepository<CostCentre, Integer>{
	
	@Query("select a from CostCentre a order by a.centreType, a.description")
	List<CostCentre> findAll();
	
	@Modifying
	@Query("select a from CostCentre a where a.campus.code=?1 and a.lockStatus in ?2 "+
			"order by a.centreType, a.description")
	List<CostCentre> findByCostCentreCampusStatus(String campus, Integer[] status);
	
	@Modifying
	@Query("select a from CostCentre a where a.campus.code=?1 and a.centreType in ?2 and "+
			"a.lockStatus in ?3 order by a.centreType, a.description")
	List<CostCentre> findByCostCentreCampusCentreStatus(String campus, String[] centreType, Integer[] status);
	
	@Modifying
	@Query("select a from CostCentre a where a.centreId in ?1 and a.campus.code=?2 and "+
			"a.lockStatus in ?3 order by a.centreType, a.description")
	List<CostCentre> findByCostCentreCentreIdCampusStatus(Integer[] centreId, String campus, Integer[] status);
	
	@Modifying
	@Query("select a from CostCentre a where (a.centreId in ?1 or a.centreType in ?2) and "+
			"a.campus.code=?3 and a.lockStatus in ?4 order by a.centreType, a.description")
	List<CostCentre> findByCostCentreCentreIdOrTypeCampusStatus(Integer[] centreId, String[] centreType, String campus, Integer[] status);

}
