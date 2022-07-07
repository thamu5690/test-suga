package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseOptionModel;
import org.vtop.CourseRegistration.repository.CourseOptionRepository;


@Service
@Transactional("academicsTransactionManager")
public class CourseOptionService {
		
	@Autowired private CourseOptionRepository courseOptionRepository;
	
	
	public List<CourseOptionModel> getAll()
	{
		return courseOptionRepository.findAll();
	}
		
	public CourseOptionModel getOne(String code)
	{
		return courseOptionRepository.findOne(code);
	}
	
	public List<CourseOptionModel> getByOptionCode(List<String> code)
	{
		return courseOptionRepository.findByOptionCode(code);
	}
	
	public List<CourseOptionModel> getByOptionCodeAndStatus(List<String> code, List<Integer> lockStatus)
	{
		return courseOptionRepository.findByOptionCodeAndStatus(code, lockStatus);
	}
	
	
	public List<Object[]> getRegistrationCourseOption(String courseOption, String genericCourseType, int regularStatus, 
								int auditStatus, int honourStatus, int minorStatus, int additionalStatus, 
								int substitutionStatus, int RPEUEStatus, int RUCUEStatus)
	{
		List<Object[]> returnObjectList = new ArrayList<Object[]>();
		
		List<String> optionList = new ArrayList<String>();
		List<String> optionList2 = new ArrayList<String>();
		List<String> optionList3 = new ArrayList<String>();
		List<Object> temp = new ArrayList<>();
						
		if ((courseOption != null) && (!courseOption.equals("")))
		{
			if (courseOption.equals("RGR") && (regularStatus == 1))
			{
				optionList.add(courseOption);
			}
			else if (!courseOption.equals("RGR"))
			{
				optionList.add(courseOption);
			}
		}
				
		if (genericCourseType.equals("TH") || genericCourseType.equals("LO") 
				|| genericCourseType.equals("ETL") || genericCourseType.equals("ETP") 
				|| genericCourseType.equals("ELP") || genericCourseType.equals("ETLP"))
		{
			if (auditStatus == 1)
			{
				optionList.add("AUD"); 
			}
			
			if (honourStatus == 1)
			{
				optionList.add("HON"); 
			}
			
			if (minorStatus == 1)
			{
				optionList.add("MIN"); 
			}
			
			if (additionalStatus == 1)
			{
				optionList.add("RGA"); 
			}
			
			if (substitutionStatus == 1)
			{
				optionList.add("CS"); 
			}
			
			if (RPEUEStatus == 1)
			{
				optionList.add("RPEUE"); 
			}
			
			if (RUCUEStatus == 1)
			{
				optionList.add("RUCUE"); 
			}
		}
			
		if (!optionList.isEmpty())
		{
			optionList2 = Arrays.asList("GI", "GICE", "GIVC", "RR", "RRCE", "RRN", "RRVC");
			optionList3 = Arrays.asList("RGR", "AUD", "DM", "RGCE", "RGVC", "RPEUE", "RUCUE");
			
			for (CourseOptionModel e : courseOptionRepository.findByOptionCode(optionList))
			{
				temp.clear();
				
				temp.add(e.getCode());
				temp.add(e.getDescription());
				
				if (optionList2.contains(e.getCode()))
				{
					temp.add(2);
				}
				else
				{
					temp.add(1);
				}
				
				if (optionList3.contains(e.getCode()))
				{
					temp.add(1);
				}
				else
				{
					temp.add(2);
				}
				
				returnObjectList.add(temp.toArray());
			}
		}
				
		return returnObjectList;
	}
} 
