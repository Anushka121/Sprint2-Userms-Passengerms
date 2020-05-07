package com.capg.flightmanagement.passengerms.dto;
import java.math.BigInteger;

import javax.validation.constraints.Size;
/**
 * 
 * for data transfer
 *
 */
public class CreatePassengerRequestDto {
	
	private BigInteger uin;
	private BigInteger pnr;
	
	
	private String passengerName;
	private int passengerAge;
	private double luggage;
	public BigInteger getUin() {
		return uin;
	}
	public void setUin(BigInteger uin) {
		this.uin = uin;
	}
	public BigInteger getPnr() {
		return pnr;
	}
	public void setPnr(BigInteger pnr) {
		this.pnr = pnr;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public double getLuggage() {
		return luggage;
	}
	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}
	
	
	

}
