package com.capg.flightmanagement.userms.dao;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.flightmanagement.userms.entities.User;

public interface IUserDao extends JpaRepository<User,BigInteger>{
	

}
