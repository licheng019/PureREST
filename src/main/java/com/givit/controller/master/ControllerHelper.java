package com.givit.controller.master;

import org.springframework.stereotype.Component;

import com.givit.model.master.APIRequest;
import com.google.gson.Gson;

@Component
public class ControllerHelper {

    public APIRequest parseRequest(String jsonRequest) {

        Gson gson = new Gson();
        APIRequest request = gson.fromJson(jsonRequest, APIRequest.class);
        System.out.println("JSON: " + jsonRequest);
        return new APIRequest();

    }
}
