package com.capg.flightmanagement.userms.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.flightmanagement.userms.entities.User;



public interface IUserService {
	public User addUser(User user);
	public List<User> viewAllUser();
	public User findUserById(BigInteger userId);
	public boolean deleteUser(BigInteger userId);
	public User modifyUser(User passenger);
	
}