package com.sports.core.controller.history;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : HistoryController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                     24-Apr-16   SubbaramiReddy
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

import com.sports.core.controller.helper.HistoryControllerHelper;
import com.sports.core.model.HistoryVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class HistoryController {

	private static final Logger logger = 
			LoggerFactory.getLogger(HistoryController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/history/{matchId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<HistoryVO> history(@PathVariable short historyId){
		
		logger.debug("Enter Method");
		HistoryVO history = null;
		if(historyId>0){
		
			try {
				history = userService.findByHistoryId(historyId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == history){
					HistoryControllerHelper helper = new HistoryControllerHelper();
					
					return new ResponseEntity<HistoryVO>(helper.errorVO("HI","History Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			HistoryControllerHelper helper = new HistoryControllerHelper();
			
			return new ResponseEntity<HistoryVO>(helper.errorVO("HI","History Invalid","Business Error"), HttpStatus.OK);
			
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<HistoryVO>(history, HttpStatus.OK);	
	}
	@RequestMapping(value= "/history/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addHistory(@ModelAttribute("history") HistoryVO h){
		
		if(h.getHistoryId() == 0){
			//new person, add it
			
			this.userService.saveHistory(h);
		}else{
			//existing person, call update
			this.userService.updateHistory(h);
		}
		
		return "this.userService.findAllHistory()";
		
	}
	@RequestMapping(value =  "/allhistory" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<HistoryVO>> getAllHistory() {	 
		  List<HistoryVO> history=this.userService.findAllHistory();
		  return new ResponseEntity<List<HistoryVO>>(history, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/history/remove/{historyId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeHistory(@PathVariable("historyId") int id){
		
        this.userService.deleteHistoryById(id);
        return "redirect:/allhistory";
    }
}
