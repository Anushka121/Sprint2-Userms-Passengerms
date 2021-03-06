package com.capg.flightmanagement.userms.util;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capg.flightmanagement.userms.entities.User;
import com.capg.flightmanagement.userms.exceptions.IncorrectArgumentException;

public class UserValidation {
    /**
     *
     * @param phoneNo
     * checks the validation condition, if not met the condition throw exception
     */

    public static void validatePhone(BigInteger phoneNo) {
        String phone = phoneNo.toString();
        
        if ((phone.charAt(0) == 0) ||(phone.length()!= 10)) {
        	
            throw new IncorrectArgumentException("Phone number is Invalid");
        }
    }
       
    /**
     *
     * @param email
     * checks the validation condition, if not met the condition throw exception
     */
    public static void validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!(matcher.matches())) {
            throw new IncorrectArgumentException("Email is invalid");
        }
    }
    /**
     *
     * @param user
     * checks the user attributes, if null throw exception
     */
    public static void validateUser(User user)
    {
        if(user.getUserType()==null||user.getUserType().isEmpty())
        {
            throw new IncorrectArgumentException("UserType  not provided");
        }
        
        if(user.getUserName()==null||user.getUserName().isEmpty())
        {
            throw new IncorrectArgumentException("UserName not provided");
        }
        if(user.getUserPassword()==null)
        {
            throw new IncorrectArgumentException("Password not mentioned");
        }
        if(user.getUserPhone()==null)
        {
            throw new IncorrectArgumentException("PhoneNo not provided");
        }
        if(user.getEmail()==null||user.getEmail().isEmpty())
        {
            throw new IncorrectArgumentException("Email  not provided");
        }

    }
}