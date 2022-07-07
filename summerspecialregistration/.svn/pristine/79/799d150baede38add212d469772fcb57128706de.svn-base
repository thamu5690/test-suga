package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationModel;
import org.vtop.CourseRegistration.repository.ProgrammeSpecializationRepository;


/*@EnableJpaRepositories(basePackageClasses={ProgrammeSpecializationRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ProgrammeSpecializationService {
		
	@Autowired private ProgrammeSpecializationRepository programmeSpecializationRepository;
	
		
	public ProgrammeSpecializationModel getByGroupIdAndCode(Integer groupId, String code)
	{
		return programmeSpecializationRepository.findByGroupIdAndCode(groupId, code);
	}
	
	public ProgrammeSpecializationModel getBySpecializationIdAndCode(Integer specializationId, String code)
	{
			/*System.out.println( specializationId + "   -   " + code );*/
		return programmeSpecializationRepository.findBySpecializationIdAndCode(specializationId, code);
	}
		
	public List<ProgrammeSpecializationModel>getAll()
	{
		return programmeSpecializationRepository.listByOrder();
	}
	
	public ProgrammeSpecializationModel getOne(Integer specId)
	{
		return programmeSpecializationRepository.findOne(specId);
	}
	
	public List<ProgrammeSpecializationModel> getProgrammeSpecializationByGroupId(Integer groupId)
	{
		return programmeSpecializationRepository.findByGroupId(groupId);
	}
}
