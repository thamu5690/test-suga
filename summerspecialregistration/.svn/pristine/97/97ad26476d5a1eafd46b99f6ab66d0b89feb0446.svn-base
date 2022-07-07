package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseAllocationModel;
import org.vtop.CourseRegistration.repository.CourseAllocationRepository;

@EnableJpaRepositories(basePackageClasses={CourseAllocationRepository.class},
entityManagerFactoryRef = "academicsEntityManagerFactory",
transactionManagerRef = "academicsTransactionManager")
@Service
@Transactional("academicsTransactionManager")
public class CourseAllocationService {
	
	@Autowired private CourseAllocationRepository courseAllocationRepository;
	
	
	public List<CourseAllocationModel> getAll()
	{
		return courseAllocationRepository.findAll();
	}
	
	public CourseAllocationModel getOne(String classId)
	{
		return courseAllocationRepository.findOne(classId);
	}
	
	public List<CourseAllocationModel> getCourseAllocationList(String semesterSubId, String[] classGroupId, 
											String[] classType, String progGroupCode, String progSpecCode, 
											String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByClassGroupAndClassType(semesterSubId, classGroupId, 
								classType);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByClassGroupClassTypeAndClassOption(semesterSubId, 
								classGroupId, classType, progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String progGroupCode, 
											String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseId(semesterSubId, classGroupId, classType, courseId);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdAndClassOption(semesterSubId, classGroupId, 
								classType, courseId, progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdList2(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, List<String> courseType, 
											String progGroupCode, String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdAndCourseType(semesterSubId, classGroupId, 
								classType, courseId, courseType);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndClassOption(semesterSubId, 
								classGroupId, classType, courseId, courseType, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdTypeList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String courseType, String progGroupCode, 
											String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdAndCourseType2(semesterSubId, classGroupId, 
								classType, courseId, courseType);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndClassOption2(semesterSubId, 
								classGroupId, classType, courseId, courseType, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdTypeEmpidList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String courseType, String erpId, 
											String progGroupCode, String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndEmpId(semesterSubId, classGroupId, 
								classType, courseId, courseType, erpId);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeEmpIdAndClassOption(semesterSubId, 
								classGroupId, classType, courseId, courseType, erpId, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdTypeEmpidAssocList(String semesterSubId, 
											String[] classGroupId, String[] classType, String courseId, String courseType, 
											String erpId, String progGroupCode, String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findAssociateClassByCourseIdCourseTypeAndEmpId(
								semesterSubId, classGroupId, classType, courseId, courseType, erpId);
		}
		else
		{
			tempModelList = courseAllocationRepository.findAssociateClassByCourseIdCourseTypeEmpIdAndClassOption(
								semesterSubId, classGroupId, classType, courseId, courseType, erpId, progGroupCode, 
								progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	public CourseAllocationModel getCourseAllocationCourseIdTypeEmpidSlotList(String semesterSubId, 
									String[] classGroupId, String[] classType, String courseId, String courseType, 
									String erpId, Long slotId, String progGroupCode, String progSpecCode, 
									String costCentreCode)
	{
		CourseAllocationModel tempModel = new CourseAllocationModel();
		
		if (progGroupCode.equals("RP"))
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdAndSlotId(semesterSubId, 
							classGroupId, classType, courseId, courseType, erpId, slotId);
		}
		else
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdSlotIdAndClassOption(semesterSubId, 
							classGroupId, classType, courseId, courseType, erpId, slotId, progGroupCode, progSpecCode, 
							costCentreCode);
		}
		
		return tempModel;
	}
	
	public CourseAllocationModel getCourseAllocationCourseIdTypeEmpidSlotAssoList(String semesterSubId, 
										String[] classGroupId, String[] classType, String courseId, String courseType, 
										String erpId, Long slotId, String assoClassId, String progGroupCode, 
										String progSpecCode, String costCentreCode)
	{
		CourseAllocationModel tempModel = new CourseAllocationModel();

		if (progGroupCode.equals("RP"))
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdSlotIdAndAssoClassId(semesterSubId, 
							classGroupId, classType, courseId, courseType, erpId, slotId, assoClassId);
		}
		else
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdSlotIdAssoClassIdAndClassOption(
							semesterSubId, classGroupId, classType, courseId, courseType, erpId, slotId, assoClassId, 
							progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModel;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdAvbList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String progGroupCode, String progSpecCode, 
											String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseId(semesterSubId, classGroupId, 
								classType, courseId);
		}
		else
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseIdAndClassOption(semesterSubId, 
								classGroupId, classType, courseId, progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseCodeAvbList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseCode, String progGroupCode, 
											String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseCode(semesterSubId, classGroupId, 
								classType, courseCode);
		}
		else
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseCodeAndClassOption(semesterSubId, 
								classGroupId, classType, courseCode, progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	
	public Integer getAvailableRegisteredSeats(String classId)
	{
		return courseAllocationRepository.findAvailableRegisteredSeats(classId);
	}
	
	public Integer getAvailableWaitingSeats(String classId)
	{
		return courseAllocationRepository.findAvailableWaitingSeats(classId);
	}
	
	public Integer getAvailableRegisteredSeatsWithLock(String classId)
	{
		return courseAllocationRepository.findAvailableRegisteredSeatsWithLock(classId);
	}
	
	public Integer getAvailableWaitingSeatsWithLock(String classId)
	{
		return courseAllocationRepository.findAvailableWaitingSeatsWithLock(classId);
	}
	
	public void increaseRegisteredSeatsByOne(String classId)
	{
		courseAllocationRepository.increaseRegisteredSeats(classId);
	}
	
	public void decreaseRegisteredSeatsByOne(String classId)
	{
		courseAllocationRepository.decreaseRegisteredSeats(classId);
	}
}
