package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailModel;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailPKModel;
import org.vtop.CourseRegistration.repository.ProgrammeSpecializationCurriculumDetailRepository;


@Service
@Transactional("academicsTransactionManager")
public class ProgrammeSpecializationCurriculumDetailService {
	
	@Autowired private ProgrammeSpecializationCurriculumDetailRepository programmeSpecializationCurriculumDetailRepository;
	
				
	public ProgrammeSpecializationCurriculumDetailModel getOne(ProgrammeSpecializationCurriculumDetailPKModel programmeSpecializationCurriculumDetailPKModel)
	{
		return programmeSpecializationCurriculumDetailRepository.findOne(programmeSpecializationCurriculumDetailPKModel);
	}
		
	public List<ProgrammeSpecializationCurriculumDetailModel>getAll()
	{
		return programmeSpecializationCurriculumDetailRepository.findAll();
	}
			
	public List<ProgrammeSpecializationCurriculumDetailModel> getBySpecId(Integer specId)
	{
		return programmeSpecializationCurriculumDetailRepository.findBySpecId(specId);
	}
	
	public List<ProgrammeSpecializationCurriculumDetailModel> getBySpecIdAdmYear(Integer specId, Integer admissionYear)
	{
		return programmeSpecializationCurriculumDetailRepository.findBySpecIdAdmYear(specId, admissionYear);
	}
	
	public List<ProgrammeSpecializationCurriculumDetailModel> getBySpecIdAdmYearCCVersion(Integer specId, 
			Integer admissionYear, Float ccVersion)
	{
		return programmeSpecializationCurriculumDetailRepository.findBySpecIdAdmYearCCVersion(specId, admissionYear, ccVersion);
	}
	
	public List<Object[]> getCurriculumByAdmsnYearCCVersionAndCourseCode(Integer specId, Integer admissionYear, 
								Float ccVersion, String courseCode)
	{
		return programmeSpecializationCurriculumDetailRepository.findCurriculumByAdmsnYearCCVersionAndCourseCode(
					specId, admissionYear, ccVersion, courseCode);
	}
	
	public List<String> getNCCourseByYearAndCCVersion(Integer specId, Integer admissionYear, Float ccVersion)
	{
		List<String> tempStringList = new ArrayList<String>();
		
		tempStringList = programmeSpecializationCurriculumDetailRepository.findNCCourseByYearAndCCVersion(specId, 
							admissionYear, ccVersion, Arrays.asList("BC","NC"));
		if (tempStringList.isEmpty())
		{
			tempStringList.add("NONE");
		}
		
		return tempStringList;
	}
	
	
	public Map<String, List<Object[]>> getCurriculumBySpecIdYearAndCCVersionAsMap(Integer specId, Integer admissionYear, 
											Float ccVersion)
	{
		Map<String, List<Object[]>> tempMapList = new HashMap<String, List<Object[]>>();
		
		String tempHashKey = "";
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		List<Object[]> tempObjectMapList = new ArrayList<Object[]>();
		
		tempObjectList = programmeSpecializationCurriculumDetailRepository.findCurriculumByAdmsnYearAndCCVersion2(specId, 
							admissionYear, ccVersion);
		if (!tempObjectList.isEmpty())
		{
			for (Object[] parameters : tempObjectList)
			{
				tempHashKey = parameters[5].toString();
				if(tempMapList.containsKey(tempHashKey))
				{
					tempObjectMapList = tempMapList.get(tempHashKey);
					tempObjectMapList.add(parameters);
					tempMapList.put(tempHashKey, tempObjectMapList);
				}
				else
				{
					tempObjectMapList = new ArrayList<Object[]>();
					tempObjectMapList.add(parameters);
					tempMapList.put(tempHashKey, tempObjectMapList);
				}
			}
		}
		
		return tempMapList;
	}
}
