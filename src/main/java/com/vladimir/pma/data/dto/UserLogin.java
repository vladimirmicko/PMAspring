package com.vladimir.pma.data.dto;

import java.io.Serializable;

/**
 * Class used for user login detais transfer from client to server
 * @author Vladimir
 *
 */
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 5890769251355841903L;
	
	private String username;
	private String password;
	private String token;

	public UserLogin() {
	}

	public UserLogin(String username, String password, String token) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
