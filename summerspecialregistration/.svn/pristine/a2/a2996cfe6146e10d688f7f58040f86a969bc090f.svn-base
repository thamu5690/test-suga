package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumCreditModel;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumCreditPKModel;
import org.vtop.CourseRegistration.repository.BasketCourseCatalogRepository;
import org.vtop.CourseRegistration.repository.ProgrammeSpecializationCurriculumCreditRepository;

/*@EnableJpaRepositories(basePackageClasses={ProgrammeSpecializationCurriculumCreditRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")*/
@Service
@Transactional("academicsTransactionManager")
public class ProgrammeSpecializationCurriculumCreditService
{		
	@Autowired private ProgrammeSpecializationCurriculumCreditRepository programmeSpecializationCurriculumCreditRepository;
	
	@Autowired private CourseRegistrationService courseRegistrationService;
	
	@Autowired private BasketCourseCatalogRepository basketCourseCatalogRepository;
	
				
	public ProgrammeSpecializationCurriculumCreditModel getOne(ProgrammeSpecializationCurriculumCreditPKModel 
				programmeSpecializationCurriculumCreditPKModel)
	{
		return programmeSpecializationCurriculumCreditRepository.findOne(programmeSpecializationCurriculumCreditPKModel);
	}
		
	public List<ProgrammeSpecializationCurriculumCreditModel> getAll()
	{
		return programmeSpecializationCurriculumCreditRepository.findAll();
	}
		
	public List<ProgrammeSpecializationCurriculumCreditModel> getBySpecId(Integer specId)
	{
		return programmeSpecializationCurriculumCreditRepository.findBySpecId(specId);
	}
	
	public List<ProgrammeSpecializationCurriculumCreditModel> getBySpecIdAdmYear(Integer specId, 
				Integer admissionYear)
	{
		return programmeSpecializationCurriculumCreditRepository.findBySpecIdAdmYear(specId, admissionYear);
	}
	
	public ProgrammeSpecializationCurriculumCreditModel getMaxVerDetailBySpecIdAndAdmYear(Integer specId, 
				Integer admissionYear)
	{
		return programmeSpecializationCurriculumCreditRepository.findMaxVerDetailBySpecIdAndAdmYear(specId, 
					admissionYear);
	}

	public List<Object[]> getCurrentSemRegCurCtgCreditByRegisterNo(Integer specId, Integer admissionYear, 
								Float ccVersion, String semSubId, List<String> registerNumber, String[] classGroupId)
	{				
		List<String> tempHistGrade = new ArrayList<String>();
		List<String> tempCourseOption = new ArrayList<String>();
		List<String> tempPSCourseCode = new ArrayList<String>();
		List<String> tempPECourseCode = new ArrayList<String>();
		
		tempHistGrade.addAll(Arrays.asList("U","W"));
		tempCourseOption.addAll(Arrays.asList("RGR","RGP","RGCE","RGW","RPCE","RWCE","RPEUE","RUCUE"));
		//tempCourseOption.addAll(Arrays.asList("RGCE","RGP","RGR","RGW","RPCE","RWCE","RPEUE","RUCUE","RUEPE"));
		
		tempPSCourseCode = courseRegistrationService.getPrevSemResultCourseList(registerNumber);
		if (tempPSCourseCode.isEmpty())
		{
			tempPSCourseCode.add("NONE");
		}
		
		tempPECourseCode = courseRegistrationService.getUECourseByRegisterNumber(registerNumber);
		if (tempPECourseCode.isEmpty())
		{
			tempPECourseCode.add("NONE");
		}
		
		/*System.out.println("specId: "+ specId +" | admissionYear: "+ admissionYear +" | ccVersion: "+ ccVersion 
				+" | semSubId: "+ semSubId +" | registerNumber: "+ registerNumber);
		
		System.out.println("Result Published Course: ");
		for(String e: tempPSCourseCode)
		{
			System.out.println(e);
		}
		
		System.out.println("PE as UE Registered Course: ");
		for(String e: tempPECourseCode)
		{
			System.out.println(e);
		}*/
		
		/*return programmeSpecializationCurriculumCreditRepository.findStudentCurriculumCreditDetailByRegisterNo(
					specId, admissionYear, ccVersion, semSubId, registerNumber, tempHistGrade, tempCourseOption, 
					tempPSCourseCode, tempPECourseCode);*/
		return programmeSpecializationCurriculumCreditRepository.findStudentCurriculumCreditDetailByRegisterNo3(
					specId, admissionYear, ccVersion, registerNumber, tempHistGrade, tempCourseOption, tempPSCourseCode, 
					tempPECourseCode, semSubId, classGroupId);
	}
	

	public Integer getBasketCtgCreditByRegisterNo(String semSubId, List<String> registerNumber, String basketId)
	{	
		Integer tempCredit = 0;
		
		List<String> tempHistGrade = new ArrayList<String>();
		List<String> tempCourseOption = new ArrayList<String>();
		List<String> tempPECourseCode = new ArrayList<String>();
		List<String> tempBasketCourseCode = new ArrayList<String>();
				
		tempHistGrade.addAll(Arrays.asList("U","W"));
		tempCourseOption.addAll(Arrays.asList("HON","MIN","RGCE","RGP","RGR","RGW","RPCE","RWCE","RPEUE","RUCUE"));
				
		tempPECourseCode = courseRegistrationService.getUECourseByRegisterNumber(registerNumber);
		if (tempPECourseCode.isEmpty())
		{
			tempPECourseCode.add("NONE");
		}
		
		tempBasketCourseCode = basketCourseCatalogRepository.findCourseCodeByBasketId(basketId);
		if (tempBasketCourseCode.isEmpty())
		{
			tempBasketCourseCode.add("NONE");
		}
		
		/*System.out.println("semSubId: "+ semSubId +" | registerNumber: "+ registerNumber 
				+" | basketId: "+ basketId);
				
		System.out.println("PE as UE Registered Course: ");
		for(String e: tempPECourseCode)
		{
			System.out.println(e);
		}
		
		System.out.println("Basket Course: ");
		for (String e: tempBasketCourseCode)
		{
			System.out.println(e);
		}*/
		
		tempCredit = programmeSpecializationCurriculumCreditRepository.findStudentBasketCreditDetailByRegisterNo(semSubId, 
							registerNumber, tempHistGrade, tempCourseOption, tempPECourseCode, tempBasketCourseCode);
		if (tempCredit == null)
		{
			tempCredit = 0;
		}
		
		return tempCredit;
	}	
	
	public List<Object[]> getStudentHonourEligibleDetailByRegisterNo(Integer specId, Integer admissionYear, 
								Float ccVersion, List<String> registerNumber)
	{
		return programmeSpecializationCurriculumCreditRepository.findStudentHonourEligibleDetailByRegisterNo(
		specId, admissionYear, ccVersion, registerNumber);
	}
}
