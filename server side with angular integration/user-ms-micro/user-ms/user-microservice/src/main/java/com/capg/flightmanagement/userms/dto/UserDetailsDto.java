package com.capg.flightmanagement.userms.dto;

import java.math.BigInteger;



/**
 * 
 * for data transfer
 *
 */
public class UserDetailsDto {
	private BigInteger userId;
	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	private String userType;
	
	private String userName;
	private String userPassword;
	private BigInteger userPhone;
	private String email;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public BigInteger getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(BigInteger userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
