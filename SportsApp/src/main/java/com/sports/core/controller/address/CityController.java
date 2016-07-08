package com.sports.core.controller.address;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : CityController.java
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.CityControllerHelper;
import com.sports.core.model.CityVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class CityController {

	private static final Logger logger = 
			LoggerFactory.getLogger(CityController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/city/{cityId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<CityVO> city(@PathVariable short cityId){
		
		logger.debug("Enter Method");
		CityVO city = null;
		if(cityId>0){
		
			try {
				city = userService.findByCityId(cityId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == city){
					CityControllerHelper helper = new CityControllerHelper();
					
					
					return new ResponseEntity<CityVO>(helper.errorVO("CI","City is Invalid","Business Error"), HttpStatus.OK);
					
				}
				
			}
			
			
		}
		else{
			CityControllerHelper helper = new CityControllerHelper();
			
			return new ResponseEntity<CityVO>(helper.errorVO("CI","City is Invalid","Business Error"), HttpStatus.OK);
			
		}
		logger.debug("End Method");
		return new ResponseEntity<CityVO>(city, HttpStatus.OK);	
	}
	@RequestMapping(value= "/city/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addCity(@ModelAttribute("city") CityVO city){
		
		if(city.getCityId() == 0){
			//new person, add it
			
			this.userService.saveCity(city);
		}else{
			//existing person, call update
			this.userService.updateCity(city);
		}
		
		return "this.userService.findAllCity()";
		
	}
	@RequestMapping(value =  "/allcity" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<CityVO>> getAllCity() {	 
		  List<CityVO> city=this.userService.findAllCity();
		  return new ResponseEntity<List<CityVO>>(city, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/allcities" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<CityVO>> getAllCity(HttpServletRequest request) {
		int  stateId = Integer.valueOf((String)request.getParameter("stateId"));
		  List<CityVO> city=this.userService.findAllCity(stateId);
		  return new ResponseEntity<List<CityVO>>(city, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/city/remove/{cityId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeCity(@PathVariable("cityId") int id){
		
        this.userService.deleteCityById(id);
        return "redirect:/allcity";
    }
	
}
