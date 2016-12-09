package com.givit.controller.master;

import com.givit.dao.UserDao;
import com.givit.dao.UserSessionDao;
import com.givit.model.master.APIRequest;
import com.givit.model.master.APIResponse;
import com.givit.model.master.MasterConfiguration;
import com.givit.model.master.User;
import com.givit.model.master.UserSession;
import com.givit.security.JWTHelper;
import com.givit.util.LoginHelper;
import com.givit.util.PasswordHelper;
import com.givit.util.StaticName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageNotWritableException;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.util.List;
import java.util.Map;

/**
 *
 * {
 *     "apiKey":               "DLKC89Xl+!w9djkxlkdSDKJLKD",
 *     "token":                 "skfjalsdkladkjlkcjlkxc...adsfasdasda..........asdfasd",
 *     "operation":         "USER",
 *     ["param1", "param2" .... ]
 * }
 *
 *
 */
@Component
@EnableJpaRepositories(basePackages = {"com.givit.dao", "com.givit.model.master"})
@EntityScan({"com.givit.dao", "com.givit.model.master"})
@Import(MasterConfiguration.class)
@Transactional
public class SecurityController{

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private User user;
    
    @Autowired
    private UserSession userSession;

    /**
     * New User sign up - create new user record
     * @param json
     * @return APIResponse
     */
    public APIResponse register(String json) {
        APIResponse response = new APIResponse();
        try {
            APIRequest request = APIRequest.parse(json);
            Map<String, String> paramMap = LoginHelper.getParamMap(request.getParams());
            UserDao userDao = new UserDao();
            // TODO: check if the user exists and data validation ..
            //email regex check, password minimum requirment check
            String username = paramMap.get(StaticName.USERNAME);
            UserDao storedUser = user.findByUsername(username);
            if(storedUser != null) {
                response.setCode("400");
                response.setMessage("User name exists, Please try again or Click Forget Password");
                response.setBody(null);
            } else {
                String token = jwtHelper.generateToken(username);
                userDao.setPassword(PasswordHelper.getHashedString(paramMap.get(StaticName.PASSWORD)));
                userDao.setUsername(username);
                user.save(userDao);
                Long userId= user.findByUsername(userDao.getUsername()).getUserId();
                UserSessionDao userSessionDao = new UserSessionDao(userId, token);
                userSession.save(userSessionDao);
                response.setCode("201");
                response.setMessage("Success");
                response.setBody(userSessionDao);
            }
        }
        catch (HttpMessageNotWritableException e){
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return response;
    }
    
    /**
     * User sign in
     * @param json
     * @return
     */
    public APIResponse signIn(String json) {

        APIResponse response = new APIResponse();
        try {
            // Step 1. parse and get request object from JSON request
            APIRequest request = APIRequest.parse(json);

            // Step 2. validate api key
            if(!validateAPIKey(request.getApiKey())) {
                throw new SecurityException("invalid API key");
            }
            String userName = "";
            String password = "";
            List<LinkedTreeMap<String,String>> params = request.getParams();
            LinkedTreeMap<String, String> map = params.get(0);
            for(Map.Entry<String, String> entry :map.entrySet()){
            	if (entry.getKey().equals("username")) {
            		userName = entry.getValue();
            		System.out.println("params[0] = " + userName);
            	} else if (entry.getKey().equals("password")){
            		password = entry.getValue();
            		System.out.println("params[0] = " + password);
            	}           	
            }

            UserDao userDao = user.findByUsername(userName);
            if(userDao != null) {
            	if (PasswordHelper.isExpectedPassword(password, userDao.getPassword())) {
            		 String token = jwtHelper.generateToken(userDao.getUsername());
                     //Saving Token and userId into user_session table;
                     Gson gson = new Gson();
                     UserSessionDao userSessionDao = gson.fromJson(json, UserSessionDao.class);
                     userSessionDao.setToken(token);
                     userSessionDao.setUserId(userDao.getUserId());
                     userSession.save(userSessionDao);
                     response.setCode("200");
                     response.setMessage("Success");
                     response.setBody(userSessionDao);
            	} else {
            		response.setCode("401");
                    response.setMessage("User name or password is invalid");
                    response.setBody(null);
            	}
            } else {
                response.setCode("400");
                response.setMessage("The User name doesn't exist");
                response.setBody(null);
            }

        } catch (SecurityException e) {
            response.setCode("500");
            response.setMessage(e.getMessage());
            response.setBody(null);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Unknown problem");
            response.setBody(null);
            System.out.println(e.getMessage());
        }

        System.out.println(response.getBody());

        return response;
    }
    
    public APIResponse checkConnect(String json) {
    	APIRequest request = APIRequest.parse(json);
    	long userId = 0l;
        String token = "";
        APIResponse response = new APIResponse();
    	List<LinkedTreeMap<String,String>> params = request.getParams();
        LinkedTreeMap<String, String> map = params.get(0);
        for(Map.Entry<String, String> entry :map.entrySet()){
        	if (entry.getKey().equals("userId")) {
        		userId = Long.valueOf(entry.getValue());
        		System.out.println("params[0] = " + userId);
        	} else if (entry.getKey().equals("token")){
        		token = entry.getValue();
        		System.out.println("params[0] = " + token);
        	}           	
        }
        UserSessionDao userSessionDao = userSession.findByToken(token);
        if (userSessionDao != null) {
        	
        	response.setCode("200");
        	response.setMessage("signOut");
        } else {
        	response.setCode("401");
        	response.setMessage("not conncted");
        }
		return response;
    	
    }
    
    public APIResponse signOut(String json) {
    	APIRequest request = APIRequest.parse(json);
    	long userId = 0l;
        String token = request.getToken();
        APIResponse response = new APIResponse();
    	List<LinkedTreeMap<String,String>> params = request.getParams();
        LinkedTreeMap<String, String> map = params.get(0);
        for(Map.Entry<String, String> entry :map.entrySet()){
        	if (entry.getKey().equals("userId")) {
        		userId = Long.valueOf(entry.getValue());
        		System.out.println("params[0] = " + userId);
        	}              	
        }
        UserSessionDao userSessionDao = userSession.findByToken(token);
        if (userSessionDao != null) {
        	userSession.delete(userId);
        	response.setCode("200");
        	response.setMessage("Sign out Success.");
        } else {
        	response.setCode("401");
        	response.setMessage("There is no token.");
        }
		return response;
    }
   

    /**
     * User sign in
     * @param json
     * @return
     */
    public APIResponse refreshToken(String json) {

        APIResponse response = new APIResponse();
        try {

            // Step 1. parse and get request object from JSON request
            APIRequest request = APIRequest.parse(json);

            // Step 2. validate api key
            if(!validateAPIKey(request.getApiKey())) throw new SecurityException("invalid API key");

            //System.out.println("apiKey = " + request.getApiKey() );
            //System.out.println("operator = " + request.getOperator());
            //System.out.println("token = " + request.getToken());
            //System.out.println("params = " + request.getParams().length);
            //String params[] = request.getParams();

            // TODO: Check API key and Token


            String token = request.getToken();
            String username = jwtHelper.getUsernameFromToken(token);
            System.out.println("Get User ID from Token: " + username);
            System.out.println("Created on: " + jwtHelper.getCreatedDateFromToken(token));
            System.out.println("Expired on: " + jwtHelper.getExpirationDateFromToken(token));

            token = jwtHelper.generateToken(username);

            response.setCode("200");
            response.setMessage("Success");
            response.setBody(token);

        }
        catch (SecurityException e) {
            response.setCode("300");
            response.setMessage(e.getMessage());
            response.setBody(null);
        }

        System.out.println(response.getBody());

        return response;
    }

    /**
     * Validate API Key
     * The current impleemntation: always return true.
     * TODO:
     * The future implementation can validate API keys against database table.
     *
     * @param apiKey
     * @return
     */
    private boolean validateAPIKey(String apiKey) {
            return true;
    }

}
