package com.sports.core.controller.address;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : StatesController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      24-Apr-16   SubbaramiReddy
*
*
*********************************************************************************/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.StatesControllerHelper;
import com.sports.core.exception.SportsException;
import com.sports.core.model.StateVO;
import com.sports.core.service.UserService;

@RestController
public class StatesController {
	private static final Logger logger = 
			LoggerFactory.getLogger(StatesController.class);
	
	@Autowired
	private UserService userService;
	
	
	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping(value = "/test2/{stateId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<StateVO> test2(@PathVariable short stateId){
		
		logger.debug("Enter Method");
		StateVO state = null;
		if(stateId>0){
		
			try {
				state = userService.findById1(stateId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == state){
					StatesControllerHelper helper = new StatesControllerHelper();
					
					return new ResponseEntity<StateVO>(helper.errorVO("SI","State Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			StatesControllerHelper helper = new StatesControllerHelper();
			
			return new ResponseEntity<StateVO>(helper.errorVO("SI","State Invalid","Business Error"), HttpStatus.OK);
			
		}
		logger.debug("End Method");
		return new ResponseEntity<StateVO>(state, HttpStatus.OK);	
	}
	
	@RequestMapping(value =  "/allstates" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<StateVO>> getAllState() {	 
		  List<StateVO> state=this.userService.findAllStates();
		  return new ResponseEntity<List<StateVO>>(state, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/allstates1" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<StateVO>> getAllState(HttpServletRequest request) {
		int  countryId = Integer.valueOf((String)request.getParameter("countryId"));
		  List<StateVO> state=this.userService.findAllStates(countryId);
		  return new ResponseEntity<List<StateVO>>(state, HttpStatus.OK);
		
		 }
}
