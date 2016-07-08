package com.sports.core.controller.helper;

import com.sports.core.model.GamesVO;
import com.sports.core.model.ErrorVO;

public class GamesControllerHelper {

public  GamesVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		GamesVO games = new GamesVO();
		games.setErrorVO(errorvo);
		
		return games;
		
	}
}
