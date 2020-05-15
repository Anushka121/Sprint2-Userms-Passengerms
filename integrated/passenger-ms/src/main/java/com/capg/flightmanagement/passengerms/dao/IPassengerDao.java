package com.capg.flightmanagement.passengerms.dao;

import java.math.BigInteger;

import com.capg.flightmanagement.passengerms.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerDao extends JpaRepository<Passenger,BigInteger>{
	

}
