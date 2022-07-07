package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.UserDetailModel;
import org.vtop.CourseRegistration.repository.UserDetailsRepository;

@Service
@Transactional("academicsTransactionManager")
public class UserDetailsService {
	
		@Autowired private UserDetailsRepository userDetailsRepository;
		
		
		public boolean isExist(String userId){
			return userDetailsRepository.exists(userId);
		}
		
		public UserDetailModel getOne(String userId){
			return userDetailsRepository.findOne(userId);
		}
		
		public int UserLoginValidation(String userId,String userPassword, Integer testStatus){
		  int validFlg=0;
		  
		  if(testStatus == 2){
			  
			  validFlg= 1;
			  
		  }else{
		    
		    try{
		      
		      String dbPassword=userDetailsRepository.findOne(userId).getPasswd();			      
		      Sdc_common_functions scf=new Sdc_common_functions();		      
		      validFlg=scf.String_Validate(userPassword, dbPassword);
		      
		    }catch(Exception e){
		      //System.out.println("Username not available");
		    }    
		  }
		  
		  return validFlg;
		}
		
		public int UserLoginValidation2(String userId, String userPassword, String dbPassword, Integer testStatus)
		{
			int validFlg = 0;
			  
			if(testStatus == 2)
			{
				validFlg = 1;
			}
			else
			{
				Sdc_common_functions scf = new Sdc_common_functions();		      
				validFlg = scf.String_Validate(userPassword, dbPassword);    
			}
			  
			return validFlg;
		}
	
}
