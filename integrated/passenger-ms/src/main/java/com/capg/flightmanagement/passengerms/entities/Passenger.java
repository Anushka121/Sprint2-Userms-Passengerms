package com.capg.flightmanagement.passengerms.entities;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "passengers")
public class Passenger {

	@Id
	private BigInteger uin;
	private BigInteger pnr;
	private String passengerName;
	private int passengerAge;
	private double luggage;
	private String gender;

	/**
	 * Default Non Parametrized Constructor
	 */
	public Passenger() {

	}

	/**
	 * @return pnr
	 */
	public BigInteger getPnr() {
		return pnr;
	}

	/**
	 * Method to set the pnr no
	 * 
	 * @param pnr
	 */
	public void setPnr(BigInteger pnr) {
		this.pnr = pnr;
	}

	/**
	 * 
	 * @return passenger name
	 */

	public String getPassengerName() {
		return passengerName;
	}

	/**
	 * set the passenger name
	 * 
	 * @param passengerName
	 */
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	/**
	 * 
	 * @return Passenger Age
	 */
	public int getPassengerAge() {
		return passengerAge;
	}

	/**
	 * Set the passenger name
	 * 
	 * @param passengerAge
	 */
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	/**
	 * 
	 * @return unique identification no
	 */
	public BigInteger getUin() {
		return uin;
	}

	/**
	 * Set the unique identification no
	 * 
	 * @param uin
	 */

	public void setUin(BigInteger uin) {
		this.uin = uin;
	}

	/**
	 * 
	 * @return luggage details
	 */
	public double getLuggage() {
		return luggage;
	}

	/**
	 * set the luggage
	 * 
	 * @param luggage
	 */
	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * override hashcode
	 * 
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		return uin.hashCode();
	}

	/**
	 * check equality of passenger object
	 * 
	 * @param object
	 * @return
	 */

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || !(object instanceof Passenger))
			return false;
		Passenger passenger = (Passenger) object;
		return this.uin.equals(passenger.getUin());
	}

	

	/**
	 * @return details of passenger
	 */
	@Override
	public String toString() {
		return "Passenger [uin=" + uin + ", pnr=" + pnr + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", luggage=" + luggage + ", gender=" + gender + "]";
	}

	
}
