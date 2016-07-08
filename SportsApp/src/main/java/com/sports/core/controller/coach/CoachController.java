package com.sports.core.controller.coach;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.TeamControllerHelper;
import com.sports.core.exception.SportsException;
import com.sports.core.model.CoachVO;
import com.sports.core.model.TeamVO;
import com.sports.core.model.UserGameMappingVO;
import com.sports.core.model.UserVO;
import com.sports.core.service.UserService;



/*******************************************************************************
* Company     : AcknoTech 

* Copy Right  : All rights reserved.....  
* File Name   : TeamController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      19-june-16   SubbaramiReddy
*
*
*********************************************************************************/

@RestController
public class CoachController {

	private static final Logger logger = 
			LoggerFactory.getLogger(CoachController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/saveCoachMapping", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody CoachVO saveCoachMap1(HttpServletRequest request){
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  coachId = Long.valueOf((String)request.getParameter("coachId"));
		long  studentId = Long.valueOf((String)request.getParameter("studentId"));
			
		this.userService.saveCoachMapping(coachId,studentId,requestId);	
		
		
		return null;
		
	}
	@RequestMapping(value= "/saveCoachMappingForChilds", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody CoachVO saveCoachMap(HttpServletRequest request){
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  coachId = Long.valueOf((String)request.getParameter("coachId"));
		long  studentId = Long.valueOf((String)request.getParameter("studentId"));
			
		this.userService.saveCoachMappingForChilds(coachId,studentId,requestId);	
		
		
		return null;
		
	}
	@RequestMapping(value= "/allcoaches", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> allcoaches(HttpServletRequest request){
		
		
			List<UserVO> userGameMap = this.userService.getAllCoaches();
		
			
		
		
		return new ResponseEntity<List<UserVO>>(userGameMap, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/deleteCoachMapping",method = RequestMethod.GET,headers="Accept=application/json")
    public long removeCoachMapping(HttpServletRequest request){
		long  studentId = Long.valueOf((String)request.getParameter("studentId"));
        this.userService.deleteCoachMappingByStudentId(studentId);
        return studentId;
        
    }
	
	
	
}
