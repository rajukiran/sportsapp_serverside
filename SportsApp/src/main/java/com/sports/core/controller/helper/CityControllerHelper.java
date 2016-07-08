package com.sports.core.controller.helper;

import com.sports.core.model.CityVO;
import com.sports.core.model.ErrorVO;

public class CityControllerHelper {

	public  CityVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		CityVO city = new CityVO();
		city.setErrorVO(errorvo);
		
		return city;
		
	}
}
