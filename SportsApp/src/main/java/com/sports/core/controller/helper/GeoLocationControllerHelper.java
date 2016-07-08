package com.sports.core.controller.helper;

import com.sports.core.model.GeoLocationVO;
import com.sports.core.model.ErrorVO;

public class GeoLocationControllerHelper {

	public  GeoLocationVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		GeoLocationVO geolocation = new GeoLocationVO();
		geolocation.setErrorVO(errorvo);
		
		return geolocation;
		
	}
}
