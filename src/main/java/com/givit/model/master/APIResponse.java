package com.givit.model.master;

/**
 * Created by Julian on 10/31/2016.
 */
public class APIResponse {
	private static final long serialVersionUID = 1L;
    final public static String CODE_SUCCESS = "200";
    final public static String CODE_FAILED = "500";

    private String code = "";

    private String message = "";

    private Object body = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
