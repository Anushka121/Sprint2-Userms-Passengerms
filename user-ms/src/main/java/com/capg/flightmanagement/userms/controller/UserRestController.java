package com.capg.flightmanagement.userms.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.capg.flightmanagement.userms.dto.CreateUserRequestDto;
import com.capg.flightmanagement.userms.dto.UserDetailsDto;
import com.capg.flightmanagement.userms.entities.User;
import com.capg.flightmanagement.userms.exceptions.IncorrectArgumentException;
import com.capg.flightmanagement.userms.exceptions.UserNotFoundException;
import com.capg.flightmanagement.userms.service.IUserService;

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
@RequestMapping("/users")
@Validated
public class UserRestController {
	private static final Logger Log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private IUserService userService;

	/***
	 * 
	 * @return list of all users
	 */
	@GetMapping
	ResponseEntity<List<UserDetailsDto>> viewAllusers() {
		List<User> userList = userService.viewAllUser();
		List<UserDetailsDto> list = convertToDetailsDto(userList);
		ResponseEntity<List<UserDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}

	/***
	 * for adding the details
	 * 
	 * @param reqDto
	 * @return
	 */
	@PostMapping("/add")
	ResponseEntity<UserDetailsDto> addUser(@Valid @RequestBody CreateUserRequestDto reqDto) {
		User user = new User();
		user.setUserType(reqDto.getUserType());
		user.setUserName(reqDto.getUserName());
		user.setUserPassword(reqDto.getUserPassword());
		user.setUserPhone(reqDto.getUserPhone());
		user.setEmail(reqDto.getEmail());
		user = userService.addUser(user);
		UserDetailsDto dto = convertToDetailsDto(user);
		ResponseEntity<UserDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}

	/**
	 * deletes the user details
	 * 
	 * @param userId
	 * @return whether the user data is deleted
	 */
	@DeleteMapping("/delete/{uid}")
	ResponseEntity<Boolean> deleteUser(@PathVariable("uid") BigInteger userId) {
		boolean result = userService.deleteUser(userId);
		ResponseEntity<Boolean> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;

	}

	/**
	 * to get the details by userId
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/get/{uid}")
	ResponseEntity<UserDetailsDto> getById(@PathVariable("uid") BigInteger userId) {
		User user = userService.findUserById(userId);
		UserDetailsDto dto = convertToDetailsDto(user);
		ResponseEntity<UserDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}

	/**
	 * to make any modification
	 * 
	 * @param uid
	 * @param dto
	 * @return
	 */

	@PutMapping("/modify/{uid}")
	ResponseEntity<UserDetailsDto> modify(@PathVariable("uid") BigInteger userId,
			@Valid @RequestBody CreateUserRequestDto dto) {
		User user = userService.findUserById(userId);
		user.setUserId(userId);
		user.setUserType(dto.getUserType());
		user.setUserName(dto.getUserName());
		user.setUserPassword(dto.getUserPassword());
		user.setUserPhone(dto.getUserPhone());
		user.setEmail(dto.getEmail());
        user=userService.modifyUser(user);
		UserDetailsDto userDetails = convertToDetailsDto(user);
		ResponseEntity<UserDetailsDto> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
		return response;

	}

	public List<UserDetailsDto> convertToDetailsDto(Collection<User> userList) {
		List<UserDetailsDto> dtos = new ArrayList<>();
		for (User user : userList) {
			UserDetailsDto userDto = convertToDetailsDto(user);
			dtos.add(userDto);
		}
		return dtos;
	}

	public UserDetailsDto convertToDetailsDto(User user) {
		UserDetailsDto dto = new UserDetailsDto();
		dto.setUserId(user.getUserId());
		dto.setUserType(user.getUserType());
		dto.setUserName(user.getUserName());
		dto.setUserPassword(user.getUserPassword());
		dto.setUserPhone(user.getUserPhone());
		dto.setEmail(user.getEmail());
		return dto;
	}

	/***
	 * handles whether any incorrect details
	 */
	@ExceptionHandler(IncorrectArgumentException.class)
	public ResponseEntity<String> handleInvalidArgument(IncorrectArgumentException ex) {
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
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
		Log.error("user not found exception", ex);
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
