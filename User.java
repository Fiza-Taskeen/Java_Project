package com.tap.model;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userAddress;
	
		
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}



	

	public User(int userId, String userName, String userEmail, String userPassword, String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
	}

	
	public User(String userName, String userEmail, String userPassword, String userAddress) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
	}
	
	
	
	public User() {
		super();
	}
	
	

	@Override
	public String toString() {
		return userId + "   " +  userName + "  " + userEmail + "  " 
				+ userPassword + "  "  + userAddress;
	}


}
