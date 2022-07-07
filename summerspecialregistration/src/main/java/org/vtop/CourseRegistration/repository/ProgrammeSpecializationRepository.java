package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationModel;

@Repository
public interface ProgrammeSpecializationRepository extends JpaRepository<ProgrammeSpecializationModel,Integer> {
	
	@Query("select nvl(max(a.specializationId),0)+1 from ProgrammeSpecializationModel a")
	Integer getMaxSpecializationId();

	@Query("select a from ProgrammeSpecializationModel a order by a.programmeGroupModel.code, "+
			"a.programmeGroupModel.programmeMode, a.programmeGroupModel.programmeDuration, "+
			"a.programmeGroupModel.programmeLevel, a.code")
	List<ProgrammeSpecializationModel> findAll();
	
	@Query("select a from ProgrammeSpecializationModel a where a.status=0 order by "+
			"a.programmeGroupModel.code, a.programmeGroupModel.programmeMode, "+
			"a.programmeGroupModel.programmeDuration, a.programmeGroupModel.programmeLevel, a.code")
	List<ProgrammeSpecializationModel> findByStatusOpen();
			
	@Query("select a from ProgrammeSpecializationModel a where a.groupId=?1 "+
			"order by a.programmeGroupModel.code, a.programmeGroupModel.programmeMode, "+
			"a.programmeGroupModel.programmeDuration, a.programmeGroupModel.programmeLevel, a.code")
	List<ProgrammeSpecializationModel> findByGroupId(Integer groupId);
	
	@Query("select a from ProgrammeSpecializationModel a where a.groupId=?1 and a.status=0 "+
			"order by a.programmeGroupModel.code, a.programmeGroupModel.programmeMode, "+
			"a.programmeGroupModel.programmeDuration, a.programmeGroupModel.programmeLevel, a.code")
	List<ProgrammeSpecializationModel> findByGroupIdStatusOpen(Integer groupId);
	
	ProgrammeSpecializationModel findByGroupIdAndCode(Integer groupId, String code);
	
	ProgrammeSpecializationModel findBySpecializationIdAndCode(Integer specializationId, String code);
		
	@Query("select a from ProgrammeSpecializationModel a order by a.programmeGroupModel.code, "+
			"a.programmeGroupModel.programmeMode, a.programmeGroupModel.programmeDuration, "+
			"a.programmeGroupModel.programmeLevel, a.code")
	List<ProgrammeSpecializationModel> listByOrder();
}
