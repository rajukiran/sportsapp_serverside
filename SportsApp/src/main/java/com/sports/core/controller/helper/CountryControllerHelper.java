package com.sports.core.controller.helper;

import com.sports.core.model.CountryVO;
import com.sports.core.model.ErrorVO;

public class CountryControllerHelper {

	public  CountryVO errorVO(String errorCode,String errorDesc,String errorType){
		
		ErrorVO errorvo = new ErrorVO();
		errorvo.setErrCode(errorCode);
		errorvo.setErrDesc(errorDesc);
		errorvo.setErrType(errorType);
		
		CountryVO country = new CountryVO();
		country.setErrorVO(errorvo);
		
		return country;
		
	}
}
