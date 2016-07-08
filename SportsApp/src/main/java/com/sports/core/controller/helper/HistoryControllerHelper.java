package com.sports.core.controller.helper;

import com.sports.core.model.HistoryVO;
import com.sports.core.model.ErrorVO;

public class HistoryControllerHelper {

public  HistoryVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		HistoryVO history = new HistoryVO();
		history.setErrorVO(errorvo);
		
		return history;
		
	}
}
