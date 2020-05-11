package com.capg.flightmanagement.userms.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.capg.flightmanagement.userms.entities.User;
import com.capg.flightmanagement.userms.exceptions.UserNotFoundException;

import javax.persistence.EntityManager;

import java.math.BigInteger;
import java.util.List;

@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(UserServiceImpl.class)
// importing RoomServiceImpl class as @DatajpaTest will only only search for repositories
class UserServiceImplTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private EntityManager entityManager;

    /**
     * case when user data does not exist in database
     */
    @Test
    public void testAddUser_1() {
        
       
        User user = new User("admin", new BigInteger("1"), "happy","xyz", new BigInteger("6789123456"),
				"anshka@gmail.com");
        
        User result = userService.addUser(user);
        List<User> fetched = entityManager.createQuery("from User").getResultList();
        Assertions.assertEquals(1, fetched.size());// verifying one got inserted
        User expected = fetched.get(0);
        Assertions.assertEquals(expected, result);// verifying fetch and stored are equal
        Assertions.assertEquals(result.getUserType(), expected.getUserType());
        Assertions.assertEquals(result.getUserId(),expected.getUserId());
        Assertions.assertEquals(result.getUserName(),expected.getUserName());
        Assertions.assertEquals(result.getUserPassword(),expected.getUserPassword());
        Assertions.assertEquals(result.getUserPhone(),expected.getUserPhone());
        Assertions.assertEquals(result.getEmail(),expected.getEmail());
        
        
    }


    /**
     * case when user exists in database before
     */
    @Test
    public void testAddUser_2() {
    	User user = new User("admin", new BigInteger("1"), "happy","xyz", new BigInteger("6789123456"),
				"anshka@gmail.com");
        
        //
        //added user already
        //
      user= entityManager.merge(user);

      String userType="admin";
      BigInteger userId=new BigInteger("2");
      String userName="Humpty";
      String userPassword="xyzzz";
      BigInteger userPhone=new BigInteger("2347896781");
      String email="xyz@gmail.com";
      
      user.setUserType(userType);
      user.setUserId(userId);
      user.setUserName(userName);
      user.setUserPassword(userPassword);
      user.setUserPhone(userPhone);
      user.setEmail(email);
       
       User result = userService.addUser(user);
        List<User> fetched = entityManager.createQuery("from User").getResultList();
        Assertions.assertEquals(1, fetched.size());
        User expected = fetched.get(0);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(userType, expected.getUserType());
        Assertions.assertEquals(userId,expected.getUserId());
        Assertions.assertEquals(userName,expected.getUserName());
        Assertions.assertEquals(userPassword,expected.getUserPassword());
        Assertions.assertEquals(userPhone,expected.getUserPhone());
        Assertions.assertEquals(email,expected.getEmail());
        
    }

    /**
     * case when User doesn't exist , verifying UserNotFoundException is thrown
     */
    @Test
    public void testFindByUserId_1() {
        Executable executable = () -> userService.findUserById(new BigInteger("2"));
       

        Assertions.assertThrows(UserNotFoundException.class, executable);

    }

    /**
     * case when user exist , verifying user is correctly fetched
     * precondition: user exists in database
     */
    @Test
    public void testFindByUserId_2() {
    	User user = new User("admin", new BigInteger("1"), "happy","xyz", new BigInteger("6789123456"),
				"anshka@gmail.com");
        
        //
        //added the room
        //
        user= entityManager.merge(user);
        BigInteger userId = user.getUserId();
        User result=userService.findUserById(userId);
        //
        //verifying details are correctly fetched
        //
        Assertions.assertEquals(user, result);
        Assertions.assertEquals(result.getUserType(), user.getUserType());
        Assertions.assertEquals(result.getUserId(),user.getUserId());
        Assertions.assertEquals(result.getUserName(),user.getUserName());
        Assertions.assertEquals(result.getUserPassword(),user.getUserPassword());
        Assertions.assertEquals(result.getUserPhone(),user.getUserPhone());
        Assertions.assertEquals(result.getEmail(),user.getEmail());
        
    }


}
