package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.StudentHistoryModel;
import org.vtop.CourseRegistration.model.StudentHistoryPKModel;
import org.vtop.CourseRegistration.repository.StudentHistoryRepository;
import org.vtop.CourseRegistration.repository.examinations.MigrationStudentHistoryAcadRepository;


@EnableJpaRepositories(basePackageClasses={StudentHistoryRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class StudentHistoryService {
	
	@Autowired private StudentHistoryRepository studentHistoryRepository;
	@Autowired private MigrationStudentHistoryAcadRepository migrationStudentHistoryAcadRepository;
	@Autowired private CourseRegistrationService courseRegistrationService;
	@Autowired private WishlistRegistrationService wishlistRegistrationService;
	
	
	//Procedure
	//To insert the fresh Data in Student History from Examination Schema
	public String studentHistoryInsertProcess(String pRegisterNumber, String pCourseSystem)
	{
		//return studentHistoryRepository.acad_student_history_insert_process(pRegisterNumber, pCourseSystem);
		return studentHistoryRepository.acad_student_history_insert_process2(pRegisterNumber, pCourseSystem);
	}
	
	//To get the CGPA & its related details from Examination Schema
	public String studentCGPA(String pRegisterNumber, Integer pSpecId, String pCourseSystem)
	{
		if (pCourseSystem.equals("CAL"))
		{
			return studentHistoryRepository.student_cgpa_cal(pRegisterNumber, "CGPA", "CGPA", 
						new Date(), new Date(), pSpecId);
		}
		else
		{
			return studentHistoryRepository.student_cgpa_ncal(pRegisterNumber, "CGPA", "CGPA", 
						new Date(), new Date(), pSpecId);
		}
	}
		
	
	public StudentHistoryModel getOne(StudentHistoryPKModel studentHistoryPKModel)
	{
		return studentHistoryRepository.findOne(studentHistoryPKModel);
	}
		
	public List<StudentHistoryModel> getAll()
	{
		return studentHistoryRepository.findAll();
	}
		
	public List<StudentHistoryModel> getByRegisterNumber(List<String> registerNumber)
	{
		return studentHistoryRepository.findByRegisterNumber(registerNumber);
	}
	
	public List<StudentHistoryModel> getByRegisterNumberCourseId(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findByRegisterNumberCourseId(registerNumber, courseId);
	}
	
	/*public StudentHistoryModel getStudentHistoryGrade(String[] registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGrade(registerNumber, courseCode);
	}*/
	
	public StudentHistoryModel getStudentHistoryGrade(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGrade(registerNumber, courseCode);
	}
	
	public String getStudentHistoryDistinctGrade(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryDistinctGrade(registerNumber, courseCode);
	}
	
	public List<StudentHistoryModel> getStudentHistoryCEGrade(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findStudentHistoryCEGrade(registerNumber, courseId);
	}
	
	public List<Object[]> getStudentHistoryCEGrade2(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryCEGrade2(registerNumber, courseCode);
	}
	
	public List<StudentHistoryModel> getStudentHistoryPARequisite(List<String> registerNumber, String[] courseId)
	{
		return studentHistoryRepository.findStudentHistoryPARequisite(registerNumber, courseId);
	}
	
	public List<StudentHistoryModel> getStudentHistoryPARequisite2(List<String> registerNumber, List<String> courseId)
	{
		return studentHistoryRepository.findStudentHistoryPARequisite2(registerNumber, courseId);
	}
	
	public List<String> getStudentHistoryCourseType(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findStudentHistoryCourseType(registerNumber, courseId);
	}
	
	public String getStudentHistoryGenericCourseType(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGenericCourseType(registerNumber, courseCode);
	}
	
	public List<StudentHistoryModel> getStudentHistoryCS(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryCS(registerNumber, courseCode);
	}
	
	public List<Object[]> getStudentHistoryGrade2(List<String> registerNumber, String courseCode)
	{
		//return studentHistoryRepository.findStudentHistoryGrade2(registerNumber, courseCode);
		return studentHistoryRepository.findStudentHistoryGrade3(registerNumber, courseCode);
	}
	
	
	//For Course Substitution
	/*public List<Object[]> getStudentHistoryCS2(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryCS2(registerNumber, Arrays.asList(courseCode));
	}*/
	
	public List<Object[]> getStudentHistoryCS2(List<String> registerNumber, String courseCode, String studySystem, 
								Integer specializationId, Integer studentYear, Float curriculumVersion, 
								String semesterSubId, String courseCategory, String courseOption, String basketId, 
								String[] classGroupId)
	{
		List<Object[]> courseSubList = new ArrayList<Object[]>();
		List<String> regCourseList = new ArrayList<String>();
		List<String> tempCourseList = new ArrayList<String>();
		
		//if (courseOption.equals("RGR"))
		if (courseOption.equals("RGR") || courseOption.equals("RGCE") 
				|| courseOption.equals("RGP") || courseOption.equals("RGW") 
				|| courseOption.equals("RPCE") || courseOption.equals("RWCE") 
				|| courseOption.equals("RGVC"))
		{
			regCourseList.add(courseCode);
			tempCourseList = wishlistRegistrationService.getRegisteredCourseByRegisterNumber(semesterSubId, classGroupId, 
									registerNumber);
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			tempCourseList = wishlistRegistrationService.getEquivalanceRegisteredCourseByRegisterNumber(semesterSubId, 
									classGroupId, registerNumber); 
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			tempCourseList = studentHistoryRepository.findCSCourseCodeByRegisterNo(semesterSubId, registerNumber); 
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			tempCourseList = courseRegistrationService.getPrevSemCourseDetailByRegisterNumber(registerNumber); 
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			//System.out.println("regCourseList: "+ regCourseList);			
			
			if (studySystem.equals("CAL"))
			{
				if (courseCategory.equals("UC"))
				{
					courseSubList = studentHistoryRepository.findCSCourseByCourseCategoryAndBasketId(registerNumber, 
										regCourseList, specializationId, studentYear, curriculumVersion, courseCategory, 
										basketId);
				}
				else if (courseCategory.equals("PE"))
				{
					courseSubList = studentHistoryRepository.findStudentHistoryCS3(registerNumber, regCourseList, 
										specializationId, studentYear, curriculumVersion);
				}
				else if (courseCategory.equals("UE"))
				{
					courseSubList = studentHistoryRepository.findStudentHistoryCS4(registerNumber, regCourseList, 
										specializationId, studentYear, curriculumVersion);
				}
			}
			else
			{
				courseSubList = studentHistoryRepository.findStudentHistoryCS2(registerNumber, regCourseList);
			}
		}
		
		return courseSubList;
	}
	
	
	public List<StudentHistoryModel> getStudentHistoryFailCourse(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryFailCourse(registerNumber);
	}
		
	public Integer getStudentHistoryFailCourseCredits(List<String> registerNumber)
	{
		Integer tempFailCredit = 0;
		
		tempFailCredit = studentHistoryRepository.findStudentHistoryFailCourseCredits(registerNumber);
		if (tempFailCredit == null)
		{
			tempFailCredit = 0;
		}
		
		return tempFailCredit;
	}
	
	public Integer getStudentHistoryFailCourseCredits2(List<String> registerNumber)
	{
		Integer tempFailCredit = 0;
		
		tempFailCredit = studentHistoryRepository.findStudentHistoryFailCourseCredits2(registerNumber);
		if (tempFailCredit == null)
		{
			tempFailCredit = 0;
		}
		
		return tempFailCredit;
	}
		
	public List<Object[]> getStudentHistoryCEGrade3(List<String> registerNumber, String courseCode)
	{
		//return studentHistoryRepository.findStudentHistoryCEGrade3(registerNumber, courseCode);
		return studentHistoryRepository.findStudentHistoryCEGrade4(registerNumber, courseCode);
	}
	
	public List<Object[]> getStudentHistoryFailCourse2(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryFailCourse2(registerNumber);
	}
	
	public List<Object[]> getStudentHistoryGIAndFailCourse(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryGIAndFailCourse(registerNumber);
	}
	
	public List<String> getStudentHistoryFailComponentCourseType(List<String> registerNumber, String courseId, 
							String examMonth)
	{
		return studentHistoryRepository.findStudentHistoryFailComponentCourseType(registerNumber, courseId, examMonth);
	}
	
	public List<Object[]> getStudentHistoryNotAllowedGrade(List<String> registerNumber, String courseId, String examMonth)
	{
		return studentHistoryRepository.findStudentHistoryNotAllowedGrade(registerNumber, courseId, examMonth);
	}
	
	//For Arrears Registration
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode2(String registerNumber, String courseCode)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "";
		
		//prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode(registerNumber, 
		//					courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				break;
			}
		
			prvSemRPList = migrationStudentHistoryAcadRepository.getResultPublishedCourseDataForRARBySemAndRegNo(
								prvSemSubId, Arrays.asList(registerNumber));
			if (!prvSemRPList.isEmpty())
			{
				for (Object[] e: prvSemRPList)
				{
					if (e[3].toString().equals(courseCode))
					{
						prvSemRegList.clear();
						break;
					}
				}
			}
		}
						
		return prvSemRegList;
	}
	
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
								String courseCode)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		int prvSemResultFlag = 2;
		String prvSemSubId = "";
				
		prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode2(registerNumber, 
								courseCode);
		//prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode3(registerNumber, 
		//						courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemResultFlag = 2;
				prvSemRPList.clear();
								
				prvSemRPList = migrationStudentHistoryAcadRepository.getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(
									prvSemSubId, registerNumber, courseCode);
				if (!prvSemRPList.isEmpty())
				{
					prvSemResultFlag = 1;
				}
				else
				{
					break;
				}
			}
		}
		
		if (prvSemResultFlag == 1)
		{
			prvSemRegList.clear();
		}
						
		return prvSemRegList;
	}
	
	public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode2(String registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "", prvSemCourseCode = "";
				
		prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode(registerNumber, 
							courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[4].toString();
				break;
			}
		
			prvSemRPList = migrationStudentHistoryAcadRepository.getResultPublishedCourseDataForRARBySemAndRegNo(
								prvSemSubId, Arrays.asList(registerNumber));
			
			if (!prvSemRPList.isEmpty())
			{
				for (Object[] e: prvSemRPList)
				{
					if (e[3].toString().equals(prvSemCourseCode))
					{
						prvSemRegList.clear();
						break;
					}
				}
			}
		}
				
		return prvSemRegList;
	}
	
	public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
								String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		int prvSemResultFlag = 2;
		String prvSemSubId = "", prvSemCourseCode = "";
				
		prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode2(
							registerNumber, courseCode);
		//prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode3(
		//					registerNumber, courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[4].toString();
				prvSemResultFlag = 2;
				prvSemRPList.clear();
				
				prvSemRPList = migrationStudentHistoryAcadRepository.getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(
									prvSemSubId, registerNumber, prvSemCourseCode);
				if (!prvSemRPList.isEmpty())
				{
					prvSemResultFlag = 1;
				}
				else
				{
					break;
				}
			}
		}
		
		if (prvSemResultFlag == 1)
		{
			prvSemRegList.clear();
		}
				
		return prvSemRegList;
	}
	
	
	//For Course Change or Course Substitution history
	public List<Object[]> getCourseChangeHistoryByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getCourseChangeHistoryByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode)
	{
		//return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode2(registerNumber, courseCode);
		return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode3(registerNumber, courseCode);
	}
	
	
	//Research Program
	public Integer getRPApprovalStatusByRegisterNumber(String registerNumber)
	{
		Integer tempStatus = 2;
		
		tempStatus = studentHistoryRepository.findRPApprovalStatusByRegisterNumber(registerNumber);
		if (tempStatus == null)
		{
			tempStatus = 2;
		}
		
		return tempStatus;
	}
	
	public List<String> getRPCourseWorkByRegisterNumber(String registerNumber)
	{
		List<String> tempCourseList = new ArrayList<String>();
		 
		if (getRPApprovalStatusByRegisterNumber(registerNumber) == 1)
		{
			tempCourseList = studentHistoryRepository.findRPCourseWorkByRegisterNumber(registerNumber);
		}
		
		if (tempCourseList.isEmpty())
		{
			tempCourseList.add("NONE");
		}
		
		return tempCourseList;
	}
	
	public List<String> getCSCourseCodeByRegisterNo(String semesterSubId, List<String> registerNumber)
	{
		return studentHistoryRepository.findCSCourseCodeByRegisterNo(semesterSubId, registerNumber);
	}
	
	public List<String> getCSCourseCodeByRegisterNoAndCourseId(String semesterSubId, List<String> registerNumber, 
								List<String> courseId)
	{
		return studentHistoryRepository.findCSCourseCodeByRegisterNoAndCourseId(semesterSubId, registerNumber, 
					courseId);
	}
}
