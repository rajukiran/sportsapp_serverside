package com.sports.core.controller.games;

/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : GamesController.java
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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.sports.core.controller.helper.GamesControllerHelper;
import com.sports.core.model.GamesVO;

import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;
@RestController
public class GamesController {

	private static final Logger logger = 
			LoggerFactory.getLogger(GamesController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<GamesVO> games(@PathVariable int gameId){
		
		logger.debug("Enter Method");
		GamesVO games = null;
		if(gameId>0){
		
			try {
				games = userService.findByGameId(gameId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == games){
					GamesControllerHelper helper = new GamesControllerHelper();
					return new ResponseEntity<GamesVO>(helper.errorVO("GI","Games Invalid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			GamesControllerHelper helper = new GamesControllerHelper();
			
			return new ResponseEntity<GamesVO>(helper.errorVO("GI","Games Invalid","Business Error"), HttpStatus.OK);
			
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<GamesVO>(games, HttpStatus.OK);	
	}
	@RequestMapping(value= "/saveGames", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody GamesVO saveGames(@RequestBody GamesVO gameVo){
		
		//if(g.getGameId() == 0){
			//new person, add it
			
			this.userService.saveGame(gameVo);
		//}else{
			//existing person, call update
			//this.userService.updateGame(g);
		//}
		
		return gameVo;
		
	}
	@RequestMapping(value =  "/allgames" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<GamesVO>> getAllGames() {	 
		  List<GamesVO> games=this.userService.findAllGames();
		  return new ResponseEntity<List<GamesVO>>(games, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/games/remove/{gameId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeTeam(@PathVariable("gameId") int id){
		
        this.userService.deleteGameById(id);
        return "redirect:/allgames";
    }
	
	
}
