package com.sports.core.dao.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.sports.core.dao.UserDao;
import com.sports.core.domain.Address;
import com.sports.core.domain.Attachments;
import com.sports.core.domain.City;
import com.sports.core.domain.CoachMapping;
import com.sports.core.domain.Continent;
import com.sports.core.domain.Country;
import com.sports.core.domain.FamilyDetails;
import com.sports.core.domain.Games;
import com.sports.core.domain.GeoLocation;
import com.sports.core.domain.History;
import com.sports.core.domain.OrgMapping;
import com.sports.core.domain.Organization;
import com.sports.core.domain.PlayersCount;
import com.sports.core.domain.Requests;
import com.sports.core.domain.Role;
import com.sports.core.domain.Schedule;
import com.sports.core.domain.SkillLevel;
import com.sports.core.domain.State;
import com.sports.core.domain.Team;
import com.sports.core.domain.TeamMapping;
import com.sports.core.domain.User;
import com.sports.core.domain.UserGameMapping;
//import com.sports.core.model.TeamVO;

import com.sports.core.util.HibernateUtil;



@Repository
public class UserDaoImpl implements UserDao {

	int id1;
	
	public UserDaoImpl() {
		System.out.println("EmployeeDAOImpl");
		
	}

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public long createUser(User user,int id1) {
		
		
		
		System.out.println(id1);
		Role role = hibernateUtil.fetchById(id1,Role.class);
		
		user.setRole(role);
		
		long id = (Long) hibernateUtil.create(user);
		 System.out.println("sadfsfsda"+id);
		return id;
	}
	@Override
	public int createRequest(Requests requests, long senderId, long receiverId,long[] childId,long receiverChildId) {
		// TODO Auto-generated method stub
		User sender = hibernateUtil.fetchById(senderId,User.class);
		User receiver = hibernateUtil.fetchById(receiverId,User.class);
		if((childId != null) && (childId.length > 0)){
		for(long childIds:childId){
			User sender1 = hibernateUtil.fetchById(childIds,User.class);
			if(sender1 != null){
			Requests request = new Requests();
			request.setUser(sender1);
			request.setUser1(receiver);
			request.setCreatedBy(requests.getCreatedBy());
			request.setMessage(requests.getMessage());
			request.setStatus("pending");
			hibernateUtil.create(request);
			}
		}
	}
		if(receiverChildId != 0){
			User receiver1 = hibernateUtil.fetchById(receiverChildId,User.class);
			if(receiver1 != null){
			Requests request1 = new Requests();
			request1.setUser(sender);
			request1.setUser1(receiver1);
			request1.setMessage(requests.getMessage());
			request1.setStatus("pending");
			request1.setCreatedBy(requests.getCreatedBy());
			hibernateUtil.create(request1);
			}
		}
		requests.setUser(sender);
		requests.setUser1(receiver);
		return (int) hibernateUtil.create(requests);
	}
	
