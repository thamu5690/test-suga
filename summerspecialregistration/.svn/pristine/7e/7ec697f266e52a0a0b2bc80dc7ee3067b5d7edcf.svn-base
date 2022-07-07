package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.StudentsLoginDetailsModel;

import org.vtop.CourseRegistration.repository.StudentLoginDetailsRepository;
import org.vtop.CourseRegistration.repository.UserDetailsRepository;

@Service
@Transactional("academicsTransactionManager")
public class StudentLoginDetailsService {

	@Autowired private StudentLoginDetailsRepository studentLoginDetailsRepository;
	
	@Autowired private UserDetailsRepository userDetailsRepository;
	

	public boolean isExist(String registerNumber)
	{
		return studentLoginDetailsRepository.exists(registerNumber);
	}
	
	public StudentsLoginDetailsModel getByRegNoAndCostCentre(String registerNumber, Integer costCentre)
	{
		return studentLoginDetailsRepository.findByRegNoAndCostCentre(registerNumber, costCentre);
	}
	
	public StudentsLoginDetailsModel getOne(String registerNumber)
	{
		return studentLoginDetailsRepository.findOne(registerNumber);
	}
	
	public int courseRegistrationEligibility(StudentsLoginDetailsModel studentsLoginDetailsModel, int studyStartYear)
	{
		int flag = 2;

		int studentBatch = studyStartYear;// studentsLoginDetailsModel.getStudentBaseModel().get

		//logger(" courseRegistrationEligibility Student Batch" + studentBatch);
		
		String groupMode = studentsLoginDetailsModel.getStudentBaseModel().getProgrammeSpecializationModel().getProgrammeGroupModel().getProgrammeMode();		

		if ((studentBatch <= 2017) && (groupMode.equals("Regular") || groupMode.equals("Twinning"))) {
			flag = 1;
		}

		return flag;
	}

	/*public int courseNotEligibility(StudentsLoginDetailsModel studentsLoginDetailsModel) {

		int flag = 2;

		String programCode = studentsLoginDetailsModel.getStudentBaseModel().getProgrammeSpecializationModel().getCode();//studentsLoginDetails.getProgcode();

		//System.out.println("courseNotEligibility -  Program Code " + programCode);

		if (programCode.equals("MBA") || programCode.equals("MBI") || programCode.equals("MIB")
				|| programCode.equals("MSD") || programCode.equals("MIN") || programCode.equals("MED")
				|| programCode.equals("MDS") || programCode.equals("MDI") || programCode.equals("MVI")) {
			
		if ( programCode.equals("MIB")
				|| programCode.equals("MSD") || programCode.equals("MIN") || programCode.equals("MED")
				|| programCode.equals("MDS") || programCode.equals("MDI") || programCode.equals("MVI")) {			
			flag = 2;
		} else {
			flag = 1;
		}
		//System.out.println("Student programCode" + programCode);

		return flag;
	}*/

	public int educationStatus(String educationStatus) {

		int flag = 2;
		

		if (educationStatus.equals("DO") || educationStatus.equals("GT")) {
			flag = 2;
		} else {
			flag = 1;
		}

		return flag;

	}

	public int logActiveTimeStamp() {
		return 0;
	}

	public int studentCreditTransfer() {

		return 0;
	}

	public int studentCGPAEarned() {

		return 0;
	}
	
	public int ValidatePassword(String registerNumber, String upassword, Integer testStatus){
		
		int validateFlag = 0;
		
		if (testStatus == 2){
			
			validateFlag = 1;
			
		}else{
			
			Sdc_common_functions sdcCommonFun = new  Sdc_common_functions();		
			/*StudentsLoginDetailsModel studentsLoginDetailsModel = getOne(registerNumber);	
			String dbPassword= studentsLoginDetailsModel.getPassword();	*/
			String dbPassword=userDetailsRepository.findOne(registerNumber).getPasswd();
			validateFlag = sdcCommonFun.String_Validate( upassword, dbPassword);
			
		}
		
		return validateFlag;		
	}
	
	public List<Object[]> getStudentDetailByRegisterNumber(String registerNumber)
	{
		return studentLoginDetailsRepository.findStudentDetailByRegisterNumber(registerNumber);
	}
	
	public List<Object[]> getStudentCategoryByFeeIdAndSpecId(Integer feeId, Integer specId)
	{
		return studentLoginDetailsRepository.findStudentCategoryByFeeIdAndSpecId(feeId, specId);
	}
	
	public List<Object[]> getStudentDetailByUserName(String userName)
	{
		return studentLoginDetailsRepository.findStudentDetailByUserName(userName);
	}
	

}
