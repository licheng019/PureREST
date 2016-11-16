package com.pureilab.controller.master;

import com.pureilab.model.master.APIRequest;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
/**
 * Created by Julian on 11/14/2016.
 */
@Component
public class ControllerHelper {

    public APIRequest parseRequest(String jsonRequest) {

        Gson gson = new Gson();
        APIRequest request = gson.fromJson(jsonRequest, APIRequest.class);
        System.out.println("JSON: " + jsonRequest);
        return new APIRequest();

    }
}
