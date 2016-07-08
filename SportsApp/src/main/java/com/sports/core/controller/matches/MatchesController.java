package com.sports.core.controller.matches;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : MatchesController.java
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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.sports.core.controller.helper.MatchesControllerHelper;
import com.sports.core.model.MatchVO;

import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class MatchesController {

	private static final Logger logger = 
			LoggerFactory.getLogger(MatchesController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<MatchVO> matches(@PathVariable short matchId){
		
		logger.debug("Enter Method");
		MatchVO match = null;
		MatchesControllerHelper helper = new MatchesControllerHelper();
		if(matchId>0){
		
			try {
				match = userService.findByMatchId(matchId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == match){
					
					
					
					return new ResponseEntity<MatchVO>(helper.errorVO("MI","Match Invalid","Business Error"), HttpStatus.OK);
					
				}
				
			}
			
			
		}
		
		else{
			
			
			
			return new ResponseEntity<MatchVO>(helper.errorVO("MI","Matches Invalid","Business Error"), HttpStatus.OK);
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<MatchVO>(match, HttpStatus.OK);	
	}
	@RequestMapping(value= "/saveMatches", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody MatchVO match(@RequestBody MatchVO matchVo){
		
		//if(m.getMatchId() == 0){
			//new person, add it
			
			this.userService.saveMatch(matchVo);
		//}else{
			//existing person, call update
			//this.userService.updateMatch(m);
		//}
		
		return matchVo;
		
	}
	/**all matches details */
	@RequestMapping(value =  "/allmatches" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<MatchVO>> getAllMatches() {	 
		  List<MatchVO> match=this.userService.findAllMatches();
		  return new ResponseEntity<List<MatchVO>>(match, HttpStatus.OK);
		
		 }
	/**particular player matches statuses */
	@RequestMapping(value =  "/mymatches" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<MatchVO>> getMyMatches(HttpServletRequest request) {	 
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<MatchVO> match=this.userService.findMyMatches(userId);
		  return new ResponseEntity<List<MatchVO>>(match, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/match/remove/{matchId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeMatch(@PathVariable("matchId") int id){
		
        this.userService.deleteMatchById(id);
        return "redirect:/allmatches";
    }
	
}
