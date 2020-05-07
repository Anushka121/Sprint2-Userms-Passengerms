package com.capg.flightmanagement.passengerms.controller;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.capg.flightmanagement.passengerms.dto.CreatePassengerRequestDto;
import com.capg.flightmanagement.passengerms.dto.PassengerDetailsDto;
import com.capg.flightmanagement.passengerms.entities.Passenger;
import com.capg.flightmanagement.passengerms.exceptions.InvalidArgumentException;
import com.capg.flightmanagement.passengerms.exceptions.PassengerNotFoundException;
import com.capg.flightmanagement.passengerms.service.IPassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Anushka Soni
 *
 */
@RestController
@RequestMapping("/passengers")
@Validated
public class PassengerRestController {
	 private static final Logger Log = LoggerFactory.getLogger(PassengerRestController.class);

	@Autowired
	private IPassengerService passengerService;

	@Value("${BookingServiceUrl}")
	private String bookingBaseUrl;
	
	
	
	
	/***
	 * 
	 * @return list of all passengers
	 */
	@GetMapping
	ResponseEntity<List<PassengerDetailsDto>> viewAllPassengers() {
		List<Passenger> passengerList = passengerService.viewAllPassenger();
		List<PassengerDetailsDto> list = convertToDetailsDto(passengerList);
		ResponseEntity<List<PassengerDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}
/***
 * for adding the details
 * @param reqDto
 * @return
 */
	@PostMapping("/add")
	ResponseEntity<PassengerDetailsDto> addPassenger(@Valid @RequestBody CreatePassengerRequestDto reqDto) {
		Passenger passenger = new Passenger();
		passenger.setUin(reqDto.getUin());
		passenger.setPnr(reqDto.getPnr());
		passenger.setPassengerName(reqDto.getPassengerName());
		passenger.setPassengerAge(reqDto.getPassengerAge());
		passenger.setLuggage(reqDto.getLuggage());

		passenger = passengerService.addPassenger(passenger);

		PassengerDetailsDto dto = convertToDetailsDto(passenger);
		ResponseEntity<PassengerDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}
/**
 * deletes the passenger details
 * @param uin
 * @return whether the passenger data is deleted
 */
	@DeleteMapping("/delete/{uin}")
	ResponseEntity<Boolean> deletePassenger( @PathVariable("uin") BigInteger uin) {
		boolean result = passengerService.deletePassenger(uin);
		ResponseEntity<Boolean> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;

	}
/**
 * to get the details by uin(unique identification no)
 * @param uin
 * @return
 */
	@GetMapping("/get/{uin}")
	ResponseEntity<PassengerDetailsDto> getById(@PathVariable("uin") BigInteger uin) {
		Passenger passenger = passengerService.findPassengerByUin(uin);
		PassengerDetailsDto dto = convertToDetailsDto(passenger);
		ResponseEntity<PassengerDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}
	/**
	 * to make any modification
	 * @param uin
	 * @param dto
	 * @return
	 */
	
	@PutMapping("/modify/{uin}")
		ResponseEntity<PassengerDetailsDto> modify(@PathVariable("uin") BigInteger uin,@Valid @RequestBody CreatePassengerRequestDto dto)
		{
		Passenger passenger=passengerService.findPassengerByUin(uin);
		passenger.setUin(uin);
		passenger.setPnr(dto.getPnr());
		passenger.setPassengerName(dto.getPassengerName());
		passenger.setPassengerAge(dto.getPassengerAge());
		passenger.setLuggage(dto.getLuggage());
		passenger=passengerService.modifyPassenger(passenger);
		PassengerDetailsDto passengerDetails=convertToDetailsDto(passenger);
		ResponseEntity<PassengerDetailsDto> response=new ResponseEntity<>(passengerDetails,HttpStatus.OK);
		return response;
		
	
	}

	public List<PassengerDetailsDto> convertToDetailsDto(Collection<Passenger> passengerList) {
		List<PassengerDetailsDto> dtos = new ArrayList<>();
		for (Passenger passenger : passengerList) {
			PassengerDetailsDto passengerDto = convertToDetailsDto(passenger);
			dtos.add(passengerDto);
		}
		return dtos;
	}

	public PassengerDetailsDto convertToDetailsDto(Passenger passenger) {
		PassengerDetailsDto dto = new PassengerDetailsDto();
		dto.setUin(passenger.getUin());
		dto.setPnr(passenger.getPnr());
		dto.setPassengerName(passenger.getPassengerName());
		dto.setPassengerAge(passenger.getPassengerAge());
		dto.setLuggage(passenger.getLuggage());
		return dto;
	}
	
	/***
	 * handles whether any incorrect details
	 */
	@ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String>handleInvalidArgument(InvalidArgumentException ex){
        Log.error("Invalid uin exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }
	
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<String>handlePassengerNotFound(PassengerNotFoundException ex){
        Log.error("passenger not found exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }
	
	
	/***
	 * 
	 * checks if any constraint is not violated
	 */
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
	        Log.error("constraint violation", ex);
	        String msg = "failed to match contraint";
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	        return response;
	    }
	 
	 /**
	  * blanket exception handler
	  * @param ex
	  * @return
	  */
	 @ExceptionHandler(Throwable.class)
	    public ResponseEntity<String> handleAll(Throwable ex) {
	        Log.error("Something went wrong", ex);
	        String msg = "something went wrong";
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	        return response;
	    }
}
