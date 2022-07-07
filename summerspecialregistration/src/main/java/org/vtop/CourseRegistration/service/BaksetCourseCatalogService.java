package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.BasketCourseCatalogModel;
import org.vtop.CourseRegistration.model.BasketCourseCatalogPK;
import org.vtop.CourseRegistration.repository.BasketCourseCatalogRepository;

/*@EnableJpaRepositories(basePackageClasses={BasketCourseCatalogRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class BaksetCourseCatalogService {
	
	@Autowired private BasketCourseCatalogRepository basketCourseCatalogRepository;
	
	
	public BasketCourseCatalogModel getOne(BasketCourseCatalogPK basketCourseCatalogPK)
	{
		return basketCourseCatalogRepository.findOne(basketCourseCatalogPK);
	}
		
	public List<BasketCourseCatalogModel> getAll()
	{
		return basketCourseCatalogRepository.findAll();
	}
		
	public List<BasketCourseCatalogModel> getByBasketId(String basketId)
	{
		return basketCourseCatalogRepository.findByBasketId(basketId);
	}
}
