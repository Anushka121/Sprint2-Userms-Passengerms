package com.capg.flightmanagement.userms.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.flightmanagement.userms.dao.IUserDao;
import com.capg.flightmanagement.userms.entities.User;
import com.capg.flightmanagement.userms.exceptions.UserNotFoundException;
import com.capg.flightmanagement.userms.util.UserValidation;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	private IUserDao dao;

	public IUserDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	/**
	 * @param User validates the User and add to database
	 * 
	 * @return User
	 */
	@Override
	public User addUser(User user) {
		UserValidation.validateUser(user);
		BigInteger phone = user.getUserPhone();
		String email = user.getEmail();
		UserValidation.validatePhone(phone);
		UserValidation.validateEmail(email);
		return dao.save(user);
	}

	/**
	 * @return list of users
	 */
	@Override
	public List<User> viewAllUser() {
		List<User> userList = dao.findAll();
		return userList;
	}

	/**
	 * @param userId validating and finding user with userId
	 * @return user
	 */

	@Override
	public User findUserById(BigInteger userId) {

		Optional<User> optional = dao.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			return user;
		}
		throw new UserNotFoundException("user not found for id =" + userId);

	}

	/**
	 * @param userId deletes user
	 * @return true/false
	 * 
	 */
	@Override
	public boolean deleteUser(BigInteger userId) {
		Optional<User> optional = dao.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			dao.delete(user);
			return true;

		}
		throw new UserNotFoundException("user not exits" + userId);
	}

	/**
	 * @param user to modify userDetails
	 * @return user
	 */

	@Override
	public User modifyUser(User user) {
		return dao.save(user);
	}

}
