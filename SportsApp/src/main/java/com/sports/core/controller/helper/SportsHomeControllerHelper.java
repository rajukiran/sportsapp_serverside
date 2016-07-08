package com.sports.core.controller.helper;

import com.sports.core.domain.ErrorDO;
import com.sports.core.domain.User;
//import com.sports.core.domain.BaseDO;
import com.sports.core.model.ErrorVO;
import com.sports.core.model.UserGameMappingVO;
import com.sports.core.model.UserVO;

public class SportsHomeControllerHelper {

	public UserVO errorVO(String errorCode,String errorDesc,String errorType){
	//String errorCode= "NUI";
	//String errorDesc= "Not User Invalid";
	//String errorType= "Bussiness Error";
	
		//String string = new String();
		//errorCode = "NUI";
		//errorDesc = "Not User Invalid";
		//errorType = "Bussiness Error";
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		UserVO user = new UserVO();
		user.setErrorVO(errorvo);
		user.setErrCode(errorCode);
		user.setErrDesc(errorDesc);
		user.setErrType(errorType);
		//BaseDO basedo = new BaseDO();
		//basedo.setErrorDO(errordo);
		return user;
		
	}
	public UserGameMappingVO errorVO1(String errorCode,String errorDesc,String errorType){
		//String errorCode= "NUI";
		//String errorDesc= "Not User Invalid";
		//String errorType= "Bussiness Error";
		
			//String string = new String();
			//errorCode = "NUI";
			//errorDesc = "Not User Invalid";
			//errorType = "Bussiness Error";
			ErrorVO errorvo = new ErrorVO();
			errorvo.setErrCode(errorCode);
			errorvo.setErrDesc(errorDesc);
			errorvo.setErrType(errorType);
			
			UserGameMappingVO user = new UserGameMappingVO();
			user.setErrorVO(errorvo);
			user.setErrCode(errorCode);
			user.setErrDesc(errorDesc);
			user.setErrType(errorType);
			//BaseDO basedo = new BaseDO();
			//basedo.setErrorDO(errordo);
			return user;
			
		}
	
	public  User errorDO(String errorCode,String errorDesc,String errorType){
		
			ErrorDO errordo = new ErrorDO();
			errordo.setErrCode(errorCode);
			errordo.setErrDesc(errorDesc);
			errordo.setErrType(errorType);
			
			User user = new User("sadfs","asdfas");
			
			return user;
			
		}
}
