package com.capg.flightmanagement.userms.entities;

import java.math.BigInteger;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private BigInteger userId;
	
	private String userType;
	private String userName;
	private String userPassword;
	private BigInteger userPhone;
	private String email;

	/**
	 * Default Non Parametrized Constructor
	 */
	public User() {

	}

	

	/**
	 * @return userType
	 */

	public String getUserType() {
		return userType;
	}

	/**
	 * Set the user type
	 * 
	 * @param userType
	 */

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return userId
	 */
	public BigInteger getUserId() {
		return userId;
	}

	/**
	 * Set the user Id
	 * 
	 * @param userId
	 */
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the user Name
	 * 
	 * @param userName
	 */

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Set the user Password
	 * 
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return userPhone
	 */
	public BigInteger getUserPhone() {
		return userPhone;
	}

	/**
	 * Set the user Phone
	 * 
	 * @param userPhone
	 */
	public void setUserPhone(BigInteger userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Set the user email *
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * override hashcode
	 * 
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		return userId.hashCode();
	}

	/**
	 * check equality of user object
	 * 
	 * @param object
	 * @return
	 */

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || !(object instanceof User))
			return false;
		User user = (User) object;
		return this.userId.equals(user.getUserId());
	}

	@Override
	public String toString() {
		return "User [userType=" + userType + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userPhone=" + userPhone + ", email=" + email + "]";
	}

}
