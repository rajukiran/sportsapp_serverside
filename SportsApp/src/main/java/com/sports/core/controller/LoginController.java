package com.sports.core.controller;
import java.util.ArrayList;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : LoginHomeController.java
* Description : This is file is used for user access content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      24-Apr-16   Subbaramireddy
*
*
*********************************************************************************/
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.SportsHomeControllerHelper;

import com.sports.core.exception.SportsException;
import com.sports.core.model.LoginVO;
import com.sports.core.model.ResponseVO;
import com.sports.core.model.UserVO;
import com.sports.core.service.UserService;





@RestController
public class LoginController {

	private static final Logger logger = 
			LoggerFactory.getLogger(LoginController.class);
	
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<UserVO> loginForm(@RequestBody LoginVO loginVo,HttpServletRequest request,HttpServletResponse response) {
		
		logger.debug("Enter Method");
		UserVO userVo = null;
		
		HttpSession session = null;
		if(loginVo != null){
			try {
				userVo =  userService.isUserExist(loginVo.getUserName(),loginVo.getUserPassword());
				  
				
				
				 session = request.getSession(true);
				//session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
						   session.setAttribute("userVO", userVo);
						   userVo.setSessionValid(true);
							 //response.addHeader("JSESSION_ID",session.getId());
						   response.addHeader("JSESSION_ID", session.getId());
				
			} catch (SportsException e) {
				
				e.printStackTrace();
				if(null == userVo){
					
					
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					
					// userVo1.add(helper.errorVO("NUV","Not User Valid","Business Error"));
					return new ResponseEntity<UserVO>(helper.errorVO("NUV","Not User Valid","Business Error"), HttpStatus.OK);
					 
					
				}
				
			}
			
			
		}
		else{
			
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			UserVO userVo1= new UserVO();
			 //userVo1.add(helper.errorVO("NUV","Not User Valid","Business Error"));
			return new ResponseEntity<UserVO>(helper.errorVO("NUV","Not User Valid","Business Error"), HttpStatus.OK);
		}
		 @SuppressWarnings("unchecked")
		 UserVO userVo2 = (UserVO) session.getAttribute("userVO");
		 System.out.println("session attribute is:"+userVo2);
		System.out.println("session id is"+session.getId());
		
		return new ResponseEntity<UserVO>(userVo2, HttpStatus.OK);	
	}
	
	@RequestMapping(value =  "/logout" , method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<ResponseVO> logout(HttpServletRequest request,HttpServletResponse response) {	 
		ResponseVO respVO = new ResponseVO();
		HttpSession session =  request.getSession(true);
		System.out.println("nearbyteams Session ID :: "+session.getId());
		session.invalidate();
		  
		  respVO.setErrCode("SESSION_INVALIDATED");
		  respVO.setErrDesc("Your Session invalidated");
		  response.addHeader("JSESSION_ID", session.getId());
		  System.out.println("Your Session invalidated :: ");
		  return new ResponseEntity<ResponseVO>(respVO, HttpStatus.OK);
		
		 }
	

	@RequestMapping(value =  "/logoutMsg" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<ResponseVO> logoutMsg(HttpServletRequest request,HttpServletResponse response) {	 
		ResponseVO respVO = new ResponseVO();
		
		  respVO.setErrCode("SESSION_INVALIDATED");
		  respVO.setErrDesc("Your Session invalidated");
		  
		  response.addHeader("JSESSION_ID",null);
		  System.out.println("Your Session invalidated :: ");
		  return new ResponseEntity<ResponseVO>(respVO, HttpStatus.OK);
		
		 }
	
	
}
