package com.sports.core.controller.team;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : TeamController.java
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
import javax.servlet.http.HttpServletResponse;

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

import com.sports.core.controller.helper.SportsHomeControllerHelper;
import com.sports.core.controller.helper.TeamControllerHelper;
import com.sports.core.model.CoachVO;
//import com.sports.core.domain.Country;
//import com.sports.core.domain.Match;
//import com.sports.core.domain.State;
import com.sports.core.model.TeamVO;

//import com.sports.core.model.Team;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class TeamController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/team", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<TeamVO> team(HttpServletRequest request) throws SportsException{
		int  teamId = Integer.valueOf((String)request.getParameter("teamId"));
		logger.debug("Enter Method");
		TeamVO team = null;
		if(teamId>0){ 
		
			try {
				team = userService.findByTeamId(teamId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == team){
					
					TeamControllerHelper helper = new TeamControllerHelper();
					
					return new ResponseEntity<TeamVO>(helper.errorVO("TI","Team Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			
			TeamControllerHelper helper = new TeamControllerHelper();
			
			return new ResponseEntity<TeamVO>(helper.errorVO("TI","Team Invalid","Business Error"), HttpStatus.OK);
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<TeamVO>(team, HttpStatus.OK);	
	}
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/teambycoach", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<TeamVO> teambycoach(HttpServletRequest request) throws SportsException{
		long  coachId = Long.valueOf((String)request.getParameter("coachId"));
		logger.debug("Enter Method");
		TeamVO team = null;
		if(coachId>0){ 
		
			try {
				team = userService.findTeamByCoach(coachId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == team){
					
					TeamControllerHelper helper = new TeamControllerHelper();
					
					return new ResponseEntity<TeamVO>(helper.errorVO("TI","Team Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			
			TeamControllerHelper helper = new TeamControllerHelper();
			
			return new ResponseEntity<TeamVO>(helper.errorVO("TI","Team Invalid","Business Error"), HttpStatus.OK);
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<TeamVO>(team, HttpStatus.OK);	
	}
	@RequestMapping(value= "/saveTeam", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody TeamVO saveTeam(@RequestBody TeamVO teamVo){
		
		
			
			this.userService.saveTeam(teamVo);
		
		
		return teamVo;
		
	}
	@RequestMapping(value= "/saveTeamMapping", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TeamVO saveTeamMap(HttpServletRequest request){
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		long  teamOwnerId = Long.valueOf((String)request.getParameter("teamOwnerId"));
			
			this.userService.saveTeamMapping(userId,teamOwnerId,requestId);
		
		
		return null;
		
	}
	@RequestMapping(value= "/saveTeamMappingForChilds", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TeamVO saveTeamMap1(HttpServletRequest request){
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  teamOwnerId = Long.valueOf((String)request.getParameter("teamOwnerId"));
		long  userId = Long.valueOf((String)request.getParameter("userId"));
			
		this.userService.saveTeamMappingForChilds(teamOwnerId,userId,requestId);	
		
		
		return null;
		
	}
	@RequestMapping(value= "/updateTeam", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TeamVO UpdateTeam(HttpServletRequest request){
		int requestId = Integer.valueOf((String)request.getParameter("requestId"));
		long  coachId = Long.valueOf((String)request.getParameter("coachId"));
		long  teamOwner = Long.valueOf((String)request.getParameter("teamOwnerId"));
			
			try {
				this.userService.updateTeam(teamOwner,coachId,requestId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return null;
		
	}
@RequestMapping(value =  "/teams" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<TeamVO>> getAllTeams() {	 
		  List<TeamVO> team=this.userService.findAllTeams();
		  return new ResponseEntity<List<TeamVO>>(team, HttpStatus.OK);
		
		 }
/*@RequestMapping(value =  "/teamplayer" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<TeamVO> getTeamPlayer(HttpServletRequest request) {	
	long playerId = Long.valueOf((String)request.getParameter("playerId"));
	  TeamVO team= this.userService.findTeamPlayer(playerId);
	  return new ResponseEntity<TeamVO>(team, HttpStatus.OK);
	
	 }*/
	@RequestMapping(value =  "/team1" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<TeamVO>> getAllTeams1(HttpServletRequest request) {	
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		  List<TeamVO> team=this.userService.findAllTeams(userId);
		  return new ResponseEntity<List<TeamVO>>(team, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/nearbyteams" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<TeamVO>> getNearByTeams(HttpServletRequest request,HttpServletResponse response) {	 
		  List<TeamVO> team=this.userService.findNearByTeam();
		  
		  return new ResponseEntity<List<TeamVO>>(team, HttpStatus.OK);
		
		 }
	@RequestMapping(value =  "/nearbyteamsforparents" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<TeamVO>> getNearByTeamsforparents(HttpServletRequest request,HttpServletResponse response) {	 
		long  parentId = Long.valueOf((String)request.getParameter("parentId"));
		List<TeamVO> team=this.userService.findNearByTeamForParents(parentId);
		  
		  return new ResponseEntity<List<TeamVO>>(team, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/team/remove/{teamId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeTeam(@PathVariable("teamId") int id){
		
        this.userService.deleteTeamById(id);
        return "redirect:/teams";
    }
	@RequestMapping(value = "/deleteteamMapping",method = RequestMethod.GET,headers="Accept=application/json")
    public long removeTeamMapping(HttpServletRequest request){
		long  playerId = Long.valueOf((String)request.getParameter("playerId"));
        this.userService.deleteTeamMappingByPlayerId(playerId);
        return playerId;
        
    }
	
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/getTeamByPlayerId", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<TeamVO> teamPlayer(HttpServletRequest request){
		
		long  playerId = Long.valueOf((String)request.getParameter("playerId"));
		logger.debug("Enter Method");
		TeamVO teamvo = null;
		if(playerId>0){
		
			try {
				teamvo = userService.getTeamByPlayerId(playerId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == teamvo){
					
					TeamControllerHelper helper = new TeamControllerHelper();
					helper.errorVO("TI","Team Invalid","Business Error");
					
					
					
				}
				
			}
			
			
		}
		
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<TeamVO>(teamvo, HttpStatus.OK);	
	}
	
	
}
