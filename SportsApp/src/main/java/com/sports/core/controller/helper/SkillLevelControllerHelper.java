package com.sports.core.controller.helper;

import com.sports.core.model.ErrorVO;
import com.sports.core.model.SkillLevelVO;

public class SkillLevelControllerHelper {

	public SkillLevelVO errorVO(String errorCode,String errorDesc,String errorType){
		
			ErrorVO errorvo = new ErrorVO();
			errorvo.setErrCode(errorCode);
			errorvo.setErrDesc(errorDesc);
			errorvo.setErrType(errorType);
			
			SkillLevelVO level = new SkillLevelVO();
			level.setErrorVO(errorvo);
			
			return level;
			
		}
}
