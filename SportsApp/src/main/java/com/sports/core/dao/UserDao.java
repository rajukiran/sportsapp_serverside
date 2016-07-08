package com.sports.core.dao;

import java.util.List;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
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
import com.sports.core.domain.Requests;
import com.sports.core.domain.Role;
import com.sports.core.domain.Schedule;
import com.sports.core.domain.SkillLevel;
import com.sports.core.domain.State;
import com.sports.core.domain.Team;
import com.sports.core.domain.TeamMapping;
import com.sports.core.domain.User;
import com.sports.core.domain.UserGameMapping;
import com.sports.core.model.UserVO;

public interface UserDao {

	public long createUser(User user,int id1);
	public int createUser(FamilyDetails family);
	public boolean checkEmail(User user);
	public int createUser(User user,UserGameMapping userGameMapping,FamilyDetails family,int id1,int[] ids,int gameId,int levelId,int yearsOfExp,String createdBy,long parentId);
	public int createRequest(Requests requests,long senderId,long receiverId,long[] childId,long receiverChildId);
	public User updateUser(User user,int roleId);
	public UserGameMapping updateUserGameMap(User user,UserGameMapping userGameMap,long userId,int roleId,int mappingId,int[] ids,int gameId,int levelId,int yearsOfExp,String createdBy);
	public User updatePass(long id,String password);
	public Team updateTeam(long teamOwner,long coachId,int requestId);
	public Requests updateRequest(int requestId,String message,String status);
	public User updateProfile(long id,byte[] profilePic);
	public void deleteUser(long id);
	public void deleteUserGameMap(int[] mappingIds);
	public void deleteUserGame(int id);
	public List<User> getAllUsers();
	public User getUser(long id); 
	public List<UserGameMapping> getUserGameMap(long userId);
	public List<UserGameMapping> getAllCoaches();
	public List<CoachMapping> getAllChildsForCoach(long childId);
	public List<CoachMapping> getAllChildsForCoach();
	public List<UserGameMapping> getAllPlayers();
	public UserGameMapping getUser1(int id);
	public List<User> getAllUsers(String userName);
	public List<User> checkLogin(String userName,String userPassword);
	public List<User> checkLogin1(String userName,String userPassword);
	public List<Team> getNearByTeam();
	public List<Requests> getRequestsBySender(long senderId);
	public List<Requests> getRequestsByReceiver(long receiverId);
	public List<UserGameMapping> getCoachByStudentId(long studentId);
	public List<UserGameMapping> getStudentsByCoachId(long coachId);
	public int createCoachMapping(CoachMapping coachMap,long coachId,long studentId,int requestId);
	public int createCoachMappingForChilds(CoachMapping coachMap,long coachId,long studentId,int requestId);
	public void deleteCoachMapping(long studentId);
	public List<Requests> getRequestByStatus(long senderId);
	public List<Requests> getRequestByStatusCoach(long senderId);
	public List<Requests> getRequestByStatusStudent(long senderId);
	public List<Requests> getRequestByStatusOrg(long senderId);
	//public User getTeamPlayer(long playerId);
	/** List of states table */
	public int createState(State state);
	public State updateState(State state);
	public void deleteState(int id);
	public List<State> getAllStates();
	public State getState(int id); 
	public List<State> getAllStates(int countryId);
	/** List of teams table */
	public int createTeam(Team team,GeoLocation geolocation,int gameId,long userId,long teamCoach,long teamCaptain,int continentId,int countryId,int stateId,Address address);
	public Team updateTeam(Team team);
	public void deleteTeam(int id);
	public void deleteTeamMapping(long playerId);
	public List<Team> getAllTeams();
	public List<TeamMapping> getAllChilds(long childId);
	public List<TeamMapping> getAllChilds();
	public Team getTeam(int id);
	public Team getTeamByCoach(long coachId);
	public List<Team> getAllTeams(long userId);
	public int createTeamMapping(TeamMapping teamMap,long teamOwnerId,long userId,int requestId);
	public int createTeamMappingForChilds(TeamMapping teamMap,long teamOwnerId,long userId,int requestId);
	public List<UserGameMapping> getTeamPlayersById(int teamId);
	public List<UserGameMapping> getTeamPlayersByTeamOwner(long teamOwnerId);
	public TeamMapping getTeamByPlayerId(long playerId);
	/** List of schedules table */
	public int createSchedule(Schedule schedule);
	public Schedule updateSchedule(Schedule schedule);
	public void deleteSchedule(int id);
	public List<Schedule> getAllSchedules();
	public Schedule getSchedule(int id);
	public List<Schedule> getAllSchedules(long userId);
	/** List of role table */
	public int createRole(Role role);
	public Role updateRole(Role role);
	public void deleteRole(int id);
	public List<Role> getAllRoles();
	public Role getRole(int id);
	public List<Role> getAllRoles(String roleName);
	/** List of organization table */
	public int createOrganization(Organization organization,long userId);
	public Organization updateOrganization(Organization organization);
	public void deleteOrganization(int id);
	public List<Organization> getAllOrganizations();
	public Organization getOrganization(int id);
	public List<OrgMapping> getAllOrganizations(long userId);
	public List<UserGameMapping> getOrgPlayers(long userId);
	public List<UserGameMapping> getOrgCoaches(long userId);
	public List<Team> getOrgTeams(long userId);
	public int createOrgMapping(OrgMapping orgMap,long orgOwnerId,long userId,int requestId);
	public void deleteOrgMapping(long userId);
	/** List of matches table */
	public int createMatches(com.sports.core.domain.Match match,int schedId,int teamId);
	public com.sports.core.domain.Match updateMatch(com.sports.core.domain.Match match);
	public void deleteMatch(int id);
	public List<com.sports.core.domain.Match> getAllMatches();
	public com.sports.core.domain.Match getMatches(int id);
	public List<com.sports.core.domain.Match> getAllMatches(long userId);
	/** List of history table */
	public int createHistory(History history,int schedId,int teamId,long userId,int matchId);
	public History updateHistory(History history);
	public void deleteHistory(int id);
	public List<History> getAllHistory();
	public History getHistory(int id);
	public List<History> getAllHistory(String historyName);
	
