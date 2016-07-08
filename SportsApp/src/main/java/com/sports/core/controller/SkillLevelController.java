package com.sports.core.controller;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : SkillLevelController.java
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
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.SkillLevelControllerHelper;

import com.sports.core.model.SkillLevelVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class SkillLevelController {

	private static final Logger logger = 
			LoggerFactory.getLogger(SkillLevelController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/skillLevel/{levelId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<SkillLevelVO> skillLevel(@PathVariable short levelId){
		
		logger.debug("Enter Method");
		SkillLevelVO skilllevel = null;
		if(levelId>0){
		
			try {
				skilllevel = userService.findByLevelId(levelId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == skilllevel){
					
					
					
					SkillLevelControllerHelper helper = new SkillLevelControllerHelper();
					return new ResponseEntity<SkillLevelVO>(helper.errorVO("IL","Invalid Level","Business Error"), HttpStatus.OK);	
				}
				
			}
			
			
		}
		else{
			
			
			SkillLevelControllerHelper helper = new SkillLevelControllerHelper();
			return new ResponseEntity<SkillLevelVO>(helper.errorVO("IL","Invalid Level","Business Error"), HttpStatus.OK);
			
			
			}
		logger.debug("End Method");
		return new ResponseEntity<SkillLevelVO>(skilllevel, HttpStatus.OK);	
	}
	@RequestMapping(value= "/SkillLevel/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addLevel(@ModelAttribute("level") SkillLevelVO level){
		
		if(level.getLevelId() == 0){
			//new person, add it
			
			this.userService.saveLevel(level);
		}else{
			//existing person, call update
			this.userService.updateLevel(level);
		}
		
		return "this.userService.findAllLevels()";
		
	}
	@RequestMapping(value =  "/allLevels" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<SkillLevelVO>> getAllLevels() {	 
		  List<SkillLevelVO> skillLevel=this.userService.findAllLevels();
		  return new ResponseEntity<List<SkillLevelVO>>(skillLevel, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/level/remove/{levelId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeLevel(@PathVariable("levelId") int id){
		
        this.userService.deleteSkillLevelById(id);
        return "redirect:/allLevels";
    }
}
