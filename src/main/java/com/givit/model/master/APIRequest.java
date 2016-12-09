package com.givit.model.master;

import java.io.StringReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

public class APIRequest {

	private static final long serialVersionUID = 1L;
    private String apiKey = "";

    private String token = "";

    private String operator = "";

    private List params  = null;

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



    public List<LinkedTreeMap<String, String>> getParams() {
		return params;
	}

	public void setParams(List params) {
		this.params = params;
	}

	public static APIRequest parse(String json) {

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(json));
        APIRequest request = gson.fromJson(reader, APIRequest.class);
        //String params[] = request.getParams();
        return request;

    }
}
