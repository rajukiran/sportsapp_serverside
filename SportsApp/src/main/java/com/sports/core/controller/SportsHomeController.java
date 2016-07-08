package com.sports.core.controller;


/*******************************************************************************
* Company     : AcknoTech 
* Copy Right  : All rights reserved.....  
* File Name   : SportsHomeController.java
* Description : This is file is used for master content  
* 
* 
* History :  Version  Description                         Date       Modify By  
*            1.0     Initial Version                      03-Apr-16   SubbaramiReddy
*
*
*********************************************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sports.core.exception.SportsException;

import com.sports.core.controller.helper.SportsHomeControllerHelper;
import com.sports.core.controller.helper.TeamControllerHelper;
import com.sports.core.model.UserVO;
import com.sports.core.model.CoachVO;
import com.sports.core.model.RequestsVO;
import com.sports.core.model.TeamVO;
import com.sports.core.model.UserGameMappingVO;
import com.sports.core.service.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SportsHomeController {

	private static final Logger logger = 
			LoggerFactory.getLogger(SportsHomeController.class);
	
	@Autowired
	private UserService userService;
	
	
	
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<UserVO> test(HttpServletRequest request )throws IOException{
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		logger.debug("Enter Method");
		UserVO user = null;
		if(userId>0){
		
			try {
				user = userService.findById(userId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == user){
					
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					
					
					
					return new ResponseEntity<UserVO>(helper.errorVO("NUV","Not User Valid","Business Error"), HttpStatus.OK);
					
					
				}
				
			}
			
			
		}
		
		else{
			
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			
			
			return new ResponseEntity<UserVO>(helper.errorVO("NUV","Not User Valid","Business Error"), HttpStatus.OK);
			
		}
		
		
		logger.debug("End Method");
		return new ResponseEntity<UserVO>(user, HttpStatus.OK);	
	}
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/getUser", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<UserVO>> userGameMap(HttpServletRequest request){
		
		long  userId = Long.valueOf((String)request.getParameter("userId"));
		logger.debug("Enter Method");
		List<UserVO> user = null;
		if(userId>0){
		
			try {
				user = userService.findUserGameMap(userId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == user){
					
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					helper.errorVO("NUI","Not User Invalid","Business Error");
					
					
					
				}
				
			}
			
			
		}
		
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<List<UserVO>>(user, HttpStatus.OK);	
	}
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/getTeamPlayer", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<UserVO>> teamPlayers(HttpServletRequest request){
		
		int  teamId = Integer.valueOf((String)request.getParameter("teamId"));
		logger.debug("Enter Method");
		List<UserVO> user = null;
		if(teamId>0){
		
			try {
				user = userService.getTeamPlayersById(teamId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == user){
					
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					helper.errorVO("NUI","Not User Invalid","Business Error");
					
					
					
				}
				
			}
			
			
		}
		
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<List<UserVO>>(user, HttpStatus.OK);	
	}
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/getteamplayerbyteamowner", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<UserVO>> teamPlayers1(HttpServletRequest request){
		
		long  teamOwnerId = Long.valueOf((String)request.getParameter("teamOwnerId"));
		logger.debug("Enter Method");
		List<UserVO> user = null;
		if(teamOwnerId>0){
		
			try {
				user = userService.getTeamPlayersByteamOwnerId(teamOwnerId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == user){
					
					SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
					helper.errorVO("NUI","Not User Invalid","Business Error");
					
					
					
				}
				
			}
			
			
		}
		
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<List<UserVO>>(user, HttpStatus.OK);	
	}
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST,value = "/upload/{userId}",consumes = {"multipart/form-data"},produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody byte[] create(@RequestBody MultipartFile fileData, @PathVariable long userId,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, SportsException {
		
		MultipartFile file = fileData;

		// Some type of file processing...
		System.out.println("--------------------------------------fileNamae-----"+file.getOriginalFilename());
		
			//MultipartFile file = userGameMap.getProfilePic();
		
		
		
			
				this.userService.updateProfile(userId,file.getBytes());
			
		
		System.out.println(file.getBytes());
			System.out.println("fileName:" + file.getOriginalFilename());
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			byte[] bytes = file.getBytes();
			UserVO uservo = new UserVO();
			System.out.println("bytes is"+bytes);
			byte[] result = null;
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				result =  IOUtils.toByteArray(inputStream);
				uservo.setProfilePic(result);
				uservo.setId(userId);
				System.out.println("result is"+result);
				if (file.getSize() > 10000) {
					System.out.println("File Size:::" + file.getSize());
					//return "/uploadfile";
				}
				System.out.println("size::" + file.getSize());
				//fileName = request.getRealPath("") + "/images/"
						//+ file.getOriginalFilename();
				//outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				try {
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					return buffer;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// ..........................................
			session.setAttribute("uploadFile", file.getOriginalFilename());
			
			
			
		return null;
		
	}
	
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/test4/{mappingId}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<UserGameMappingVO> test4(@PathVariable short mappingId){
		
		logger.debug("Enter Method");
		UserGameMappingVO userGame = null;
		if(mappingId>0){
		
			try {
				userGame = userService.findById2(mappingId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == userGame){
					
					
					
					//new SportsException();
					
				}
				
			}
			
			
		}
		
		
		
		
		logger.debug("End Method");
		return new ResponseEntity<UserGameMappingVO>(userGame, HttpStatus.OK);	
	}
	/*@RequestMapping(value= "/saveUserGameMap1", method = RequestMethod.GET ,headers="Accept=application/json")
	public String  user2(){
		
		//if(userVo.getId() == 0){
			//new person, add it
		UserGameMappingVO userGameMap = new UserGameMappingVO();
		userGameMap.setPriorityFlag("P");
		userGameMap.setYearsOfExp(4);
			this.userService.saveUser(userGameMap);
		//}else{
			//existing person, call update
			//this.userService.updateUser(userVo);
		//}
		
		return "redirect:/";
		
	}*/
	
	@SuppressWarnings({ "unused", "null" })
	@RequestMapping(value= "/saveUserGameMap", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserGameMappingVO userGameMap(@RequestBody UserGameMappingVO userGameMap){
		
		SecureRandom random = new SecureRandom();

		 
	    String rpwd = new BigInteger(130, random).toString(32);
	    userGameMap.setPassword(rpwd);
	   
		
		//if(userGameMap != null){
		try {
			int userId = this.userService.saveUser(userGameMap);
			if(userId != 0)
			{
			userGameMap.setUserId(userId);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth",true);
	                props.put("mail.smtp.starttls.enable","true");
	                
	               
			
			props.put("mail.smtp.port", "587");

			/*Scanner scanner = new Scanner(System.in);
	System.out.println("Username for Authentication :");
	final String username = scanner.nextLine();
	System.out.println("password for authentication :");
	final String password = scanner.nextLine();
	System.out.println("from email:");
	String fromEmailAddress = scanner.nextLine();
	System.out.println("to mail");
	String toEmailAddress = scanner.nextLine();
	System.out.println("subject");
	String subject = scanner.nextLine();
	System.out.println("message");
	String message = scanner.nextLine();*/
	Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("subbaram.kummithi@gmail.com","ramasubbareddy");
					}
				});

			try {

				Message message1 = new MimeMessage(session);
				message1.setFrom(new InternetAddress("subbaram.kummithi@gmail.com"));
				message1.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(userGameMap.getEmail()));
				message1.setSubject("Sprots Platform Registration Success");
				//message1.setText("your Registered Successfully, password is:"+userGameMap.getPassword()+" If you want reset password please Login");
				
				/*String message2 = "<div>To ensure the security of the account information associated with yourSports Platform login, please take a moment to click through the link belowand verify that we have the correct email address. If you do notconfirm your email address, your Red Hat login will eventually bedisabled.</div>";
				String message3 = "<div><p>To confirm your email address, please visit the following URL:</p>";
				String message4 = "<p><a href='aarvilift.com/#/resetpassword'>confirm</a></p>";
				String message5 = "</div><div>Thank you for using Sports Platform.</br></br>Account Information:Your login: "+userGameMap.getEmail()+"</br>Your email address: "+userGameMap.getEmail()+"</div>";
				String message = "<div id='container'><p><h3>Dear Sports Platform User,</h3></p></br><div>This email is sent to validate the email address that you haveprovided for your Sports Platform login. Your Sports Platform login, in combinationwith an active Sports Platform subscription, provides you with access tosystems management capabilities through Red Hat Network.</div>"+message2+""+message3+""+message4+""+message5+"";*/
				String message = "Your Registered successfully,New Password is:"+userGameMap.getPassword()+",please Reset your password";
				message1.setContent(message, "text/html");
				Transport.send(message1);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
				
			}
			
			}
			else{
			
				SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
				
				
				
				return helper.errorVO1("EAE","Email Already Exist","Duplicate Error");
			}
			
		} catch (SportsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		if(userGameMap == null)
		{
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			
			
			
			return helper.errorVO1("NULL","Not Inserted","Insertion Error");	
		}
	
		}
		/*}
		else{
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			return helper.errorVO1("NULL","Not Inserted","Insertion Error");
		}*/
		
		return userGameMap;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public  @ResponseBody UserVO user(){
		UserVO user = new UserVO();
		user.setId(1);
		user.setFirstName("subbu");
		return user;	
	}
	
	
	
	@RequestMapping(value = "/saveTeamMap", method = RequestMethod.POST)
	public  @ResponseBody Map<String,UserVO> saveTeamMap(@RequestBody UserVO userVo){
		UserVO user = new UserVO();
		Map<String,UserVO> list = new HashMap<String,UserVO>();
				
		user.setFirstName("Veera");
		//user.setAge(1);
		user.setId(2);
		list.put(String.valueOf(user.getId()),userVo);
		list.put(String.valueOf(userVo.getId()),user);
		return list;	
	}
	@RequestMapping(value= "/saveUser1", method = RequestMethod.POST ,headers="Accept=application/json")
	public @ResponseBody UserVO user(@RequestBody UserVO userVo){
		
		//if(userVo.getId() == 0){
			//new person, add it
			
			  long userId = this.userService.saveUser(userVo);
			  //System.out.println(userId);
			 userVo.setId(userId);
			 
		//}else{
			//existing person, call update
			//this.userService.updateUser(userVo);
		//}
			
		return userVo;
		
	}
	@RequestMapping(value= "/sendrequest", method = RequestMethod.POST ,headers="Accept=application/json")
	public @ResponseBody RequestsVO requests(@RequestBody RequestsVO requestVo){
		
		//if(userVo.getId() == 0){
			//new person, add it
			
			   this.userService.saveRequest(requestVo);
			  
			
		return requestVo;
		
	}
	@SuppressWarnings("unused")
	@RequestMapping(value= "/updateUser", method = RequestMethod.POST ,headers="Accept=application/json")
	public @ResponseBody UserVO updateUser(@RequestBody UserVO userVo) throws ParseException{
		
		if(userVo.getId() > 0){
			//new person, add it
			try {
			  this.userService.updateUser(userVo);
			 
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null == userVo){
					
					
					
					
				}
				
			}
			 
		}
			  
			
		return userVo;
		
	}
	@RequestMapping(value= "/deleteUserGame", method = RequestMethod.GET ,headers="Accept=application/json")
	public @ResponseBody String updateUser(HttpServletRequest request){
		
		int  mappingId = Integer.valueOf((String)request.getParameter("mappingId"));
			 this.userService.deleteUserGameById(mappingId);
		
			  
			
		return null;
		
	}
	
	@RequestMapping(value= "/updaterequest", method = RequestMethod.POST ,headers="Accept=application/json")
	public @ResponseBody RequestsVO updaterequests(@RequestBody RequestsVO requestVo){
		
		//if(userVo.getId() == 0){
			//new person, add it
			
			   this.userService.updateRequest(requestVo);
			  
			
		return requestVo;
		
	}
	@SuppressWarnings({ "unused", "unused" })
	@RequestMapping(value= "/updatePass", method = RequestMethod.POST ,headers="Accept=application/json")
	public ResponseEntity<UserVO> updatePass(@RequestBody UserVO userVo){
		
		
			//new person, add it
		long id =userVo.getId();
			//long id=10;
		
		if(userVo != null){
		try {
			
			
			   this.userService.updatePassword(id,userVo);
			  //System.out.println(userId);
			 //userVo.setId(userId);
			  Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.auth",true);
		                props.put("mail.smtp.starttls.enable","true");
		                
		               
				
				props.put("mail.smtp.port", "587");

				/*Scanner scanner = new Scanner(System.in);
		System.out.println("Username for Authentication :");
		final String username = scanner.nextLine();
		System.out.println("password for authentication :");
		final String password = scanner.nextLine();
		System.out.println("from email:");
		String fromEmailAddress = scanner.nextLine();
		System.out.println("to mail");
		String toEmailAddress = scanner.nextLine();
		System.out.println("subject");
		String subject = scanner.nextLine();
		System.out.println("message");
		String message = scanner.nextLine();*/
		Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("subbaram.kummithi@acknotech.com","subbaramireddy");
						}
					});

				try {

					Message message1 = new MimeMessage(session);
					message1.setFrom(new InternetAddress("subbaram.kummithi@acknotech.com"));
					message1.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(userVo.getEmail()));
					message1.setSubject("Sports Platform Password Reset Success");
					//message1.setText("your Registered Successfully, password is:"+userGameMap.getPassword()+" If you want reset password please Login");
					String message = "Password Reset Successfully";
					message1.setContent(message, "text/html");
					Transport.send(message1);

					System.out.println("Done");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
		} catch (SportsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(userVo == null){
				SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
				return new ResponseEntity<UserVO>(helper.errorVO("NUI","Not User Invalid","Business Error"), HttpStatus.OK);
			}
		}
		}
		else{
			SportsHomeControllerHelper helper = new SportsHomeControllerHelper();
			
			return new ResponseEntity<UserVO>(helper.errorVO("NUI","Not User Invalid","Business Error"), HttpStatus.OK);
		}
			
		return new ResponseEntity<UserVO>(userVo, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value =  "/allusers" , method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<UserVO>> getAllUsers() {	 
		  List<UserVO> user=this.userService.findAllUsers();
		  return new ResponseEntity<List<UserVO>>(user, HttpStatus.OK);
		
		 }
	


@RequestMapping(value =  "/sendrequests" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getSendRequests(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  senderId = Long.valueOf((String)request.getParameter("senderId"));
	  if(senderId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestsBySender(senderId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }
@RequestMapping(value =  "/getrequestbystatus" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getRequests(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  senderId = Long.valueOf((String)request.getParameter("senderId"));
	
	  if(senderId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestByStatus(senderId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }

@RequestMapping(value =  "/getrequestbystatuscoach" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getRequestsCoach(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  senderId = Long.valueOf((String)request.getParameter("senderId"));
	
	  if(senderId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestByStatusCoach(senderId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }

@RequestMapping(value =  "/getrequestbystatusstudent" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getRequestsStudent(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  senderId = Long.valueOf((String)request.getParameter("senderId"));
	
	  if(senderId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestByStatusStudent(senderId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }
@RequestMapping(value =  "/getrequestbystatusorg" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getRequestsOrg(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  senderId = Long.valueOf((String)request.getParameter("senderId"));
	
	  if(senderId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestByStatusOrg(senderId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }


@RequestMapping(value =  "/receivedrequests" , method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<RequestsVO>> getReceivedRequests(HttpServletRequest request) throws IOException{	 
	List<RequestsVO> requests= null;
	long  receiverId = Long.valueOf((String)request.getParameter("receiverId"));
	  if(receiverId > 0){
			//new person, add it
		  
			try {
				requests = this.userService.getRequestsByReceiver(receiverId);
			} catch (SportsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return new ResponseEntity<List<RequestsVO>>(requests, HttpStatus.OK);
	
	 }

@SuppressWarnings({ "static-access" })
@RequestMapping(value = "/getCoachByStudentId", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
public  ResponseEntity<List<UserVO>> CoachStudent(HttpServletRequest request){
	
	long  studentId = Long.valueOf((String)request.getParameter("studentId"));
	logger.debug("Enter Method");
	List<UserVO> uservo = null;
	if(studentId>0){
	
		try {
			uservo = userService.getCoachByStudentId(studentId);
		} catch (SportsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(null == uservo){
				
				TeamControllerHelper helper = new TeamControllerHelper();
				helper.errorVO("CI","Coach Invalid","Business Error");
				
				
				
			}
			
		}
		
		
	}
	
	
	
	
	logger.debug("End Method");
	return new ResponseEntity<List<UserVO>>(uservo, HttpStatus.OK);	
}

@SuppressWarnings({ "static-access" })
@RequestMapping(value = "/getStudentsByCoachId", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
public  ResponseEntity<List<UserVO>> CoachStudents(HttpServletRequest request){
	
	long  coachId = Long.valueOf((String)request.getParameter("userId"));
	logger.debug("Enter Method");
	List<UserVO> uservo = null;
	if(coachId>0){
	
		try {
			uservo = userService.getStudentsByCoachId(coachId);
		} catch (SportsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(null == uservo){
				
				TeamControllerHelper helper = new TeamControllerHelper();
				helper.errorVO("CI","Coach Invalid","Business Error");
				
				
				
			}
			
		}
		
		
	}
	
	
	
	
	logger.debug("End Method");
	return new ResponseEntity<List<UserVO>>(uservo, HttpStatus.OK);	
}

@RequestMapping(value= "/allplayers", method = RequestMethod.GET,headers="Accept=application/json")
public ResponseEntity<List<UserVO>> allplayers(HttpServletRequest request){
	
	
		List<UserVO> userGameMap = this.userService.getAllPlayers();
	
		
	
	
	return new ResponseEntity<List<UserVO>>(userGameMap, HttpStatus.OK);
	
}



}


