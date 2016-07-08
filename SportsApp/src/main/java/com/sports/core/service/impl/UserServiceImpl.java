package com.sports.core.service.impl;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
//import java.util.Collection;
//import java.util.Iterator;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sports.core.dao.UserDao;
import com.sports.core.exception.SportsException;
import com.sports.core.domain.Address;
import com.sports.core.domain.Attachments;
import com.sports.core.domain.FamilyDetails;
import com.sports.core.model.AddressVO;
import com.sports.core.model.CityVO;
import com.sports.core.model.ContinentVO;
import com.sports.core.model.CountryVO;
import com.sports.core.model.FamilyDetailsVO;
//import com.sports.core.domain.Games;
import com.sports.core.model.GamesVO;
import com.sports.core.model.GeoLocationVO;
import com.sports.core.domain.GeoLocation;

import com.sports.core.model.HistoryVO;

import com.sports.core.model.MatchVO;
import com.sports.core.model.OrganizationVO;
import com.sports.core.model.RequestsVO;
import com.sports.core.model.RoleVO;
import com.sports.core.model.ScheduleVO;
import com.sports.core.model.SkillLevelVO;
import com.sports.core.model.StateVO;
import com.sports.core.model.TeamVO;
import com.sports.core.model.UserVO;
import com.sports.core.model.UserGameMappingVO;
import com.sports.core.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<UserVO> users;
  int id1; 
	@Autowired
	private UserDao userDao;
	

	static {
		users = populateDummyUsers();
	}

	public List<UserVO> findAllUsers() {
		//return this.userDao.getAllUsers();
		List<com.sports.core.domain.User> user =  this.userDao.getAllUsers();
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.User users : user){
			
			if (null != users) {
				userTO = new UserVO();
				userTO.setId(users.getId());
				userTO.setFirstName(users.getFirstName());
				userTO.setLastName(users.getLastName());
				userTO.setRoleId(users.getRole().getRole_id());
				userTO.setRoleName(users.getRole().getRole_name());
				//userTO.setCreatedBy(users.getCreatedBy());
				//userTO.setCreatedDate(users.getCreatedDate());
				userTO.setEmail(users.getEmail());
				
				userTO.setProfilePic(users.getProfilePic());
				
				userTO.setDateOfBirth(users.getDateOfBirth());
				userTO.setGender(users.getGender());
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	public UserVO findById(long id)throws SportsException,IOException {

		com.sports.core.domain.User user = userDao.getUser(id);
		UserVO userTO = null;
		if (null != user) {
			userTO = new UserVO("asdfsaf","sadfsaf");
			userTO.setId(user.getId());
			userTO.setFirstName(user.getFirstName());
			userTO.setLastName(user.getLastName());
			userTO.setDateOfBirth(user.getDateOfBirth());
			userTO.setRoleId(user.getRole().getRole_id());
			userTO.setRoleName(user.getRole().getRole_name());
			userTO.setEmail(user.getEmail());
			userTO.setPhoneNum(user.getPhoneNum());
			userTO.setGender(user.getGender());
			if(user.getProfilePic() != null){
			
			userTO.setProfilePic(user.getProfilePic());
			}
			userTO.setPassword(user.getPassword());
			userTO.setCreatedBy(user.getCreatedBy());
			userTO.setCreatedDate(user.getCreatedDate());
			
			//userTO.setUserGameMapping(user.getUserGameMapping());
		}
		else{
			
			throw new SportsException("UNF");
		}
		/*
		 * for(User user : users){ if(user.getId() == id){ return user; } }
		 */
		return userTO;
	}

	@SuppressWarnings("unused")
	public UserGameMappingVO findById2(int id)throws SportsException{
		com.sports.core.domain.UserGameMapping userGameMapping = userDao.getUser1(id);
		UserGameMappingVO userGame = null;
		if(null != userGameMapping){
			userGame = new UserGameMappingVO();
			//userGame.setGameId(userGameMapping.getGames().getGame_id());
			//userGame.setMapping_id(userGameMapping.getMapping_id());
			//userGame.setUserId(userGameMapping.getUser().getId());
			//userGame.setLevelId(userGameMapping.getSkilllevel().getLevel_id());
			userGame.setPriorityFlag(userGameMapping.getPriority_flag());
			userGame.setYearsOfExp(userGameMapping.getYears_of_exp());
			
		}
		else{
			throw new SportsException("UNF");
		}
		return userGame;
	}
	public UserVO findByName(String name) {
		
		/*List<User> users1 = this.userDao.getAllUsers(name);
		for (User user : users1) {
			if (user.getFirstName().equalsIgnoreCase(name)) {
				return user;
			}
		}*/
		return null;
	}

	public long saveUser(UserVO user) {
		//user.setId(counter.incrementAndGet());
		//users.add(user);
		id1 = user.getRoleId();
		com.sports.core.domain.User users = new com.sports.core.domain.User();
		//teams.setGames(team.getGames());
		//users.setId(user.getId());
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		//Role role = new Role();
		//role.setRoleId(user.getRoleId());
		//user.setRole(role);
		//role.setRoleName(user.getRole_name());
		//users.setRole(user.getRole());
		
		return (Long) this.userDao.createUser(users,id1);
		
	}
	public int saveRequest(RequestsVO request) {
		
		long senderId = request.getSenderId();
		long receiverId = request.getReceiverId();
		long[] childId = request.getChildId();
		long receiverChildId = request.getReceiverChildId();
		System.out.println("receiver id is"+receiverId);
		System.out.println("sender id is"+senderId);
		com.sports.core.domain.Requests requests = new com.sports.core.domain.Requests();
		requests.setStatus(request.getStatus());
		requests.setMessage(request.getMessage());
		requests.setCreatedBy(request.getCreatedBy());
		return (int) this.userDao.createRequest(requests,senderId,receiverId,childId,receiverChildId);
		
	}
	public int saveUser(UserGameMappingVO userGameMapping) throws SportsException { 
		//user.setId(counter.incrementAndGet());
		//users.add(user);
		//int[] gameId = {1000,1001,1002};
		int id1 = 0;
		com.sports.core.domain.User users = null;
		if(null != userGameMapping){
		 id1 = userGameMapping.getRoleId();
		users = new com.sports.core.domain.User();
		
		users.setFirstName(userGameMapping.getFirstName());
		users.setLastName(userGameMapping.getLastName());
		users.setEmail(userGameMapping.getEmail());
		//SecureRandom random = new SecureRandom();

		 
	    //String rpwd = new BigInteger(130, random).toString(32);
		users.setPassword(userGameMapping.getPassword());
		users.setPhoneNum(userGameMapping.getPhoneNum());
		users.setGender(userGameMapping.getGender());
		users.setDateOfBirth(userGameMapping.getDateOfBirth());
		//users.setProfilePic(userGameMapping.getProfilePic());
		users.setCreatedBy(userGameMapping.getCreatedBy());
		
		
		
		}
		else{
			throw new SportsException("UNF");
		}
		//long userId = userGameMapping.getUserId();
		//long userId = 1;
		
		int levelId = userGameMapping.getLevelId();
		int gameId = userGameMapping.getGameId();
		
		//int levelId = 2;
		//int gameId = 1001;
		com.sports.core.domain.UserGameMapping userGameMap = new com.sports.core.domain.UserGameMapping();
		userGameMap.setPriority_flag(userGameMapping.getPriorityFlag());
		userGameMap.setYears_of_exp(userGameMapping.getYearsOfExp());
		userGameMap.setCreatedBy(userGameMapping.getCreatedBy());
		String createdBy = userGameMapping.getCreatedBy();
		int yearsOfExp = userGameMapping.getYearsOfExp();
		int[] ids = userGameMapping.getSecGameIds();
		for(int id:ids){
			System.out.println(id);
		}
		System.out.println(Arrays.toString(ids));
		
		//if(gameId != null){
			
		com.sports.core.domain.FamilyDetails family = new com.sports.core.domain.FamilyDetails();
		family.setChild_desc(userGameMapping.getChildDesc());
		long parentId = userGameMapping.getParentId();
		System.out.println(parentId);
		int userId = this.userDao.createUser(users,userGameMap,family,id1,ids,gameId,levelId,yearsOfExp,createdBy,parentId);
		
		 //String gameIds = null;
		/*StringTokenizer st = new StringTokenizer(Arrays.toString(ids),",");  
	     while (st.hasMoreTokens()) {  
	         System.out.println(st.nextToken()); 
	         //gameIds = st.nextToken();
	        // userGameMap.setPriority_flag("S");
	 		//userGameMap.setYears_of_exp(userGameMapping.getYearsOfExp());
	 		//this.userDao.createUser(users,userGameMap,id1,gameIds,levelId);
	         
	     }  */
		
	     
		return userId;
		
		
	}

	public void updateUser(UserVO user)throws SportsException,ParseException {
		//int index = users.indexOf(user);
		//users.set(index, user);
		com.sports.core.domain.User users = new com.sports.core.domain.User();
		if(null != user){
		users.setId(user.getId());
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmail(user.getEmail());
		
		
		SimpleDateFormat sm =new SimpleDateFormat("yyyy-MM-DD");
		
		
		
		Date parsed = null;
	    try {
	        parsed = sm.parse(user.getDate());
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		users.setDateOfBirth(sqlDate);
		users.setGender(user.getGender());
		users.setPhoneNum(user.getPhoneNum());
		users.setPassword(user.getPassword());
		users.setProfilePic(user.getProfilePic());
		int roleId = user.getRoleId();
		this.userDao.updateUser(users,roleId);
		com.sports.core.domain.UserGameMapping userGameMapping = new com.sports.core.domain.UserGameMapping();
		userGameMapping.setMapping_id(user.getMappingId());
		userGameMapping.setYears_of_exp(user.getYearsOfExp());
		userGameMapping.setPriority_flag("P");
		int levelId = user.getLevelId(); 
		int gameId = user.getGameId();
		long userId = user.getId();
		 int id2 = (int) user.getId();
		int yearsOfExp = user.getYearsOfExp();
		String updatedBy = user.getUpdatedBy();
		int[] ids = user.getSecGameIds();
		int mappingId = 1;
		for(int id:ids){
			System.out.println(id);
		}
		int[] ids1 = user.getSecMappingIds();
		
		for(int id1:ids1){
			System.out.println(id1);
		}
		com.sports.core.domain.Address address = new com.sports.core.domain.Address();
		if(user.getAddrId() == 0){
		address.setAddress_type_1(user.getAddrType1());
		address.setAddress_type_2(user.getAddrType2());
		address.setColumn_id(id2);
		address.setColumn_name("id");
		address.setTable_name("user");
		int continentId = user.getContinentId();
		int countryId =user.getCountryId();
		int stateId = user.getStateId();
		address.setPincode(user.getPincode());
		address.setCreatedBy("subbaram");
		
		this.userDao.createAddress(address,continentId,countryId,stateId);
		}
		else{
			address.setAddr_id(user.getAddrId());
			address.setAddress_type_1(user.getAddrType1());
			address.setAddress_type_2(user.getAddrType2());
			address.setColumn_id(id2);
			address.setColumn_name("id");
			address.setTable_name("user");
			int continentId = user.getContinentId();
			int countryId =user.getCountryId();
			int stateId = user.getStateId();
			address.setPincode(user.getPincode());
			this.userDao.updateAddress(address,continentId,countryId,stateId);
		}
		com.sports.core.domain.GeoLocation geolocation = new com.sports.core.domain.GeoLocation();
		if(user.getGeoLocationId() == 0 ){
			geolocation.setGeo_location_latit(user.getGeoLocationLatit());
			geolocation.setGeo_location_long(user.getGeoLocationLong());
			geolocation.setTable_name("user");
			geolocation.setColumn_id(id2);
			geolocation.setColumn_name("id");
			this.userDao.createGeoLocation(geolocation);
		}
		else{
			geolocation.setGeo_location_id(user.getGeoLocationId());
			geolocation.setGeo_location_latit(user.getGeoLocationLatit());
			geolocation.setGeo_location_long(user.getGeoLocationLong());
			geolocation.setTable_name("user");
			geolocation.setColumn_id(id2);
			geolocation.setColumn_name("id");
			this.userDao.updateGeoLocation(geolocation);
		}
		
		this.userDao.deleteUserGameMap(ids1);
		
		this.userDao.updateUserGameMap(users,userGameMapping,userId,roleId,mappingId,ids,gameId,levelId,yearsOfExp,updatedBy);
		}
		else{
			
				throw new SportsException("UNF");
			
		}
	}

	public void deleteUserById(long id) {
        this.userDao.deleteUser(id);
		/*for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}*/
	}
	public void deleteUserGameById(int id) {
        this.userDao.deleteUserGame(id);
		
	}

	public UserVO isUserExist(String userName,String userPassword) throws SportsException {
		System.out.println("In Service class...Check Login");
		
	List<com.sports.core.domain.User> user =  userDao.checkLogin1(userName, userPassword);
		UserVO userTO = null;
		//List<UserVO> user2 = new ArrayList<UserVO>();
		if((user != null) && (user.size() > 0)){
		for(com.sports.core.domain.User users : user){
			
			if (null != users) {
				userTO = new UserVO();
				userTO.setId(users.getId());
				userTO.setFirstName(users.getFirstName());
				userTO.setRoleId(users.getRole().getRole_id());
				userTO.setRoleName(users.getRole().getRole_name());
				userTO.setPassword(users.getPassword());
				userTO.setLastName(users.getLastName());
				userTO.setEmail(users.getEmail());
				userTO.setDateOfBirth(users.getDateOfBirth());
				userTO.setGender(users.getGender());
				userTO.setPhoneNum(users.getPhoneNum());
				userTO.setCreatedBy(users.getCreatedBy());
				userTO.setCreatedDate(users.getCreatedDate());
				System.out.println(userTO.getId());
				
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			else{
				throw new SportsException("UNF");
			}
			
			
			//user2.add((UserVO) userTO);
		}
		}
		else{
			throw new SportsException("UNF");
		}
		return userTO;
		
       
	}
	
	
	@Override
	public void saveCoachMapping(long coachId, long studentId, int requestId) {
		// TODO Auto-generated method stub
		com.sports.core.domain.CoachMapping coachMap = new com.sports.core.domain.CoachMapping();
		coachMap.setCreatedBy("subbaram");
		this.userDao.createCoachMapping(coachMap, coachId, studentId,requestId);
		
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<UserVO> populateDummyUsers() {
		List<UserVO> users = new ArrayList<UserVO>();
		//users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
		//users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
		//users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
		//users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
		return users;
	}

	public StateVO findById1(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.State state = userDao.getState(id);
		StateVO stateTO = null;
		if (null != state) {
			stateTO = new StateVO();
			stateTO.setStateId(state.getState_id());
			stateTO.setStateName(state.getState_name());
			stateTO.setStateDesc(state.getState_desc());
			//CountryVO country = new CountryVO();
			//stateTO.setCountry(country);
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return stateTO;
	}

	@Override
	public StateVO findByName1(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveState(StateVO state) {
		// TODO Auto-generated method stub
		//this.userDao.createState(state);
	}

	@Override
	public void updateState(StateVO state) {
		// TODO Auto-generated method stub
		//this.userDao.updateState(state);
	}

	@Override
	public void deleteStateById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteState(id);
	}

	@Override
	public List<StateVO> findAllStates() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllStates();
		return null;
	}

	@Override
	public List<StateVO> findAllStates(int countryId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.State> state =  this.userDao.getAllStates(countryId);
		StateVO stateTO = null;
		List<StateVO> state2 = new ArrayList<StateVO>();
		for(com.sports.core.domain.State states : state){
			
			if (null != states) {
				stateTO = new StateVO();
				stateTO.setStateId(states.getState_id());
				stateTO.setStateName(states.getState_name());
				stateTO.setStateDesc(states.getState_desc());
				
				System.out.println(stateTO.getStateId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			state2.add((StateVO)stateTO);
		}
		
		return state2;
		
		
	}
	@Override
	public void deleteAllStates() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStateExist(StateVO state) {
		// TODO Auto-generated method stub
		return false;
	}
/*List of teams */
	@Override
	public TeamVO findByTeamId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Team team = userDao.getTeam(id);
		TeamVO teamTO = null;
		if (null != team) {
			teamTO = new TeamVO();
			teamTO.setTeamId(team.getTeam_id());
			teamTO.setTeamName(team.getTeam_name());
			teamTO.setTeamDesc(team.getTeam_desc());
			teamTO.setTeamCoachName(team.getTeamCoach().getFirstName());
			teamTO.setTeamCaptainName(team.getTeamCaptain().getFirstName());
			teamTO.setTeamOwnerName(team.getTeamOwner().getFirstName());
			teamTO.setTeamOwner(team.getTeamOwner().getId());
			teamTO.setTeamCoach(team.getTeamCoach().getId());
			teamTO.setTeamCaptain(team.getTeamCaptain().getId());
			teamTO.setGameName(team.getGames().getGame_name());
			teamTO.setGameId(team.getGames().getGame_id());
			teamTO.setTeamOrg(team.getTeam_org());
			teamTO.setCreatedBy(team.getCreatedBy());
			teamTO.setCreatedDate(team.getCreatedDate());
			
			
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return teamTO;
	}

	@Override
	public TeamVO findByTeamName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTeam(TeamVO team) {
		// TODO Auto-generated method stub
		 //team = new com.sports.core.model.Team(); 
		//team.getGames();
		//team.getTeam_id();
		int gameId = team.getGameId();
		long userId = team.getUserId();
		long teamCoach = team.getTeamCoach();
		long teamCaptain = team.getTeamCaptain();
		
		com.sports.core.domain.Team teams = new com.sports.core.domain.Team();
		teams.setTeam_name(team.getTeamName());
		//teams.setTeam_captain(team.getTeamCaptain());
		//teams.setTeam_owner(team.getTeamOwner());
		teams.setTeam_desc(team.getTeamDesc());
		teams.setTeam_org(team.getTeamOrg());
		//teams.setTeam_coach(team.getTeamCoach());
		//teams.setPlayer_count(team.getPlayerCount());
		teams.setCreatedBy(team.getCreatedBy());
		//teams.setGames(team.getGames());
		com.sports.core.domain.Address address = new com.sports.core.domain.Address();
		
		address.setAddress_type_1(team.getAddrType1());
		address.setAddress_type_2(team.getAddrType2());
		
		address.setColumn_name("team_id");
		address.setTable_name("team");
		int continentId = team.getContinentId();
		int countryId =team.getCountryId();
		int stateId = team.getStateId();
		address.setPincode(team.getPincode());
		address.setCreatedBy(team.getCreatedBy());
		
		
		com.sports.core.domain.GeoLocation geolocation = new com.sports.core.domain.GeoLocation();
		geolocation.setGeo_location_long(team.getGeoLocationLong());
		geolocation.setGeo_location_latit(team.getGeoLocationLatit());
		geolocation.setTable_name("team");
		geolocation.setColumn_name("team_id");
		
		geolocation.setCreatedBy(team.getCreatedBy());
		
		this.userDao.createTeam(teams,geolocation,gameId,userId,teamCoach,teamCaptain,continentId,countryId,stateId,address);
	}
	@Override
	public void saveTeamMapping(long userId, long teamOwnerId,int requestId) {
		// TODO Auto-generated method stub
		com.sports.core.domain.TeamMapping teamMap = new com.sports.core.domain.TeamMapping();
		teamMap.setCreatedBy("subbaram");
		this.userDao.createTeamMapping(teamMap, teamOwnerId, userId,requestId);
	}
	@Override
	public void updateTeam(TeamVO team) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Team teams = new com.sports.core.domain.Team();
		//teams.setGames(team.getGames());
		teams.setTeam_id(team.getTeamId());
		teams.setTeam_name(team.getTeamName());
		teams.setUpdatedBy(team.getUpdatedBy());
		java.util.Date utilDate = new java.util.Date();
		
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		teams.setUpdatedDate(sqlDate);
		this.userDao.updateTeam(teams);
	}

	@Override
	public void deleteTeamById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteTeam(id);
	}
	@Override
	public void deleteTeamMappingByPlayerId(long playerId) {
		// TODO Auto-generated method stub
		this.userDao.deleteTeamMapping(playerId);
	}
	@Override
	public List<TeamVO> findAllTeams() {
		// TODO Auto-generated method stub
		 
		//Object[] obj = null;
		List<com.sports.core.domain.Team> team =  this.userDao.getAllTeams();
		TeamVO teamTO = null;
		List<TeamVO> team2 = new ArrayList<TeamVO>();
		for(com.sports.core.domain.Team teams : team){
			
			if (null != teams) {
				teamTO = new TeamVO();
				teamTO.setTeamId(teams.getTeam_id());
				teamTO.setTeamName(teams.getTeam_name());
				teamTO.setTeamDesc(teams.getTeam_desc());
				teamTO.setGameId(teams.getGames().getGame_id());
				teamTO.setGameName(teams.getGames().getGame_name());
				teamTO.setTeamOwner(teams.getTeamOwner().getId());
				teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
				teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
				
				teamTO.setTeamCoach(teams.getTeamCoach().getId());
				teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
				teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
				teamTO.setTeamOrg(teams.getTeam_org());
				teamTO.setPlayerCount(teams.getPlayer_count());
				System.out.println(teamTO.getTeamId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			team2.add((TeamVO)teamTO);
		}
		
		return team2;
		//Iterator<com.sports.core.domain.Team> itr = team.iterator();
		
        /*while(itr.hasNext()){
            System.out.println(itr.next());
            System.out.println(itr.toString());
        }*/
        
		/*List<Team> team2 = new ArrayList<Team>();
      for (int i = 0; i < obj.length; i++) {
    	    team2.add((Team) obj[i]);
    	}
		
		//team2.addAll(Object[] obj);
		//team2.add(1, teamTO);*/
		
		
		
		
		
		
	}
	
	@Override
	public List<TeamVO> findAllTeams(long userId) {
		// TODO Auto-generated method stub
		 
		//Object[] obj = null;
		List<com.sports.core.domain.Team> team =  this.userDao.getAllTeams(userId);
		TeamVO teamTO = null;
		List<TeamVO> team2 = new ArrayList<TeamVO>();
		for(com.sports.core.domain.Team teams : team){
			
			if (null != teams) {
				teamTO = new TeamVO();
				teamTO.setTeamId(teams.getTeam_id());
				teamTO.setTeamName(teams.getTeam_name());
				teamTO.setTeamDesc(teams.getTeam_desc());
				teamTO.setGameId(teams.getGames().getGame_id());
				teamTO.setGameName(teams.getGames().getGame_name());
				teamTO.setTeamOwner(teams.getTeamOwner().getId());
				teamTO.setTeamCoach(teams.getTeamCoach().getId());
				teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
				teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
				teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
				teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
				teamTO.setTeamOrg(teams.getTeam_org());
				teamTO.setPlayerCount(teams.getPlayer_count());
				teamTO.setVacancy(teams.getVacancy());
				System.out.println(teamTO.getTeamId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			team2.add((TeamVO)teamTO);
		}
		
		return team2;
	
	}
	@Override
	public List<TeamVO> findNearByTeam() {
		// TODO Auto-generated method stub
		 List<com.sports.core.domain.Team> team = this.userDao.getNearByTeam();
		 List<TeamVO> team2 = new ArrayList<TeamVO>();
		 TeamVO teamTO = null;
		 for(com.sports.core.domain.Team teams:team){
			 if(null != teams){
				 teamTO = new TeamVO();
				 teamTO.setTeamId(teams.getTeam_id());
					teamTO.setTeamName(teams.getTeam_name());
					teamTO.setTeamDesc(teams.getTeam_desc());
					teamTO.setGameId(teams.getGames().getGame_id());
					teamTO.setGameName(teams.getGames().getGame_name());
					teamTO.setTeamOwner(teams.getTeamOwner().getId());
					teamTO.setTeamCoach(teams.getTeamCoach().getId());
					teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
					teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
					teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
					teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
					teamTO.setTeamOrg(teams.getTeam_org());
					teamTO.setPlayerCount(teams.getPlayer_count());
					teamTO.setVacancy(teams.getVacancy());
					System.out.println(teamTO.getTeamId());
					
					 //obj = ((List<Team>) teamTO).toArray();
					// System.out.println(obj);
				}
				
				
				team2.add((TeamVO)teamTO);
		 }
		 return team2;
	}
	
	@Override
	public List<TeamVO> findNearByTeamForParents(long parentId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Team> team = this.userDao.getNearByTeam();
		 List<TeamVO> team2 = new ArrayList<TeamVO>();
		 TeamVO teamTO = null;
		 for(com.sports.core.domain.Team teams:team){
			 if(null != teams){
				 teamTO = new TeamVO();
				 List<com.sports.core.domain.UserGameMapping> family1 = userDao.getAllFamilyDetails(parentId);
					for(com.sports.core.domain.UserGameMapping families:family1){
					if(families != null){
						teamTO = new TeamVO();
						if(families.getGames().getGame_id() == teams.getGames().getGame_id()){
							
							 teamTO.setTeamId(teams.getTeam_id());
								teamTO.setTeamName(teams.getTeam_name());
								teamTO.setTeamDesc(teams.getTeam_desc());
								teamTO.setGameId(teams.getGames().getGame_id());
								teamTO.setGameName(teams.getGames().getGame_name());
								teamTO.setTeamOwner(teams.getTeamOwner().getId());
								teamTO.setTeamCoach(teams.getTeamCoach().getId());
								teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
								teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
								teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
								teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
								teamTO.setTeamOrg(teams.getTeam_org());
								teamTO.setPlayerCount(teams.getPlayer_count());
								teamTO.setVacancy(teams.getVacancy());
								
						}
					}
					if(families.getGames().getGame_id() == teams.getGames().getGame_id()){
					team2.add((TeamVO)teamTO);
					}
					
					 //obj = ((List<Team>) teamTO).toArray();
					// System.out.println(obj);
				}
				
			 }
				
		 }
		 return team2;
	}
	@Override
	public List<UserVO> getTeamPlayersById(int teamId) throws SportsException{
		// TODO Auto-generated method stub
		
		List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getTeamPlayersById(teamId);
		
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			else{
				
				throw new SportsException("UNF");
			}
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}
	@Override
	public List<UserVO> getTeamPlayersByteamOwnerId(long teamOwnerId) throws SportsException{
		// TODO Auto-generated method stub
		
		List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getTeamPlayersByTeamOwner(teamOwnerId);
		
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			else{
				
				throw new SportsException("UNF");
			}
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}
	
	@Override
	public TeamVO getTeamByPlayerId(long playerId) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.TeamMapping team =  userDao.getTeamByPlayerId(playerId);
		TeamVO teamTO = null;
		if (null != team) {
			teamTO = new TeamVO();
			 teamTO.setTeamId(team.getTeam().getTeam_id());
				teamTO.setTeamName(team.getTeam().getTeam_name());
				teamTO.setTeamDesc(team.getTeam().getTeam_desc());
				teamTO.setGameId(team.getTeam().getGames().getGame_id());
				teamTO.setGameName(team.getTeam().getGames().getGame_name());
				teamTO.setTeamOwner(team.getTeam().getTeamOwner().getId());
				teamTO.setTeamCoach(team.getTeam().getTeamCoach().getId());
				teamTO.setTeamCaptain(team.getTeam().getTeamCaptain().getId());
				teamTO.setTeamCoachName(team.getTeam().getTeamCoach().getFirstName());
				teamTO.setTeamCaptainName(team.getTeam().getTeamCaptain().getFirstName());
				teamTO.setTeamOwnerName(team.getTeam().getTeamOwner().getFirstName());
				teamTO.setTeamOrg(team.getTeam().getTeam_org());
				teamTO.setPlayerCount(team.getTeam().getPlayer_count());
				teamTO.setVacancy(team.getTeam().getVacancy());
		}
		else{
			throw new SportsException("UNF");
		}
		return teamTO;
	}
	
	
	@Override
	public void deleteAllTeams() {
		// TODO Auto-generated method stub
		
	}
	/*List of schedules */
	@Override
	public ScheduleVO findByScheduleId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Schedule schedule = userDao.getSchedule(id);
		ScheduleVO scheduleTO = null;
		if (null != schedule) {
			scheduleTO = new ScheduleVO();
			scheduleTO.setSchedId(schedule.getSched_id());
			
			scheduleTO.setSchedDesc(schedule.getSched_desc());
			//scheduleTO.setGames(schedule.getGames());
			//scheduleTO.setTeam(schedule.getTeam());
			//scheduleTO.setState(schedule.getState());
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return scheduleTO;
	}

	@Override
	public ScheduleVO findByScheduleName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSchedule(ScheduleVO schedule) {
		// TODO Auto-generated method stub
		//this.userDao.createSchedule(schedule);
	}

	@Override
	public void updateSchedule(ScheduleVO schedule) {
		// TODO Auto-generated method stub
		//this.userDao.updateSchedule(schedule);
	}

	@Override
	public void deleteScheduleById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteSchedule(id);
	}

	@Override
	public List<ScheduleVO> findAllSchedules() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Schedule> schedule = this.userDao.getAllSchedules();
		ScheduleVO scheduleTO = null;
		List<ScheduleVO> schedule2 = new ArrayList<ScheduleVO>();
		for(com.sports.core.domain.Schedule schedules : schedule){
			
			if (null != schedules) {
				scheduleTO = new ScheduleVO();
				scheduleTO.setSchedId(schedules.getSched_id());
				scheduleTO.setSchedDesc(schedules.getSched_desc());
				
				
				System.out.println(scheduleTO.getSchedId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			schedule2.add((ScheduleVO) scheduleTO);
		}
		
		return schedule2;
		
	}
	@Override
	public List<ScheduleVO> findMySchedules(long userId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Schedule> schedule = this.userDao.getAllSchedules(userId);
		ScheduleVO scheduleTO = null;
		List<ScheduleVO> schedule2 = new ArrayList<ScheduleVO>();
		for(com.sports.core.domain.Schedule schedules : schedule){
			
			if (null != schedules) {
				scheduleTO = new ScheduleVO();
				scheduleTO.setSchedId(schedules.getSched_id());
				scheduleTO.setSchedDesc(schedules.getSched_desc());
				scheduleTO.setCityName(schedules.getCity_name());
				scheduleTO.setStartDate(schedules.getStart_date());
				scheduleTO.setEndDate(schedules.getEnd_date());
				scheduleTO.setStartTime(schedules.getStart_time());
				scheduleTO.setEndTime(schedules.getEnd_time());
				scheduleTO.setMeridian(schedules.getMeridian());
				scheduleTO.setGameId(schedules.getGames().getGame_id());
				scheduleTO.setGameName(schedules.getGames().getGame_name());
				scheduleTO.setTeamId(schedules.getTeam().getTeam_id());
				scheduleTO.setTeamName(schedules.getTeam().getTeam_name());
				scheduleTO.setContinentId(schedules.getContinent().getContinent_id());
				scheduleTO.setContinentName(schedules.getContinent().getContinent_name());
				scheduleTO.setCountryName(schedules.getCountry().getCountry_name());
				scheduleTO.setStateName(schedules.getState().getState_name());
				System.out.println(scheduleTO.getSchedId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			schedule2.add((ScheduleVO) scheduleTO);
		}
		
		return schedule2;
		
	}
	
	@Override
	public void deleteAllSchedules() {
		// TODO Auto-generated method stub
		
	}
	/*List of Roles */
	@Override
	public RoleVO findByRoleId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Role role = userDao.getRole(id);
		RoleVO roleTO = null;
		if (null != role) {
			roleTO = new RoleVO();
			roleTO.setRoleId(role.getRole_id());
			roleTO.setRoleName(role.getRole_name());
			
			
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return roleTO;
	}

	@Override
	public RoleVO findByRoleName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRole(RoleVO role) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Role roles = new com.sports.core.domain.Role();
		//teams.setGames(team.getGames());
		roles.setRole_id(role.getRoleId());
		roles.setRole_name(role.getRoleName());
		roles.setRole_desc(role.getRoleDesc());
		roles.setCreatedBy(role.getCreatedBy());
		this.userDao.createRole(roles);
	}

	@Override
	public void updateRole(RoleVO role) {
		// TODO Auto-generated method stub
		//this.userDao.updateRole(role);
	}

	@Override
	public void deleteRoleById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteRole(id);
	}

	@Override
	public List<RoleVO> findAllRoles() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Role> role = this.userDao.getAllRoles();
		RoleVO roleTO = null;
		List<RoleVO> role2 = new ArrayList<RoleVO>();
		for(com.sports.core.domain.Role roles : role){
			
			if (null != roles) {
				roleTO = new RoleVO();
				roleTO.setRoleId(roles.getRole_id());
				roleTO.setRoleName(roles.getRole_name());
				roleTO.setCreatedBy(roles.getCreatedBy());
				
				System.out.println(roleTO.getRoleId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			role2.add((RoleVO) roleTO);
		}
		
		return role2;
		
	}

	@Override
	public void deleteAllRoles() {
		// TODO Auto-generated method stub
		
	}
	/*List of organizations */
	@Override
	public OrganizationVO findByOrganizationId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Organization organization = userDao.getOrganization(id);
		OrganizationVO organizationTO = null;
		if (null != organization) {
			organizationTO = new OrganizationVO();
			organizationTO.setOrgId(organization.getOrg_id());
			organizationTO.setOrgName(organization.getOrg_name());
			organizationTO.setOrgDesc(organization.getOrg_desc());
			organizationTO.setOrgOwner(organization.getOrg_owner().getId());
			
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return organizationTO;
	}

	@Override
	public OrganizationVO findByOrganizationName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrganization(OrganizationVO organizationVo) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Organization organization = new com.sports.core.domain.Organization();
		//teams.setGames(team.getGames());
		organization.setOrg_id(organizationVo.getOrgId());
		organization.setOrg_name(organizationVo.getOrgName());
		organization.setOrg_desc(organizationVo.getOrgDesc());
		//organization.setOrg_owner(organizationVo.getOrgOwner());
		organization.setCreatedBy(organizationVo.getCreatedBy());
		long userId = organizationVo.getUserId();
		this.userDao.createOrganization(organization,userId);
	}

	@Override
	public void updateOrganization(OrganizationVO organization) {
		// TODO Auto-generated method stub
		//this.userDao.updateOrganization(organization);
	}

	@Override
	public void deleteOrganizationById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteOrganization(id);
	}

	@Override
	public List<OrganizationVO> findAllOrganizations() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Organization> organization = this.userDao.getAllOrganizations();
		OrganizationVO orgTO = null;
		List<OrganizationVO> org2 = new ArrayList<OrganizationVO>();
		for(com.sports.core.domain.Organization orgs : organization){
			
			if (null != orgs) {
				orgTO = new OrganizationVO();
				orgTO.setOrgId(orgs.getOrg_id());
				orgTO.setOrgName(orgs.getOrg_name());
				orgTO.setOrgOwner(orgs.getOrg_owner().getId());
				orgTO.setOrgOwnerFirstName(orgs.getOrg_owner().getFirstName());
				orgTO.setOrgOwnerLastName(orgs.getOrg_owner().getLastName());
				orgTO.setOrgDesc(orgs.getOrg_desc());
				
				System.out.println(orgTO.getOrgId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			org2.add((OrganizationVO) orgTO);
		}
		
		return org2;
		
	}
	@Override
	public List<OrganizationVO> findAllOrganizations(long userId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.OrgMapping> organization = this.userDao.getAllOrganizations(userId);
		OrganizationVO orgTO = null;
		List<OrganizationVO> org2 = new ArrayList<OrganizationVO>();
		for(com.sports.core.domain.OrgMapping orgs : organization){
			
			if (null != orgs) {
				orgTO = new OrganizationVO();
				orgTO.setOrgId(orgs.getOrg().getOrg_id());
				orgTO.setOrgName(orgs.getOrg().getOrg_name());
				orgTO.setOrgOwner(orgs.getOrg().getOrg_owner().getId());
				orgTO.setOrgOwnerFirstName(orgs.getOrg().getOrg_owner().getFirstName());
				orgTO.setOrgOwnerLastName(orgs.getOrg().getOrg_owner().getLastName());
				orgTO.setOrgDesc(orgs.getOrg().getOrg_desc());
				
				System.out.println(orgTO.getOrgId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			org2.add((OrganizationVO) orgTO);
		}
		
		return org2;
		
	}
	
	@Override
	public void saveOrganizationMapping(long orgOwnerId,long userId,int requestId) {
		// TODO Auto-generated method stub
		com.sports.core.domain.OrgMapping orgMap = new com.sports.core.domain.OrgMapping();
		orgMap.setCreatedBy("subbaram");
		this.userDao.createOrgMapping(orgMap, orgOwnerId, userId,requestId);
	}
	@Override
	public void deleteAllOrganizations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MatchVO findByMatchId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Match match = userDao.getMatches(id);
		MatchVO matchTO = null;
		if (null != match) {
			matchTO = new MatchVO();
			matchTO.setMatchId(match.getMatch_id());
			matchTO.setMatchName(match.getMatch_name());
			
			
			
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		return matchTO;
	}
	/*List of matches */
	@Override
	public MatchVO findByMatchName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMatch(MatchVO matchVo) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Match matches = new com.sports.core.domain.Match();
		//teams.setGames(team.getGames());
		//matches.setMatch_id(matchVo.getMatchId());
		matches.setMatch_name(matchVo.getMatchName());
		matches.setMatch_desc(matchVo.getMatchDesc());
		matches.setStatus(matchVo.getStatus());
		matches.setCreatedBy(matchVo.getCreatedBy());
		int schedId = matchVo.getSchedId();
		int teamId = matchVo.getTeamId();
		this.userDao.createMatches(matches,schedId,teamId);
	}

	@Override
	public void updateMatch(MatchVO match) {
		// TODO Auto-generated method stub
		//this.userDao.updateMatch(match);
	}

	@Override
	public void deleteMatchById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteMatch(id);
	}

	@Override
	public List<MatchVO> findAllMatches() {
		// TODO Auto-generated method stub
		  
		List<com.sports.core.domain.Match> match1 = this.userDao.getAllMatches();
		MatchVO matchTO = null;
		List<MatchVO> match2 = new ArrayList<MatchVO>();
		for(com.sports.core.domain.Match matches : match1){
			
			if (null != matches) {
				matchTO = new MatchVO();
				matchTO.setMatchId(matches.getMatch_id());
				matchTO.setMatchName(matches.getMatch_name());
				
				
				System.out.println(matchTO.getMatchId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			match2.add((MatchVO) matchTO);
		}
		
		return match2;
		
	}
	@Override
	public List<MatchVO> findMyMatches(long userId) {
		// TODO Auto-generated method stub
		  
		List<com.sports.core.domain.Match> match1 = this.userDao.getAllMatches(userId);
		MatchVO matchTO = null;
		List<MatchVO> match2 = new ArrayList<MatchVO>();
		for(com.sports.core.domain.Match matches : match1){
			
			if (null != matches) {
				matchTO = new MatchVO();
				matchTO.setMatchId(matches.getMatch_id());
				matchTO.setMatchName(matches.getMatch_name());
				matchTO.setMatchDesc(matches.getMatch_desc());
				matchTO.setStatus(matches.getStatus());
				matchTO.setTeamId(matches.getTeam().getTeam_id());
				matchTO.setTeamName(matches.getTeam().getTeam_name());
				matchTO.setSchedDesc(matches.getSchedule().getSched_desc());
				
				System.out.println(matchTO.getMatchId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			match2.add((MatchVO) matchTO);
		}
		
		return match2;
		
	}
	@Override
	public void deleteAllMatches() {
		// TODO Auto-generated method stub
		
	}
/*List of History table */
	@Override
	public HistoryVO findByHistoryId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.History history = userDao.getHistory(id);
		HistoryVO historyTO = null;
		if (null != history) {
			historyTO = new HistoryVO();
			historyTO.setHistoryId(history.getHistory_id());
			//historyTO.setSchedule(history.getSchedule());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return historyTO;
	}

	@Override
	public HistoryVO findByHistoryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveHistory(HistoryVO historyVo) {
		// TODO Auto-generated method stub
		//this.userDao.createHistory(history);
		com.sports.core.domain.History history = new com.sports.core.domain.History();
		//teams.setGames(team.getGames());
		//matches.setMatch_id(matchVo.getMatchId());
	
		int schedId = historyVo.getSchedId();
		int teamId = historyVo.getTeamId();
		long userId = historyVo.getUserId();
		int matchId = historyVo.getMatchId();
		
		this.userDao.createHistory(history,schedId,teamId,userId,matchId);
	}

	@Override
	public void updateHistory(HistoryVO history) {
		// TODO Auto-generated method stub
		//this.userDao.updateHistory(history);
	}

	@Override
	public void deleteHistoryById(int id) {
		// TODO Auto-generated method stub
		//this.deleteHistoryById(id);
	}

	@Override
	public List<HistoryVO> findAllHistory() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.History> history = this.userDao.getAllHistory();
		HistoryVO historyTO = null;
		List<HistoryVO> history2 = new ArrayList<HistoryVO>();
		for(com.sports.core.domain.History histories : history){
			
			if (null != histories) {
				historyTO = new HistoryVO();
				historyTO.setHistoryId(histories.getHistory_id());
				
				
				
				System.out.println(historyTO.getHistoryId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			history2.add((HistoryVO) historyTO);
		}
		
		return history2;
		
	}

	@Override
	public void deleteAllHistory() {
		// TODO Auto-generated method stub
		
	}
/*List of Country table */
	@Override
	public CountryVO findByCountryId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Country country = userDao.getCountry(id);
		CountryVO countryTO = null;
		if (null != country) {
			countryTO = new CountryVO();
			countryTO.setCountryId(country.getCountry_id());
			countryTO.setCountryName(country.getCountry_name());
			//countryTO.setContinent(country.getContinent());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return countryTO;
	}

	@Override
	public CountryVO findByCountryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCountry(CountryVO country) {
		// TODO Auto-generated method stub
		//this.userDao.createCountry(country);
	}

	@Override
	public void updateCountry(CountryVO country) {
		// TODO Auto-generated method stub
		//this.userDao.updateCountry(country);
	}

	@Override
	public void deleteCountryById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteCountry(id);
	}

	@Override
	public List<CountryVO> findAllCountry() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllCountry();
		List<com.sports.core.domain.Country> country = this.userDao.getAllCountry();
		CountryVO countryTO = null;
		List<CountryVO> country2 = new ArrayList<CountryVO>();
		for(com.sports.core.domain.Country countries : country){
			
			if (null != countries) {
				countryTO = new CountryVO();
				countryTO.setCountryId(countries.getCountry_id());
				countryTO.setCountryName(countries.getCountry_name());
				
				
				System.out.println(countryTO.getCountryId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			country2.add((CountryVO) countryTO);
		}
		
		return country2;
		
	}

	@Override
	public void deleteAllCountry() {
		// TODO Auto-generated method stub
		
	}
/* List of Continent table */
	@Override
	public ContinentVO findByContinentId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Continent continent = userDao.getContinent(id);
		ContinentVO continentTO = null;
		if (null != continent) {
			continentTO = new ContinentVO();
			continentTO.setContinentId(continent.getContinent_id());
			continentTO.setContinentName(continent.getContinent_name());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return continentTO;
	}

	@Override
	public ContinentVO findByContinentName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContinent(ContinentVO continent) {
		// TODO Auto-generated method stub
		//this.userDao.createContinent(continent);
	}

	@Override
	public void updateContinent(ContinentVO continent) {
		// TODO Auto-generated method stub
		//this.userDao.updateContinent(continent);
	}

	@Override
	public void deleteContinentById(int id) {
		// TODO Auto-generated method stub
		//this.userDao.deleteContinent(id);
	}

	@Override
	public List<ContinentVO> findAllContinents() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllContinent();
		List<com.sports.core.domain.Continent> continent = this.userDao.getAllContinent();
		ContinentVO continentTO = null;
		List<ContinentVO> continent2 = new ArrayList<ContinentVO>();
		for(com.sports.core.domain.Continent continents : continent){
			
			if (null != continents) {
				continentTO = new ContinentVO();
				continentTO.setContinentId(continents.getContinent_id());
				continentTO.setContinentName(continents.getContinent_name());
				
				
				System.out.println(continentTO.getContinentId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			continent2.add((ContinentVO) continentTO);
		}
		
		return continent2;
		
	}

	@Override
	public void deleteAllContinents() {
		// TODO Auto-generated method stub
		
	}
/* List of city table */
	@Override
	public CityVO findByCityId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.City city = userDao.getCity(id);
		CityVO cityTO = null;
		if (null != city) {
			cityTO = new CityVO();
			cityTO.setCityId(city.getCity_id());
			//cityTO.setState(city.getState());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return cityTO;
	}

	@Override
	public CityVO findByCityName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCity(CityVO city) {
		// TODO Auto-generated method stub
		//this.userDao.createCity(city);
	}

	@Override
	public void updateCity(CityVO city) {
		// TODO Auto-generated method stub
		//this.userDao.updateCity(city);
	}

	@Override
	public void deleteCityById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteCity(id);
	}

	@Override
	public List<CityVO> findAllCity() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllCity();
		return null;
	}

	@Override
	public void deleteAllCity() {
		// TODO Auto-generated method stub
		
	}
/*List of address table */
	@Override
	public AddressVO findByAddressId(int id) throws SportsException {
		// TODO Auto-generated method stub
		Address address = userDao.getAddress(id);
		AddressVO addressTO = null;
		if (null != address) {
			addressTO = new AddressVO();
			addressTO.setAddrId(address.getAddr_id());
			//addressTO.setState(address.getState());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return addressTO;
	}

	@Override
	public void saveAddress(AddressVO address) {
		// TODO Auto-generated method stub
		//this.userDao.createAddress(address);
	}

	@Override
	public void updateAddress(AddressVO address) {
		// TODO Auto-generated method stub
		//this.userDao.updateAddress(address);
	}

	@Override
	public void deleteAddressById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteAddress(id);
	}

	@Override
	public List<AddressVO> findAllAddresses() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllAddress();
		return null;
	}

	@Override
	public void deleteAllAddresses() {
		// TODO Auto-generated method stub
		
	}
/*List of attachments table */
	@Override
	public Attachments findByAttachmentsId(int id) throws SportsException {
		// TODO Auto-generated method stub
		Attachments attachments = userDao.getAttachement(id);
		Attachments attachmentsTO = null;
		if (null != attachments) {
			attachmentsTO = new Attachments();
			attachmentsTO.setAttach_id(attachments.getAttach_id());
			attachmentsTO.setAttach_desc(attachments.getAttach_desc());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return attachmentsTO;
	}

	@Override
	public void saveAttachment(Attachments attachments) {
		// TODO Auto-generated method stub
		this.userDao.createAttachment(attachments);
	}

	@Override
	public void updateAttachment(Attachments attachments) {
		// TODO Auto-generated method stub
		this.userDao.updateAttachements(attachments);
	}

	@Override
	public void deleteAttachmentById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteAttachements(id);
	}

	@Override
	public List<Attachments> findAllAttachments() {
		// TODO Auto-generated method stub
		return this.userDao.getAllAttachments();
	}

	@Override
	public void deleteAllAttachments() {
		// TODO Auto-generated method stub
		
	}
/*List of family details table */
	@Override
	public FamilyDetailsVO findByFamilyId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.FamilyDetails familyDetails = userDao.getFamily(id);
		FamilyDetailsVO familyTO = null;
		if (null != familyDetails) {
			familyTO = new FamilyDetailsVO();
			familyTO.setChildDesc(familyDetails.getChild_desc());
			familyTO.setFamilyId(familyDetails.getFamily_id());
			//familyTO.setRegId(familyDetails.getRegId());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return familyTO;
	}

	@Override
	public void saveFamily(FamilyDetailsVO familyDetails) {
		// TODO Auto-generated method stub
		//this.userDao.createFamily(familyDetails);
	}

	@Override
	public void updateFamily(FamilyDetailsVO familyDetails) {
		// TODO Auto-generated method stub
		//this.userDao.updateFamily(familyDetails);
	}

	@Override
	public void deleteFamilyById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteFamily(id);
	}

	@Override
	public List<FamilyDetailsVO> findAllFamilyDetails() {
		// TODO Auto-generated method stub
		//return this.userDao.getAllFamilyDetails();
		List<com.sports.core.domain.FamilyDetails> family = this.userDao.getAllFamilyDetails();
		FamilyDetailsVO familyTO = null;
		List<FamilyDetailsVO> family2 = new ArrayList<FamilyDetailsVO>();
		for(com.sports.core.domain.FamilyDetails families : family){
			
			if (null != families) {
				familyTO = new FamilyDetailsVO();
				familyTO.setFamilyId(families.getFamily_id());
				familyTO.setChildName(families.getUser().getFirstName());
				familyTO.setParentName(families.getUser1().getFirstName());
				familyTO.setChildDesc(families.getChild_desc());
				familyTO.setChildLastName(families.getUser().getLastName());
				familyTO.setChildemail(families.getUser().getEmail());
				familyTO.setChildpassword(families.getUser().getPassword());
				familyTO.setChildDateOfBirth(families.getUser().getDateOfBirth());
				familyTO.setChildGender(families.getUser().getGender());
				
				System.out.println(familyTO.getFamilyId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			family2.add((FamilyDetailsVO) familyTO);
		}
		
		return family2;
		 
		
	}
	@Override
	public List<UserVO> findAllFamilyDetails(long parentId) {
		// TODO Auto-generated method stub
		//return this.userDao.getAllFamilyDetails();
		List<com.sports.core.domain.UserGameMapping> family = this.userDao.getAllFamilyDetails(parentId);
		UserVO userTO = null;
		List<UserVO> family2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : family){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			family2.add((UserVO) userTO);
		}
		
		return family2;
		 
		
	}
	@Override
	public List<UserVO> findAllFamilyDetailsForUnjoin(long parentId) {
		// TODO Auto-generated method stub
		//return this.userDao.getAllFamilyDetails();
		List<com.sports.core.domain.FamilyDetails> family = this.userDao.getAllFamilyDetails1(parentId);
		UserVO userTO = null;
		List<UserVO> family2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.FamilyDetails childs : family){
			
			if (null != childs) {
				userTO = new UserVO();
				
				long childId = childs.getUser().getId();
				List<com.sports.core.domain.TeamMapping> teamchilds = this.userDao.getAllChilds(childId);
				for(com.sports.core.domain.TeamMapping teamchild:teamchilds){
					if(teamchild != null){
						userTO = new UserVO();
						userTO.setId(teamchild.getUser().getId());
						userTO.setFirstName(teamchild.getUser().getFirstName());
						userTO.setLastName(teamchild.getUser().getLastName());
						userTO.setRoleId(teamchild.getUser().getRole().getRole_id());
						userTO.setRoleName(teamchild.getUser().getRole().getRole_name());
					}
					family2.add((UserVO) userTO);
				}
			}
			
			
			
		}
		
		return family2;
		 
		
	}
	@Override
	public List<UserVO> findAllFamilyDetailsForUnjoinToCoach(long parentId) {
		// TODO Auto-generated method stub
		//return this.userDao.getAllFamilyDetails();
		List<com.sports.core.domain.FamilyDetails> family = this.userDao.getAllFamilyDetails1(parentId);
		UserVO userTO = null;
		List<UserVO> family2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.FamilyDetails childs : family){
			
			if (null != childs) {
				userTO = new UserVO();
				
				long childId = childs.getUser().getId();
				List<com.sports.core.domain.CoachMapping> coachchilds = this.userDao.getAllChildsForCoach(childId);
				for(com.sports.core.domain.CoachMapping coachchild:coachchilds){
					if(coachchild != null){
						userTO = new UserVO();
						userTO.setId(coachchild.getStudent().getId());
						userTO.setFirstName(coachchild.getStudent().getFirstName());
						userTO.setLastName(coachchild.getStudent().getLastName());
						userTO.setRoleId(coachchild.getStudent().getRole().getRole_id());
						userTO.setRoleName(coachchild.getStudent().getRole().getRole_name());
					}
					family2.add((UserVO) userTO);
				}
			}
			
			
			
		}
		
		return family2;
		 
		
	}
	@SuppressWarnings("unused")
	@Override
	public List<UserVO> findAllFamilyDetailsForAdd(long parentId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.FamilyDetails> family = this.userDao.getAllFamilyDetails1(parentId);
		UserVO userTO = null;
		List<UserVO> family2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.FamilyDetails childs : family){
			
			if (null != childs) {
				System.out.println(childs.getUser().getId());
				userTO = new UserVO();
				List<com.sports.core.domain.TeamMapping> teamchilds = this.userDao.getAllChilds();
				if((teamchilds != null) && (teamchilds.size() > 0)){
				for(com.sports.core.domain.TeamMapping teamchild:teamchilds){
					userTO = new UserVO();
					if(teamchild != null){
						if(childs.getUser().getId() != teamchild.getUser().getId()){
							userTO.setId(childs.getUser().getId());
							userTO.setFirstName(childs.getUser().getFirstName());
							userTO.setLastName(childs.getUser().getLastName());
							userTO.setRoleId(childs.getUser().getRole().getRole_id());
							userTO.setRoleName(childs.getUser().getRole().getRole_name());
						}
					}
					
					
					  if(childs.getUser().getId() != teamchild.getUser().getId()){
					   family2.add((UserVO) userTO);
					 }
					
				}
				
				}else{
					userTO = new UserVO();
					userTO.setId(childs.getUser().getId());
					userTO.setFirstName(childs.getUser().getFirstName());
					userTO.setLastName(childs.getUser().getLastName());
					userTO.setRoleId(childs.getUser().getRole().getRole_id());
					userTO.setRoleName(childs.getUser().getRole().getRole_name());
					family2.add((UserVO) userTO);
				}
				
				
			}
		}	
		return family2;
	}
	@Override
	public List<UserVO> findAllFamilyDetailsForAddToCoach(long parentId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.FamilyDetails> family = this.userDao.getAllFamilyDetails1(parentId);
		UserVO userTO = null;
		List<UserVO> family2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.FamilyDetails childs : family){
			
			if (null != childs) {
				userTO = new UserVO();
				List<com.sports.core.domain.CoachMapping> coachchilds = this.userDao.getAllChildsForCoach();
				if((coachchilds != null) && (coachchilds.size() > 0)){
				for(com.sports.core.domain.CoachMapping coachchild:coachchilds){
					userTO = new UserVO();
					if(coachchild != null){
						if(childs.getUser().getId() != coachchild.getStudent().getId()){
							userTO.setId(childs.getUser().getId());
							userTO.setFirstName(childs.getUser().getFirstName());
							userTO.setLastName(childs.getUser().getLastName());
							userTO.setRoleId(childs.getUser().getRole().getRole_id());
							userTO.setRoleName(childs.getUser().getRole().getRole_name());
						}
					}
					if(childs.getUser().getId() != coachchild.getStudent().getId()){
					family2.add((UserVO) userTO);
					}
				}
				
				}else{
					userTO.setId(childs.getUser().getId());
					userTO.setFirstName(childs.getUser().getFirstName());
					userTO.setLastName(childs.getUser().getLastName());
					userTO.setRoleId(childs.getUser().getRole().getRole_id());
					userTO.setRoleName(childs.getUser().getRole().getRole_name());
					family2.add((UserVO) userTO);
				}
				
			}
		}	
		return family2;
	}
	
	

	@Override
	public void deleteAllFamilyDetails() {
		// TODO Auto-generated method stub
		
	}
/*List of GeoLocation's table */
	@Override
	public GeoLocation findByGeoLocationId(int id) throws SportsException {
		// TODO Auto-generated method stub
		GeoLocation geoLocation = userDao.getGeoLocation(id);
		GeoLocation geolocationTO = null;
		if (null != geoLocation) {
			geolocationTO = new GeoLocation();
			geolocationTO.setGeo_location_id(geoLocation.getGeo_location_id());
			geolocationTO.setTable_name(geoLocation.getTable_name());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return geolocationTO;
	}

	@Override
	public void saveGeoLocation(GeoLocationVO geoLocation) {
		// TODO Auto-generated method stub
		com.sports.core.domain.GeoLocation geoloc = new com.sports.core.domain.GeoLocation();
		geoloc.setGeo_location_long(geoLocation.getGeoLocationLong());
		geoloc.setGeo_location_latit(geoLocation.getGeoLocationLatit());
		geoloc.setTable_name(geoLocation.getTableName());
		geoloc.setColumn_name(geoLocation.getColumnName());
		geoloc.setCreatedBy("subbaram");
		geoloc.setColumn_id(geoLocation.getColumnId());
				this.userDao.createGeoLocation(geoloc);
	}

	@Override
	public void updateGeoLocation(GeoLocation geoLocation) {
		// TODO Auto-generated method stub
		this.userDao.updateGeoLocation(geoLocation);
	}

	@Override
	public void deleteGeoLocationById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteGeoLocation(id);
	}

	@Override
	public List<GeoLocation> findAllGeoLocation() {
		// TODO Auto-generated method stub
		return this.userDao.getAllGeoLocations();
	}

	@Override
	public void deleteAllGeoLocation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GamesVO findByGameId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Games games = userDao.getGames(id);
		GamesVO gamesTO = null;
		if (null != games) {
			gamesTO = new GamesVO();
			gamesTO.setGameId(games.getGame_id());
			gamesTO.setGameName(games.getGame_name());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return gamesTO;
	}

	@Override
	public void saveGame(GamesVO games) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Games game = new com.sports.core.domain.Games();
		//teams.setGames(team.getGames());
		game.setGame_id(games.getGameId());
		game.setGame_name(games.getGameName());
		game.setGame_desc(games.getGameDesc());
		game.setCreatedBy(games.getCreatedBy());
		this.userDao.createGames(game);
	}

	@Override
	public void updateGame(GamesVO games) {
		// TODO Auto-generated method stub
		
		//this.userDao.updateGames(games);
	}

	@Override
	public void deleteGameById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteGames(id);
	}

	@Override
	public List<GamesVO> findAllGames() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Games> game = this.userDao.getAllGames();
		GamesVO gameTO = null;
		List<GamesVO> games2 = new ArrayList<GamesVO>();
		for(com.sports.core.domain.Games games : game){
			
			if (null != games) {
				gameTO = new GamesVO();
				gameTO.setGameId(games.getGame_id());
				gameTO.setGameName(games.getGame_name());
				
				
				System.out.println(gameTO.getGameId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			games2.add((GamesVO) gameTO);
		}
		
		return games2;
		
		
	}

	@Override
	public void deleteAllGames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SkillLevelVO findByLevelId(int id) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.SkillLevel skillLevel = userDao.getSkillLevel(id);
		SkillLevelVO levelTO = null;
		if (null != skillLevel) {
			levelTO = new SkillLevelVO();
			levelTO.setLevelId(skillLevel.getLevel_id());
			levelTO.setLevelName(skillLevel.getLevel_name());
		}
		else{
			
			throw new SportsException("UNF");
		}
		return levelTO;
	}

	@Override
	public void saveLevel(SkillLevelVO skillLevel) {
		// TODO Auto-generated method stub
		com.sports.core.domain.SkillLevel levels = new com.sports.core.domain.SkillLevel();
		//teams.setGames(team.getGames());
		levels.setLevel_id(skillLevel.getLevelId());
		levels.setLevel_name(skillLevel.getLevelName());
		levels.setLevel_desc(skillLevel.getLevelDesc());
		levels.setCreatedBy(skillLevel.getCreatedBy());
		this.userDao.createSkillLevel(levels);
	}

	@Override
	public void updateLevel(SkillLevelVO skillLevel) {
		// TODO Auto-generated method stub
		//this.userDao.updateSkillLevel(skillLevel);
	}

	@Override
	public void deleteSkillLevelById(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteSkillLevel(id);
	}

	@Override
	public List<SkillLevelVO> findAllLevels() {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.SkillLevel> level = this.userDao.getAllSkillLevel();
		SkillLevelVO levelTO = null;
		List<SkillLevelVO> level2 = new ArrayList<SkillLevelVO>();
		for(com.sports.core.domain.SkillLevel levels : level){
			
			if (null != levels) {
				levelTO = new SkillLevelVO();
				levelTO.setLevelId(levels.getLevel_id());
				levelTO.setLevelName(levels.getLevel_name());
				
				
				System.out.println(levelTO.getLevelId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			level2.add((SkillLevelVO) levelTO);
		}
		
		return level2;
	}

	@Override
	public void deleteAllLevels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CountryVO> findAllCountry(int continentId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Country> country =  this.userDao.getAllCountry(continentId);
		CountryVO countryTO = null;
		List<CountryVO> country2 = new ArrayList<CountryVO>();
		for(com.sports.core.domain.Country countries : country){
			
			if (null != countries) {
				countryTO = new CountryVO();
				countryTO.setCountryId(countries.getCountry_id());
				countryTO.setCountryName(countries.getCountry_name());
				countryTO.setCountryDesc(countries.getCountry_desc());
				
				System.out.println(countryTO.getCountryId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			country2.add((CountryVO)countryTO);
		}
		
		return country2;
		
	}

	@Override
	public List<CityVO> findAllCity(int stateId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.City> city =  this.userDao.getAllCity(stateId);
		CityVO cityTO = null;
		List<CityVO> city2 = new ArrayList<CityVO>();
		for(com.sports.core.domain.City cities : city){
			
			if (null != cities) {
				cityTO = new CityVO();
				cityTO.setCityId(cities.getCity_id());
				cityTO.setCityName(cities.getCity_name());
				cityTO.setCityDesc(cities.getCity_desc());
				
				System.out.println(cityTO.getCityId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			city2.add((CityVO)cityTO);
		}
		
		return city2;
		
	}

	@Override
	public void updatePassword(long id,UserVO userVo)throws SportsException {
		// TODO Auto-generated method stub
		String password = userVo.getPassword();
		com.sports.core.domain.User user =this.userDao.updatePass(id,password);
		if(user == null)
		{
			throw new SportsException("UNF");
		}
		
	}

	@Override
	public UserVO updateProfile(long id,byte[] profilePic)throws SportsException {
		// TODO Auto-generated method stub
		
		com.sports.core.domain.User user =this.userDao.updateProfile(id,profilePic);
		UserVO userTO = null;
		if(user != null)
		{
			userTO = new UserVO();
			//userTO.setProfilePic(user.getProfilePic());
			userTO.setId(user.getId());
			//userTO.setFirstName(user.getFirstName());
		}
		else{
			throw new SportsException("UNF");
		}
		
		return userTO;
		
	}

	
	@Override
	public List<UserVO> findUserGameMap(long userId) throws SportsException{ 
		// TODO Auto-generated method stub
		List<com.sports.core.domain.UserGameMapping> userGameMap =  this.userDao.getUserGameMap(userId);
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			else{
				
				throw new SportsException("UNF");
			}
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	@Override
	public AddressVO findAddress(int columnId,String tableName) throws SportsException{
		// TODO Auto-generated method stub
		
		com.sports.core.domain.Address address = this.userDao.getAddress(columnId, tableName);
		AddressVO addressTO = null;
		if (null != address) {
			addressTO = new AddressVO();
			addressTO.setAddrId(address.getAddr_id());
			addressTO.setAddrType1(address.getAddress_type_1());
			addressTO.setAddrType2(address.getAddress_type_2());
			addressTO.setContinentId(address.getContinent().getContinent_id());
			addressTO.setContinentName(address.getContinent().getContinent_name());
			addressTO.setCountryId(address.getCountry().getCountry_id());
			addressTO.setCountryName(address.getCountry().getCountry_name());
			addressTO.setStateId(address.getState().getState_id());
			addressTO.setStateName(address.getState().getState_name());
			addressTO.setPincode(address.getPincode());
		}
		else{
			
			throw new SportsException("UNF");
		}
		
		return addressTO; 
	}

	@Override
	public GeoLocationVO findLocation(int columnId, String tableName) throws SportsException {
		// TODO Auto-generated method stub
		
		com.sports.core.domain.GeoLocation geolocation = this.userDao.getGeoLocation(columnId, tableName);
		GeoLocationVO geolocationTO = null;
		if (null != geolocation) {
			geolocationTO = new GeoLocationVO();
			geolocationTO.setGeoLocationId(geolocation.getGeo_location_id());
			geolocationTO.setGeoLocationLatit(geolocation.getGeo_location_latit());
			geolocationTO.setGeoLocationLong(geolocation.getGeo_location_long());
			
		}
		else{
			
			throw new SportsException("UNF");
		}
		
		return geolocationTO; 
	}

	

	@Override
	public List<RequestsVO> getRequestsBySender(long senderId) throws SportsException{
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestsBySender(senderId);
		com.sports.core.domain.FamilyDetails family = userDao.getFamilyByChildId(senderId);
		
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		
		for(com.sports.core.domain.Requests request:requests)
		{
			if(request != null)
			{
				requestTO = new RequestsVO();
				
				if(family != null){
					requestTO.setParentId(family.getUser1().getId());
					requestTO.setParentFirstName(family.getUser1().getFirstName());
					requestTO.setParentLastName(family.getUser1().getLastName());
					}
				List<com.sports.core.domain.FamilyDetails> family1 = userDao.getAllFamilyDetails1(senderId);
				for(com.sports.core.domain.FamilyDetails families:family1){
				if(families != null){
					
					long childId = families.getUser().getId();
					System.out.println(childId);
					List<com.sports.core.domain.Requests> requests1 = userDao.getRequestsBySender(childId);
					for(com.sports.core.domain.Requests request1:requests1)
					{
						if(request1 != null)
						{
							System.out.println(request1.getUser().getId());
							requestTO = new RequestsVO();
					requestTO.setRequestId(request.getRequest_id());
					requestTO.setMessage(request1.getMessage());
					requestTO.setStatus(request1.getStatus());
					requestTO.setSenderId(request.getUser().getId());
					requestTO.setReceiverId(request.getUser1().getId());
					requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
					requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
					requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
					requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
					requestTO.setReceiverFirstName(request.getUser1().getFirstName());
					requestTO.setReceiverLastName(request.getUser1().getLastName());
					requestTO.setSenderFirstName(request.getUser().getFirstName());
					requestTO.setSenderLastName(request.getUser().getLastName());
					requestTO.setCreatedDate(request.getCreatedDate());
					requestTO.setChildsId(request1.getUser().getId());
					requestTO.setChildFirstName(request1.getUser().getFirstName());
					requestTO.setChildLastName(request1.getUser().getLastName());
						}
						request2.add(requestTO);
					}
				}
			}
				
				int roleId = request.getUser().getRole().getRole_id();
				if(roleId != 103){
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setCreatedDate(request.getCreatedDate());
				}
				
			}
			else{
				throw new SportsException("UNF");
			}
			if(request.getUser().getRole().getRole_id() != 103){
				request2.add(requestTO);
				}
		}
		return request2;
	}

	@Override
	public List<RequestsVO> getRequestsByReceiver(long receiverId) throws SportsException{
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestsByReceiver(receiverId);
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		for(com.sports.core.domain.Requests request:requests)
		{
			if(request != null)
			{
				requestTO = new RequestsVO();
				long senderId = request.getUser().getId();
				com.sports.core.domain.FamilyDetails family = userDao.getFamilyByChildId(senderId);
				if(family != null){
					requestTO.setParentId(family.getUser1().getId());
					requestTO.setParentFirstName(family.getUser1().getFirstName());
					requestTO.setParentLastName(family.getUser1().getLastName());
					}
				//RequestsVO requestTO1 = null;
				List<com.sports.core.domain.FamilyDetails> family1 = userDao.getAllFamilyDetails1(receiverId);
				for(com.sports.core.domain.FamilyDetails families:family1){
				if(families != null){
					
					long childId = families.getUser().getId();
					System.out.println(childId);
					List<com.sports.core.domain.Requests> requests1 = userDao.getRequestsByReceiver(childId);
					for(com.sports.core.domain.Requests request1:requests1)
					{
						if(request1 != null)
						{
							System.out.println(request1.getUser().getId());
							requestTO = new RequestsVO();
					requestTO.setRequestId(request.getRequest_id());
					requestTO.setMessage(request.getMessage());
					requestTO.setStatus(request.getStatus());
					requestTO.setSenderId(request.getUser().getId());
					requestTO.setReceiverId(request.getUser1().getId());
					requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
					requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
					requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
					requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
					requestTO.setReceiverFirstName(request.getUser1().getFirstName());
					requestTO.setReceiverLastName(request.getUser1().getLastName());
					requestTO.setSenderFirstName(request.getUser().getFirstName());
					requestTO.setSenderLastName(request.getUser().getLastName());
					requestTO.setCreatedDate(request.getCreatedDate());
					requestTO.setChildsId(request1.getUser1().getId());
					requestTO.setChildFirstName(request1.getUser1().getFirstName());
					requestTO.setChildLastName(request1.getUser1().getLastName());
						}
						request2.add(requestTO);
					}
				}
				//request2.add(requestTO);
				
				
			}
				//FamilyDetails[] array = new FamilyDetails[family1.size()];
				//family1.toArray(array);
				int roleId = request.getUser1().getRole().getRole_id();
				if(roleId != 103 && request.getUser().getRole().getRole_id() != 103 ){
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
				requestTO.setCreatedDate(request.getCreatedDate());
				}
				
			}
			else{
				throw new SportsException("UNF"); 
			}
			if(request.getUser1().getRole().getRole_id() != 103 && request.getUser().getRole().getRole_id() != 103){
			request2.add(requestTO);
			}
			
			
		}
		return request2;
	}
	@Override
	public List<RequestsVO> getRequestByStatus(long senderId) throws SportsException {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestByStatus(senderId);
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		for(com.sports.core.domain.Requests request:requests){
			if(request != null)
			{
				requestTO = new RequestsVO();
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
			}
			else{
				throw new SportsException("UNF");
			}
			request2.add(requestTO);
		}
		return request2;
	}
	
	@Override
	public List<RequestsVO> getRequestByStatusCoach(long senderId) throws SportsException {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestByStatusCoach(senderId);
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		for(com.sports.core.domain.Requests request:requests){
			if(request != null)
			{
				requestTO = new RequestsVO();
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
			}
			else{
				throw new SportsException("UNF");
			}
			request2.add(requestTO);
		}
		return request2;
	}
	
	@Override
	public List<RequestsVO> getRequestByStatusStudent(long senderId) throws SportsException {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestByStatusStudent(senderId);
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		for(com.sports.core.domain.Requests request:requests){
			if(request != null)
			{
				requestTO = new RequestsVO();
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
			}
			else{
				throw new SportsException("UNF");
			}
			request2.add(requestTO);
		}
		return request2;
	}
	

	@Override
	public List<RequestsVO> getRequestByStatusOrg(long senderId) throws SportsException {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Requests> requests = userDao.getRequestByStatusOrg(senderId);
		RequestsVO requestTO = null;
		List<RequestsVO> request2 = new ArrayList<RequestsVO>();
		for(com.sports.core.domain.Requests request:requests){
			if(request != null)
			{
				requestTO = new RequestsVO();
				requestTO.setRequestId(request.getRequest_id());
				requestTO.setMessage(request.getMessage());
				requestTO.setStatus(request.getStatus());
				requestTO.setSenderId(request.getUser().getId());
				requestTO.setReceiverId(request.getUser1().getId());
				requestTO.setSenderRoleId(request.getUser().getRole().getRole_id());
				requestTO.setSenderRoleName(request.getUser().getRole().getRole_name());
				requestTO.setReceiverRoleId(request.getUser1().getRole().getRole_id());
				requestTO.setReceiverRoleName(request.getUser1().getRole().getRole_name());
				requestTO.setReceiverFirstName(request.getUser1().getFirstName());
				requestTO.setReceiverLastName(request.getUser1().getLastName());
				requestTO.setSenderFirstName(request.getUser().getFirstName());
				requestTO.setSenderLastName(request.getUser().getLastName());
			}
			else{
				throw new SportsException("UNF");
			}
		request2.add(requestTO);
	}
		
	return request2;
	}

	
	@Override
	public List<UserVO> getCoachByStudentId(long studentId) throws SportsException {
		// TODO Auto-generated method stub
		
		List<com.sports.core.domain.UserGameMapping> userGameMap =  this.userDao.getCoachByStudentId(studentId);
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			else{
				
				throw new SportsException("UNF");
			}
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
		
	}

	@Override
	public void updateRequest(RequestsVO requestvo) {
		// TODO Auto-generated method stub
		String message = requestvo.getMessage();
		String status = requestvo.getStatus();
		int requestId = requestvo.getRequestId();
		com.sports.core.domain.Requests user =this.userDao.updateRequest(requestId,message,status);
	}

	@Override
	public List<UserVO> getStudentsByCoachId(long coachId) throws SportsException {
		// TODO Auto-generated method stub
List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getStudentsByCoachId(coachId);
		
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			else{
				
				throw new SportsException("UNF");
			}
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
		
		
		
	}

	@Override
	public List<UserVO> getAllCoaches() {
		// TODO Auto-generated method stub
List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getAllCoaches();
		
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	@Override
	public void deleteCoachMappingByStudentId(long studentId) {
		// TODO Auto-generated method stub
		this.userDao.deleteCoachMapping(studentId);
	}

	@Override
	public void deleteOrgMappingByUserId(long userId) {
		// TODO Auto-generated method stub
		this.userDao.deleteCoachMapping(userId);
	}

	@Override
	public List<UserVO> getAllPlayers() {
		// TODO Auto-generated method stub
List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getAllPlayers();
		
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				long userId = userGameMaps.getUser().getId();
				//System.out.println(userId);
				List<com.sports.core.domain.FamilyDetails> family = userDao.getFamilyByChildId1(userId);
				for(com.sports.core.domain.FamilyDetails families:family){
				if(families != null){
					userTO.setParentId(families.getUser1().getId());
					userTO.setParentFirstName(families.getUser1().getFirstName());
					userTO.setParentLastName(families.getUser1().getLastName());
					
					}
					}
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	@Override
	public void updateTeam(long teamOwner, long coachId,int requestId) throws SportsException {
		// TODO Auto-generated method stub
		
		com.sports.core.domain.Team team =this.userDao.updateTeam(teamOwner,coachId,requestId);
		if(team == null)
		{
			throw new SportsException("UNF");
		}
	}

	@Override
	public void saveCoachMappingForChilds(long coachId, long studentId, int requestId) {
		// TODO Auto-generated method stub
		com.sports.core.domain.CoachMapping coachMap = new com.sports.core.domain.CoachMapping();
		coachMap.setCreatedBy("subbaram");
		this.userDao.createCoachMappingForChilds(coachMap, coachId, studentId,requestId);
	}

	@Override
	public List<UserVO> findOrgPlayers(long userId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getOrgPlayers(userId);
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	@Override
	public List<UserVO> findOrgCoaches(long userId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.UserGameMapping> userGameMap =  userDao.getOrgCoaches(userId);
		UserVO userTO = null;
		List<UserVO> user2 = new ArrayList<UserVO>();
		for(com.sports.core.domain.UserGameMapping userGameMaps : userGameMap){
			
			if (null != userGameMaps) {
				userTO = new UserVO();
				userTO.setMappingId(userGameMaps.getMapping_id());
				//userGameMapTO.setUser(cities.getCity_name());
				//userGameMapTO.setCityDesc(cities.getCity_desc());
				userTO.setId(userGameMaps.getUser().getId());
				
				userTO.setFirstName(userGameMaps.getUser().getFirstName());
				userTO.setLastName(userGameMaps.getUser().getLastName());
				userTO.setDateOfBirth(userGameMaps.getUser().getDateOfBirth());
				userTO.setRoleId(userGameMaps.getUser().getRole().getRole_id());
				userTO.setRoleName(userGameMaps.getUser().getRole().getRole_name());
				userTO.setEmail(userGameMaps.getUser().getEmail());
				userTO.setPhoneNum(userGameMaps.getUser().getPhoneNum());
				userTO.setGender(userGameMaps.getUser().getGender());
				userTO.setPassword(userGameMaps.getUser().getPassword());
				userTO.setProfilePic(userGameMaps.getUser().getProfilePic());
				userTO.setGameId(userGameMaps.getGames().getGame_id());
				userTO.setGameName(userGameMaps.getGames().getGame_name());
				userTO.setLevelId(userGameMaps.getSkilllevel().getLevel_id());
				userTO.setLevelName(userGameMaps.getSkilllevel().getLevel_name());
				userTO.setYearsOfExp(userGameMaps.getYears_of_exp());
				userTO.setPriorityFlag(userGameMaps.getPriority_flag());
				//String priorityFlag =  userGameMaps.getPriority_flag();
				/*if(priorityFlag == 'S'){
					
					userTO.setGameId(userGameMaps.getGames().getGame_id());
				}*/
				System.out.println(userTO.getId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			
			user2.add((UserVO)userTO);
		}
		
		return user2;
	}

	@Override
	public List<TeamVO> findOrgTeams(long userId) {
		// TODO Auto-generated method stub
		List<com.sports.core.domain.Team> team =  this.userDao.getOrgTeams(userId);
		TeamVO teamTO = null;
		List<TeamVO> team2 = new ArrayList<TeamVO>();
		for(com.sports.core.domain.Team teams : team){
			
			if (null != teams) {
				teamTO = new TeamVO();
				teamTO.setTeamId(teams.getTeam_id());
				teamTO.setTeamName(teams.getTeam_name());
				teamTO.setTeamDesc(teams.getTeam_desc());
				teamTO.setGameId(teams.getGames().getGame_id());
				teamTO.setGameName(teams.getGames().getGame_name());
				teamTO.setTeamOwner(teams.getTeamOwner().getId());
				teamTO.setTeamCoach(teams.getTeamCoach().getId());
				teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
				teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
				teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
				teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
				teamTO.setTeamOrg(teams.getTeam_org());
				teamTO.setPlayerCount(teams.getPlayer_count());
				teamTO.setVacancy(teams.getVacancy());
				System.out.println(teamTO.getTeamId());
				
				 //obj = ((List<Team>) teamTO).toArray();
				// System.out.println(obj);
			}
			
			
			team2.add((TeamVO)teamTO);
		}
		
		return team2;
	}

	@Override
	public TeamVO findTeamByCoach(long coachId) throws SportsException {
		// TODO Auto-generated method stub
		com.sports.core.domain.Team teams =  this.userDao.getTeamByCoach(coachId);
		TeamVO teamTO = null;
		if (null != teams) {
			teamTO = new TeamVO();
			teamTO.setTeamId(teams.getTeam_id());
			teamTO.setTeamName(teams.getTeam_name());
			teamTO.setTeamDesc(teams.getTeam_desc());
			teamTO.setGameId(teams.getGames().getGame_id());
			teamTO.setGameName(teams.getGames().getGame_name());
			teamTO.setTeamOwner(teams.getTeamOwner().getId());
			teamTO.setTeamCoach(teams.getTeamCoach().getId());
			teamTO.setTeamCaptain(teams.getTeamCaptain().getId());
			teamTO.setTeamCoachName(teams.getTeamCoach().getFirstName());
			teamTO.setTeamCaptainName(teams.getTeamCaptain().getFirstName());
			teamTO.setTeamOwnerName(teams.getTeamOwner().getFirstName());
			teamTO.setTeamOrg(teams.getTeam_org());
			teamTO.setPlayerCount(teams.getPlayer_count());
			teamTO.setVacancy(teams.getVacancy());
			System.out.println(teamTO.getTeamId());
			
			 //obj = ((List<Team>) teamTO).toArray();
			// System.out.println(obj);
		}
else{
			
			throw new SportsException("UNF");
		}

     return teamTO;
	
	}

	@Override
	public void saveTeamMappingForChilds(long teamOwnerId, long userId, int requestId) {
		// TODO Auto-generated method stub
		com.sports.core.domain.TeamMapping teamMap = new com.sports.core.domain.TeamMapping();
		teamMap.setCreatedBy("subbaram");
		this.userDao.createTeamMappingForChilds(teamMap, teamOwnerId, userId,requestId);
	}

	

	

	

	

	

	

	

	

	
}
	
	
	

