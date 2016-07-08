package com.sports.core.controller.address;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : CountryController.java
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

import com.sports.core.controller.helper.CountryControllerHelper;
import com.sports.core.model.CountryVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class CountryController {

	private static final Logger logger = 
			LoggerFactory.getLogger(CountryController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/country/{countryId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<CountryVO> country(@PathVariable short countryId){
		
		logger.debug("Enter Method");
		CountryVO country = null;
		if(countryId>0){
		
			try {
				country = userService.findByCountryId(countryId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == country){
					CountryControllerHelper helper = new CountryControllerHelper();
					
					
					return new ResponseEntity<CountryVO>(helper.errorVO("CI","Country Invalid","Business Error"), HttpStatus.OK);
					
				}
				
			}
			
			
		}
		else{
			CountryControllerHelper helper = new CountryControllerHelper();
			
			
			return new ResponseEntity<CountryVO>(helper.errorVO("CI","Country Invalid","Business Error"), HttpStatus.OK);
		}
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<CountryVO>(country, HttpStatus.OK);	
	}
	@RequestMapping(value= "/country/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addCountry(@ModelAttribute("country") CountryVO country){
		
		if(country.getCountryId() == 0){
			//new person, add it
			
			this.userService.saveCountry(country);
		}else{
			//existing person, call update
			this.userService.updateCountry(country);
		}
		
		return "this.userService.findAllCountry()";
		
	}
	@RequestMapping(value =  "/allcountries" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<CountryVO>> getAllCountry() {	 
		  List<CountryVO> country=this.userService.findAllCountry();
		  return new ResponseEntity<List<CountryVO>>(country, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/allcountries1" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<CountryVO>> getAllCountry1(HttpServletRequest request) {	
		int  continentId = Integer.valueOf((String)request.getParameter("continentId"));
		  List<CountryVO> country=this.userService.findAllCountry(continentId);
		  return new ResponseEntity<List<CountryVO>>(country, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/country/remove/{countryId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeCountry(@PathVariable("countryId") int id){
		
        this.userService.deleteCountryById(id);
        return "redirect:/allcountry";
    }
	
}