	/** List of country's table */
	public int createCountry(Country country);
	public Country updateCountry(Country country);
	public void deleteCountry(int id);
	public List<Country> getAllCountry();
	public Country getCountry(int id);
	public List<Country> getAllCountry(int continentId);
	
	/** List of continent's table */
	public int createContinent(Continent continent);
	public Continent updateContinent(Continent continent);
	public void deleteContinent(int id);
	public List<Continent> getAllContinent();
	public Continent getContinent(int id);
	public List<Continent> getAllContinent(String continentName);
/** List of city's table */
	public int createCity(City city);
	public City updateCity(City city);
	public void deleteCity(int id);
	public List<City> getAllCity();
	public City getCity(int id);
	public List<City> getAllCity(int stateId);
	/** List of Address's table 
	 * @param stateId 
	 * @param countryId 
	 * @param continentId */
	public int createAddress(Address address, int continentId, int countryId, int stateId);
	public Address updateAddress(Address address,int continentId, int countryId, int stateId);
	public void deleteAddress(int id);
	public List<Address> getAllAddress();
	public Address getAddress(int id);
	public Address getAddress(int columnId,String tableName);
	//public List<Object> getAllList(String tableName);
	
	/** List of attachment's table */
	public int createAttachment(Attachments attachments);
	public Attachments updateAttachements(Attachments attachments);
	public void deleteAttachements(int id);
	public List<Attachments> getAllAttachments();
	public Attachments getAttachement(int id);
	
	/** List of family details's table */
	public int createFamily(FamilyDetails familyDetails);
	public FamilyDetails updateFamily(FamilyDetails familyDetails);
	public void deleteFamily(int id);
	public List<FamilyDetails> getAllFamilyDetails();
	public FamilyDetails getFamily(int id);
	public  List<UserGameMapping> getAllFamilyDetails(long parentId);
	public  List<FamilyDetails> getAllFamilyDetails1(long parentId);
	public  FamilyDetails getFamilyByChildId(long childId);
	public  List<FamilyDetails> getFamilyByChildId1(long childId);
	
	/** List of geolocation's table */
	public int createGeoLocation(GeoLocation geoLocation);
	public GeoLocation updateGeoLocation(GeoLocation geoLocation);
	public void deleteGeoLocation(int id);
	public List<GeoLocation> getAllGeoLocations();
	public GeoLocation getGeoLocation(int id);
	public GeoLocation getGeoLocation(int columnId,String tableName);
	/** List of game's table */
	public int createGames(Games games);
	public Games updateGames(Games games);
	public void deleteGames(int id);
	public List<Games> getAllGames();
	public Games getGames(int id);
	/** List of skillLevel's table */
	public int createSkillLevel(SkillLevel skillLevel);
	public SkillLevel updateSkillLevel(SkillLevel skillLevel);
	public void deleteSkillLevel(int id);
	public List<SkillLevel> getAllSkillLevel();
	public SkillLevel getSkillLevel(int id);
	
	
}
