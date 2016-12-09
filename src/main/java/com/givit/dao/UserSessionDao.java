package com.givit.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
public class UserSessionDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "userId")
    private long userId;
	
	@Column(name = "token")
    private String token;
	
	public UserSessionDao() {
		
	}
	
	public UserSessionDao(long userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
