package com.sports.core.controller.helper;

import com.sports.core.model.ScheduleVO;
import com.sports.core.model.ErrorVO;

public class ScheduleControllerHelper {

public  ScheduleVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		ScheduleVO schedule = new ScheduleVO();
		schedule.setErrorVO(errorvo);
		
		return schedule;
		
	}
}
