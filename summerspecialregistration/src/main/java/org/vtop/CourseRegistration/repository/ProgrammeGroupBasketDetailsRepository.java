package org.vtop.CourseRegistration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeGroupBasketDetailsModel;
import org.vtop.CourseRegistration.model.ProgrammeGroupBasketDetailsPK;

@Repository
public interface ProgrammeGroupBasketDetailsRepository extends JpaRepository<ProgrammeGroupBasketDetailsModel, ProgrammeGroupBasketDetailsPK  > {
	
	@Query("select a from ProgrammeGroupBasketDetailsModel a where a.pgbPkId.groupId=?1 "+
			"order by a.pgbPkId.basketId")
	List<ProgrammeGroupBasketDetailsModel> findByProgGroup(Integer groupId);
	
	List<ProgrammeGroupBasketDetailsModel> findAll();
	
}
