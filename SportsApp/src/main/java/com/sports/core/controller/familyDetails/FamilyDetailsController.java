package com.sports.core.controller.familyDetails;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : FamilyDetailsController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      24-Apr-16  SubbaramiReddy
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sports.core.controller.helper.FamilyDetailsControllerHelper;
import com.sports.core.model.FamilyDetailsVO;
import com.sports.core.model.UserVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class FamilyDetailsController {

	private static final Logger logger = 
			LoggerFactory.getLogger(FamilyDetailsController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/familydetails/{familyId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<FamilyDetailsVO> familyDetails(@PathVariable int familyId){
		
		logger.debug("Enter Method");
		FamilyDetailsVO familyDetails = null;
		if(familyId>0){
		
			try {
				familyDetails = userService.findByFamilyId(familyId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == familyDetails){
					FamilyDetailsControllerHelper helper = new FamilyDetailsControllerHelper();
					return new ResponseEntity<FamilyDetailsVO>(helper.errorVO("FDI","Family Details Invalid","Business Error"), HttpStatus.OK);
					
					
					
				}
				
			}
			
			
		}
		
		else{
			FamilyDetailsControllerHelper helper = new FamilyDetailsControllerHelper();
			
			return new ResponseEntity<FamilyDetailsVO>(helper.errorVO("FDI","Family Details Invalid","Business Error"), HttpStatus.OK);
			
		}
		logger.debug("End Method");
		return new ResponseEntity<FamilyDetailsVO>(familyDetails, HttpStatus.OK);	
	}
	@RequestMapping(value= "/familydetails/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addFamilyDetails(@ModelAttribute("familydetails") FamilyDetailsVO family){
		
		if(family.getFamilyId() == 0){
			//new person, add it
			
			this.userService.saveFamily(family);
		}else{
			//existing person, call update
			this.userService.updateFamily(family);
		}
		
		return "this.userService.findAllFamily()";
		
	}
	@RequestMapping(value =  "/allfamily" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<FamilyDetailsVO>> getAllFamily() {	 
		  List<FamilyDetailsVO> familydetails=this.userService.findAllFamilyDetails();
		  return new ResponseEntity<List<FamilyDetailsVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/familyById" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllFamily(HttpServletRequest request) {	
		String  parentId1 = (String)request.getParameter("parentId");
		long parentId = Long.valueOf(parentId1).longValue();
		
		  List<UserVO> familydetails=this.userService.findAllFamilyDetails(parentId);
		  return new ResponseEntity<List<UserVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/familyByTeamChildsForUnjoin" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllFamilyForChildsForUnjoin(HttpServletRequest request) {	
		String  parentId1 = (String)request.getParameter("parentId");
		long parentId = Long.valueOf(parentId1).longValue();
		
		  List<UserVO> familydetails=this.userService.findAllFamilyDetailsForUnjoin(parentId);
		  return new ResponseEntity<List<UserVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/familyByCoachChildsForUnjoinToCoach" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllFamilyForChildsForUnjoinToCoach(HttpServletRequest request) {	
		String  parentId1 = (String)request.getParameter("parentId");
		long parentId = Long.valueOf(parentId1).longValue();
		
		  List<UserVO> familydetails=this.userService.findAllFamilyDetailsForUnjoinToCoach(parentId);
		  return new ResponseEntity<List<UserVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/familyByTeamChildsForAdd" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllFamilyForChildsForAdd(HttpServletRequest request) {	
		String  parentId1 = (String)request.getParameter("parentId");
		long parentId = Long.valueOf(parentId1).longValue();
		
		  List<UserVO> familydetails=this.userService.findAllFamilyDetailsForAdd(parentId);
		  return new ResponseEntity<List<UserVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/familyByCoachChildsForAddToCoach" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllFamilyForChildsForAddToCoach(HttpServletRequest request) {	
		String  parentId1 = (String)request.getParameter("parentId");
		long parentId = Long.valueOf(parentId1).longValue();
		
		  List<UserVO> familydetails=this.userService.findAllFamilyDetailsForAddToCoach(parentId);
		  return new ResponseEntity<List<UserVO>>(familydetails, HttpStatus.OK);
		
		 }
	@RequestMapping(value = "/familydetails/remove/{familyId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeFamily(@PathVariable("familyId") int id){
		
        this.userService.deleteFamilyById(id);
        return "redirect:/allfamily";
    }
	
}
