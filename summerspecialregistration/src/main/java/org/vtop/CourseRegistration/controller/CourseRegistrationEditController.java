/*package org.vtop.CourseRegistration.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.vtop.CourseRegistration.model.CourseAllocationModel;
import org.vtop.CourseRegistration.model.CourseCatalogModel;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationPKModel;
import org.vtop.CourseRegistration.repository.RegistrationLogRepository;
import org.vtop.CourseRegistration.service.CourseAllocationService;
import org.vtop.CourseRegistration.service.CourseCatalogService;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationService;
import org.vtop.CourseRegistration.service.ProgrammeSpecializationCurriculumDetailService;
import org.vtop.CourseRegistration.service.RegistrationLogService;


@Controller
public class CourseRegistrationEditController
{	
	@Autowired private CourseCatalogService courseCatalogService;

	@Autowired private CourseAllocationService courseAllocationService;

	@Autowired private CourseRegistrationService courseRegistrationService;
	
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn; 	
	
	@Autowired private RegistrationLogService registrationLogService;
	
	@Autowired private RegistrationLogRepository registrationLogRepository;
	
	@Autowired private ProgrammeSpecializationCurriculumDetailService programmeSpecializationCurriculumDetailService;
	
	
	private static final String[] classType = { "BFS" };
	private static final String RegErrorMethod = "SSST1201920REG";
	

	@PostMapping("modifySlots")
	public String modifySlots(Model model, HttpSession session, HttpServletRequest request) 
	{	
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		int pageAuthStatus = 2;
		String pageAuthKey = "";
		int allowStatus = 0, updateStatus = 1;
		String urlPage = "", msg = null, infoMsg = "";
		pageAuthKey = (String) session.getAttribute("pageAuthKey");
		pageAuthStatus = courseRegCommonFn.validatePageAuthKey(pageAuthKey, registerNumber, 2);
		
		try
		{
			if ((registerNumber!=null) && (pageAuthStatus == 1))
			{
				session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNumber, 2));
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
				
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegCommonFn.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				switch(allowStatus)
				{
					case 1:
						List<Object[]> courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
																	semesterSubId, registerNumber, classGroupId);
						
						model.addAttribute("ModifyCourse", courseRegistrationModel);
						model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForUpdate(
																semesterSubId, registerNumber));
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						
						urlPage = "mainpages/ModifySlots::section";
						break;
					default:
						msg = infoMsg;
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;						
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception ex)
		{
			model.addAttribute("flag", 1);
			registrationLogService.addErrorLog(ex.toString(), RegErrorMethod+"CourseRegistrationEditController", 
					"modifySlots", registerNumber, IpAddress);
			registrationLogRepository.UpdateLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		return urlPage;
	}
	
	@PostMapping("editRegisteredSlots")
	public String editRegisteredSlots(String courseId, Model model, HttpSession session, HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		int pageAuthStatus = 2;
		String pageAuthKey = "";
		int allowStatus = 0, updateStatus = 1, statusFlag = 2;
		
		String urlPage = "", msg = null, classId = "", courseId2 = "", courseType = "", infoMsg = "", 
					courseAuthStatus = "", oldErpId = "", oldSlot = "";
		String[] cId = new String[]{};
		String[] validateStatusArr = new String[]{};
		
		pageAuthKey = (String) session.getAttribute("pageAuthKey");
		pageAuthStatus = courseRegCommonFn.validatePageAuthKey(pageAuthKey, registerNumber, 2);
		
		try
		{
			if ((registerNumber != null) && (pageAuthStatus == 1))
			{	
				session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNumber, 2));
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				String programGroupCode = (String) session.getAttribute("ProgramGroupCode");
				String ProgramSpecCode = (String) session.getAttribute("ProgramSpecCode");
				String costCentreCode = (String) session.getAttribute("costCentreCode");
				
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
				String studentEMailId = (String) session.getAttribute("studentEMailId");
				int otpStatus = (int) session.getAttribute("otpStatus");
											
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegCommonFn.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				CourseRegistrationPKModel courseRegistrationPKModel = new CourseRegistrationPKModel();
				List<CourseAllocationModel> courseAllocationList = new ArrayList<CourseAllocationModel>();
				List<Object[]> objectList = new ArrayList<Object[]>();
				
				switch(allowStatus)
				{
					case 1:
						cId = courseId.split("/");
						classId = cId[0];
						courseId2 = cId[1];
						courseType = cId[2];						
						
						courseRegistrationPKModel.setSemesterSubId(semesterSubId);
						courseRegistrationPKModel.setRegisterNumber(registerNumber);
						courseRegistrationPKModel.setCourseId(courseId2);
						courseRegistrationPKModel.setCourseType(courseType);						
						CourseRegistrationModel courseRegistrationModel = courseRegistrationService.getOne(
																				courseRegistrationPKModel);
						if (courseRegistrationModel != null)
						{
							oldErpId = courseRegistrationModel.getCourseAllocationModel().getErpId();
							oldSlot = courseRegistrationModel.getCourseAllocationModel().getTimeTableModel().getSlotName();
						}
						
						CourseCatalogModel courseCatalogModel = courseCatalogService.getOne(courseId2);
											
						
						if (otpStatus == 1)
						{
							validateStatusArr = courseRegCommonFn.validateCourseAndSendOTP(semesterSubId, registerNumber, 
													courseId2, studentEMailId, IpAddress, "MODIFY").split("\\|");
							statusFlag = Integer.parseInt(validateStatusArr[0]);
							courseAuthStatus = validateStatusArr[1];
							msg = validateStatusArr[3];
						}
						else if (otpStatus == 2)
						{
							statusFlag = 1;
							courseAuthStatus = courseRegCommonFn.generateCourseAuthKey(registerNumber, courseId2, 
													statusFlag, 2);
							msg = "SUCCESS";
						}
												
						if (statusFlag == 1)
						{
							if (courseType.equals("ETH") || courseType.equals("ELA")) 
							{
								courseAllocationList = courseAllocationService.getCourseAllocationCourseIdTypeEmpidList(
															semesterSubId, classGroupId, classType, courseId2, courseType, 
															oldErpId, programGroupCode, ProgramSpecCode, costCentreCode);
							} 
							else 
							{
								courseAllocationList = courseAllocationService.getCourseAllocationCourseIdTypeList(
															semesterSubId, classGroupId, classType, courseId2, courseType, 
															programGroupCode, ProgramSpecCode, costCentreCode);							
							}
							
							session.setAttribute("authStatus", courseAuthStatus);
							
							model.addAttribute("courseCatalogModel", courseCatalogModel);
							model.addAttribute("oldClassId", classId);
							model.addAttribute("oldCourseId", courseId2);
							model.addAttribute("oldCourseType", courseType);
							model.addAttribute("courseAllocationList", courseAllocationList);
							model.addAttribute("tlClashMapList", courseRegCommonFn.getClashSlotStatus(semesterSubId, 
									registerNumber, courseAllocationList));
							model.addAttribute("oldSlot", oldSlot);
							model.addAttribute("otpAllowStatus", otpStatus);
														
							urlPage = "mainpages/ModifyEdit :: section";
						}
						else
						{
							objectList = courseRegistrationService.getByRegisterNumberAndClassGroup(semesterSubId, 
												registerNumber, classGroupId);

							model.addAttribute("ModifyCourse", objectList);
							model.addAttribute("blockedCourse", courseRegistrationService.
										getBlockedCourseIdByRegisterNumberForUpdate(semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
										getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, 
												curriculumVersion));
							
							urlPage = "mainpages/ModifySlots :: section";
						}
						break;
						
					default:					
						msg = infoMsg;
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;					
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception ex)
		{
			model.addAttribute("flag", 1);
			registrationLogService.addErrorLog(ex.toString(), RegErrorMethod+"CourseRegistrationEditController", 
					"editRegisteredSlots", registerNumber, IpAddress);
			registrationLogRepository.UpdateLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		return urlPage;
	}		
		
	@PostMapping("UpdateRegisteredSlots")
	public String UpdateRegisteredSlots(String newCourseDetail, String oldClassId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		
		int allowStatus = 0, updateStatus = 1;
		int checkflag = 2, regStatusFlag = 0, oldRegStatus = 0, oldCompType = 0;
		Long newSlotId = 0L;
		
		String urlPage = "", msg = null, message = null, infoMsg = "";
		String[] courseDetailArr = {}, regStatusArr = {};		
		String oldGenericType = "", oldCourseType = "", newEpjClassId = "", oldCourseOption = "";
		String newCourseId = "", newClassId = "", newCourseType = "", newSlotClash = "", 
					newGenericType = "", newErpId = "", newAssoClassId = "";
		String pClassIdArr = "", pCompTypeArr = "", pOldClassIdArr = "", mailOTP = "";
		String pRegStatus = "", oldErpId = "";
		int pageAuthStatus = 2;
		String pageAuthKey = "";
		
		CourseRegistrationModel courseRegistrationModel2 =  new CourseRegistrationModel();
		CourseAllocationModel courseAllocationModel = new CourseAllocationModel();
		CourseAllocationModel courseAllocationModel2 = new CourseAllocationModel();
		List<CourseAllocationModel> camList = new ArrayList<CourseAllocationModel>();
		CourseCatalogModel courseCatalogModel =  new CourseCatalogModel();
		
		pageAuthKey = (String) session.getAttribute("pageAuthKey");
		pageAuthStatus = courseRegCommonFn.validatePageAuthKey(pageAuthKey, registerNumber, 2);
		
		
		try
		{
			if ((registerNumber != null) && (pageAuthStatus == 1))
			{								
				session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNumber, 2));
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
				String programGroupCode = (String) session.getAttribute("ProgramGroupCode");
				String ProgramSpecCode = (String) session.getAttribute("ProgramSpecCode");
				String costCentreCode = (String) session.getAttribute("costCentreCode");
				int otpStatus = (int) session.getAttribute("otpStatus");
												
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
								
				String returnVal = courseRegCommonFn.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				int statusFlag = 2, redirectFlag = 2;
				List<String> clashslot = new ArrayList<String>();
				String[] validateStatusArr = new String[]{};
				
				courseAllocationModel = new CourseAllocationModel();
				
				
				mailOTP = request.getParameter("mailOTP");
				if ((mailOTP != null) && (!mailOTP.equals("")))
				{
					mailOTP = mailOTP.trim();
				}
				else
				{
					mailOTP = "NONE";
				}
								
				switch(allowStatus)
				{
					case 1:
						
						courseDetailArr = newCourseDetail.split(",");						
						newClassId = courseDetailArr[0];
						newCourseId = courseDetailArr[1];
						newCourseType = courseDetailArr[2];
						newSlotClash = courseDetailArr[3];
						clashslot.add(newSlotClash);
						
						pOldClassIdArr = oldClassId;
						
						String authStatus = (String) session.getAttribute("authStatus");
						int authCheckStatus = courseRegCommonFn.validateCourseAuthKey(authStatus, registerNumber, 
												newCourseId, 2);
						
						
						courseCatalogModel = courseCatalogService.getOne(newCourseId);
						
						courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberCourseIdAndType(semesterSubId,
														registerNumber, newCourseId, newCourseType);
						if (courseRegistrationModel2 != null)
						{
							oldGenericType = courseRegistrationModel2.getCourseCatalogModel().getGenericCourseType();
							oldCourseType = courseRegistrationModel2.getCourseRegistrationPKId().getCourseType();
							oldCourseOption = courseRegistrationModel2.getCourseOptionCode();
							oldRegStatus = courseRegistrationModel2.getStatusNumber();
							oldCompType = courseRegistrationModel2.getComponentType();
							oldErpId = courseRegistrationModel2.getCourseAllocationModel().getErpId();
						}
						
						
						courseAllocationModel = courseAllocationService.getOne(newClassId);
						if (courseAllocationModel != null)
						{
							newGenericType = courseAllocationModel.getCourseCatalogModel().getGenericCourseType();
							newErpId = courseAllocationModel.getErpId();
							newSlotId = courseAllocationModel.getSlotId();
							newAssoClassId = courseAllocationModel.getAssoClassId();
						}
						
						if (authCheckStatus == 1)
						{
							if (otpStatus == 1)
							{
								validateStatusArr = courseRegCommonFn.validateCourseAndOTP(semesterSubId, registerNumber, 
														newCourseId, mailOTP, IpAddress, "MODIFY").split("\\|");
								statusFlag = Integer.parseInt(validateStatusArr[0]);
								redirectFlag = Integer.parseInt(validateStatusArr[1]);
								message = validateStatusArr[2];
							}
							else if (otpStatus == 2)
							{
								statusFlag = 1;
								redirectFlag = 2;
								message = "SUCCESS";
							}
						}
						else
						{
							message = "Not a valid course to modify.";
						}
						
						if (statusFlag == 1)
						{
							if ((oldGenericType.equals(newGenericType)) && (oldCourseType.equals(newCourseType)))
							{
								checkflag = 1;
							}
							else
							{
								message = "Selected course generic type is not valid.";
							}
							
							if (checkflag == 1) 
							{
								if (!oldClassId.equals(newClassId))
								{
									checkflag = 1;
								}
								else
								{
									checkflag = 2;
									message = "Old class and selected new class are same, cannot allowed to update.";
								}
							}
						}
						
						if (checkflag == 1) 
						{
							regStatusArr = courseRegCommonFn.checkClash(clashslot, semesterSubId, registerNumber, 
												"MODIFY", oldClassId).split("/");
							regStatusFlag = Integer.parseInt(regStatusArr[0]);
							message = regStatusArr[1];
							if (regStatusFlag == 2)
							{
								redirectFlag = 1;
							}
						}
												
						if ((authCheckStatus == 1) && (statusFlag == 1) && (checkflag == 1) && (regStatusFlag == 1)) 
						{
							pClassIdArr = newClassId;
							pCompTypeArr = newCourseType;
							courseRegistrationModel2 =  new CourseRegistrationModel();
														
							if (((newGenericType.equals("ETLP")) || (newGenericType.equals("ELP"))) && (newCourseType.equals("ELA")))
							{
								courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberCourseIdAndType(
																semesterSubId, registerNumber, newCourseId, "EPJ");
								if (courseRegistrationModel2 != null)
								{
									pOldClassIdArr = pOldClassIdArr +"|"+ courseRegistrationModel2.getClassId();
									
									courseAllocationModel2 = courseAllocationService.getCourseAllocationCourseIdTypeEmpidSlotAssoList(
																semesterSubId, classGroupId, classType, newCourseId, "EPJ", newErpId, 
																newSlotId, newAssoClassId,programGroupCode,ProgramSpecCode,costCentreCode);
									
									if (courseAllocationModel2 != null)
									{
										newEpjClassId = courseAllocationModel2.getClassId();
										pClassIdArr = pClassIdArr +"|"+ newEpjClassId;
										pCompTypeArr = pCompTypeArr +"|EPJ";
									}
								}
							}
							else if ((newGenericType.equals("ETP")) && (newCourseType.equals("ETH")))
							{
								courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberCourseIdAndType(semesterSubId, 
																registerNumber, newCourseId, "EPJ");
								if (courseRegistrationModel2 != null)
								{
									pOldClassIdArr = pOldClassIdArr +"|"+ courseRegistrationModel2.getClassId();
									
									courseAllocationModel2 = courseAllocationService.getCourseAllocationCourseIdTypeEmpidSlotAssoList(
																semesterSubId, classGroupId, classType, newCourseId, "EPJ", newErpId, 
																newSlotId, newAssoClassId,programGroupCode,ProgramSpecCode,costCentreCode);
									
									if (courseAllocationModel2 != null)
									{
										newEpjClassId = courseAllocationModel2.getClassId();
										pClassIdArr = pClassIdArr +"|"+ newEpjClassId;
										pCompTypeArr = pCompTypeArr +"|EPJ";
									}
								}
							}
							
							
							pRegStatus = courseRegistrationService.courseRegistrationUpdate2(semesterSubId, registerNumber, 
											newCourseId, pCompTypeArr, oldCourseOption, pOldClassIdArr, pClassIdArr, 
											registerNumber, IpAddress, oldRegStatus, oldCompType, "GEN", "", "", "");
							if (pRegStatus.equals("SUCCESS"))
							{
								msg = "Slot Modified Successfully";
								message = null;
							}
							else if ((pRegStatus.equals("FAIL")) || (pRegStatus.substring(0, 5).equals("error")))
							{
								message = "Technical error.";
								registrationLogService.addErrorLog(pRegStatus+"Code"+newCourseId, RegErrorMethod+"CourseRegistrationEditController", 
										"UpdateRegProc", registerNumber, IpAddress);
								registrationLogRepository.UpdateLogoutTimeStamp2(IpAddress,registerNumber);
							}
							else
							{
								message = pRegStatus;
							}							
						}						
						
						if (redirectFlag == 1)
						{
							if (oldCourseType.equals("ETH") || oldCourseType.equals("ELA")) 
							{
								camList = courseAllocationService.getCourseAllocationCourseIdTypeEmpidList(semesterSubId, 
												classGroupId, classType, newCourseId, oldCourseType, oldErpId, 
												programGroupCode, ProgramSpecCode, costCentreCode);
							} 
							else 
							{
								camList = courseAllocationService.getCourseAllocationCourseIdTypeList(semesterSubId, 
												classGroupId, classType, newCourseId, oldCourseType, programGroupCode,
												ProgramSpecCode, costCentreCode);							
							}
							
							//session.setAttribute("authStatus", courseAuthStatus);
							
							model.addAttribute("courseCatalogModel", courseCatalogModel);
							model.addAttribute("oldClassId", oldClassId);
							model.addAttribute("oldCourseId", newCourseId);
							model.addAttribute("oldCourseType", oldCourseType);
							model.addAttribute("courseAllocationList", camList);
							model.addAttribute("tlClashMapList", courseRegCommonFn.getClashSlotStatus(semesterSubId, 
									registerNumber, camList));
							model.addAttribute("otpAllowStatus", otpStatus);
							
							model.addAttribute("info", msg);
							model.addAttribute("infoMessage", message);
														
							urlPage = "mainpages/ModifyEdit :: section";
						}
						else
						{
							List<Object[]> courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
																		semesterSubId, registerNumber, classGroupId);
	
							model.addAttribute("ModifyCourse", courseRegistrationModel);
							model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForUpdate(
									semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
									getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
							model.addAttribute("info", msg);
							model.addAttribute("infoMessage", message);
													
							urlPage = "mainpages/ModifySlots::section";
						}
						break;
						
					default:
						msg = infoMsg;
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception ex)
		{
			model.addAttribute("flag", 1);
			registrationLogService.addErrorLog(ex.toString(), RegErrorMethod+"CourseRegistrationEditController", 
					"UpdateRegisteredSlots", registerNumber, IpAddress);
			registrationLogRepository.UpdateLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		return urlPage;
	}	
}
*/