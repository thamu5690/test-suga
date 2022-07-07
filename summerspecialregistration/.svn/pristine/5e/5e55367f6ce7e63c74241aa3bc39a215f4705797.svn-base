package org.vtop.CourseRegistration.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeGroupModel;
import org.vtop.CourseRegistration.repository.ProgrammeGroupRepository;


/*@EnableJpaRepositories(basePackageClasses={ProgrammeGroupRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ProgrammeGroupService {
	
	@Autowired private ProgrammeGroupRepository programmeGroupRepository;
	
		
	public List<ProgrammeGroupModel>getAll()
	{
		return programmeGroupRepository.findAll();
	}
	
	public List<ProgrammeGroupModel>getByStatus(){
		return programmeGroupRepository.findByStatus();
	}
			
	public ProgrammeGroupModel getOne(Integer grpId)
	{
		return programmeGroupRepository.findOne(grpId);
	}

	public Page<ProgrammeGroupModel> findAllPageable(Pageable pageable)
	{
		return programmeGroupRepository.findAll(pageable);
	}
}
