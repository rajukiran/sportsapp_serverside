package com.sports.core.controller.helper;

import com.sports.core.model.MatchVO;
import com.sports.core.model.ErrorVO;

public class MatchesControllerHelper {

public  MatchVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		MatchVO match = new MatchVO();
		match.setErrorVO(errorvo);
		
		return match;
		
	}
}
