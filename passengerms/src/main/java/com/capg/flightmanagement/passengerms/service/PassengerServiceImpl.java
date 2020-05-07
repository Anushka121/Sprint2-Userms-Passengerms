package com.capg.flightmanagement.passengerms.service;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import com.capg.flightmanagement.passengerms.dao.IPassengerDao;
import com.capg.flightmanagement.passengerms.entities.Passenger;
import com.capg.flightmanagement.passengerms.exceptions.PassengerNotFoundException;
import com.capg.flightmanagement.passengerms.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PassengerServiceImpl implements IPassengerService{
	private IPassengerDao dao;
	
	
	public IPassengerDao getDao() {
		return dao;
	}

      @Autowired
	public void setDao(IPassengerDao dao) {
		this.dao = dao;
	}

/**
 * @param passenger 
 * validates the passenger and add to database
 * 
 * @return passenger
 */
	@Override
	public Passenger addPassenger(Passenger passenger) {
		BigInteger uin=passenger.getUin();
		Validation.validateUin(uin);
		return dao.save(passenger);
	}
/**
 * @return list of passengers
 */
@Override
public List<Passenger> viewAllPassenger() {
	List<Passenger>passengerList=dao.findAll();
	return passengerList;
}
/**
 * @param uin(unique identification no)
 * validating and finding the passenger with unique identification number
 * @return passenger
 */

@Override
public Passenger findPassengerByUin(BigInteger uin) {
	
	
	 Optional<Passenger> optional=dao.findById(uin);
     if(optional.isPresent()) {
         Passenger passenger=optional.get();
         return passenger;
     }
     throw new PassengerNotFoundException("passenger not found for pnr ="+uin);

 }
/**
 * @param uin
 * deletes the passenger
 * @return true/false
 * 
 */
@Override
public boolean deletePassenger(BigInteger uin)
{
Optional<Passenger> optional=dao.findById(uin);
if(optional.isPresent()) {
	Passenger passenger=optional.get();
	dao.delete(passenger);
	return true;
	
}
throw new PassengerNotFoundException("passenger not exits"+uin);
}
/**
 * @param passenger
 * to modify passengerdetails
 * @return passenger
 */

@Override
public Passenger modifyPassenger(Passenger passenger) {
	BigInteger uin=passenger.getUin();
	Optional<Passenger> optional=dao.findById(uin);
	if(optional.isPresent()) {
		Passenger passenger1=optional.get();
		passenger1=dao.save(passenger);
		return passenger1;
}
	throw new PassengerNotFoundException("No passenger to be modified");


	
}

}

		
	

	
	
	


