package com.givit.model.master;

import com.google.gson.Gson;

/**
 * Created by Julian on 10/31/2016.
 *
 *
 */
public class APIRequest {

    private String apiKey = "";

    private String token = "";

    private String operator = "";

    private String[] params  = null;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public static APIRequest parse(String json) {

        Gson gson = new Gson();
        APIRequest request = gson.fromJson(json, APIRequest.class);

        return request;

    }
}
