package com.givit.controller.master;

import com.givit.model.master.*;
import com.givit.security.JWTHelper;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.stereotype.Component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.gson.Gson;

import javax.sound.midi.SysexMessage;

/**
 * Created by Julian on 11/14/2016.
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
public class SecurityController {

    // ------------------------
    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private UserDao userDao;

    /**
     *  New User sign up - create new user record
     * @param json
     * @return
     */
    public APIResponse signUp(String json) {

        APIResponse response = new APIResponse();
        try {
            //allObjs = countryDao.findAll();
            //allObjs = countryDao.findAll(new PageRequest(0,2));
            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);
            System.out.println("JSON: " + json);

            // TODO: check if the user exists and data validation ..
            User storedUser = userDao.findByUsername(user.getUsername());
            if(storedUser != null) {
                response.setCode("300");
                response.setMessage("User name exists ...");
                response.setBody(null);
            } else {

                String token = jwtHelper.generateToken(user.getUsername());
                System.out.println("TOKEN: " + token);
                //String userID = jwtHelper.getUsernameFromToken(token);
                //System.out.println("Get User ID from Token: " + userID);

                // Hash Password testing
                String hashedPassword = User.getSaltedHash(user.getPassword());
                System.out.println("Hash: " + hashedPassword);

                user.setPassword(hashedPassword);
                userDao.save(user);

                response.setCode("200");
                response.setMessage("Success");
                response.setBody(user);
            }
        }
        catch (Exception ex) {
            //return "Country not found";
            ex.printStackTrace();
            return null;
        }
        System.out.println(response.getBody());

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

            System.out.println("apiKey = " + request.getApiKey() );
            System.out.println("operator = " + request.getOperator());
            System.out.println("token = " + request.getToken());
            System.out.println("params = " + request.getParams().length);

            String params[] = request.getParams();

            // TODO: Check API key and Token

            System.out.println("params[0] = " + params[0]);
            System.out.println("params[1] = " + params[1]);

            String username = params[0];
            String password = params[1];

            //String hashedPassword =  User.getSaltedHash(params[1]);
            //User user = userDao.findByUsernameAndPassword(params[0], hashedPassword);

            User user = userDao.findByUsername(params[0]);
            if(user != null && User.check(password, user.getPassword())) {

                System.out.println("password: " + user.getPassword());
                String token = jwtHelper.generateToken(user.getUsername());
                System.out.println("TOKEN: " + token);
                response.setCode("200");
                response.setMessage("Success");
                response.setBody(token);
            } else {
                response.setCode("300");
                response.setMessage("User name or password is invalid");
                response.setBody(null);
            }

        } catch (SecurityException e) {
            response.setCode("300");
            response.setMessage(e.getMessage());
            response.setBody(null);
        } catch (Exception e) {
            response.setCode("300");
            response.setMessage("Unknown problem");
            response.setBody(null);
        }

        System.out.println(response.getBody());

        return response;
    }

    /**
     * User sign in
     * @param json
     * @return
     */
    public APIResponse test(String json) {

        APIResponse response = new APIResponse();
        try {

            // Step 1. parse and get request object from JSON request
            APIRequest request = APIRequest.parse(json);

            // Step 2. validate api key
            if(!validateAPIKey(request.getApiKey())) throw new SecurityException("invalid API key");

            System.out.println("apiKey = " + request.getApiKey() );
            System.out.println("operator = " + request.getOperator());
            System.out.println("token = " + request.getToken());
            System.out.println("params = " + request.getParams().length);

            String params[] = request.getParams();

            // TODO: Check API key and Token

            System.out.println("params[0] = " + params[0]);
            System.out.println("params[1] = " + params[1]);

            String token = request.getToken();
            String username = jwtHelper.getUsernameFromToken(token);
            System.out.println("Get User ID from Token: " + username);
            System.out.println("Created on: " + jwtHelper.getCreatedDateFromToken(token));
            System.out.println("Expired on: " + jwtHelper.getExpirationDateFromToken(token));

            response.setCode("200");
            response.setMessage("Success");
            response.setBody("successfully complete the task");

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
