package com.sports.core.controller.helper;

import com.sports.core.model.TeamVO;
import com.sports.core.model.ErrorVO;

public class TeamControllerHelper {

public  TeamVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		TeamVO team = new TeamVO();
		team.setErrorVO(errorvo);
		
		return team;
		
	}
}
