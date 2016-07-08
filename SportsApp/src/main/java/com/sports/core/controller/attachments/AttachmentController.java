package com.sports.core.controller.attachments;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : AttachmentController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      24-Apr-16  SubbaramiReddy
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


import com.sports.core.controller.helper.SportsHomeControllerHelper;
import com.sports.core.domain.Attachments;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class AttachmentController {

	private static final Logger logger = 
			LoggerFactory.getLogger(AttachmentController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/attachments/{attachId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Attachments> attachments(@PathVariable short attachId){
		
		logger.debug("Enter Method");
		Attachments attachment = null;
		if(attachId>0){
		
			try {
				attachment = userService.findByAttachmentsId(attachId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == attachment){
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					helper.errorDO("NUI","Not User Invalid","Business Error");
					
					
					
				}
				
			}
			
			
		}
		
		else{
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			helper.errorDO("NUI","Not User Invalid","Business Error");
			
			
		}
		logger.debug("End Method");
		return new ResponseEntity<Attachments>(attachment, HttpStatus.OK);	
	}
	@RequestMapping(value= "/attachment/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addAttachment(@ModelAttribute("attachment") Attachments attach){
		
		if(attach.getAttach_id() == 0){
			//new person, add it
			
			this.userService.saveAttachment(attach);
		}else{
			//existing person, call update
			this.userService.updateAttachment(attach);
		}
		
		return "this.userService.findAllAttachments()";
		
	}
	@RequestMapping(value =  "/allattachments" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<Attachments>> getAllAttachments() {	 
		  List<Attachments> attachment=this.userService.findAllAttachments();
		  return new ResponseEntity<List<Attachments>>(attachment, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/attachment/remove/{attachId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeAttachment(@PathVariable("attachId") int id){
		
        this.userService.deleteAttachmentById(id);
        return "redirect:/allattachments";
    }
	
}
