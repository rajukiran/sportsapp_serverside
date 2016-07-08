package com.sports.core.controller.helper;

import com.sports.core.model.ContinentVO;
import com.sports.core.model.ErrorVO;

public class ContinentControllerHelper {

	public  ContinentVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		ContinentVO continent = new ContinentVO();
		continent.setErrorVO(errorvo);
		
		return continent;
		
	}
}
