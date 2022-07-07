package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseCatalogModel;
import org.vtop.CourseRegistration.repository.CourseCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@EnableJpaRepositories(basePackageClasses={CourseCatalogRepository.class},
											entityManagerFactoryRef = "academicsEntityManagerFactory",
											transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class CourseCatalogService {
		
	@Autowired private CourseCatalogRepository courseCatalogRepository;
	
	@Autowired private CourseRegistrationCommonFunction courseRegistrationCommonFunction;
	
	@Autowired private StudentHistoryService studentHistoryService;
		
		
	public CourseCatalogModel getOne(String courseId)
	{
		return courseCatalogRepository.findOne(courseId);
	}
	
	public List<CourseCatalogModel> getByCourseCode(String searchVal)
	{
		return courseCatalogRepository.findByCourseCode(searchVal);
	}
		
	public CourseCatalogModel getByCourseCodeAndVersion(String code, float courseVersion)
	{
		return courseCatalogRepository.findByCourseCodeAndVersion(code, courseVersion);
	}
		
	public List<CourseCatalogModel> getRegistrationCourseList(String campusCode, String[] courseSystem, 
			List<Integer> egbGroupId, String groupCode)
	{
		return courseCatalogRepository.findRegistrationCourseList(campusCode, courseSystem, 
				egbGroupId, groupCode);
	}
			
	//To get Course Owner's List
	public List<Object[]> getCourseCostCentre (String campus)
	{
		return courseCatalogRepository.findCourseCostCentre(campus);
	}
			
	public boolean isExist(String courseId)
	{
		return courseCatalogRepository.exists(courseId);
	}
	
	//Compulsory Course Pagination
	public Page<CourseCatalogModel> getCompulsoryCoursePagination(String campusCode, String[] courseSystem, 
										List<Integer> egbGroupId, String groupCode, String semesterSubId, 
										String[] classGroupId, String[] classType, List<String> courseCode, 
										String progGroupCode, String progSpecCode, String costCentreCode, 
										Pageable pageable)
	{
		return courseCatalogRepository.findCompulsoryCourseAsPage(campusCode, courseSystem, egbGroupId, 
						groupCode, semesterSubId, courseCode, pageable);
	}
	
	
	public List<CourseCatalogModel> getCourseListForRegistration(String registrationOption, String campusCode, 
										String[] courseSystem, List<Integer> egbGroupId, Integer programGroupId, 
										String semesterSubId, Integer programSpecId, String[] classGroupId, 
										String[] classType, Integer admissionYear, Float curriculumVersion, 
										String registerNumber, int searchType, String searchValue, 
										Integer studentGraduateYear, String programGroupCode, 
										String programSpecCode, String registrationMethod, String[] registerNumber2, 
										int evalPage, int evalPageSize, String costCentreCode)
	{
		List<CourseCatalogModel> tempList = new ArrayList<CourseCatalogModel>();
				
		int dataListFlag = 2;
		String programGroup = "";
		List<String> courseCode = new ArrayList<String>();
		
		if ((registrationOption != null) && (!registrationOption.equals("")))
		{
			dataListFlag = 1;
		}
		
		if ((searchType == 2) || (searchType == 3))
		{
			if ((searchValue == null) || (searchValue.equals("")))
			{
				searchValue = "NONE";
			}
			else
			{
				//searchValue = searchValue.toUpperCase() + "%";
				searchValue = searchValue.toUpperCase();
			}
		}
		
		if (programGroupId == null)
		{
			programGroup = "NONE";
		}
		else
		{
			programGroup = programGroupId.toString();
		}
		
		
		/*System.out.println("registrationOption: "+ registrationOption +" | srhType: "+ searchType 
				+" | dataListFlag: "+ dataListFlag);	
		
		System.out.println("campusCode: "+ campusCode +" | programGroup: "+ programGroup 
			+" | semesterSubId: "+ semesterSubId +" | registerNumber: "+ registerNumber
			+" | searchValue: "+ searchValue +" | evalPage: "+ evalPage 
			+" | evalPageSize: "+ evalPageSize +" | programSpecId: "+ programSpecId 
			+" | admissionYear: "+ admissionYear +" | curriculumVersion: "+ curriculumVersion 
			+" | registrationMethod: "+ registrationMethod +" | costCentreCode: "+ costCentreCode 
			+" | programGroupCode: "+ programGroupCode +" | programSpecCode: "+ programSpecCode);
		
		//Course System
		for(int i=0; i<courseSystem.length; i++)
		{
			System.out.println("Course System: "+ courseSystem[i]);
		}
		
		//Eligible Program Group Id
		System.out.println("egbGroupId: ");
		for(Integer e: egbGroupId)
		{
			System.out.println("Eligible Program Group Id: "+ e);
		}
		
		//Class Group Id
		System.out.println("classGroupId: ");
		for(int i=0; i<classGroupId.length; i++)
		{
			System.out.println("Class Group Id: "+ classGroupId[i]);
		}
		
		//Class Type
		System.out.println("classType: ");
		for(int i=0; i<classType.length; i++)
		{
			System.out.println("Class Type: "+ classType[i]);
		}
		
		//Register Number List 2
		System.out.println("registerNumber2: ");
		for(int i=0; i<registerNumber2.length; i++)
		{
			System.out.println("Register No.: "+ registerNumber2[i]);
		}*/
		
		
		if (dataListFlag == 1)
		{
			switch(registrationOption)
			{	
				case "PC":
				case "PE":
				case "UC":
				case "BC":
				case "NC":
					switch(searchType)
					{
						case 1:
							tempList = courseCatalogRepository.findCurriculumPCPEUCByCourseCode(
											campusCode, courseSystem, egbGroupId, programGroup, semesterSubId, 
											programSpecId, admissionYear, curriculumVersion, registrationOption, 
											searchValue);
							break;
						default:
							tempList = courseCatalogRepository.findCurriculumPCPEUC(campusCode, courseSystem, 
											egbGroupId, programGroup, semesterSubId, programSpecId, admissionYear, 
											curriculumVersion, registrationOption);
							break;
					}
					break;
					
				case "UE":
					switch(searchType)
					{
						case 1:
							tempList = courseCatalogRepository.findCurriculumUEByCourseCode(campusCode, courseSystem, 
											egbGroupId, programGroup, semesterSubId, programSpecId, admissionYear, 
											curriculumVersion, searchValue);
							break;
						default:
							tempList = courseCatalogRepository.findCurriculumUE(campusCode, courseSystem, egbGroupId, 
											programGroup, semesterSubId, programSpecId, admissionYear, curriculumVersion);
							break;
					}
					break;
					
				case "RGR":
					if ((programGroupCode.equals("RP")) && (admissionYear >= 2018))
					{
						courseCode = studentHistoryService.getRPCourseWorkByRegisterNumber(registerNumber);
						tempList = courseCatalogRepository.findRegularCourseByCourseCodeForResearch(campusCode, 
										courseSystem, egbGroupId, programGroup, semesterSubId, registerNumber2, 
										courseCode);
					}
					else
					{
						switch(searchType)
						{
							case 1:
								tempList = courseCatalogRepository.findRegularCourseByCourseCode(campusCode, 
												courseSystem, egbGroupId, programGroup, semesterSubId, registerNumber2, 
												searchValue);
								break;
							default:
								tempList = courseCatalogRepository.findRegularCourse(campusCode, courseSystem,
												egbGroupId, programGroup, semesterSubId, registerNumber2);
								break;
						}
					}
					break;
					
				case "RR":
					switch(searchType)
					{
						case 1:
							tempList = courseCatalogRepository.findRRCourseByCourseCode(campusCode, courseSystem, 
											egbGroupId, programGroup, semesterSubId, registerNumber2, searchValue);
							break;
						default:
							tempList = courseCatalogRepository.findRRCourse(campusCode, courseSystem, egbGroupId, 
											programGroup, semesterSubId, registerNumber2);
							break;
					}
					break;
									
				case "FFCSCAL":
					switch(searchType)
					{
						case 1:
							tempList = courseCatalogRepository.findCALToFFCSCEByCourseCode(campusCode, egbGroupId, 
											programGroup, semesterSubId, searchValue);
							break;
						default:
							tempList = courseCatalogRepository.findCALToFFCSCE(campusCode, egbGroupId, programGroup,
											semesterSubId);
							break;
					}
					break;

				case "SS":
					courseCode = courseRegistrationCommonFunction.SoftSkillCourseCheck(programGroupId, admissionYear, 
									studentGraduateYear, registerNumber, programSpecCode, programGroupCode,semesterSubId);
						
					if (registrationMethod.equals("CAL"))
					{
						tempList = courseCatalogRepository.findCALSoftSkillCourse(campusCode, courseSystem, 
										egbGroupId, programGroup, semesterSubId, courseCode);
					}
					else if (!registrationMethod.equals("CAL"))
					{
						tempList = courseCatalogRepository.findFFCSSoftSkillCourse(campusCode, egbGroupId,
										programGroup, semesterSubId, courseCode);
					}
					break;	
			}
		}
		
		return tempList;
	}
	
	public String getTotalPageAndIndex(int dataSize, int pageSize, int pageNumber)
	{
		int totalPage = 0, fromIndex = 0, toIndex = 0;
		double calcTotalPage = 0;
		
		if (pageSize > 0)
		{
			calcTotalPage = (double)dataSize / (double)pageSize;
			totalPage = (int) Math.ceil(calcTotalPage);
		}
		
		if (pageNumber <= 0)
		{
			pageNumber = 0;
		}
		else if (pageNumber >= totalPage)
		{
			pageNumber = totalPage - 1;
		}
				
		if (totalPage > 0)
		{
			fromIndex = pageNumber * pageSize;
			toIndex = fromIndex + pageSize;
			if (toIndex > dataSize)
			{
				toIndex = dataSize;
			}
		}
						
		return totalPage +"|"+ fromIndex +"|"+ toIndex;
	}
}
