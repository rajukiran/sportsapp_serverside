package com.sports.core.controller.organization;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : OrganizationController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                     24-Apr-16  SubbaramiReddy
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.OrganizationControllerHelper;
import com.sports.core.model.OrganizationVO;
import com.sports.core.model.TeamVO;
import com.sports.core.model.UserVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;



@RestController
public class OrganizationController {

	private static final Logger logger = 
			LoggerFactory.getLogger(OrganizationController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ })
	@RequestMapping(value = "/organization/{orgId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<OrganizationVO> organization(@PathVariable short orgId){
		
		logger.debug("Enter Method");
		OrganizationVO organization = null;
		if(orgId>0){
		
			try {
				organization = userService.findByOrganizationId(orgId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
           if(null == organization){
					
					OrganizationControllerHelper helper = new OrganizationControllerHelper();
					
					
					return new ResponseEntity<OrganizationVO>(helper.errorVO("NUI","Not User Invalid","Business Error"), HttpStatus.OK);	
					
				}
				
			}
			
			
		}
		else{
			OrganizationControllerHelper helper = new OrganizationControllerHelper();
			
			
			return new ResponseEntity<OrganizationVO>(helper.errorVO("NUI","Not User Invalid","Business Error"), HttpStatus.OK);
		}
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<OrganizationVO>(organization, HttpStatus.OK);	
	}
	@RequestMapping(value= "/saveOrganization", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody OrganizationVO orgvo(@RequestBody OrganizationVO orgVo){
		
		//if(o.getOrgId() == 0){
			//new person, add it
			
			this.userService.saveOrganization(orgVo);
		//}else{
			//existing person, call update
			//this.userService.updateOrganization(orgVo);
		//}
		
		return orgVo;
		
	}
	@RequestMapping(value= "/saveOrganizationMapping", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody OrganizationVO orgmapping(HttpServletRequest request){
		
		//if(o.getOrgId() == 0){
			//new person, add it
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  orgOwnerId = Long.valueOf((String)request.getParameter("orgOwnerId"));
		long  userId = Long.valueOf((String)request.getParameter("userId"));
			this.userService.saveOrganizationMapping(orgOwnerId,userId,requestId);
		//}else{
			//existing person, call update
			//this.userService.updateOrganization(orgVo);
		//}
		
		return null;
		
	}
	@RequestMapping(value =  "/allorganizations" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<OrganizationVO>> getAllOrganization() {	 
		  List<OrganizationVO> organization=this.userService.findAllOrganizations();
		  return new ResponseEntity<List<OrganizationVO>>(organization, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/orgs" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<OrganizationVO>> getAllOrganizations(HttpServletRequest request) {
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<OrganizationVO> organization=this.userService.findAllOrganizations(userId);
		  return new ResponseEntity<List<OrganizationVO>>(organization, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/orgplayers" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllOrgplayers(HttpServletRequest request) {
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<UserVO> players=this.userService.findOrgPlayers(userId);
		  return new ResponseEntity<List<UserVO>>(players, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/orgcoaches" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllOrgcoaches(HttpServletRequest request) {
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<UserVO> coaches=this.userService.findOrgCoaches(userId);
		  return new ResponseEntity<List<UserVO>>(coaches, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/orgteams" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<TeamVO>> getAllOrgteams(HttpServletRequest request) {
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<TeamVO> teams=this.userService.findOrgTeams(userId);
		  return new ResponseEntity<List<TeamVO>>(teams, HttpStatus.OK);
		
		 }
	@RequestMapping(value = "/organization/remove/{orgId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeOrganization(@PathVariable("orgId") int id){
		
        this.userService.deleteOrganizationById(id);
        return "redirect:/allorganizations";
    }
	@RequestMapping(value = "/deleteOrgMapping",method = RequestMethod.GET,headers="Accept=application/json")
    public long removeOrgMapping(HttpServletRequest request){
		long  userId = Long.valueOf((String)request.getParameter("userId"));
        this.userService.deleteOrgMappingByUserId(userId);
        return userId;
        
    }
	
}
