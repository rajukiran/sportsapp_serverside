package com.sports.core.controller.helper;

import com.sports.core.model.OrganizationVO;
import com.sports.core.model.ErrorVO;

public class OrganizationControllerHelper {

public  OrganizationVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		OrganizationVO organization = new OrganizationVO();
		organization.setErrorVO(errorvo);
		
		return organization;
		
	}
}
