package com.sports.core.controller.role;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : RoleController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      24-Apr-16   SubbaramiReddy
*
*
*********************************************************************************/

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.sports.core.controller.helper.RoleControllerHelper;

import com.sports.core.model.RoleVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class RoleController {

	private static final Logger logger = 
			LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<RoleVO> role(@PathVariable short roleId){
		
		logger.debug("Enter Method");
		RoleVO role = null;
		if(roleId>0){
		
			try {
				role = userService.findByRoleId(roleId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == role){
					RoleControllerHelper helper = new RoleControllerHelper();
					
					
					return new ResponseEntity<RoleVO>(helper.errorVO("RI","Role Invalid","Business Error"), HttpStatus.OK);	
					
				}
				
			}
			
			
		}
		
		else{
			RoleControllerHelper helper = new RoleControllerHelper();
			
			
			return new ResponseEntity<RoleVO>(helper.errorVO("RI","Role Invalid","Business Error"), HttpStatus.OK);
		}
		logger.debug("End Method");
		return new ResponseEntity<RoleVO>(role, HttpStatus.OK);	
	}
	@RequestMapping(value= "/role/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addRole(@ModelAttribute("role") RoleVO r){
		
		if(r.getRoleId() == 0){
			//new person, add it
			
			this.userService.saveRole(r);
		}else{
			//existing person, call update
			this.userService.updateRole(r);
		}
		
		return "this.userService.findAllRoles()";
		
	}
	@RequestMapping(value =  "/allroles" , method = RequestMethod.GET,headers="Accept=application/json")
	public  @ResponseBody List<RoleVO> getAllRoles() {	 
		  List<RoleVO> role=this.userService.findAllRoles();
		  return role;
		
		 }
	
	@RequestMapping(value = "/role/remove/{roleId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeRole(@PathVariable("roleId") int id){
		
        this.userService.deleteRoleById(id);
        return "redirect:/allroles";
    }
	
}
