package com.sports.core.controller.address;
/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : AddressController.java
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sports.core.controller.helper.AddressControllerHelper;
import com.sports.core.model.AddressVO;
import com.sports.core.exception.SportsException;
import com.sports.core.service.UserService;

@RestController
public class AddressController {

	private static final Logger logger = 
			LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/address/{addrId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<AddressVO> address(@PathVariable short addrId){
		
		logger.debug("Enter Method");
		AddressVO address = null;
		if(addrId>0){
		
			try {
				address = userService.findByAddressId(addrId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == address){
					AddressControllerHelper helper = new AddressControllerHelper();
					helper.errorVO("AI","Address Invalid","Business Error");
					return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
					
					
				}
				
			}
			
			
		}
		else{
			AddressControllerHelper helper = new AddressControllerHelper();
			helper.errorVO("AIE","Address Is Empty","Business Error");
			return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
			
			}
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
	}
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "/getaddress", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<AddressVO> findaddress(@RequestBody AddressVO addressvo){
		
		logger.debug("Enter Method");
		AddressVO address = null;
		
		if(addressvo != null){
		
			try {
				address = userService.findAddress(addressvo.getColumnId(),addressvo.getTableName());
				
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == address){
					AddressControllerHelper helper = new AddressControllerHelper();
					helper.errorVO("AI","Address Invalid","Business Error");
					return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
					
					
				}
				
			}
			
			
		}
		else{
			AddressControllerHelper helper = new AddressControllerHelper();
			helper.errorVO("AIE","Address Is Empty","Business Error");
			return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
			
			}
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<AddressVO>(address, HttpStatus.OK);	
	}
	@RequestMapping(value= "/address/add/", method = RequestMethod.GET,headers="Accept=application/json")
	public String addAddress(@ModelAttribute("address") AddressVO address){
		
		if(address.getAddrId() == 0){
			//new person, add it
			
			this.userService.saveAddress(address);
		}else{
			//existing person, call update
			this.userService.updateAddress(address);
		}
		
		return "this.userService.findAllAddress()";
		
	}
	@RequestMapping(value =  "/alladdress" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<AddressVO>> getAllAddresses() {	 
		  List<AddressVO> address=this.userService.findAllAddresses();
		  return new ResponseEntity<List<AddressVO>>(address, HttpStatus.OK);
		
		 }
	
	@RequestMapping(value = "/address/remove/{addrId}",method = RequestMethod.GET,headers="Accept=application/json")
    public String removeAddress(@PathVariable("addrId") int id){
		
        this.userService.deleteAddressById(id);
        return "redirect:/alladdress";
    }
}
