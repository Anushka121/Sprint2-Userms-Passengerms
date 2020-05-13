package com.capg.flightmanagement.passengerms.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import com.capg.flightmanagement.passengerms.dto.PassengerDto;
import com.capg.flightmanagement.passengerms.entities.Passenger;
import com.capg.flightmanagement.passengerms.exceptions.InvalidArgumentException;
import com.capg.flightmanagement.passengerms.exceptions.PassengerNotFoundException;
import com.capg.flightmanagement.passengerms.service.IPassengerService;
import com.capg.flightmanagement.passengerms.util.PassengerValidation;

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

@RestController
@RequestMapping("/passengers")
@Validated
public class PassengerRestController {
	private static final Logger Log = LoggerFactory.getLogger(PassengerRestController.class);

	@Autowired
	private IPassengerService passengerService;

	@Value("${bookingServiceUrl}")
	private String bookingBaseUrl;

	/***
	 * 
	 * @return list of all passengers
	 */
	@GetMapping
	ResponseEntity<List<PassengerDto>> viewAllPassengers() {
		List<Passenger> passengerList = passengerService.viewAllPassenger();
		List<PassengerDto> list = convertToDto(passengerList);
		ResponseEntity<List<PassengerDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}

	

	public List<PassengerDto> convertToDto(List<Passenger> passengerList) {
		List<PassengerDto> dto = new ArrayList<>();
		for (Passenger passenger : passengerList) {
			PassengerDto dtos = convertToPassengerDto(passenger);
			dto.add(dtos);
		}
		return dto;
	}

	public PassengerDto convertToPassengerDto(Passenger passenger) {
		PassengerDto passengerDto = new PassengerDto();
		passengerDto.setUin(passenger.getUin());
		passengerDto.setPnr(passenger.getPnr());
		passengerDto.setPassengerName(passenger.getPassengerName());
		passengerDto.setPassengerAge(passenger.getPassengerAge());
		passengerDto.setLuggage(passenger.getLuggage());
		passengerDto.setGender(passenger.getGender());
		return passengerDto;

	}

	/***
	 * for adding the details
	 * 
	 * @param passengerDto
	 * @return whether added or not
	 */
	@PostMapping("/add")
	ResponseEntity<List<PassengerDto>> storePassengersDetails(@Valid @RequestBody PassengerDto[] passengerDto) {
		List<PassengerDto> passengerList=new ArrayList<>();
		for (PassengerDto passengerDetails : passengerDto) {
			Passenger passenger=convertFromDtoToPassenger(passengerDetails);
			passenger=passengerService.addPassenger(passenger);
			PassengerDto dto=convertToPassengerDto(passenger);
			passengerList.add(dto);
		}
		ResponseEntity<List<PassengerDto>> response = new ResponseEntity<>(passengerList, HttpStatus.OK);
		return response;
	}
	public Passenger convertFromDtoToPassenger(PassengerDto dto)
	{
		Passenger passenger = new Passenger();
		PassengerValidation.validateUin(dto.getUin());
		passenger.setUin(dto.getUin());
		passenger.setPnr(dto.getPnr());
		passenger.setPassengerName(dto.getPassengerName());
		passenger.setPassengerAge(dto.getPassengerAge());
		passenger.setLuggage(dto.getLuggage());
		passenger.setGender(dto.getGender());
		return passenger;
	}
	/**
	 * deletes the passenger details
	 * 
	 * @param passengerDto 
	 * @return whether the passenger data is deleted
	 */
	

	@DeleteMapping("/delete")
	ResponseEntity<Boolean> removePassengers( @RequestBody PassengerDto[] passengerDto) {

		  boolean message=false;
			for (PassengerDto passengerDetails : passengerDto) {
		
			Passenger passenger=convertFromDtoToPassenger(passengerDetails);
			BigInteger passengerUin=passenger.getUin();
			
			message=passengerService.deletePassenger(passengerUin);
		}
		ResponseEntity<Boolean> response = new ResponseEntity<>(message, HttpStatus.OK);
		return response;
	}

	/**
	 * to get the details by uin(unique identification no)
	 * 
	 * @param uin
	 * @return
	 */
	@GetMapping("/get/{uin}")
	ResponseEntity<PassengerDto> getById(@PathVariable("uin") BigInteger uin) {
		Passenger passenger = passengerService.findPassengerByUin(uin);
		PassengerDto dto = convertToPassengerDto(passenger);
		ResponseEntity<PassengerDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}

	/***
	 * handles whether any incorrect details
	 */
	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<String> handleInvalidArgument(InvalidArgumentException ex) {
		Log.error("Invalid uin exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<String> handlePassengerNotFound(PassengerNotFoundException ex) {
		Log.error("passenger not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
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
	 * 
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
