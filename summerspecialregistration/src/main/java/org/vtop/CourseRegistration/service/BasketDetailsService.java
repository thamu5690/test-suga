package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.BasketDetailsModel;
import org.vtop.CourseRegistration.repository.BasketDetailsRepository;

/*@EnableJpaRepositories(basePackageClasses={BasketDetailsRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class BasketDetailsService {
	
	@Autowired private BasketDetailsRepository basketDetailsRepository;
	
	
	public List<BasketDetailsModel>getAll()
	{
		return basketDetailsRepository.findAll();
	}
	
	public BasketDetailsModel getOne(String basketId)
	{
		return basketDetailsRepository.findOne(basketId);
	}
		
	public BasketDetailsModel getByBasketCodeAndVersion(String code, float basketVersion)
	{
		return basketDetailsRepository.findByBasketCodeAndVersion(code, basketVersion);
	}
}
