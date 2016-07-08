package com.sports.core.controller.address;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : ContinentController.java
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

import com.sports.core.controller.helper.ContinentControllerHelper;
import com.sports.core.model.ContinentVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class ContinentController {

	private static final Logger logger = 
			LoggerFactory.getLogger(ContinentController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/continent/{continentId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ContinentVO> continent(@PathVariable short continentId){
		
		logger.debug("Enter Method");
		ContinentVO continent = null;
		if(continentId>0){
		
			try {
				continent = userService.findByContinentId(continentId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == continent){
					ContinentControllerHelper helper = new ContinentControllerHelper();
					
					return new ResponseEntity<ContinentVO>(helper.errorVO("CI","Continent Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		
		else{
			ContinentControllerHelper helper = new ContinentControllerHelper();
			
			return new ResponseEntity<ContinentVO>(helper.errorVO("CI","Continent Invalid","Business Error"), HttpStatus.OK);
			
		}
		logger.debug("End Method");
		return new ResponseEntity<ContinentVO>(continent, HttpStatus.OK);	
	}
	@RequestMapping(value= "/continent/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addContinent(@ModelAttribute("continent") ContinentVO continent){
		
		if(continent.getContinentId() == 0){
			//new person, add it
			
			this.userService.saveContinent(continent);
		}else{
			//existing person, call update
			this.userService.updateContinent(continent);
		}
		
		return "this.userService.findAllContinent()";
		
	}
	@RequestMapping(value =  "/allcontinents" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<ContinentVO>> getAllContinent() {	 
		  List<ContinentVO> continent=this.userService.findAllContinents();
		  return new ResponseEntity<List<ContinentVO>>(continent, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/continent/remove/{continentId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeContinent(@PathVariable("continentId") int id){
		
        this.userService.deleteContinentById(id);
        return "redirect:/allcontinent";
    }
}
