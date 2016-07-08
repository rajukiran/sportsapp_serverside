package com.sports.core.controller.helper;

import com.sports.core.model.RoleVO;
import com.sports.core.model.ErrorVO;

public class RoleControllerHelper {

public  RoleVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		RoleVO role = new RoleVO();
		role.setErrorVO(errorvo);
		
		return role;
		
	}
}
