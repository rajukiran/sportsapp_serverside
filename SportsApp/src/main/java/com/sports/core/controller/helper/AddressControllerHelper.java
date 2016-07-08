package com.sports.core.controller.helper;

import com.sports.core.model.ErrorVO;
import com.sports.core.model.AddressVO;

public class AddressControllerHelper {

	public  AddressVO errorVO(String errorCode,String errorDesc,String errorType){
		
			ErrorVO errorvo = new ErrorVO();
			errorvo.setErrCode(errorCode);
			errorvo.setErrDesc(errorDesc);
			errorvo.setErrType(errorType);
			
			AddressVO address = new AddressVO();
			address.setErrorVO(errorvo);
			
			return address;
			
		}
	
}
