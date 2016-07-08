package com.sports.core.controller.helper;

import com.sports.core.model.StateVO;
import com.sports.core.model.ErrorVO;

public class StatesControllerHelper {

	public  StateVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		StateVO state = new StateVO();
		state.setErrorVO(errorvo);
		
		return state;
		
	}
}
