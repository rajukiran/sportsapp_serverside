package com.sports.core.controller.geolocation;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : GeoLocationController.java
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sports.core.controller.helper.AddressControllerHelper;
import com.sports.core.controller.helper.GeoLocationControllerHelper;
import com.sports.core.controller.helper.SportsHomeControllerHelper;


import com.sports.core.exception.SportsException;
import com.sports.core.model.AddressVO;
import com.sports.core.model.GeoLocationVO;
import com.sports.core.domain.GeoLocation;
import com.sports.core.service.UserService;

@RestController
public class GeoLocationController {

	private static final Logger logger = 
			LoggerFactory.getLogger(GeoLocationController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/geolocation/{locationId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<GeoLocation> geolocation(@PathVariable short locationId){
		
		logger.debug("Enter Method");
		GeoLocation geolocation = null;
		if(locationId>0){
		
			try {
				geolocation = userService.findByGeoLocationId(locationId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == geolocation){
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					helper.errorDO("NUI","Not User Invalid","Business Error");
					return new ResponseEntity<GeoLocation>(geolocation, HttpStatus.OK);	
					
					
				}
				
			}
			
			
		}
		
		else{
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			helper.errorDO("NUI","Not User Invalid","Business Error");
			
			
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<GeoLocation>(geolocation, HttpStatus.OK);	
	}
	@RequestMapping(value= "/savelocation", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody GeoLocationVO addGeoLocation(@RequestBody GeoLocationVO geo){
		
		
			
			this.userService.saveGeoLocation(geo);
		
		
		return geo;
		
	}
	@RequestMapping(value =  "/alllocations" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<GeoLocation>> getAllHistory() {	 
		  List<GeoLocation> geolocation=this.userService.findAllGeoLocation();
		  return new ResponseEntity<List<GeoLocation>>(geolocation, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/geolocation/remove/{locationId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeGeoLocation(@PathVariable("locationId") int id){
		
        this.userService.deleteGeoLocationById(id);
        return "redirect:/alllocations";
    }
	
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/getlocation", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<GeoLocationVO> findlocation(HttpServletRequest request){
		
		int columnId = Integer.valueOf((String)request.getParameter("columnId"));
		String  tableName = (String)request.getParameter("tableName");
		logger.debug("Enter Method");
		GeoLocationVO geolocation = null;
		
		if(columnId != 0){
		
			try {
				geolocation = userService.findLocation(columnId,tableName);
				
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == geolocation){
					GeoLocationControllerHelper helper = new GeoLocationControllerHelper();
					helper.errorVO("AI","Location Invalid","Business Error");
					return new ResponseEntity<GeoLocationVO>(geolocation, HttpStatus.OK);	
					
					
				}
				
			}
			
			
		}
		else{
			GeoLocationControllerHelper helper = new GeoLocationControllerHelper();
			helper.errorVO("AIE","Location Is Empty","Business Error");
			return new ResponseEntity<GeoLocationVO>(geolocation, HttpStatus.OK);	
			
			}
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<GeoLocationVO>(geolocation, HttpStatus.OK);	
	}
	
	
	
	
}
