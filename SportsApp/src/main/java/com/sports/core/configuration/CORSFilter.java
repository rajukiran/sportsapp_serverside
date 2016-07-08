package com.sports.core.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CORSFilter implements Filter {

	private String urls = "/login,/logout,/allfamily,/familyById,/allusers,/nearbyteams,/getUserById,/getUser,/getTeamPlayer,/sendrequest,/receivedrequest,/deleteUserGame,/updatePass,/updateUser,/allLevels,/getaddress,/alladdress,/allcity,/allcities,/allcontinents,/allcountries,/allcountries1,/allstates,/allstates1,/allgames,/allschedules,/allmatches,/teams,/allroles,/orgs,/saveOrganization,/saveTeam,/saveTeamMapping,/schedules,/team,/team1,/allmatches,/mymatches";
	private List<String> allowUrls = null;
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out
				.println("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Expose-Headers"," X-Token, header-a,JSESSION_ID");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"Content-Type, Accept, Access-Control-Allow-Headers,Access-Control-Expose-Headers, Authorization, X-Requested-With,X-Token, header-a");

		HttpServletRequest request = (HttpServletRequest) req;
		boolean allowedRequest = false;
		String url = request.getServletPath();
		
		//System.out.println("Requested URL :: "+url);
		 //request.getHeader(response.getHeader("JSESSION_ID"));
        //System.out.println("jsession id is"+request.getParameter("JSESSIONID"));
        HttpSession session1 = request.getSession(false);
		String session = request.getParameter("JSESSIONID");
		if (validateURL(url)) {
			if(allowUrls.contains(url)){
				allowedRequest = true;	
			}
			else{
				allowedRequest = (null != session || null != session1)? true:false;	
			}
			
		}

		if (!allowedRequest) {

			if (null == session) {
				RequestDispatcher rd = req.getRequestDispatcher("/logoutMsg");
	            rd.forward(request, response);
	            return;
				
			}
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) {
		
		allowUrls = new ArrayList<String>();
		allowUrls.add("/login");
		allowUrls.add("/logout");
		allowUrls.add("/saveUserGameMap");
		allowUrls.add("/allroles");
	}
	
	public boolean validateURL(String urlStr) {

		StringTokenizer token = new StringTokenizer(urls, ",");
		boolean urlFlag = false;
		while (token.hasMoreTokens()) {
			if(urlStr.equals(token.nextToken())){
				
				urlFlag = true;
			}

		}
		return urlFlag;
	}

	public void destroy() {
	}

}