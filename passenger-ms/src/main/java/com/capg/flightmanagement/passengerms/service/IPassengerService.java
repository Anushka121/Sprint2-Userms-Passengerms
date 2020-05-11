package com.capg.flightmanagement.passengerms.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.flightmanagement.passengerms.entities.Passenger;

public interface IPassengerService {
	public Passenger addPassenger(Passenger passenger);
	public List<Passenger> viewAllPassenger();
	public Passenger findPassengerByUin(BigInteger uin);
	public boolean deletePassenger(BigInteger uin);
	public Passenger modifyPassenger(Passenger passenger);
	
}