package com.sports.core.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.sports.core.exception.SportsException;
import com.sports.core.domain.Address;
import com.sports.core.domain.Attachments;
import com.sports.core.model.AddressVO;
import com.sports.core.model.CityVO;
import com.sports.core.model.ContinentVO;
import com.sports.core.model.CountryVO;
import com.sports.core.model.FamilyDetailsVO;
import com.sports.core.model.GamesVO;
import com.sports.core.model.GeoLocationVO;
import com.sports.core.domain.GeoLocation;
import com.sports.core.model.HistoryVO;
import com.sports.core.model.LoginVO;
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



public interface UserService {
	
	UserVO findById(long id) throws SportsException,IOException;
	UserGameMappingVO findById2(int id) throws SportsException;
	List<UserVO> findUserGameMap(long userId) throws SportsException;
	UserVO findByName(String name);
	
	long saveUser(UserVO user);
	int saveUser(UserGameMappingVO userGameMapping) throws SportsException;
	int saveRequest(RequestsVO request);
	void updateUser(UserVO user)throws SportsException,ParseException;
	void updatePassword(long id,UserVO userVo)throws SportsException;
	void updateTeam(long teamOwner,long coachId,int requestId)throws SportsException;
	void updateRequest(RequestsVO requestvo);
	UserVO updateProfile(long id,byte[] profilePic)throws SportsException;
	void deleteUserById(long id);
    void deleteUserGameById(int id);
	List<UserVO> findAllUsers(); 
	List<RequestsVO> getRequestsBySender(long senderId)throws SportsException;
	List<RequestsVO> getRequestsByReceiver(long receiverId)throws SportsException;
	List<RequestsVO> getRequestByStatus(long senderId)throws SportsException;
	List<RequestsVO> getRequestByStatusCoach(long senderId)throws SportsException;
	List<RequestsVO> getRequestByStatusStudent(long senderId)throws SportsException;
	List<RequestsVO> getRequestByStatusOrg(long senderId)throws SportsException;
	List<UserVO> getCoachByStudentId(long studentId)throws SportsException;
	List<UserVO> getStudentsByCoachId(long coachId)throws SportsException;
	void saveCoachMapping(long coachId,long studentId,int requestId);
	void saveCoachMappingForChilds(long coachId,long studentId,int requestId);
	void deleteCoachMappingByStudentId(long studentId);
	void deleteAllUsers();
	List<UserVO> getAllCoaches();
	List<UserVO> getAllPlayers();
	public UserVO isUserExist(String userName,String password) throws SportsException;
	/*List of States table */
	
StateVO findById1(int id) throws SportsException;
	
	StateVO findByName1(String name);
	
	void saveState(StateVO state);
	
	void updateState(StateVO state);
	
	void deleteStateById(int id);

	List<StateVO> findAllStates(); 
	List<StateVO> findAllStates(int countryId); 
	
	void deleteAllStates();
	
	public boolean isStateExist(StateVO state);
	/* List of teams */
	
	TeamVO findByTeamId(int id) throws SportsException;
	TeamVO findTeamByCoach(long coachId) throws SportsException;
	
	TeamVO findByTeamName(String name);
	
	void saveTeam(TeamVO team);
	void saveTeamMapping(long userId,long teamOwnerId,int requestId);
	void updateTeam(TeamVO team);
	
	void deleteTeamById(int id);
	void deleteTeamMappingByPlayerId(long playerId);
	List<TeamVO> findAllTeams(); 
	
	List<TeamVO> findAllTeams(long userId); 
	List<TeamVO> findNearByTeam();
	List<TeamVO> findNearByTeamForParents(long parentId);
	List<UserVO> getTeamPlayersById(int teamId)throws SportsException;
	List<UserVO> getTeamPlayersByteamOwnerId(long teamOwnerId)throws SportsException;
	TeamVO getTeamByPlayerId(long playerId)throws SportsException;
	void saveTeamMappingForChilds(long teamOwnerId,long userId,int requestId);
	void deleteAllTeams();
	/* List Of schedules */
ScheduleVO findByScheduleId(int id) throws SportsException;
	
	ScheduleVO findByScheduleName(String name);
	
	void saveSchedule(ScheduleVO schedule);
	
	void updateSchedule(ScheduleVO schedule);
	
	void deleteScheduleById(int id);

	List<ScheduleVO> findAllSchedules(); 
	List<ScheduleVO> findMySchedules(long userId);
	void deleteAllSchedules();
	/* List Of Roles */
RoleVO findByRoleId(int id) throws SportsException;
	
	RoleVO findByRoleName(String name);
	
	void saveRole(RoleVO role);
	
	void updateRole(RoleVO role);
	
	void deleteRoleById(int id);

	List<RoleVO> findAllRoles(); 
	
	void deleteAllRoles();
	/*List of Organizations table */
OrganizationVO findByOrganizationId(int id) throws SportsException;
	
	OrganizationVO findByOrganizationName(String name);
	
