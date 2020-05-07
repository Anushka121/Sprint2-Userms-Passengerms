package com.capg.flightmanagement.passengerms.util;

import java.math.BigInteger;

import com.capg.flightmanagement.passengerms.exceptions.InvalidArgumentException;

public class Validation {

	public static void validateUin(BigInteger uin)
	{
		String passengerUin=uin.toString();
		if(passengerUin.length()!=12)
		{
			throw new InvalidArgumentException("uin value should be of 12 digit");
		}
	}
}
