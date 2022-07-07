package org.vtop.CourseRegistration.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CurriculumProgramModel;
import org.vtop.CourseRegistration.model.CurriculumProgramModelPK;
import org.vtop.CourseRegistration.repository.CurriculumProgramRepository;


@Service
@Transactional("academicsTransactionManager")
public class CurriculumProgramService {
	
	@Autowired private CurriculumProgramRepository curriculumProgramRepository;
	
	
	public CurriculumProgramModel getByIdAndStatus(CurriculumProgramModelPK curriculumProgramModelPK, int status)
	{
		return curriculumProgramRepository.findByCpmPkIdAndStatus(curriculumProgramModelPK, status);
	}
	
	public boolean isExist(CurriculumProgramModelPK curriculumProgramModelPK)
	{
		return curriculumProgramRepository.exists(curriculumProgramModelPK);
	}
	
	public String getRegMethodByStudentSpecIdAndStartYear(Integer specId, Integer studyStartYear)
	{
		String tempRegistrationMethod = "";
		
		/*Integer tempCurriculumYear = 0;
		
		if (studyStartYear >= 2014)
		{							
			if ((studyStartYear == 2014) || (studyStartYear == 2015))
			{
				tempCurriculumYear = studyStartYear;								
			}
			else
			{
				tempCurriculumYear = 2016;
			}
		}
		tempRegistrationMethod = curriculumProgramRepository.findCourseSystemBySpecIdAndCurProgYear(specId, tempCurriculumYear);
		if (tempRegistrationMethod == null)
		{
			tempRegistrationMethod = "GEN";
		}
		*/
		
		Integer tempStudySystemYear = 0;
		
		tempStudySystemYear = curriculumProgramRepository.findCALStartYearBySpecId(specId);
		if ((tempStudySystemYear != null) && (tempStudySystemYear > 0) && (studyStartYear >= tempStudySystemYear))
		{
			tempRegistrationMethod = "CAL";
		}
		else
		{
			tempRegistrationMethod = "GEN";
		}
		
		return tempRegistrationMethod;
	}
}
