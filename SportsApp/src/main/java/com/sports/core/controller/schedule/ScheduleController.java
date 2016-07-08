package com.sports.core.controller.schedule;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : ScheduleController.java
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

import com.sports.core.controller.helper.ScheduleControllerHelper;
import com.sports.core.model.ScheduleVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;



@RestController
public class ScheduleController {

	private static final Logger logger = 
			LoggerFactory.getLogger(ScheduleController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/schedule/{schedId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ScheduleVO> schedule(@PathVariable short schedId){
		
		logger.debug("Enter Method");
		ScheduleVO schedule = null;
		if(schedId>0){
		
			try {
				schedule = userService.findByScheduleId(schedId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == schedule){
					ScheduleControllerHelper helper = new ScheduleControllerHelper();
					
					return new ResponseEntity<ScheduleVO>(helper.errorVO("SI","Schedule Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			ScheduleControllerHelper helper = new ScheduleControllerHelper();
			return new ResponseEntity<ScheduleVO>(helper.errorVO("SI","Schedule Invalid","Business Error"), HttpStatus.OK);
			
			
		}
		logger.debug("End Method");
		return new ResponseEntity<ScheduleVO>(schedule, HttpStatus.OK);	
	}
	@RequestMapping(value= "/schedule/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addSchedule(@ModelAttribute("schedule") ScheduleVO s){
		
		if(s.getSchedId() == 0){
			//new person, add it
			
			this.userService.saveSchedule(s);
		}else{
			//existing person, call update
			this.userService.updateSchedule(s);
		}
		
		return "this.userService.findAllSchedules()";
		
	}
	@RequestMapping(value =  "/allschedules" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<ScheduleVO>> getAllSchedules() {	 
		  List<ScheduleVO> schedule=this.userService.findAllSchedules();
		  return new ResponseEntity<List<ScheduleVO>>(schedule, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/schedules" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<ScheduleVO>> getMySchedules(HttpServletRequest request) {
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<ScheduleVO> schedule=this.userService.findMySchedules(userId);
		  return new ResponseEntity<List<ScheduleVO>>(schedule, HttpStatus.OK);
		
		 }
	@RequestMapping(value = "/schedule/remove/{schedId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeSchedule(@PathVariable("schedId") int id){
		
        this.userService.deleteScheduleById(id);
        return "redirect:/allschedules";
    }
	
	
}
