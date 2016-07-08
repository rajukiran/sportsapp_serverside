package com.sports.core.controller.helper;

import com.sports.core.model.FamilyDetailsVO;
import com.sports.core.model.ErrorVO;

public class FamilyDetailsControllerHelper {

public  FamilyDetailsVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		FamilyDetailsVO family = new FamilyDetailsVO();
		family.setErrorVO(errorvo);
		
		return family ;
		
	}
}
