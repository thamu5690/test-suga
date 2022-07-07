package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeGroupModel;

@Repository
public interface ProgrammeGroupRepository extends JpaRepository<ProgrammeGroupModel,Integer>,PagingAndSortingRepository<ProgrammeGroupModel,Integer> {
	
	@Query("select nvl(max(a.groupId),0)+1 from ProgrammeGroupModel a")
	Integer getMaxGroupId();
	
	@Query("select a from ProgrammeGroupModel a order by a.code, a.programmeMode, "+
			"a.programmeDuration, a.programmeLevel")
	public List<ProgrammeGroupModel> findAll();
		
	@Query("select a from ProgrammeGroupModel a where a.status=0 order by a.code, "+
			"a.programmeMode, a.programmeDuration, a.programmeLevel")
	public List<ProgrammeGroupModel> findByStatus();
		
}