	@Override
	public int createUser(User user,UserGameMapping userGameMapping,FamilyDetails family, int id1,int[] ids,int gameId, int levelId,int yearsOfExp,String createdBy,long parentId) {
		// TODO Auto-generated method stub
		Games games = null;
		SkillLevel level = null;
		if(gameId != 0 && levelId !=0){
		 games = hibernateUtil.fetchById(gameId,Games.class);
		 level = hibernateUtil.fetchById(levelId,SkillLevel.class);
		}
		//Games games1 = hibernateUtil.fetchById(gameIds,Games.class);
		
		//id1 = users.getRoleId();
		System.out.println(id1);
		Role role = hibernateUtil.fetchById(id1,Role.class);
		//User user1 = new User();
		user.setRole(role);
		long userId = 0;
		if(checkEmail(user)){
		 userId = (Long) hibernateUtil.create(user);
		}
		else{
			return 0;
		}
		User users1 = null;
		User users2 = null;
		
		if(userId != 0){
		 users1 = hibernateUtil.fetchById(userId,User.class);
		 users2 = hibernateUtil.fetchById(userId,User.class);
		
		}
		User puser = hibernateUtil.fetchById(parentId,User.class);
		for(int gameIds:ids){
	         Games games1 = hibernateUtil.fetchById(gameIds,Games.class);
	         if(games1 != null){
	         UserGameMapping userGameMapping1 = new UserGameMapping();
	         userGameMapping1.setGames(games1);
	         userGameMapping1.setUser(user);
	        userGameMapping1.setPriority_flag("S");
	        userGameMapping1.setYears_of_exp(yearsOfExp);
	        userGameMapping1.setSkilllevel(level);
	        userGameMapping1.setCreatedBy(createdBy);
	        
	        hibernateUtil.create(userGameMapping1);
	         }
	         
	 		//userGameMap.setYears_of_exp(userGameMapping.getYearsOfExp());
	 		//this.userDao.createUser(users,userGameMap,id1,gameIds,levelId);
		}
		
         if(puser != null && users2 != null){
			
			
			family.setUser1(puser);
			family.setUser(users2);
			 hibernateUtil.create(family);
			}
        if(games != null && users1 != null){
			userGameMapping.setGames(games);
			//userGameMapping.setGames(games1);
			userGameMapping.setSkilllevel(level);
			userGameMapping.setUser(users1);
			
			  hibernateUtil.create(userGameMapping);
			}

		
		//StringTokenizer st = new StringTokenizer(Arrays.toString(ids),",");  
	     //while (st.hasMoreTokens()) {  
	         //System.out.println(st.nextToken()); 
	         //int gameIds = Integer.valueOf(st.nextToken());
	         //System.out.println(gameIds);
      
		
		

		return (int) userId;
		
		
		
	}
	public boolean checkEmail(User user){

		
		String query = "from User use where use.email=:userName";
		//boolean userFound = false;
		@SuppressWarnings({ "unchecked" })
		User result = hibernateUtil.exist(query,user.getEmail());
		if(result == null){
        return true;
		}
		else{
			return false;
		}
    }
	@Override
	public User updateUser(User user,int roleId) {
		// TODO Auto-generated method stub
		Role role = hibernateUtil.fetchById(roleId,Role.class);
		//User user1 = new User();
		user.setRole(role);
		return hibernateUtil.update(user);
	}
	@Override
	public UserGameMapping updateUserGameMap(User user,UserGameMapping userGameMap,long userId,int roleId,int mappingId,int[] ids,int gameId,int levelId,int yearsOfExp,String createdBy) {
		// TODO Auto-generated method stub
		Games games = null;
		SkillLevel level = null;
		if(gameId != 0 && levelId !=0){
		 games = hibernateUtil.fetchById(gameId,Games.class);
		 level = hibernateUtil.fetchById(levelId,SkillLevel.class);
		}
		//Games games1 = hibernateUtil.fetchById(gameIds,Games.class);
		
		//id1 = users.getRoleId();
		
		Role role = hibernateUtil.fetchById(roleId,Role.class);
		//User user1 = new User();
		user.setRole(role);
		 
		User users1 = hibernateUtil.fetchById(userId,User.class);
		//User users1 = hibernateUtil.fetchById(userId,User.class);
		//StringTokenizer st = new StringTokenizer(Arrays.toString(ids),",");  
	     //while (st.hasMoreTokens()) {  
	         //System.out.println(st.nextToken()); 
	         //int gameIds = Integer.valueOf(st.nextToken());
	         //System.out.println(gameIds);
		for(int gameIds:ids){
	         Games games1 = hibernateUtil.fetchById(gameIds,Games.class);
	         if(games1 != null){
	         UserGameMapping userGameMapping1 = new UserGameMapping();
	         //userGameMapping1.setMapping_id(mappingId);
	         userGameMapping1.setGames(games1);
	         userGameMapping1.setUser(users1);
	        userGameMapping1.setPriority_flag("S");
	        userGameMapping1.setYears_of_exp(yearsOfExp);
	        userGameMapping1.setSkilllevel(level);
	        userGameMapping1.setCreatedBy(createdBy);
	        
	        hibernateUtil.create(userGameMapping1);
	         }
	 		
		}
	    // }  
		if(games != null){
			
		userGameMap.setGames(games);
		//userGameMap.setGames(games1);
		userGameMap.setSkilllevel(level);
		userGameMap.setUser(users1);
		
		  return hibernateUtil.update(userGameMap);
		}
		//return (int) userId;
		return null;
		
	}
	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		//User user = new User();
		//user.setId(id);
		//hibernateUtil.delete(user);
	}
	@Override
	public void deleteUserGameMap(int[] mappingIds) {
		// TODO Auto-generated method stub
		for(int Ids:mappingIds){
			String query = "delete UserGameMapping where mapping_id = :id";
			 hibernateUtil.delete(query,Ids);
		}
	}
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(User.class);
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, User.class);
	}

	@Override
	public UserGameMapping getUser1(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, UserGameMapping.class);
	}
	@Override
	public List<User> getAllUsers(String userName) {
		List<User> users = new ArrayList<User>();
		// TODO Auto-generated method stub
	/*	String query = "SELECT u.* FROM user u WHERE u.first_name like '%"+ userName +"%'";
		List<Object[]> userObjects = hibernateUtil.fetchAll(query);
		List<User> users = new ArrayList<User>();
		for(Object[] userObject: userObjects) {
			User user = new User();
		long id = ((BigInteger) userObject[0]).longValue(); 
		String firtName = (String) userObject[1];
		String lastName = (String) userObject[2];
		Date dob = (Date) userObject[3];
		user.setId(id);
		user.setFirstName(firtName);
		user.setLastName(lastName);
		user.setDateOfBirth(dob);
		users.add(user);
		}
		System.out.println(users);
		return users;*/
		return users;
	}
	
	
	@Override
	public int createCoachMapping(CoachMapping coachMap, long coachId, long studentId, int requestId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(coachId, User.class);
		User user1 = hibernateUtil.fetchById(studentId, User.class);
		String query3 = "from Requests p where p.user=:entity and status='pending'";
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2 (query3,user1);
		if((requests != null) && (requests.size() > 0)){
			
		
		String query2 = "from CoachMapping p where p.student=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<CoachMapping> list = hibernateUtil.fetchAll2 (query2,user1);
		if((list != null) && (list.size() > 0)){
			String query4 = "from CoachMapping p where p.coach=:entity and p.student=:entity1";
			CoachMapping coach = hibernateUtil.fetchAll7 (query4,user,user1);
			if(coach != null){
				String query = "delete CoachMapping where student = :entity";
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Student UnJoined From CoachGroup");
				request.setStatus("Accepted");
				
				  hibernateUtil.delete1(query,user1);
			}
			else{
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Sorry, already member for other");
				request.setStatus("Cancelled");
				
			}
		}else{
			coachMap.setCoach(user);
			coachMap.setStudent(user1);
			
			  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Joined as Student In CoachGroup");
				request.setStatus("Accepted");
				
			return (int) hibernateUtil.create(coachMap);
		}
		
	}
		return 0;
		
	}
	
	@Override
	public int createCoachMappingForChilds(CoachMapping coachMap, long coachId, long studentId, int requestId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(coachId, User.class);
		User user1 = hibernateUtil.fetchById(studentId, User.class);
		String query3 = "from Requests p where p.user=:entity and status='pending'";
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2 (query3,user1);
		if((requests != null) && (requests.size() > 0)){
			
		
		String query2 = "from CoachMapping p where p.student=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<CoachMapping> list = hibernateUtil.fetchAll2 (query2,user1);
		if((list != null) && (list.size() > 0)){
			String query4 = "from CoachMapping p where p.coach=:entity and p.student=:entity1";
			CoachMapping coach = hibernateUtil.fetchAll7 (query4,user,user1);
			if(coach != null){
				String query = "delete CoachMapping where student = :entity";
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Student UnJoined From CoachGroup");
				request.setStatus("Accepted");
				String queryb= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
				Requests request1 =  hibernateUtil.fetchAll7(queryb,user,user1);
				request1.setMessage("Student UnJoined From CoachGroup");
				request1.setStatus("Accepted");
				  hibernateUtil.delete1(query,user1);
			}
			else{
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Sorry, already member for other");
				request.setStatus("Cancelled");
				String querya= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
				Requests request1 =  hibernateUtil.fetchAll7(querya,user,user1);
				request1.setMessage("Sorry, already member for other");
				request1.setStatus("Cancelled");
			}
		}else{
			coachMap.setCoach(user);
			coachMap.setStudent(user1);
			
			  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Joined as Student In CoachGroup");
				request.setStatus("Accepted");
				String query4= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
				Requests request1 =  hibernateUtil.fetchAll7(query4,user,user1);
				request1.setMessage("Joined as Student In CoachGroup");
				request1.setStatus("Accepted");
			return (int) hibernateUtil.create(coachMap);
		}
		
	}
		return 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CoachMapping> getAllChildsForCoach(long childId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(childId, User.class);
		String query = "from CoachMapping where student = :entity";
		return hibernateUtil.fetchAll2(query,user);
	}
	@Override
	public List<CoachMapping> getAllChildsForCoach() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(CoachMapping.class);
	}
	
	
	/*List of states table methods */
	
	public int createState(State state) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(state);
	}
	@Override
	public State updateState(State state) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(state);
	}

	@Override
	public void deleteState(int id) {
		// TODO Auto-generated method stub
		State state = new State();
		state.setState_id(id);
		hibernateUtil.delete(state);
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(State.class);
	}

	@Override
	public State getState(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, State.class);
	}

	@Override
	public List<State> getAllStates(int countryId) {
		Country country = hibernateUtil.fetchById(countryId, Country.class);
		String query = "FROM State u WHERE u.country=:entity";
		@SuppressWarnings("unchecked")
		List<State> states = hibernateUtil.fetchAll2(query,country);
		
		
		// TODO Auto-generated method stub
	
		return states;
	}
	/*List of teams table methods */
	@Override
	public int createTeam(Team team,GeoLocation geolocation,int gameId,long userId,long teamCoach,long teamCaptain,int continentId,int countryId,int stateId,Address address) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		Games games = hibernateUtil.fetchById(gameId, Games.class);
		String query = "from PlayersCount use where use.games=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<PlayersCount> count = hibernateUtil.fetchAll2(query,games);
       for(PlayersCount counts:count){
			
    	   team.setVacancy(counts.getPlayers_count());
			
		}
		
		team.setGames(games);
		
		User teamCoach1 = hibernateUtil.fetchById(teamCoach, User.class);
		User teamCaptain1 = hibernateUtil.fetchById(teamCaptain, User.class);
		team.setTeamOwner(user);
		team.setTeamCoach(teamCoach1);
		team.setTeamCaptain(teamCaptain1);
		
		int teamId = (int) hibernateUtil.create(team);
		geolocation.setColumn_id(teamId);
		    hibernateUtil.create(geolocation);
		    
		    Continent continent = hibernateUtil.fetchById(continentId, Continent.class);
		    Country country = hibernateUtil.fetchById(countryId, Country.class);
		    State state = hibernateUtil.fetchById(stateId, State.class);
		    address.setColumn_id(teamId);
		    address.setContinent(continent);
		    address.setCountry(country);
		    address.setState(state);
		    hibernateUtil.create(address);
		Team teams = hibernateUtil.fetchById(teamId, Team.class);
		TeamMapping teamMap = new TeamMapping();
		teamMap.setUser(user);
		teamMap.setTeam(teams);
		teamMap.setCreatedBy("subbaram");
		hibernateUtil.create(teamMap);
		
		return (int) teamId;
	}
	@Override
	public Team updateTeam(Team team) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(team);
	}
	@Override
	public void deleteTeam(int id) {
		// TODO Auto-generated method stub
		Team team = new Team();
		team.setTeam_id(id);
		hibernateUtil.delete(team);
	}
	@Override
	public void deleteTeamMapping(long playerId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(playerId, User.class);
		String query = "delete TeamMapping where user = :entity";
		 hibernateUtil.delete1(query,user);
	}
	
	@Override
	public List<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Team.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TeamMapping> getAllChilds(long childId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(childId, User.class);
		String query = "from TeamMapping where user = :entity";
		return hibernateUtil.fetchAll2(query,user);
	}
	public List<TeamMapping> getAllChilds() {
		// TODO Auto-generated method stub
		
		return hibernateUtil.fetchAll(TeamMapping.class);
	}
	@Override
	public Team getTeam(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Team.class);
	}
	@Override
	public TeamMapping getTeamByPlayerId(long playerId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(playerId, User.class);
		String query = "from TeamMapping p inner join p.user where p.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Object[]> list = hibernateUtil.fetchAll2 (query,user);
		for (Object[] aRow : list) {
		      TeamMapping teamMap = (TeamMapping) aRow[0];
		      User user1 = (User) aRow[1];
		      System.out.println(teamMap.getUser().getFirstName() + " - " );
		      return teamMap;
		  }
		
		return null;
		
		
	}
	@Override
	public List<Team> getAllTeams(long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "from Team use where use.teamOwner=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Team> team = hibernateUtil.fetchAll2(query,user);
		return team;
		
	}
	@Override
	public int createTeamMappingForChilds(TeamMapping teamMap, long teamOwnerId, long userId,int requestId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		User teamOwner = hibernateUtil.fetchById(teamOwnerId, User.class);
		//User user1 = hibernateUtil.fetchById(userId, User.class);
		String query3 = "from Requests p where p.user=:entity and status='pending'";
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2 (query3,user);
		if((requests != null) && (requests.size() > 0)){
			
		
		String query2 = "from TeamMapping p where p.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<TeamMapping> list = hibernateUtil.fetchAll2 (query2,user);
		if((list != null) && (list.size() > 0)){
			String query6 = "from Team use where use.teamOwner=:entity";
			Team teams1 = hibernateUtil.fetchteam(query6,teamOwner);
			String query4 = "from TeamMapping p where p.team=:entity and p.user=:entity1";
			TeamMapping team = hibernateUtil.fetchAll7 (query4,teams1,user);
			if(team != null){
				User user2 = hibernateUtil.fetchById(userId, User.class);
				String query5 = "delete TeamMapping where user = :entity";
				String query1 = "from Team use where use.teamOwner=:entity";
				//boolean userFound = false;
				@SuppressWarnings("unchecked")
				List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
				int teamId = 0;
				for(Team team2 : teams){
				 teamId = team2.getTeam_id();
				
				}
				//Team team = hibernateUtil.fetchById(teamId, Team.class);
				
				if(teamId != 0){
				String query = "update Team t set t.vacancy = t.vacancy + 1,t.player_count = t.player_count - 1 where t.team_id=:id";
				  hibernateUtil.update(query,teamId);
				}
				  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
					request.setMessage("UnJoined From Team");
					request.setStatus("Accepted");
					String queryc= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
					Requests request1 =  hibernateUtil.fetchAll7(queryc,teamOwner,user);
					request1.setMessage("UnJoined From Team");
					request1.setStatus("Accepted");
				 return hibernateUtil.delete1(query5,user2);
			}
			else{
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Sorry, already member for other");
				request.setStatus("Cancelled");
				String queryc= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
				Requests request1 =  hibernateUtil.fetchAll7(queryc,teamOwner,user);
				request1.setMessage("Sorry, already member for other");
				request.setStatus("Cancelled");
			}
		}else{
			String query1 = "from Team use where use.teamOwner=:entity";
			//boolean userFound = false;
			@SuppressWarnings("unchecked")
			List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
			int teamId = 0;
			for(Team team1 : teams){
			 teamId = team1.getTeam_id();
			teamMap.setTeam(team1);
			}
			//Team team = hibernateUtil.fetchById(teamId, Team.class);
			teamMap.setUser(user);
			if(teamId != 0){
			String query = "update Team t set t.vacancy = t.vacancy - 1,t.player_count = t.player_count + 1 where t.team_id=:id";
			  hibernateUtil.update(query,teamId);
			}
			  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Added as TeamMember");
				request.setStatus("Accepted");
				
				String query4= "from Requests use where use.user=:entity and use.user1=:entity1 and status='pending'";
				Requests request1 =  hibernateUtil.fetchAll7(query4,teamOwner,user);
				request1.setMessage("Added as TeamMember");
				request1.setStatus("Accepted");
				
			return (int) hibernateUtil.create(teamMap);
		}
			
		}
		
		
		return 0;
		
	}
	@Override
	public int createTeamMapping(TeamMapping teamMap, long teamOwnerId, long userId,int requestId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		User teamOwner = hibernateUtil.fetchById(teamOwnerId, User.class);
		//User user1 = hibernateUtil.fetchById(userId, User.class);
		String query3 = "from Requests p where p.user=:entity and status='pending'";
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2 (query3,user);
		if((requests != null) && (requests.size() > 0)){
			
		
		String query2 = "from TeamMapping p where p.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<TeamMapping> list = hibernateUtil.fetchAll2 (query2,user);
		if((list != null) && (list.size() > 0)){
			String query6 = "from Team use where use.teamOwner=:entity";
			Team teams1 = hibernateUtil.fetchteam(query6,teamOwner);
			String query4 = "from TeamMapping p where p.team=:entity and p.user=:entity1";
			TeamMapping team = hibernateUtil.fetchAll7 (query4,teams1,user);
			if(team != null){
				User user2 = hibernateUtil.fetchById(userId, User.class);
				String query5 = "delete TeamMapping where user = :entity";
				String query1 = "from Team use where use.teamOwner=:entity";
				//boolean userFound = false;
				@SuppressWarnings("unchecked")
				List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
				int teamId = 0;
				for(Team team2 : teams){
				 teamId = team2.getTeam_id();
				
				}
				//Team team = hibernateUtil.fetchById(teamId, Team.class);
				
				if(teamId != 0){
				String query = "update Team t set t.vacancy = t.vacancy + 1,t.player_count = t.player_count - 1 where t.team_id=:id";
				  hibernateUtil.update(query,teamId);
				}
				  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
					request.setMessage("UnJoined From Team");
					request.setStatus("Accepted");
					
				 return hibernateUtil.delete1(query5,user2);
			}
			else{
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Sorry, already member for other");
				request.setStatus("Cancelled");
				
			}
		}else{
			String query1 = "from Team use where use.teamOwner=:entity";
			//boolean userFound = false;
			@SuppressWarnings("unchecked")
			List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
			int teamId = 0;
			for(Team team1 : teams){
			 teamId = team1.getTeam_id();
			teamMap.setTeam(team1);
			}
			//Team team = hibernateUtil.fetchById(teamId, Team.class);
			teamMap.setUser(user);
			if(teamId != 0){
			String query = "update Team t set t.vacancy = t.vacancy - 1,t.player_count = t.player_count + 1 where t.team_id=:id";
			  hibernateUtil.update(query,teamId);
			}
			  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Added as TeamMember");
				request.setStatus("Accepted");
				
				
				
			return (int) hibernateUtil.create(teamMap);
		}
			
		}
		
		
		return 0;
	}
	
	/*@Override
	public int createTeamMapping(TeamMapping teamMap, long teamOwnerId, long userId,int requestId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		User teamOwner = hibernateUtil.fetchById(teamOwnerId, User.class);
		//User user1 = hibernateUtil.fetchById(userId, User.class);
		String query2 = "from TeamMapping p where p.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<TeamMapping> list = hibernateUtil.fetchAll2 (query2,user);
		if((list != null) && (list.size() > 0)){
			//User user = hibernateUtil.fetchById(userId, User.class);
			String query3 = "delete TeamMapping where user = :entity";
			String query1 = "from Team use where use.teamOwner=:entity";
			//boolean userFound = false;
			@SuppressWarnings("unchecked")
			List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
			int teamId = 0;
			for(Team team : teams){
			 teamId = team.getTeam_id();
			
			}
			//Team team = hibernateUtil.fetchById(teamId, Team.class);
			
			if(teamId != 0){
			String query = "update Team t set t.vacancy = t.vacancy + 1,t.player_count = t.player_count - 1 where t.team_id=:id and t.vacancy > 0";
			  hibernateUtil.update(query,teamId);
			}
			  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("UnJoined From Team");
				request.setStatus("Accepted");
			 return hibernateUtil.delete1(query3,user);
		}
		else{
		
		String query1 = "from Team use where use.teamOwner=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Team> teams = hibernateUtil.fetchAll2(query1,teamOwner);
		int teamId = 0;
		for(Team team : teams){
		 teamId = team.getTeam_id();
		teamMap.setTeam(team);
		}
		//Team team = hibernateUtil.fetchById(teamId, Team.class);
		teamMap.setUser(user);
		if(teamId != 0){
		String query = "update Team t set t.vacancy = t.vacancy - 1,t.player_count = t.player_count + 1 where t.team_id=:id and t.vacancy > 0";
		  hibernateUtil.update(query,teamId);
		}
		  Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
			request.setMessage("Added as TeamMember");
			request.setStatus("Accepted");
		return (int) hibernateUtil.create(teamMap);
		}
	}*/
	
	/*List of schedules table methods */
	@Override
	public int createSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(schedule);
	}
	@Override
	public Schedule updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(schedule);
	}
	@Override
	public void deleteSchedule(int id) {
		// TODO Auto-generated method stub
		Schedule schedule = new Schedule();
		schedule.setSched_id(id);
		hibernateUtil.delete(schedule);
	}
	@Override
	public List<Schedule> getAllSchedules() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Schedule.class);
	}
	@Override
	public Schedule getSchedule(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Schedule.class);
	}
	@Override
	public List<Schedule> getAllSchedules(long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "from TeamMapping use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<TeamMapping> teamMap = hibernateUtil.fetchAll2(query,user);
		if((teamMap != null) && (teamMap.size() > 0)){
		for(TeamMapping teamMaps:teamMap)
		{
			int teamId = teamMaps.getTeam().getTeam_id();
			Team team = hibernateUtil.fetchById(teamId, Team.class);
			String query1 = "from Schedule use where use.team=:entity";
			@SuppressWarnings("unchecked")
			List<Schedule> schedule = hibernateUtil.fetchAll2(query1,team);
			return schedule;
		}
		}
		return null;
	}
	/*List of roles table methods */
	@Override
	public int createRole(Role role) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(role);
	}
	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(role);
	}
	@Override
	public void deleteRole(int id) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRole_id(id);
		hibernateUtil.delete(role);
	}
	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Role.class);
	}
	@Override
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Role.class);
	}
	@Override
	public List<Role> getAllRoles(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}
	/*List of organizations table methods */
	@Override
	public int createOrganization(Organization organization,long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		organization.setOrg_owner(user);
		int orgId = (int) hibernateUtil.create(organization);
		
		
		
		Organization org = hibernateUtil.fetchById(orgId, Organization.class);
		com.sports.core.domain.OrgMapping orgMap = new com.sports.core.domain.OrgMapping();
		orgMap.setUser(user);
		orgMap.setOrg(org);
		orgMap.setCreatedBy("subbaram");
		hibernateUtil.create(orgMap);
		return (int) orgId;
	}
	@Override
	public int createOrgMapping(OrgMapping orgMap, long orgOwnerId, long userId, int requestId) {
		// TODO Auto-generated method stub
		User orgOwner = hibernateUtil.fetchById(orgOwnerId, User.class);
		User user = hibernateUtil.fetchById(userId, User.class);
		String query3 = "from Requests p where p.user=:entity and status='pending'";
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2 (query3,user);
		if((requests != null) && (requests.size() > 0)){
			String query2 = "from OrgMapping p where p.user=:entity";
			//boolean userFound = false;
			@SuppressWarnings("unchecked")
			List<TeamMapping> list = hibernateUtil.fetchAll2 (query2,user);
			if((list != null) && (list.size() > 0)){
				String query6 = "from Organization use where use.orgOwner=:entity";
				Organization orgs1 = hibernateUtil.fetchteam(query6,orgOwner);
				String query4 = "from OrgMapping p where p.org=:entity and p.user=:entity1";
				OrgMapping org = hibernateUtil.fetchAll7 (query2,orgs1,user);
				if(org != null){
		String query1 = "delete OrgMapping where user = :entity";
		Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
		request.setMessage("UnJoined From Organization");
		request.setStatus("Accepted");
		 return hibernateUtil.delete1(query1,user);
		     }
				else{
					
						Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
						request.setMessage("Sorry, already member for other");
						request.setStatus("Cancelled");
						
					
				}
		}else{
		orgMap.setUser(user);
		String query1 = "from Organization use where use.org_owner=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Organization> orgs = hibernateUtil.fetchAll2(query1,orgOwner);
		
		for(Organization org1 : orgs){
		
		 orgMap.setOrg(org1);
		}
		orgMap.setCreatedBy("subbaram");
		Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
		request.setMessage("Added To Organization");
		request.setStatus("Accepted");
		
		return (int) hibernateUtil.create(orgMap);
		}
				
	}
			

	return 0;	
	}
	/*@Override
	public int createOrgMapping(OrgMapping orgMap, long orgOwnerId, long userId, int requestId) {
		// TODO Auto-generated method stub
		User orgOwner = hibernateUtil.fetchById(orgOwnerId, User.class);
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "from OrgMapping use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<OrgMapping> list = hibernateUtil.fetchAll2(query,user);
		if((list != null) && (list.size() > 0)){
		String query1 = "delete OrgMapping where user = :entity";
		Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
		request.setMessage("UnJoined From Organization");
		request.setStatus("Accepted");
		 return hibernateUtil.delete1(query1,user);
		}
		else{
		orgMap.setUser(user);
		String query1 = "from Organization use where use.org_owner=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Organization> orgs = hibernateUtil.fetchAll2(query1,orgOwner);
		
		for(Organization org : orgs){
		
		 orgMap.setOrg(org);
		}
		orgMap.setCreatedBy("subbaram");
		Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
		request.setMessage("Added To Organization");
		request.setStatus("Accepted");
		
		return (int) hibernateUtil.create(orgMap);
		}
		
	}*/
	@Override
	public Organization updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(organization);
	}
	@Override
	public void deleteOrganization(int id) {
		// TODO Auto-generated method stub
		Organization organization = new Organization();
		organization.setOrg_id(id);
		hibernateUtil.delete(organization);
	}
	@Override
	public List<Organization> getAllOrganizations() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Organization.class);
	}
	@Override
	public Organization getOrganization(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Organization.class);
	}
	@Override
	public List<OrgMapping> getAllOrganizations(long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "from OrgMapping use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<OrgMapping> orgMap = hibernateUtil.fetchAll2(query,user);
		return orgMap;
	}
	
	/*  List of matches table*/
	
	public int createMatches(com.sports.core.domain.Match match,int schedId,int teamId) {
		// TODO Auto-generated method stub
		Schedule schedule = hibernateUtil.fetchById(schedId, Schedule.class);
		Team team = hibernateUtil.fetchById(teamId, Team.class);
		match.setTeam(team);
		match.setSchedule(schedule);
		return (int) hibernateUtil.create(match);
	}
	@Override
	public com.sports.core.domain.Match updateMatch(com.sports.core.domain.Match match) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(match);
	}
	@Override
	public void deleteMatch(int id) {
		// TODO Auto-generated method stub
		com.sports.core.domain.Match match = new com.sports.core.domain.Match();
		match.setMatch_id(id);
		hibernateUtil.delete(match);
	}
	@Override
	public List<com.sports.core.domain.Match> getAllMatches() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(com.sports.core.domain.Match.class);
	}
	@Override
	public com.sports.core.domain.Match getMatches(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, com.sports.core.domain.Match.class);
	}
	@Override
	public List<com.sports.core.domain.Match> getAllMatches(long userId) { 
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "from TeamMapping use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<TeamMapping> teamMap = hibernateUtil.fetchAll2(query,user);
		if((teamMap != null) && (teamMap.size() > 0)){
		for(TeamMapping teamMaps:teamMap)
		{
			int teamId = teamMaps.getTeam().getTeam_id();
			Team team = hibernateUtil.fetchById(teamId, Team.class);
			String query1 = "from Match use where use.team=:entity";
			@SuppressWarnings("unchecked")
			List<com.sports.core.domain.Match> match = hibernateUtil.fetchAll2(query1,team);
			return match;
		}
		}
		return null;
	}
	/*  List of history's table*/
	@Override
	public int createHistory(History history,int schedId,int teamId,long userId,int matchId) {
		// TODO Auto-generated method stub
		Schedule schedule = hibernateUtil.fetchById(schedId, Schedule.class);
		Team team = hibernateUtil.fetchById(teamId, Team.class);
		User user = hibernateUtil.fetchById(userId, User.class);
		com.sports.core.domain.Match match = hibernateUtil.fetchById(matchId, com.sports.core.domain.Match.class);
		history.setTeam(team);
		history.setSchedule(schedule);
		history.setUser(user);
		history.setMatch(match);
		return (int) hibernateUtil.create(history);
	}
	@Override
	public History updateHistory(History history) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(history);
	}
	@Override
	public void deleteHistory(int id) {
		// TODO Auto-generated method stub
		History history = new History();
		history.setHistory_id(id);
		hibernateUtil.delete(history);
	}
	@Override
	public List<History> getAllHistory() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(History.class);
	}
	@Override
	public History getHistory(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, History.class);
	}
	@Override
	public List<History> getAllHistory(String historyName) {
		// TODO Auto-generated method stub
		return null;
	}
	/*  List of country's table*/
	@Override
	public int createCountry(Country country) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(country);
	}
	@Override
	public Country updateCountry(Country country) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(country);
	}
	@Override
	public void deleteCountry(int id) {
		// TODO Auto-generated method stub
		Country country = new Country();
		country.setCountry_id(id);
		hibernateUtil.delete(country);
	}
	@Override
	public List<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Country.class);
	}
	@Override
	public Country getCountry(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Country.class);
	}
	@Override
	public List<Country> getAllCountry(int continentId) {
		// TODO Auto-generated method stub
		Continent continent = hibernateUtil.fetchById(continentId, Continent.class);
		String query = "FROM Country u WHERE u.continent=:entity";
		@SuppressWarnings("unchecked")
		List<Country> countries = hibernateUtil.fetchAll2(query,continent);
		/*List<Country> countries = new ArrayList<Country>();
		for(Object[] countryObject: countryObjects) {
			Country country = new Country();
			int id = (int) countryObject[0];
			String countryName = (String) countryObject[1];
			String countryDesc = (String) countryObject[2];
			String createdBy = (String) countryObject[3];
			Date createdDate = (Date) countryObject[4];
			Date updatedDate = (Date) countryObject[6];
			country.setCountry_id(id);
			country.setCountry_name(countryName);
			country.setCountry_desc(countryDesc);
			country.setCreatedBy(createdBy);
			country.setCreatedDate(createdDate);
			country.setUpdatedDate(updatedDate);
			countries.add(country);
		}*/
		
		// TODO Auto-generated method stub
	
		return countries;
	}
	/*  List of continent's table*/
	@Override
	public int createContinent(Continent continent) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(continent);
	}
	@Override
	public Continent updateContinent(Continent continent) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(continent);
	}
	@Override
	public void deleteContinent(int id) {
		// TODO Auto-generated method stub
		Continent continent = new Continent();
		continent.setContinent_id(id);
		hibernateUtil.delete(continent);
	}
	@Override
	public List<Continent> getAllContinent() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Continent.class);
	}
	@Override
	public Continent getContinent(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Continent.class);
	}
	@Override
	public List<Continent> getAllContinent(String continentName) {
		// TODO Auto-generated method stub
		return null;
	}
	/*  List of city's table*/
	@Override
	public int createCity(City city) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(city);
	}
	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(city);
	}
	@Override
	public void deleteCity(int id) {
		// TODO Auto-generated method stub
		City city = new City();
		city.setCity_id(id);
		hibernateUtil.delete(city);
	}
	@Override
	public List<City> getAllCity() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(City.class);
	}
	@Override
	public City getCity(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, City.class);
	}
	@Override
	public List<City> getAllCity(int stateId) {
		// TODO Auto-generated method stub
		State state = hibernateUtil.fetchById(stateId, State.class);
		String query = "FROM City u WHERE u.state=:entity";
		@SuppressWarnings("unchecked")
		List<City> cities = hibernateUtil.fetchAll2(query,state);
		
		
		// TODO Auto-generated method stub
	
		return cities;
	}
	/*List of address table */
	@SuppressWarnings("unused")
	@Override
	public int createAddress(Address address,int continentId,int countryId,int stateId) {
		// TODO Auto-generated method stub
		Continent continent = hibernateUtil.fetchById(continentId, Continent.class);
		Country country = hibernateUtil.fetchById(countryId, Country.class);
		State state = hibernateUtil.fetchById(stateId, State.class);
		address.setContinent(continent);
		address.setCountry(country);
		address.setState(state);
		if(address != null){
		return (int) hibernateUtil.create(address);
		}
		else{
		return 0;
		}
	}
	@Override
	public Address updateAddress(Address address,int continentId,int countryId,int stateId) {
		// TODO Auto-generated method stub
		Continent continent = hibernateUtil.fetchById(continentId, Continent.class);
		Country country = hibernateUtil.fetchById(countryId, Country.class);
		State state = hibernateUtil.fetchById(stateId, State.class);
		address.setContinent(continent);
		address.setCountry(country);
		address.setState(state);
		return hibernateUtil.update(address);
	}
	@Override
	public void deleteAddress(int id) {
		// TODO Auto-generated method stub
		Address address = new Address();
		address.setAddr_id(id);
		hibernateUtil.delete(address);
	}
	@Override
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Address.class);
	}
	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Address.class);
	}
	/*List of attachemnts table */
	@Override
	public int createAttachment(Attachments attachments) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(attachments);
	}
	@Override
	public Attachments updateAttachements(Attachments attachments) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(attachments);
	}
	@Override
	public void deleteAttachements(int id) {
		// TODO Auto-generated method stub
		Attachments attachments = new Attachments();
		attachments.setAttach_id(id);
		hibernateUtil.delete(attachments);
	}
	@Override
	public List<Attachments> getAllAttachments() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Attachments.class);
	}
	@Override
	public Attachments getAttachement(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Attachments.class);
	}
	/*List of family details table */
	@Override
	public int createFamily(FamilyDetails familyDetails) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(familyDetails);
	}
	@Override
	public FamilyDetails updateFamily(FamilyDetails familyDetails) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(familyDetails);
	}
	@Override
	public void deleteFamily(int id) {
		// TODO Auto-generated method stub
		FamilyDetails familyDetails = new FamilyDetails();
		familyDetails.setFamily_id(id);
		hibernateUtil.delete(familyDetails);
	}
	@Override
	public List<FamilyDetails> getAllFamilyDetails() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(FamilyDetails.class);
	}
	@Override
	public FamilyDetails getFamily(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, FamilyDetails.class);
	}
	/*List of geolocations table */
	@Override
	public int createGeoLocation(GeoLocation geoLocation) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(geoLocation);
	}
	@Override
	public GeoLocation updateGeoLocation(GeoLocation geoLocation) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(geoLocation);
	}
	@Override
	public void deleteGeoLocation(int id) {
		// TODO Auto-generated method stub
		GeoLocation geoLocation = new GeoLocation();
		geoLocation.setGeo_location_id(id);
		hibernateUtil.delete(geoLocation);
	}
	@Override
	public List<GeoLocation> getAllGeoLocations() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(GeoLocation.class);
	}
	@Override
	public GeoLocation getGeoLocation(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, GeoLocation.class);
	}
	@Override
	public int createGames(Games games) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(games);
	}
	@Override
	public Games updateGames(Games games) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(games);
	}
	@Override
	public void deleteGames(int id) {
		// TODO Auto-generated method stub
		Games games = new Games();
		games.setGame_id(id);
		hibernateUtil.delete(games);
	}
	@Override
	public List<Games> getAllGames() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(Games.class);
	}
	@Override
	public Games getGames(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, Games.class);
	}
	@Override
	public int createSkillLevel(SkillLevel skillLevel) {
		// TODO Auto-generated method stub
		return (int) hibernateUtil.create(skillLevel);
	}
	@Override
	public SkillLevel updateSkillLevel(SkillLevel skillLevel) {
		// TODO Auto-generated method stub
		return hibernateUtil.update(skillLevel);
	}
	@Override
	public void deleteSkillLevel(int id) {
		// TODO Auto-generated method stub
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setLevel_id(id);
		hibernateUtil.delete(skillLevel);
	}
	@Override
	public List<SkillLevel> getAllSkillLevel() {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchAll(SkillLevel.class);
	}
	@Override
	public SkillLevel getSkillLevel(int id) {
		// TODO Auto-generated method stub
		return hibernateUtil.fetchById(id, SkillLevel.class);
	}
	@Override
	public List<User> checkLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		System.out.println("In Check login");
		//boolean userFound = false;
		String query = "SELECT u.* FROM user u WHERE u.first_name ='" + userName + "' AND u.password='" +userPassword +"'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Object[]> list = hibernateUtil.fetchAll(query);
		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			System.out.println("success");
			List<User> user = new ArrayList<User>();
			for(Object[] userObject: list) {
				User users = new User();
				int id = (int) userObject[0];
				String userName1 = (String) userObject[1];
				//String userPassword1 = (String) userObject[2];
				String query1 = "SELECT r.* FROM role_mapping r WHERE r.user_id =" + id;
				@SuppressWarnings("unchecked")
				List<Object[]> list1 = hibernateUtil.fetchAll(query1);
				for(Object[] userObject1: list1) {
					int id1 = (int) userObject1[1];
					Role role =  hibernateUtil.fetchById(id1, Role.class);
				users.setRole(role);
				}
				users.setId(id);
				users.setFirstName(userName1);
				//users.setPassword(userPassword1);
				
				user.add(users);
				
			}
			return user;
		}

		return null;
	}
	@Override
	public List<User> checkLogin1(String userName, String userPassword) {
		// TODO Auto-generated method stub
		System.out.println("In Check login");
		//boolean userFound = false;
		String query = "from User use where use.email=:userName and use.password=:userPassword";
		//boolean userFound = false;
		@SuppressWarnings({ "unchecked" })
		List<User> list = hibernateUtil.fetchAll1(query,userName,userPassword);
		
		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			System.out.println("success");
			return list;
		}

		return null;
	}
	@Override
	public User updatePass(long id,String password) {
		// TODO Auto-generated method stub
		User user =  hibernateUtil.fetchById(id, User.class);
		user.setPassword(password);
		return user;
	}
	@Override
	public List<UserGameMapping> getUserGameMap(long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "FROM UserGameMapping u WHERE u.user=:entity";
		@SuppressWarnings("unchecked")
		List<UserGameMapping> userGameMap = hibernateUtil.fetchAll2(query,user);
		return userGameMap;
	}
	@Override
	public List<Team> getNearByTeam() {
		// TODO Auto-generated method stub
		String query = "from Address  where table_name=:tablename and pincode BETWEEN :frompincode and :topincode";
		//boolean userFound = false;
		String tablename = "team";
		String frompincode = "516172";
		String topincode = "516180";
		@SuppressWarnings({ "unchecked" })
		List<Address> list = hibernateUtil.fetchAddress(query,frompincode,topincode,tablename);
		List<Team> teams = new ArrayList<Team>();
		Team team = null;
		if((list != null) && (list.size() > 0)){
			System.out.println("success");
		for(Address lists:list){
			
			//if(columnName == "team_id"){
			int columnId = lists.getColumn_id();
			System.out.println(columnId);
			 team =  hibernateUtil.fetchById(columnId, Team.class);
			 
			teams.add(team);
			//}
		}
		}
		//return hibernateUtil.fetchAll(Address.class);
		/*if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			System.out.println("success");
			return list;
		}*/
		return teams;
		
	}
	@Override
	public void deleteUserGame(int id) {
		// TODO Auto-generated method stub
		String query = "delete UserGameMapping where mapping_id = :id";
		 hibernateUtil.delete(query,id);
	}
	@Override
	public int createUser(FamilyDetails family) {
		// TODO Auto-generated method stub

			
			
			
			return (int) hibernateUtil.create(family);
			
		
	}
	
	@Override
	public FamilyDetails getFamilyByChildId(long childId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(childId, User.class);
		String query = "from FamilyDetails use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		FamilyDetails familyDetails = hibernateUtil.fetchfamily(query,user);
		
		return familyDetails;
	}
	@Override
	public List<UserGameMapping> getAllFamilyDetails(long parentId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(parentId, User.class);
		String query = "from FamilyDetails use where use.user1=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<FamilyDetails> familyDetails = hibernateUtil.fetchAll2(query,user);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((familyDetails != null) && (familyDetails.size() > 0)){
			for (FamilyDetails family : familyDetails) {
			    long userId = family.getUser().getId();
			    System.out.println(userId);
			    User user1 =  hibernateUtil.fetchById(userId, User.class);
			    
				//boolean userFound = false;
				
			    String query1 = "from UserGameMapping use where use.user=:entity and priority_flag='P'";
				@SuppressWarnings("unchecked")
				List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query1,user1);
				
				userGameMap.addAll(userGameMaps);
			   System.out.println(userGameMaps);		    
			}
			
			
			return userGameMap; 
		}
		else{
		
		return null;
		}
		
		
	}
	@Override
	public List<FamilyDetails> getAllFamilyDetails1(long parentId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(parentId, User.class);
		String query = "from FamilyDetails use where use.user1=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<FamilyDetails> familyDetails = hibernateUtil.fetchAll2(query,user);
		return familyDetails;
	}
	@Override
	public Address getAddress(int columnId, String tableName) {
		// TODO Auto-generated method stub
		String query = "from Address use where use.column_id=:id and use.table_name=:tablename";
		//boolean userFound = false;
		
		@SuppressWarnings({ "unchecked" })
		Address address = hibernateUtil.fetchAddress1(query,columnId,tableName);
		
		if(address != null){
			return address;
		}
		
		return null;
	}

	@Override
	public User updateProfile(long id, byte[] profilePic) {
		// TODO Auto-generated method stub
		User user =  hibernateUtil.fetchById(id, User.class);
		user.setProfilePic(profilePic);
		return user;
		
	}
	@Override
	public GeoLocation getGeoLocation(int columnId, String tableName) {
		// TODO Auto-generated method stub
		String query = "from GeoLocation use where use.column_id=:id and use.table_name=:tablename";
		//boolean userFound = false;
		
		@SuppressWarnings({ "unchecked" })
		GeoLocation geolocation = hibernateUtil.fetchAddress1(query,columnId,tableName);
		
		if(geolocation != null){
			return geolocation;
		}
		
		return null;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGameMapping> getTeamPlayersById(int teamId) {
		// TODO Auto-generated method stub
		Team team1 =  hibernateUtil.fetchById(teamId, Team.class);
		String query = "from TeamMapping t where t.team =:entity";
		@SuppressWarnings({ "unchecked" })
		List<TeamMapping> teamMap= hibernateUtil.fetchAll2(query,team1);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((teamMap != null) && (teamMap.size() > 0)){
		for (TeamMapping teamMaps : teamMap) {
		    long userId = teamMaps.getUser().getId();
		    System.out.println(userId);
		    User user =  hibernateUtil.fetchById(userId, User.class);
		    
			//boolean userFound = false;
			
		    String query1 = "from UserGameMapping use where use.user=:entity";
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query1,user);
			
			userGameMap.addAll(userGameMaps);
		   System.out.println(userGameMaps);		    
		}
		
		
		return userGameMap; 
	}
		else{
		
		return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGameMapping> getTeamPlayersByTeamOwner(long teamOwnerId) {
		// TODO Auto-generated method stub
		User user2 =  hibernateUtil.fetchById(teamOwnerId, User.class);
		String query = "from Team t where t.teamOwner =:entity";
		Team team2= hibernateUtil.fetchteam(query,user2);
		
		String query1 = "from TeamMapping t where t.team =:entity";
		@SuppressWarnings({ "unchecked" })
		List<TeamMapping> teamMap= hibernateUtil.fetchAll2(query1,team2);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((teamMap != null) && (teamMap.size() > 0)){
		for (TeamMapping teamMaps : teamMap) {
		    long userId = teamMaps.getUser().getId();
		    System.out.println(userId);
		    User user =  hibernateUtil.fetchById(userId, User.class);
		    
			//boolean userFound = false;
			
		    String query2 = "from UserGameMapping use where use.user=:entity";
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query2,user);
			
			userGameMap.addAll(userGameMaps);
		   System.out.println(userGameMaps);		    
		}
		
		
		return userGameMap; 
	}
		else{
		
		return null;
		}
	}
	
	
	@Override
	public List<Requests> getRequestsBySender(long senderId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(senderId, User.class);
		
		
		
		java.util.Date utilDate = new java.util.Date();
		  Calendar calendar = new GregorianCalendar();
			calendar.setTimeZone(TimeZone.getTimeZone("UTC+1"));//Munich time
			calendar.setTime(utilDate);
			calendar.add(Calendar.DATE, -8);//substract the number of days to look back
			java.util.Date dateToLookBackAfter = calendar.getTime();
			System.out.println(dateToLookBackAfter);
			String qlString = "from Requests p where p.user=:entity and p.createdDate > :dateToLookBackAfter";
			@SuppressWarnings("unchecked")
			List<Requests> requests = hibernateUtil.fetchAll5(qlString,user,dateToLookBackAfter);
			
			
			
		
		return requests;
	}
	@Override
	public List<Requests> getRequestsByReceiver(long receiverId) {
		// TODO Auto-generated method stub
		
		User user = hibernateUtil.fetchById(receiverId, User.class);
		String query = "from Requests use where use.user1=:entity and use.status = 'pending'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Requests> requests = hibernateUtil.fetchAll2(query,user);
		
		return requests;
	}
	@Override
	public List<Requests> getRequestByStatus(long senderId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(senderId, User.class);
		List<Team> teams = hibernateUtil.fetchAll(Team.class);
		List<Requests> request = new ArrayList<Requests>();
		if((teams != null) && (teams.size() > 0)){
		for(Team team:teams){
			long receiverId1 = team.getTeamOwner().getId();
		User user1 = hibernateUtil.fetchById(receiverId1, User.class);
		String query = "from Requests use where use.user=:entity and use.user1=:entity1 and use.status='pending'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		Requests requests = hibernateUtil.fetchAll3(query,user,user1);
         if(requests != null){
			
			request.add(requests);
			}
	      }
          return request;
		
		}

		else{
			return null;
			}
		
	}
	@Override
	public List<Requests> getRequestByStatusCoach(long senderId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(senderId, User.class);
		Role role = hibernateUtil.fetchById(101, Role.class);
		String query1 = "from User use where use.role=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<User> users = hibernateUtil.fetchAll2(query1,role);
		List<Requests> request = new ArrayList<Requests>();
		if((users != null) && (users.size() > 0)){
		for(User user2:users){
			long receiverId1 = user2.getId();
		User user1 = hibernateUtil.fetchById(receiverId1, User.class);
		String query = "from Requests use where use.user=:entity and use.user1=:entity1 and use.status='pending'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		Requests requests = hibernateUtil.fetchAll3(query,user,user1);
		if(requests != null){
			
			request.add(requests);
			}
	      }
          return request;
		
		}

		else{
			return null;
			}
	}
	@Override
	public List<Requests> getRequestByStatusStudent(long senderId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(senderId, User.class);
		Role role = hibernateUtil.fetchById(100, Role.class);
		String query1 = "from User use where use.role=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<User> users = hibernateUtil.fetchAll2(query1,role);
		List<Requests> request = new ArrayList<Requests>();
		if((users != null) && (users.size() > 0)){
		for(User user2:users){
			long receiverId1 = user2.getId();
		User user1 = hibernateUtil.fetchById(receiverId1, User.class);
		String query = "from Requests use where use.user=:entity and use.user1=:entity1 and use.status='pending'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		Requests requests = hibernateUtil.fetchAll3(query,user,user1);
		if(requests != null){
			
			request.add(requests);
			}
	      }
          return request;
		
		}

		else{
			return null;
			}
	}
	
	
	@Override
	public List<Requests> getRequestByStatusOrg(long senderId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(senderId, User.class);
		List<Organization> orgs = hibernateUtil.fetchAll(Organization.class);
		List<Requests> request = new ArrayList<Requests>();
		if((orgs != null) && (orgs.size() > 0)){
		for(Organization org:orgs){
			long receiverId1 = org.getOrg_owner().getId();
			System.out.println(receiverId1);
		User user1 = hibernateUtil.fetchById(receiverId1, User.class);
		String query = "from Requests use where use.user=:entity and use.user1=:entity1 and use.status='pending'";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		Requests requests = hibernateUtil.fetchAll3(query,user,user1);
		if(requests != null){
		System.out.println(requests.getStatus());
		request.add(requests);
		}
	}
		
		return request;
}
		else{
		return null;
		}
		
	}
	
	
	@Override
	public List<UserGameMapping> getCoachByStudentId(long studentId) {
		// TODO Auto-generated method stub
		
		User user = hibernateUtil.fetchById(studentId, User.class);
		/*String query = "from CoachMapping p inner join p.student where p.student=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<Object[]> list = hibernateUtil.fetchAll2 (query,user);
		for (Object[] aRow : list) {
		      CoachMapping coachMap = (CoachMapping) aRow[0];
		      User user1 = (User) aRow[1];
		      System.out.println(coachMap.getCoach().getFirstName() + " - " );
		      return coachMap;
		      
		  }*/
		
		
		String query = "from CoachMapping t where t.student =:entity";
		@SuppressWarnings({ "unchecked" })
		List<CoachMapping> coachMap= hibernateUtil.fetchAll2(query,user);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((coachMap != null) && (coachMap.size() > 0)){
		for (CoachMapping coachMaps : coachMap) {
		    long userId = coachMaps.getCoach().getId();
		    System.out.println(userId);
		    User user1 =  hibernateUtil.fetchById(userId, User.class);
		    
			//boolean userFound = false;
			
		    String query1 = "from UserGameMapping use where use.user=:entity";
			@SuppressWarnings("unchecked")
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query1,user1);
			
			userGameMap.addAll(userGameMaps);
		   System.out.println(userGameMaps);		    
		}
		
		
		return userGameMap; 
	}
		else{
		
		return null;
		}
		
		
		
	}
	@Override
	public Requests updateRequest(int requestId, String message, String status) {
		// TODO Auto-generated method stub
		
		Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
		request.setMessage(message);
		request.setStatus(status);
		return request;
		
	}
	@Override
	public List<UserGameMapping> getStudentsByCoachId(long coachId) {
		// TODO Auto-generated method stub
		
		User user =  hibernateUtil.fetchById(coachId, User.class);
		String query = "from CoachMapping t where t.coach =:entity";
		@SuppressWarnings({ "unchecked" })
		List<CoachMapping> coachMap= hibernateUtil.fetchAll2(query,user);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((coachMap != null) && (coachMap.size() > 0)){
		for (CoachMapping coachMaps : coachMap) {
		    long userId = coachMaps.getStudent().getId();
		    System.out.println(userId);
		    User user1 =  hibernateUtil.fetchById(userId, User.class);
		    
			//boolean userFound = false;
			
		    String query1 = "from UserGameMapping use where use.user=:entity";
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query1,user1);
			
			userGameMap.addAll(userGameMaps);
		   System.out.println(userGameMaps);		    
		}
		
		
		return userGameMap; 
	}
		else{
		
		return null;
		}
		
	}
	@Override
	public List<UserGameMapping> getAllCoaches() {
		// TODO Auto-generated method stub
		Role role =  hibernateUtil.fetchById(101, Role.class);
		String query1 = "from User use where use.role=:entity";
		//boolean userFound = false;
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		List<User> user = hibernateUtil.fetchAll2(query1,role);
		if((user != null) && (user.size() > 0)){
		for(User users:user){
			
	    String query = "from UserGameMapping use where use.user=:entity";
		List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query,users);
		userGameMap.addAll(userGameMaps);
		}
		return userGameMap;
		}
		else{
		
		return null;
		}
	}
	@Override
	public void deleteCoachMapping(long studentId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(studentId, User.class);
		String query = "delete CoachMapping where student = :entity";
		 hibernateUtil.delete1(query,user);
	}
	@Override
	public void deleteOrgMapping(long userId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(userId, User.class);
		String query = "delete OrgMapping where user = :entity";
		 hibernateUtil.delete1(query,user);
	}
	@Override
	public List<UserGameMapping> getAllPlayers() {
		// TODO Auto-generated method stub
		Role role =  hibernateUtil.fetchById(100, Role.class);
		String query1 = "from User use where use.role=:entity";
		//boolean userFound = false;
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		List<User> user = hibernateUtil.fetchAll2(query1,role);
		if((user != null) && (user.size() > 0)){
		for(User users:user){
			
	    String query = "from UserGameMapping use where use.user=:entity";
		List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query,users);
		userGameMap.addAll(userGameMaps);
		}
		return userGameMap;
		}
		else{
		
		return null;
		}
	}
	@Override
	public List<FamilyDetails> getFamilyByChildId1(long childId) {
		// TODO Auto-generated method stub
		User user = hibernateUtil.fetchById(childId, User.class);
		String query = "from FamilyDetails use where use.user=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		List<FamilyDetails> familyDetails = hibernateUtil.fetchAll2(query,user);
		return familyDetails;
	}
	@Override
	public Team updateTeam(long teamOwner, long coachId,int requestId) {
		// TODO Auto-generated method stub
		User teamowner = hibernateUtil.fetchById(teamOwner, User.class);
		User teamcoach = hibernateUtil.fetchById(coachId, User.class);
		String query = "from Team use where use.teamCoach=:entity";
		@SuppressWarnings("unchecked")
		Team team = hibernateUtil.fetchteam(query,teamcoach);
		if(team != null){
			String query2 = "from Team use where use.teamOwner=:entity";
			//boolean userFound = false;
			@SuppressWarnings("unchecked")
			Team team2 = hibernateUtil.fetchteam(query2,teamowner);
			
			//Team team1 = hibernateUtil.fetchById(team.getTeam_id(),Team.class);
			User teamcoach1 = null;
				team2.setTeamCoach(teamcoach1);
				Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Your UnJoined From Team");
				request.setStatus("Accepted");
			
			
			return team2;
		}else{
		String query1 = "from Team use where use.teamOwner=:entity";
		//boolean userFound = false;
		@SuppressWarnings("unchecked")
		Team team1 = hibernateUtil.fetchteam(query1,teamowner);
		
		//Team team1 = hibernateUtil.fetchById(team.getTeam_id(),Team.class);
			team1.setTeamCoach(teamcoach);
			
			 Requests request =  hibernateUtil.fetchById(requestId, Requests.class);
				request.setMessage("Your Added as TeamMember");
				request.setStatus("Accepted");
			
			
			return team1;
		}
		
		
	}
	@Override
	public List<UserGameMapping> getOrgPlayers(long userId) {
		// TODO Auto-generated method stub
		User user =  hibernateUtil.fetchById(userId, User.class);
		String query = "from Organization t where t.org_owner =:entity";
		Organization org = hibernateUtil.fetchteam(query,user);
		String query1 = "from OrgMapping t where t.org =:entity";
		@SuppressWarnings({ "unchecked" })
		List<OrgMapping> orgMap= hibernateUtil.fetchAll2(query1,org);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((orgMap != null) && (orgMap.size() > 0)){
		for (OrgMapping orgMaps : orgMap) {
		int roleId = orgMaps.getUser().getRole().getRole_id();
		if(roleId == 100){
			User user1 =  hibernateUtil.fetchById(orgMaps.getUser().getId(), User.class);
			String query2 = "from UserGameMapping use where use.user=:entity";
			@SuppressWarnings("unchecked")
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query2,user1);
			userGameMap.addAll(userGameMaps);
		 }
		}
		return userGameMap;
	}
		else{
		return null;
		}
	}
	@Override
	public List<UserGameMapping> getOrgCoaches(long userId) {
		// TODO Auto-generated method stub
		User user =  hibernateUtil.fetchById(userId, User.class);
		String query = "from Organization t where t.org_owner =:entity";
		Organization org = hibernateUtil.fetchteam(query,user);
		String query1 = "from OrgMapping t where t.org =:entity";
		@SuppressWarnings({ "unchecked" })
		List<OrgMapping> orgMap= hibernateUtil.fetchAll2(query1,org);
		List<UserGameMapping> userGameMap = new ArrayList<UserGameMapping>();
		if((orgMap != null) && (orgMap.size() > 0)){
		for (OrgMapping orgMaps : orgMap) {
		int roleId = orgMaps.getUser().getRole().getRole_id();
		if(roleId == 101){
			User user1 =  hibernateUtil.fetchById(orgMaps.getUser().getId(), User.class);
			String query2 = "from UserGameMapping use where use.user=:entity";
			@SuppressWarnings("unchecked")
			List<UserGameMapping> userGameMaps = hibernateUtil.fetchAll2(query2,user1);
			userGameMap.addAll(userGameMaps);
		 }
		}
		return userGameMap;
		}
	
		else{
		return null;
		}
	}
	@Override
	public List<Team> getOrgTeams(long userId) {
		// TODO Auto-generated method stub
		User user =  hibernateUtil.fetchById(userId, User.class);
		String query = "from Organization t where t.org_owner =:entity";
		Organization org = hibernateUtil.fetchteam(query,user);
		String query1 = "from OrgMapping t where t.org =:entity";
		@SuppressWarnings({ "unchecked" })
		List<OrgMapping> orgMap= hibernateUtil.fetchAll2(query1,org);
		List<Team> team = new ArrayList<Team>();
		if((orgMap != null) && (orgMap.size() > 0)){
		for (OrgMapping orgMaps : orgMap) {
		int roleId = orgMaps.getUser().getRole().getRole_id();
		if(roleId == 102){
			User user1 =  hibernateUtil.fetchById(orgMaps.getUser().getId(), User.class);
			String query2 = "from Team use where use.teamOwner=:entity";
			@SuppressWarnings("unchecked")
			List<Team> teams = hibernateUtil.fetchAll2(query2,user1);
			team.addAll(teams);
		 }
		}
		
	}
		return team;
	}
	@Override
	public Team getTeamByCoach(long coachId) {
		// TODO Auto-generated method stub
		User user1 =  hibernateUtil.fetchById(coachId, User.class);
		String query1 = "from Team use where use.teamCoach=:entity";
		@SuppressWarnings("unchecked")
		Team team = hibernateUtil.fetchteam(query1,user1);
		return team;
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
