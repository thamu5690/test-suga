package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeGroupCurriculumCreditModel;
import org.vtop.CourseRegistration.model.ProgrammeGroupCurriculumCreditPKModel;
import org.vtop.CourseRegistration.repository.ProgrammeGroupCurriculumCreditRepository;

/*@EnableJpaRepositories(basePackageClasses={ProgrammeGroupCurriculumCreditRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ProgrammeGroupCurriculumCreditService {
		
	@Autowired private ProgrammeGroupCurriculumCreditRepository programmeGroupCurriculumCreditRepository;
	
				
	public ProgrammeGroupCurriculumCreditModel getOne(ProgrammeGroupCurriculumCreditPKModel programmeGroupCurriculumCreditPKModel)
	{
		return programmeGroupCurriculumCreditRepository.findOne(programmeGroupCurriculumCreditPKModel);
	}
		
	public List<ProgrammeGroupCurriculumCreditModel> getAll()
	{
		return programmeGroupCurriculumCreditRepository.findAll();
	}
	
	public List<ProgrammeGroupCurriculumCreditModel> getByGroupId(Integer groupId)
	{
		return programmeGroupCurriculumCreditRepository.findByGroupId(groupId);
	}
}