	void saveOrganization(OrganizationVO organization);
	void saveOrganizationMapping(long orgOwnerId,long userId,int requestId);
	void updateOrganization(OrganizationVO organization);
	void deleteOrgMappingByUserId(long userId);
	void deleteOrganizationById(int id);

	List<OrganizationVO> findAllOrganizations(); 
	List<OrganizationVO> findAllOrganizations(long userId); 
	List<UserVO> findOrgPlayers(long userId); 
	List<UserVO> findOrgCoaches(long userId); 
	List<TeamVO> findOrgTeams(long userId); 
	void deleteAllOrganizations();
	/*List of Matches */
MatchVO findByMatchId(int id) throws SportsException;
	
	MatchVO findByMatchName(String name);
	
	void saveMatch(MatchVO match);
	
	void updateMatch(MatchVO match);
	
	void deleteMatchById(int id);

	List<MatchVO> findAllMatches(); 
	List<MatchVO> findMyMatches(long userId); 
	
	void deleteAllMatches();
	/*List of History */
	HistoryVO findByHistoryId(int id) throws SportsException;
		
		HistoryVO findByHistoryName(String name);
		
		void saveHistory(HistoryVO history);
		
		void updateHistory(HistoryVO history);
		
		void deleteHistoryById(int id);

		List<HistoryVO> findAllHistory(); 
		
		void deleteAllHistory();
		/*List of Country's */
		CountryVO findByCountryId(int id) throws SportsException;
			
			CountryVO findByCountryName(String name);
			
			void saveCountry(CountryVO country);
			
			void updateCountry(CountryVO country);
			
			void deleteCountryById(int id);

			List<CountryVO> findAllCountry(); 
			List<CountryVO> findAllCountry(int continentId); 
			void deleteAllCountry();
			/*List of Continents */
			ContinentVO findByContinentId(int id) throws SportsException;
				
				ContinentVO findByContinentName(String name);
				
				void saveContinent(ContinentVO continent);
				
				void updateContinent(ContinentVO continent);
				
				void deleteContinentById(int id);

				List<ContinentVO> findAllContinents(); 
				
				void deleteAllContinents();
				/*List of city's */
				CityVO findByCityId(int id) throws SportsException;
					
					CityVO findByCityName(String name);
					
					void saveCity(CityVO city);
					
					void updateCity(CityVO city);
					
					void deleteCityById(int id);

					List<CityVO> findAllCity(); 
					List<CityVO> findAllCity(int stateId); 
					void deleteAllCity();
					/*List of Address's */
					AddressVO findByAddressId(int id) throws SportsException;
						
						
						
						void saveAddress(AddressVO address);
						
						void updateAddress(AddressVO address);
						
						void deleteAddressById(int id);

						List<AddressVO> findAllAddresses(); 
						AddressVO findAddress(int columnId,String tableName)throws SportsException;
						
						void deleteAllAddresses();
						
						/*List of attachment's */
						Attachments findByAttachmentsId(int id) throws SportsException;
							
							
							
							void saveAttachment(Attachments attachments);
							
							void updateAttachment(Attachments attachments);
							
							void deleteAttachmentById(int id);

							List<Attachments> findAllAttachments(); 
							
							void deleteAllAttachments();
							
							
							/*List of family detail's */
							FamilyDetailsVO findByFamilyId(int id) throws SportsException;
								
								
								
								void saveFamily(FamilyDetailsVO familyDetails);
								
								void updateFamily(FamilyDetailsVO familyDetails);
								
								void deleteFamilyById(int id);

								List<FamilyDetailsVO> findAllFamilyDetails(); 
								List<UserVO> findAllFamilyDetails(long parentId); 
								List<UserVO> findAllFamilyDetailsForUnjoin(long parentId);
								List<UserVO> findAllFamilyDetailsForAdd(long parentId);
								List<UserVO> findAllFamilyDetailsForUnjoinToCoach(long parentId);
								List<UserVO> findAllFamilyDetailsForAddToCoach(long parentId);
								void deleteAllFamilyDetails();
								/*List of geoLocation's */
								GeoLocation findByGeoLocationId(int id) throws SportsException;
									
									
									
									void saveGeoLocation(GeoLocationVO geoLocation);
									
									void updateGeoLocation(GeoLocation geoLocation);
									
									void deleteGeoLocationById(int id);

									List<GeoLocation> findAllGeoLocation(); 
									
									void deleteAllGeoLocation();
									GeoLocationVO findLocation(int columnId,String tableName)throws SportsException;
									/*List of game's */
									GamesVO findByGameId(int id) throws SportsException;
										
										
										
										void saveGame(GamesVO games);
										
										void updateGame(GamesVO games);
										
										void deleteGameById(int id);

										List<GamesVO> findAllGames(); 
										
										void deleteAllGames();	
										/*List of SkillLevel's */
										SkillLevelVO findByLevelId(int id) throws SportsException;
											
											
											
											void saveLevel(SkillLevelVO skillLevel);
											
											void updateLevel(SkillLevelVO skillLevel);
											
											void deleteSkillLevelById(int id);

											List<SkillLevelVO> findAllLevels(); 
											
											void deleteAllLevels();	
}
