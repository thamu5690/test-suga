package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeGroupCurriculumCreditModel;
import org.vtop.CourseRegistration.model.ProgrammeGroupCurriculumCreditPKModel;

@Repository
public interface ProgrammeGroupCurriculumCreditRepository extends JpaRepository<ProgrammeGroupCurriculumCreditModel, ProgrammeGroupCurriculumCreditPKModel> {
	
	@Query("select a from ProgrammeGroupCurriculumCreditModel a order by a.pgcccPkId.groupId, a.pgcccPkId.admissionYear")
	List<ProgrammeGroupCurriculumCreditModel> findAll();
	
	@Query("select a from ProgrammeGroupCurriculumCreditModel a where "+
			"a.pgcccPkId.groupId=?1 order by a.pgcccPkId.admissionYear desc")
	List<ProgrammeGroupCurriculumCreditModel> findByGroupId(Integer groupId);

}
