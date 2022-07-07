package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.AdditionalLearningCourseCatalogModel;
import org.vtop.CourseRegistration.model.AdditionalLearningCourseCatalogPkModel;
import org.vtop.CourseRegistration.repository.AdditionalLearningCourseCatalogRepository;

@EnableJpaRepositories(basePackageClasses={AdditionalLearningCourseCatalogRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class AdditionalLearningCourseCatalogService {
	
	@Autowired private AdditionalLearningCourseCatalogRepository additionalLearningCourseCatalogRepository;
	
	
	public AdditionalLearningCourseCatalogModel getOne(AdditionalLearningCourseCatalogPkModel additionalLearningCourseCatalogPkModel)
	{
		return additionalLearningCourseCatalogRepository.findOne(additionalLearningCourseCatalogPkModel);
	}
	
	public List<AdditionalLearningCourseCatalogModel> getAdditionalLearningCourse()
	{
		return additionalLearningCourseCatalogRepository.findAdditionalLearningCourse();
	}
	
	public List<AdditionalLearningCourseCatalogModel> getAdditionalLearningCode(String code)
	{
		return additionalLearningCourseCatalogRepository.findAdditionalLearningCode(code);
	}
		
	public List<Object[]> getHonourTitleByCourseCode(String courseCode, String progSpecCode, 
								String studStudySystem, Integer groupId, Integer specId, 
								Integer honorAllowFlag)
	{
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		
		if (honorAllowFlag == 1)
		{
			if (studStudySystem.equals("CAL") || studStudySystem.equals("RCAL"))
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findHonourTitleByCourseCode2(
										groupId, specId.toString());
			}
			else
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findHonourTitleByCourseCode(
										courseCode, progSpecCode);
			}
			
			/*tempObjectList = additionalLearningCourseCatalogRepository.findHonourTitleByCourseCode3(
									groupId, specId.toString(), courseCode);*/
		}
		
		return tempObjectList;
	}
	
	public List<Object[]> getMinorTitleByCourseCode(String courseCode, String studStudySystem, 
								Integer groupId, Integer specId, Integer minorAllowFlag)
	{
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		
		if (minorAllowFlag == 1)
		{
			if (studStudySystem.equals("CAL") || studStudySystem.equals("RCAL"))
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findMinorTitleByCourseCode2(groupId, 
									 specId.toString(), courseCode);
			}
			else
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findMinorTitleByCourseCode(courseCode);
			}
		}
		
		return tempObjectList;
	}
	
	public List<Object[]> getTitleByLearnTypeGroupIdSpecIdAndCourseCode(Integer allowFlag, String learningType, 
								Integer groupId, Integer specId, String courseCode, String studStudySystem)
	{
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
	
		if (allowFlag == 1)
		{
			if ((learningType.equals("HON")) && (studStudySystem.equals("CAL")))
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findTitleByLearnTypeGroupIdAndSpecId(
									learningType, groupId, specId.toString());
			}
			else
			{
				tempObjectList = additionalLearningCourseCatalogRepository.findTitleByLearnTypeGroupIdSpecIdAndCourseCode(
										learningType, groupId, specId.toString(), courseCode);
			}
		}
	
		return tempObjectList;
	}
}
